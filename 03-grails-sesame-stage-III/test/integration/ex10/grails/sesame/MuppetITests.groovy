package ex10.grails.sesame

import grails.test.mixin.TestFor
import org.junit.Test
import grails.validation.ValidationException

class MuppetITests {

	@Test
	void testSaveMuppets() {
		def ernie = new Muppet(name: 'ernie', color: 'orange', gender: Muppet.MALE)
		
		def initialSize = Muppet.count
		ernie.save()
		assert initialSize + 1 == Muppet.count

		def bert = new Muppet(name: 'bert', gender: Muppet.MALE)
		bert.save()
		assert 1 == bert.errors.errorCount
		
		//other way to do so throwing an exception
		try {
			bert.save(failOnError:true)
			fail 'cannot get here'
		} catch (ValidationException e) {
			assert 1 == bert.errors.errorCount
		}
	}
}
