package carWithoutFactory;

public class User {
	public static void main(String[] args) {
		// Sáng t7
		Honda hon = new Honda();
		hon.viewCar();
		hon.driverCar();
		
		// Chiều t7
		Toyota toy = new Toyota();
		toy.viewCar();
		toy.driverCar();
		
		// Sáng CN
		Ford ford = new Ford();
		ford.viewCar();
		ford.driverCar();
		
		// Chiều CN
		Huyndai huyn = new Huyndai();
		huyn.viewCar();
		huyn.driverCar();
	}
}
