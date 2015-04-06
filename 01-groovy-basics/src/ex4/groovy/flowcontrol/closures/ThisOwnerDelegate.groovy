package ex4.groovy.flowcontrol.closures

class Person {
	String toString() {
		"I'm a Person"
	}

	def closure1 = {
		def closure2 = {
			println this.toString()
			println owner.toString()
			println delegate.toString()
		}
		closure2.delegate = "Delegate"
		closure2()
	}
}
new Person().closure1()
