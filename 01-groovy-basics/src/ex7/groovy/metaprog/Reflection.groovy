package ex7.groovy.metaprog

def ej = new String()

def line = { println it }
def head = { println "---$it---"}

head "interfaces"
ej.class.interfaces.each line
head "constructors"
ej.class.constructors.each line
head "methods"
ej.class.methods.each line
head "properties"
ej.properties.each line
