class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/$login"(controller:"contributor", action:"show")

		"/"(controller:"home")
		"500"(view:'/error')
	}
}
