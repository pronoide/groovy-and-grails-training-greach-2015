package ex6.groovy.libraries.builders;

def b = DOMBuilder.newInstance()


def xml = b.people(kind:'folks'){
	person(x:123, name:'James') {
		project(name:'groovy')
		project(name:'grails')
	}
	person(x:234, name:'Gosling') { project(name:'drools') }
}

new NodePrinter().print(xml)
