package vn.group14.object;

public class User {
	private String PhoneNumber;
	private String UserName;
	public User(String phone , String name){
		this.PhoneNumber = phone;
		this.UserName = name;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
}
