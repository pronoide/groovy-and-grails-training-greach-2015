package sesamestreet

import grails.test.mixin.*
import org.junit.*

@TestFor(MuppetDiscoveryController)
@Mock([MuppetDiscoveryService, Muppet])
class MuppetDiscoveryControllerUnitTests {

	@Before
	void prepareCharacters(){
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE).save()
	}
	
	@Test
    void testFoundPerfectMuppet() {
		request.method = 'POST'
		def params = [color: 'orange', hasFur: false ]
		controller.params.putAll(params)
	
		controller.search()
		
		assert '/muppetDiscovery/found' == view
		assert flash.msg.startsWith('Congrats')
		assert 'Ernie' == model.muppet.name
    }
	
	@Test
	void testNotFoundPerfectMuppet() {
		request.method = 'POST'
		def params = [color: 'yellow', hasFur: false ]
		controller.params.putAll(params)
		
		controller.search()
		
		assert '/muppetDiscovery/notfound' == view
		assert flash.msg.startsWith('Sorry')
		assert 1 == model.muppets.size()
	}
}