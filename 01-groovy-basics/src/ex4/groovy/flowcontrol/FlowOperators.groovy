package ex4.groovy.flowcontrol

def safe = null
println safe?.toUpperCase()


def value = safe ?: "default"
println value


def result = value.contains("a") ?
		"yes" : "no"
println result
