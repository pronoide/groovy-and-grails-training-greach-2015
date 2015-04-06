package ex5.groovy.oop
import groovy.transform.PackageScope

class Language{
	@PackageScope def id
	def name

	Language(id, name){
		this.id = id
		this.name = name
	}
	def getAt(i){
		if(i==0) return id
		if(i==1) return name
	}
	def print(){
		println "$id: $name"
	}
}

def lng = [1, "Groovy"] as Language
def closure = lng.&print
println lng.@id
println lng[1]

def languages = [
	[2, "Grails"] as Language,
	[3, "Java"] as Language,
	lng
]
languages*.print()
