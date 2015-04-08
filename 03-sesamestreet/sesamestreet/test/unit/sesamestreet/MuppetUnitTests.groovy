package sesamestreet

@TestFor(Muppet)
class MuppetUnitTests {

	void testSomeCharacters() {

		def ernie = new Muppet(name: 'ernie', color: 'orange', gender: Muppet.MALE)
		assert ernie.validate()

		def bert = new Muppet(name:'bert', gender: Muppet.MALE)
		assert !bert.validate()
		assert bert.errors.errorCount == 1

	}
}