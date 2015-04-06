package ex7.groovy.metaprog

def a = new Article()
def meta = Article.metaClass

if(meta.hasProperty(a, "price")){
	println a.price
}
if(meta.respondsTo(a, "withVAT")){
	println a.withVAT()
}

Integer.metaClass.rnd = {
	def r = new Random()
	r.nextInt(delegate.intValue())
}
println 5.rnd()
