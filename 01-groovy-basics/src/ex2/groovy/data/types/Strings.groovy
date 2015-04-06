package ex2.groovy.data.types

def value = 7.5

println "Value double-quoted is: $value"
println 'Value single-quoted is: $value'

println( ('7' as char).class )

/// MULTILINE

def name = "Groovy"
def multiline1 = """ \
Hello $name
I'm using double quotes
"""
def multiline2 = ''' \
Hello $name
I'm using simple quotes
'''

println multiline1
println multiline2
