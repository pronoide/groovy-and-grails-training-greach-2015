package ex4.groovy.flowcontrol.functions

class MyClass{
	def value
	def plus(MyClass other){
		new MyClass(value:
		this.value+other.value)
	}
}

def o1 = new MyClass(value:5)
def o2 = new MyClass(value:7)
def sum = o1 + o2

assert sum.value == 12, "Value must be the sum of values"
