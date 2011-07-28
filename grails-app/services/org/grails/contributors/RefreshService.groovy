/*
 * Copyright 2011 Bobby Warner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.grails.contributors

import java.text.SimpleDateFormat;

import org.codehaus.groovy.grails.commons.*

/**
 * Service to call GitHub API
 *
 * @author Bobby Warner
 */
class RefreshService {
    static transactional = false
    static scope = "singleton"
    def mongo

    def commits() {
        commits("grails/grails-core")
        commits("grails/grails-doc")
    }

    def commits(String repository){
        log.debug "ENTER RefreshService#commits"

        if (!Commit.findByRepository(repository)) {
            def baseUrl = ConfigurationHolder.config.github.url
            def commitsCall = "commits/list/${repository}/master"
            def commitsUrl = new URL(baseUrl + commitsCall)

            def commitsResult = new XmlParser().parseText(commitsUrl.getText())
			def df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            commitsResult.commit.each {commit ->
                // (felipe)
                // IMPORTANT: Commit#commitId is constrained as unique. I'm not checking whether
                // a given commit id is already present on DB. If a commit has already been
                // persisted, no exception will be raised, and life goes on.
                // I've done this to improve performance: instead of a DB operation to check
                // whether it exists, and another one to persist it, we always try to persist,
                // meaning one less DB operation. We should be careful and see if it's really
                // being more "performatic".

                def login = commit.committer.login?.text()
                if (!login || login.size()==0) {
                    //try to get by name
                    def test = Contributor.findByName(commit.committer.name?.text())
                    if (test) login = test.login
                    //try to guess login, based on graeme rocher case!
                    else {
                        login=commit.committer.name?.text().toLowerCase().replace(" ", "")
                        test=Contributor.findByLogin(login)
                        if (test) {
                            //for next time store the name
                            test.name=commit.committer.name?.text()
                            test.save()
                        }
                    }
                }
				def date=commit."committed-date".text()
				date=date[0..21]+date[23..24]
                Contributor.findByLogin(login).addToCommits(
                        new Commit(commitId: commit.id.text(),
                        url: commit.url.text(),
                        message: commit.message.text(),
                        repository:repository,
						commitDate:df.parse(date)
                        )).save()
            }
        }

    }

    def contributors() {
        contributors("grails/grails-core")
        contributors("grails/grails-doc")
        contributors("grails-plugins/grails-resources")
        contributors("grails-plugins/grails-spring-security-core")
        contributors("grails-plugins/grails-tomcat-plugin")
        contributors("grails-plugins/grails-hibernate-plugin")
        contributors("grails-plugins/grails-database-migration")
    }

    def contributors(String repository) {
        log.info("Contributors refresh method called")
        
        if (!Contribution.findByRepository(repository)) {
            Refresh lastRefresh = Refresh.findByRepository(repository)
            def today = new Date()
            
            if (lastRefresh?.dateCreated < today - 1) {
                log.info("Contributors refresh being executed")
                
                // Can't do this on Cloud Foundry, don't have permission
                //mongo.getDB("grails-contributors").dropDatabase()
                //mongo.getDB("grails-contributors")
                
                //TODO this is hard refresh
				//note that Contributors are never refreshed
				//(so any change in blog etc... are never taken in account)
				Contribution.list().each { c ->
                    c.delete()
                }
                Commit.list().each { c ->
                    c.delete()
                }
            
                def refresh = new Refresh()
                def start = System.currentTimeMillis()

                def config = ConfigurationHolder.config
                String baseUrl = config.github.url
                String apiCall = "repos/show/${repository}/contributors"

                def url = new URL(baseUrl + apiCall)
                def result = new XmlParser().parseText(url.getText())
                int rank = 1
                result.contributor.each {
                    def contributor = Contributor.findByLogin(it.login.text())
                    if (!contributor) {
                        def name = it.name.text()
                        if (name?.length() == 0) name = it.login.text()
                        contributor = new Contributor(blog: it.blog.text(), company: it.company.text(), location:it.location.text(), name: name, login: it.login.text(), gravatarId: it."gravatar-id".text()) 
                    }
                    contributor.addToContributions(new Contribution(rank: rank++, total: Integer.parseInt(it.contributions.text()), repository: repository))
                    contributor.save()
                }

                def stop = System.currentTimeMillis()
                refresh.executionTime = stop - start
                refresh.save()
            }
        }
    }
}
