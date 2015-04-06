package ex7.groovy.metaprog

class Article {
	def description
	def price
}

class ArticleExtension{
	static double withVAT(Article a) {
		a.price * 1.18
	}
}
Article a = new Article (price:4.50)
use(ArticleExtension) { println a.withVAT() }

