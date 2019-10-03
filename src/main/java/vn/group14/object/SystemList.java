package vn.group14.object;

import java.util.ArrayList;
import java.util.List;

public class SystemList {
	private static List<User> users = new ArrayList<User>();
	public static void add(User user) {
		users.add(user);
	}
	public static TVL getUser(String phone) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getPhoneNumber()==phone) {
				return new TVL((short) 2 , users.get(i).getUserName());
			}
		}
		return null;
	}
}
