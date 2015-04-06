package ex1.groovy.core.features

class HelloWorld{
	def name
	def sayHello(){
		"Hello ${name}"
	}
}

def hw = new HelloWorld()
hw.name = "Groovy"
println hw.sayHello()

