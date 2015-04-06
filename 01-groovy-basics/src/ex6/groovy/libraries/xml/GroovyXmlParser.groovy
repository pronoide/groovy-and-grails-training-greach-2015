package ex6.groovy.libraries.xml

parser = new XmlParser()
rootP = parser.parse("file.xml")
l = rootP.appendNode("lang", [id:3])
l.appendNode("name", [:], ["Guava"])

slurper = new XmlSlurper()
rootS = slurper.parse("file.xml")
rootS.each { println it }

nodes = rootS.'**'.findAll{ node ->
node.name()=="lang"
}
print nodes*.name*.text()
