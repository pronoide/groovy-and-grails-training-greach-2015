package ex4.groovy.flowcontrol.functions

def function(val, res='level ') {
	switch(val) {
		case 300..<1000:
			res + 1
			break
		case 1000..<2000:
			res + 2
			break
	}
}

println function(350)
println function(val=1350)
println function(300, 2000)