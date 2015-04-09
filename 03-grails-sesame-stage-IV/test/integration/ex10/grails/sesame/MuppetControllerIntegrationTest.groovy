package ex10.grails.sesame

import org.junit.Test
import org.junit.Before

class MuppetControllerIntegrationTest {

    @Before
	void prepareCharacters(){
		new Muppet(name: 'Ernie', color: "orange", hasFur: false, gender: Muppet.MALE)
			.save()
	}
	
	@Test
    void testFoundPerfectMuppet() {
		def controller = new MuppetController()
		
		controller.request.method = 'POST'
		def params = [color: 'orange', hasFur: false ]
		controller.params.putAll(params)
	
		controller.search()
		
		assert '/muppetDiscovery/found' == controller.modelAndView.viewName
		assert controller.flash.msg.startsWith('Congrats')
		assert 'Ernie' == controller.modelAndView.model.muppet.name
    }
	
	@Test
	void testNotFoundPerfectMuppet() {
		def controller = new MuppetController()
		
		controller.request.method = 'POST'
		def params = [color: 'yellow', hasFur: false ]
		controller.params.putAll(params)
		
		controller.search()
		
		assert '/muppetDiscovery/notfound' == controller.modelAndView.viewName
		assert controller.flash.msg.startsWith('Sorry')
		assert 1 == controller.modelAndView.model.muppets.size()
	}

}
