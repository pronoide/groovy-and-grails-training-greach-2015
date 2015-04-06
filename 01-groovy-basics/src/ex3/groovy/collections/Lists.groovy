package ex3.groovy.collections

def list = [1]
list += 'one'
list << "two"
list[5] = [1,3,5]

println list
println list[-1]
println list.flatten()

def list2 = [1,2,3,4]
assert [1,2,3] == list2 - [4]

assert list2.disjoint([0,5]) == true
assert list2.intersect([1,3,5]) == [1,3]
assert list2.collect { it + 2 } == [3,4,5,6]
assert [1,2,3,4,2,4].unique() == list2

assert list2.min() == 1
assert list2.max() == 4

assert list2.sum() == 10
assert list2.sum { it % 2 == 0 ? it : 0 } == 6

assert [4,2,3,1].sort() == list2

println "asserts satisfied"