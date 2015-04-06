package ex7.groovy.dsl

def turn(where){
	print "turning $where"
	this
}
def then(where){
	println ", now $where"
	this
}

def left = "left"
def right = "right"

turn(left).then(right)
turn left then right

////////////////////////////////////

def paint(what){ this }
def with(a,b){ this }
def and(what){ this }
def wall = null
def red = "red"
def green = "green"
def yellow = "yellow"

paint(wall).with(red, green).and(yellow)
paint wall with red, green and yellow

////////////////////////////////////

def check(that){this}
def is(what){this}
def groovy = "Groovy"
def good = "Good"

check(that: groovy).is(good)
check that: groovy is good

////////////////////////////////////

def select(what){this}
def unique(){this}
def from(where){this}
def all = "ALL"
def names = "A,B,C"

select(all).unique().from(names)
select all unique() from names

////////////////////////////////////

def take(howMany){this}
def getCookies(){" cookies"}

take(3).getCookies()
take 3 cookies