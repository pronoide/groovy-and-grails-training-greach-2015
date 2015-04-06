package ex4.groovy.flowcontrol.closures

def list = ['a', 'b', 'c', 'd']
def set = [1, 2, 3, 4] as Set
def map = ["uno":"one", "dos":"two"]


def firstItem = set.find { it % 2 == 0 }
def result = set.sum { it % 2 == 0 ? it : 0 }

println "Firt: $firstItem, Sum: $result"

// Creates new collections
def newCollection = list.collect { it.toUpperCase() }
def allItems = set.findAll { it % 2 == 0 }

newCollection.each { println it }
allItems.eachWithIndex { item, index ->
	println "$item located at $index"
}

map.each {
	println it.key + " : " + it.value
}
map.each { k, v ->
	println "$k : $v"
}
