package ex4.groovy.flowcontrol.closures

def closure = { x, y, z ->
	println "$x $y $z"
}

def groovyRules = closure.
		curry("Groovy", "Rules")

groovyRules "man!"
