package qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LOMG_TIME_OUT = 20;
	
	
	//Login Page Constants
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String  LOGIN_PAGE_FRACTION_URL= "route=account/login";
	
	//Accounts Page Constants
	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	public static final List<String> ACCOUNT_PAGE_HEADERS =Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	public static final int ACCOUNT_PAGE_HEADER_COUNT = 4;
	
	
	public static final CharSequence USER_REG_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "register";
	
}
