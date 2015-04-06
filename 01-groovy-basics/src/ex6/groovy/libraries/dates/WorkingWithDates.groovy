package ex6.groovy.libraries.dates;

def today = new Date()
println String.format(
		'%tY/%<tm/%<td', today)

def date = Date.parse("yyyy/MM/dd HH:mm:ss", "2013/05/01 11:12:13")

assert date.before(today)
assert today.after(date)
println date.compareTo(today)

println today - 1
println today + 7
