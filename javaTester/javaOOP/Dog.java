package javaOOP;

public class Dog extends Animal{
	public static void main(String[] args) {
		Animal dog = new Animal();
		dog.eat();
		dog.run();
	}
	
	public void move() {
		eat();
		run();
	}
}
