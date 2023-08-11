package carFactory;

public class User {
	public static void main(String[] args) {
		Car car = null;
	
		car = getCar("Honda");
		car.vỉewCar();
		car.driveCar();
		car.bookCar("Honda");
		
		car = getCar("Huyndai");
		car.vỉewCar();
		car.driveCar();
		car.bookCar("Huyndai");
		
		car = getCar("Mer");
		car.vỉewCar();
		car.driveCar();
	}
	
	public static Car getCar(String carName) {
		Car car = null;
		if (carName.equals("Honda")) {
			car = new Honda();
		} else if (carName.equals("Huyndai")) {
			car = new Huyndai();
		} else if (carName.equals("Toyota")) {
			car = new Toyota();
		} else if (carName.equals("Ford")) {
			car = new Ford();
		} else {
			throw new RuntimeException("Car name is not valid!");
		}
		return car;
	}
}
