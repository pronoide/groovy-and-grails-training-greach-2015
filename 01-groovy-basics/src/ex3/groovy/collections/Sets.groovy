package ex3.groovy.collections

def set = [1,[2,3],5] as Set
println set.class

assert set.disjoint([0,5]) == false
assert set.intersect([0,5]) == [5]

assert [1,1,1].unique() == [1]
