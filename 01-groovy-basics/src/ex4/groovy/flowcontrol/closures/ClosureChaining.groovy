package ex4.groovy.flowcontrol.closures

show = { println "Result is "+it }

square = { it**2 }

// Common chaining
def n = 10
def s = square n
show s

// Closure chaining
apply = { action ->
	to= { value ->
		of= { m ->
			action(value(m))
		}
	}
}

apply(show).to(square).of(10)
