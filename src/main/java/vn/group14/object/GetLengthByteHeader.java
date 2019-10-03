package vn.group14.object;

import vn.group14.util.ByteHandle;

public class GetLengthByteHeader {
	private static int lengthOfHeader;
	
	public static int lengthByte() {
		if (lengthOfHeader==0) {
			lengthOfHeader= ByteHandle.sizeofObject(Headers.class);
			return lengthOfHeader;
		}else {
			return lengthOfHeader;
		}
	}
}
