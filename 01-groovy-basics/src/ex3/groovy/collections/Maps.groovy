package ex3.groovy.collections

def map = [:]
println map.getClass()

map += ["uno":"one", "dos":"two"]

// key access
assert map.uno == map["uno"]
assert map.get("uno") == map."uno"

// remove elements
map.remove("uno")
assert map.size() == 1

// Object key
def key = new Object()
map += [(key) : "value"]
assert map.containsKey(key)
