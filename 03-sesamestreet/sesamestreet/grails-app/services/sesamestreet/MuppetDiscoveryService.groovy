package sesamestreet

import java.util.Set;

class MuppetDiscoveryService {

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