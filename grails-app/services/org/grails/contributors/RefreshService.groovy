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

//import groovy.json.JsonSlurper
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

	def commits(){
		commits("grails/grails-core")
		commits("grails/grails-doc")
	}

	def commits(String repository){
		log.debug "ENTER RefreshService#commits"

		if(!Commit.findByRepository(repository)){
			def baseUrl = ConfigurationHolder.config.github.url
			def commitsCall = "commits/list/${repository}/master"
			def commitsUrl = new URL(baseUrl + commitsCall)

			def commitsResult = new XmlParser().parseText(commitsUrl.getText())

			commitsResult.commit.each {commit ->
				// (felipe)
				// IMPORTANT: Commit#commitId is constrained as unique. I'm not checking whether
				// a given commit id is already present on DB. If a commit has already been
				// persisted, no exception will be raised, and life goes on.
				// I've done this to improve performance: instead of a DB operation to check
				// whether it exists, and another one to persist it, we always try to persist,
				// meaning one less DB operation. We should be careful and see if it's really
				// being more "performatic".

				def login=commit.committer.login?.text()
				if (!login || login.size()==0){
					//try to get by name
					def test=Contributor.findByName(commit.committer.name?.text())
					if(test) login = test.login
					//try to guess login, based on graeme rocher case!
					else{
						login=commit.committer.name?.text().toLowerCase().replace(" ", "")
						test=Contributor.findByLogin(login)
						if(test){
							//for next time store the name
							test.name=commit.committer.name?.text()
							test.save()
						}
					}
				}
				Contributor.findByLogin(login).addToCommits(
						new Commit(commitId: commit.id.text(),
						url: commit.url.text(),
						message: commit.message.text(),
						repository:repository
						)).save()
			}
		}

	}

	def contributors() {
		contributors("grails/grails-core")
		contributors("grails/grails-doc")
	}

	def contributors(String repository) {
		log.info("Contributors refresh method called")
		Refresh lastRefresh = Refresh.get(Refresh.count())
		def today = new Date()

		
		if (!Contribution.findByRepository(repository) || lastRefresh?.dateCreated < today - 1) {
			
			if(lastRefresh?.dateCreated < today - 1){
				mongo.getDB("grails-contributors").dropDatabase()
				mongo.getDB("grails-contributors")
			}
			else{
				Contribution.findAllByRepository(repository).each { c ->
					c.delete()
				}
				Commit.findAllByRepository(repository).each { c ->
					c.delete()
				}
			}

			log.info("Contributors refresh being executed")
			def refresh = new Refresh()
			def start = System.currentTimeMillis()

			def config = ConfigurationHolder.config
			String baseUrl = config.github.url
			String coreApiCall = "repos/show/${repository}/contributors"

			def coreUrl = new URL(baseUrl + coreApiCall)
			def coreResult = new XmlParser().parseText(coreUrl.getText())
			int rank=1
			coreResult.contributor.each {
				def contributor=Contributor.findByLogin(it.login.text())
				if(!contributor){
					def name=it.name.text()
					if(name?.length()==0) name=it.login.text()
					contributor= new Contributor(blog: it.blog.text(), company: it.company.text(), location:it.location.text(), name: name, login: it.login.text(), gravatarId:it."gravatar-id".text())
					
				}
				contributor.addToContributions(new Contribution(rank:rank++, total: Integer.parseInt(it.contributions.text()), repository:repository))
				contributor.save()
			}

			def stop = System.currentTimeMillis()
			refresh.executionTime = stop - start
			refresh.save()
		}
	}
}
