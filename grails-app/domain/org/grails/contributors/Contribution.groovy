package org.grails.contributors

class Contribution {
	
	String repository
	Integer rank
	Integer total
	

	static belongsTo= [contributor:Contributor]
    static constraints = {
    }
}
