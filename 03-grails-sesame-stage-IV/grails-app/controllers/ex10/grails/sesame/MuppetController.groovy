package ex10.grails.sesame

class MuppetController {

	def muppetService
	def desiredTraits

	static scope = 'session'

	def index() {
		render view: 'choosemuppettraits'
	}

	def search(){
		desiredTraits = new Muppet(params)

		if(!desiredTraits.validate()) {
			return redirect (action: 'index')
		}

		log.debug "searching perfect match for ${desiredTraits.properties}..."
		flash.msg = "Congrats We've found the perfect muppet for you!"
		def targetView='found'
		def perfectMuppet = muppetService.lookForYourPerfectMuppet desiredTraits
		def relatedMuppets

		if(!perfectMuppet){
			log.debug "searching related muppets for  ${desiredTraits.properties}..."
			flash.msg = "Sorry We haven't found the perfect muppet for you!"
			targetView='notfound'
			relatedMuppets = muppetService.lookForRelatedMuppets desiredTraits
		}

		render view:targetView , model:[muppet:perfectMuppet,muppets:relatedMuppets]
	}
}
