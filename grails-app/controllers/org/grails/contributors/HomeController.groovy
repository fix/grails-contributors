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

    def repository = {
        // need to decide if we are going to implement dynamically adding repos, or just have them listed in Config.groovy like they are now long-term
        
        //if (!params.name || !params.name.contains("grails")) {
        //    [:]
        //} else {
        //    refreshService.contributors(params.name)
        //    [contributions:Contribution.findAllByRepository(params.name,[order:'asc', sort: 'rank'])]
        //}
        
        //refreshService.contributors(params.name)
        
        [contributions:Contribution.findAllByRepository(params.name,[order:'asc', sort: 'rank'])]
    }
	
	def repositories = {
		// almost no aggregation in mongodb, doing something like 
		// "select sum(total), contributor from contributions groupby repository"
		// manually
		def contributions=[:]
		Contribution.list().each{
			if(contributions[it.repository]){
				contributions[it.repository].total+=it.total
			}
			else{
				contributions[it.repository]=[total:it.total]
			}
			if(it.rank==1){
				contributions[it.repository].contributor=it.contributor
				contributions[it.repository].max=it.total
			}
		}
		[contributions:contributions]
	}
}
