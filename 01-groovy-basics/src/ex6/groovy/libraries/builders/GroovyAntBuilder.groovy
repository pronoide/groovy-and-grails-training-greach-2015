package ex6.groovy.libraries.builders

def ant = new AntBuilder()
def basedir = "C:/project"
def src = basedir + "/src"
def build = basedir + "/classes"
def cp = ant.path(id:"lib") {
	pathelement(path:"${basedir}/")
	fileset(dir:"${basedir}/lib")
}
def clean() {
	ant.delete(dir:"${build}")
}
def prepare() {
	ant.mkdir(dir:"${build}")
}

def compile() {
	prepare()
	ant.javac(
			srcdir:"${src}", destdir:"${build}",
			classpath:"$cp",deprecation:"on"
			)
	ant.copy(todir:"${build}") {
		fileset (dir:"${src}") { exclude(name:"**/*.java") }
	}
}
