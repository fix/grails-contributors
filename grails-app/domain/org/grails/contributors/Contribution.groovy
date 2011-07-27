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

class Contribution implements Comparable<Contribution> {
    
    String repository
    Integer rank
    Integer total

    static belongsTo = [contributor: Contributor]
    
    static constraints = {}
    
    public int compareTo(Contribution that) {
        if (!that) return Integer.MAX_VALUE
        if (this.total != that.total) {
            return this.total-that.total
        } else {
            return that.rank - this.rank
        }
    }
}
