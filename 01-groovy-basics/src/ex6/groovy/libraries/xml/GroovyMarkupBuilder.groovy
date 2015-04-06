package ex6.groovy.libraries.xml

import groovy.xml.MarkupBuilder

def writer = new StringWriter()
def xml = new MarkupBuilder(writer)
xml.setDoubleQuotes true
xml.languages{
	lang(id:"1"){
		name "Groovy"
		version 2.2_1
	}
	lang(id:"2"){
		name "Grails"
		version 2.4_2
	}
}
println writer.toString()

new File("file.xml").write(writer.toString())