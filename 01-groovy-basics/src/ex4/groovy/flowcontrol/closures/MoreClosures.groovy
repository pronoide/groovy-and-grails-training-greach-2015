package ex4.groovy.flowcontrol.closures

def noArgsClosure = { println "Hello Groovy!" }
noArgsClosure()

///

def defaultArgClosure = { println "Hello ${it}" }
defaultArgClosure "Groovy"

///

def closureWithArgs = { name -> println "Hello ${name}" }
closureWithArgs "Groovy"

///

def closureOptionalParams = { x, y=2 ->
	println "$x + $y = ${x+y}"
}
closureOptionalParams 6

///

def closureWithClosureParam = { closure ->
	for(int i=0; i<5; i++) {
		closure(i)
	}
}
closureWithClosureParam { println it }
