package ex7.groovy.metaprog

class Article3 extends Article2 {
	def invokeMethod(
	String id, Object args) {
		println "Method ${id}() invoked with params ${args}"
	}
	}
	
	def a3 = new Article3()
	a3.myNewMethod('abc', 123, true)
	