package ex5.groovy.oop

trait Flyer{
	def fly() {
		"I'm flying!"
	}
}
class Bird implements Flyer{}
class Plane{}

def b = new Bird()
assert b.fly() == "I'm flying!"

def p = new Plane() as Flyer
assert p.fly() == "I'm flying!"
