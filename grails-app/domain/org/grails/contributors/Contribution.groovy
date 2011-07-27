package org.grails.contributors

class Contribution implements Comparable<Contribution> {
	
	String repository
	Integer rank
	Integer total
	

	static belongsTo= [contributor:Contributor]
    static constraints = {
    }
	
	public int compareTo(Contribution that){
		if(!that) return Integer.MAX_VALUE
		if(this.total!=that.total){
			return this.total-that.total
		}
		else{
			return that.rank-this.rank
		}
	}
}
