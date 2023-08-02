package phuongHoaXuan;

// Quán cafe nhà bà A
public class AZ_Cafe {
	// Property/ Field/ Variable
	public String espresso = "Espresso Coffee";
	
	// Function/ Method
	public void shipEspresso() {
		System.out.println("Ship: " + espresso);
	}
	
	protected String capuccino = "Capuccino Coffee";
	
	protected void shipCapuccino() {
		System.out.println("Ship: " + capuccino);
	}
	
	String hxCafe = "Hoa Xuan Coffee";
	
	void shipHxCafe() {
		System.out.println("Ship: " + hxCafe);
	}
	
	private String azCafe = "AZ Coffee";
	// 1 - Sử dụng trong chính cái class này
	public static void main(String[] args) {
		AZ_Cafe az = new AZ_Cafe();
		System.out.println(az.espresso);
		System.out.println(az.capuccino);
		System.out.println(az.hxCafe);
		az.shipHxCafe();
	}
}
