package ex1.groovy.core.features;

public class HelloJava {
	private String name;

	public String sayHello() {
		return "Hello " + name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
