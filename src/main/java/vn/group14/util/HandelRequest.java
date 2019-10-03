package vn.group14.util;

import java.util.List;

import vn.group14.enums.Tags;
import vn.group14.object.Letters;
import vn.group14.object.TVL;

public class HandelRequest {
	/**
	 * 2.1 validate Phone number
	 * @return boolean
	 */
	public static boolean validatePhone(Letters letter) {
		boolean check =  false;
		List<TVL> tvls=  letter.getTvl();
		short phone = (short)Tags.valueOf("PhoneNumber").ordinal();
		for (int i = 0; i < tvls.size(); i++) {
			if (tvls.get(i).getTag() == phone) {
				System.out.println("phone");
				return CheckData.checkPhone(tvls.get(i).getValue());
			}
		}
		return check;
	}
	public static boolean validateKey(Letters letter) {
		boolean check =  false;
		List<TVL> tvls=  letter.getTvl();
		for (int i = 0; i < tvls.size(); i++) {
			if (tvls.get(i).getTag() == (short)(Tags.valueOf("Key").ordinal())) {
				return CheckData.checkKey(tvls.get(i).getValue());
			}
		}
		return check;
	}
}
