package ex7.groovy.expandos

def e = new Expando()
e.id = 5
e.name = ""
e.edit = { a, b ->
	e.id = a
	e.name = b
	e // equivalent to return this
}
e.show = {
	println e.id + e.name
}

e.edit(1, "Groovy").show()
