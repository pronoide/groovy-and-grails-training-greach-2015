package ex10.grails.sesame

import grails.transaction.Transactional

@Transactional
class MuppetService {

	Muppet lookForYourPerfectMuppet(Muppet desiredTraitsExample) {
		log.debug "looking perfect muppet with these traits ${desiredTraitsExample.dump()}"
		def perfectMuppet = Muppet.find(desiredTraitsExample)
	}

	Set<Muppet> lookForRelatedMuppets(Muppet desiredTraitsExample) {
		log.debug "looking related muppets with these traits ${desiredTraitsExample.properties}"
		def relatedMuppets =
				Muppet.findAllByColorLikeOrHasFur(
				desiredTraitsExample.color, desiredTraitsExample.hasFur)

		return relatedMuppets
	}
}
