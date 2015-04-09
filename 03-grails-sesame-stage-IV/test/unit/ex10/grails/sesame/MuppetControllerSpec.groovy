package ex10.grails.sesame

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.junit.Before
import org.junit.Test
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MuppetController)
@Mock([MuppetService, Muppet])
class MuppetControllerUnitTest {

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
