package ex7.groovy.dsl

Integer.metaClass.getSecs = {
	new Secs(secs:delegate)
}

Integer.metaClass.getMins = {
	new Mins(mins:delegate)
}

Integer.metaClass.toInt = { delegate }

println 2.mins.to.secs
println 180.secs.to.mins

println 3.mins + 2.mins
println 120.secs + 180.secs

println 3.mins + 60.secs
println 120.secs + 5.mins

