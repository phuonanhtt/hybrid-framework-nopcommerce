package phuongHoaXuan;

// Cùng nằm trong phường Hòa Xuân
public class BinhHouse {
	// 2- CÙng package
	public static void main(String[] args) {
		AZ_Cafe az = new AZ_Cafe();
		System.out.println(az.espresso);
		System.out.println(az.capuccino);
		System.out.println(az.hxCafe);
//		System.out.println(az.azCafe);
		
		az.shipEspresso();
		az.shipCapuccino();
		az.shipHxCafe();
	}
}
