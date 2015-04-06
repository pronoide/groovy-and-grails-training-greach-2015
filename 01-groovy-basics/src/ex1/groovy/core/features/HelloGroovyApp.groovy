package ex1.groovy.core.features

class HelloGroovyApp {

	static main(args) {
		def hw = new HelloGroovy()
		hw.name = "Groovy"
		println hw.sayHello()
	}
}
