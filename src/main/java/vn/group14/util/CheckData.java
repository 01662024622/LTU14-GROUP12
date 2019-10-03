package vn.group14.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.group14.define.DefineData;

public class CheckData {
	public static boolean checkPhone(String numberPhone) {
		String regex = "^098([2-9]{1})([0-9]{6})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(numberPhone);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	public static boolean checkKey(String key) {
		if (key.toLowerCase().matches("(.*)"+DefineData.key+"(.*)")) {
			return true;
		}
		return false;
	}
}
