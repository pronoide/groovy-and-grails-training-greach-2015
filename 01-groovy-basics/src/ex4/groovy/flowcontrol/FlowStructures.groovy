package ex4.groovy.flowcontrol

def value = 7
def list = new ArrayList() + value
def f = new File("file${dValue}.txt")

if(dValue && list)
	f.createNewFile()

def salary = 1500
switch(salary) {
	case 300..<1000:
		println 'nivel 1'
		break
	case 1000..<2000:
		println 'nivel 2'
}

def n = 10
while(n){
	n--
}

for(i in (1..10)) print "$i "
