
public class LoginInfo {
	private static String username = "";
	private static String userType = "";
	public static String userInfo(){
		return username;
	}
	public static void setInfo (String u){
		username = u;
	}
	
	public static void setType (String  u){
		userType = u;
	}
	
	public static String userType(){
		return userType;
	}
	
}
