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

class HomeController {
    def refreshService

    def index = {
        refreshService.contributors()
        [coreContributions: Contribution.findAllByRepository('grails/grails-core',[order:'asc', sort: 'rank']), docContributions: Contribution.findAllByRepository('grails/grails-doc',[order:'asc', sort: 'rank'])]
    }

    def commits = {
        refreshService.commits(params.repository)
        def allCommits = Commit.findAllByRepository(params.repository,[sort: "commitDate", order: "desc"])
        [repository: params.repository,commits: allCommits]
    }

    def contributor = {
        def contributor=Contributor.findByLogin(params.login)
        def contribution
        contributor.contributions.each{
            if(it.compareTo(contribution)>0){
                contribution=it
            }
        }
        [contributor:contributor, bestContribution:contribution]
    }

    def repository = {
        if(!params.name){
            [:]
        }
        else{
            refreshService.contributors(params.name)
            [contributions:Contribution.findAllByRepository(params.name,[order:'asc', sort: 'rank'])]
        }
    }
}
