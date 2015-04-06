package ex6.groovy.libraries.files

def dir = new File(".")
dir.eachFile { println it }
dir.eachDir { println it }
dir.eachDirRecurse { println it }

def file = new File('data.dat')
println file.text
file.eachLine { println "$it" }

file.createNewFile()
file.write "content overwitten"
file << "appended content"
file.delete()
