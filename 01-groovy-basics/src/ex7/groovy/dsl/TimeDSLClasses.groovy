package ex7.groovy.dsl

class Mins {
	def mins
	def plus(val) {
		new Mins(mins:mins +
		val.mins.toInt())
	}
	Integer toInt() {
		mins
	}
	String toString() {
		"$mins mn"
	}
	def getTo() {
		this
	}
	def getSecs() {
		new Secs(secs:mins*60)
	}
}
class Secs {
	def secs
	def plus(val) {
		new Secs(secs:secs +
		val.secs.toInt())
	}
	Integer toInt() {
		secs
	}
	String toString() {
		"$secs sc"
	}
	def getTo() {
		this
	}
	def getMins() {
		new Mins(mins:secs/60)
	}
}
