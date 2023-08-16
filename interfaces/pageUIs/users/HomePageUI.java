package pageUIs.users;

public class HomePageUI {
	// Chứa những locator/ element của page này
	// static: có thể truy cập trực tiếp từ phạm vi class - chứ ko càn phạm vi từ instance
	// final: ko cho phép ghi đè biến
	// Tên biến: static + final (mặc định là hằng số - Constant)
	// Hằng số trong java: bắt buộc viết hoa và phân tách bởi gạch nối _
	// Biến: Variable/ Property
	public static final String REGISTER_LINK = "css=a.ico-register";
	public static final String LOGIN_LINK =	"css=a.ico-login";
	public static final String LOGOUT_LINK = "css=a.ico-logout";
	public static final String MY_ACCOUNT_LINK = "css=a.ico-account";
}
