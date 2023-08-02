package phuongKhueTrung;

import phuongHoaXuan.AZ_Cafe;

// quán cafe của nhà cô B - con bà A
// dùng các thuôc tính và phương thức bởi lớp cha chia sẻ
public class AZ_Cafe_Child extends AZ_Cafe{
	// Khác package nhưng lại là lớp con
	public static void main(String[] args) {
		AZ_Cafe_Child azChild = new AZ_Cafe_Child();
		azChild.buildRoom();
		
		System.out.println(azChild.espresso);
		azChild.shipEspresso();
		
		System.out.println(azChild.capuccino);
		azChild.shipCapuccino();
		
//		System.out.println(az.hxCafe);
//		System.out.println(az.azCafe);
	}
	
	public void buildRoom() {
		System.out.println(espresso);
		shipEspresso();
	}
}
