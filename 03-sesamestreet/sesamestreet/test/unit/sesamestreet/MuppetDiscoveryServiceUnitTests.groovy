package sesamestreet

import grails.test.mixin.*
import org.junit.*

@TestFor(MuppetDiscoveryService)
@Mock(Muppet)
class MuppetDiscoveryServiceUnitTests {


	@Before
	void prepareCharacters(){
		def muppets = [
			new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Bert', color: "yellow", hasFur: false, gender: Muppet.MALE),
			new Muppet(name: 'Elmo', color: "red", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Grover', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Cookie Monster', color: "blue", hasFur: true, gender: Muppet.MALE),
			new Muppet(name: 'Zoe', color:"orange", hasFur: true, gender: Muppet.FEMALE)
		]

		muppets.each{ 
			it.save()
		}
	}

	@Test
	void testLookForPerfectAndRelatedMuppets() {
		def desiredTraits = new Muppet(color: "orange",  hasFur: false)
		
		def perfectMuppet = service.lookForYourPerfectMuppet(desiredTraits)
		assert perfectMuppet != null
		assert 'Ernie' == perfectMuppet.name
		
		def relatedMuppets = service.lookForRelatedMuppets(desiredTraits)
		assert 3 == relatedMuppets.size()
	}
}