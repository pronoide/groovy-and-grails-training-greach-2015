package ex4.groovy.flowcontrol.closures

def sayHello = {  title, name ->
	println "Hello ${title} ${name}!"
}

sayHello "Mr", "Groovy"
