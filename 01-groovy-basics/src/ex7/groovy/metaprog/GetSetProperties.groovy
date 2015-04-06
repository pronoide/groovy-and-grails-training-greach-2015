package ex7.groovy.metaprog

class Article2 extends Article {
	def props = [:]

	def getProperty(String id) {
		props[id]
	}
	void setProperty(
			String id, Object val) {
		props[id] = val
	}
}
Article2 a2 = new Article2()
a2.stock = 5
println a2.stock

