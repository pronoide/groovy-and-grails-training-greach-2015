package ex5.groovy.oop

interface Greeter {
	void greet(String name)
}

class DefaultGreeter {
	void greet(String name) {
		println "Hello"
	}
}

greeter = new DefaultGreeter()
coerced = greeter as Greeter
assert coerced instanceof Greeter