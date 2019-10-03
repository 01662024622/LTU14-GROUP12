package vn.group14.object;

import vn.group14.enums.Tags;
import vn.group14.util.ByteHandle;

public class TVL{
	private short tag;
	private short length;
	private String value;
	public TVL(short tag,String value) {
		this.tag= (short) ((short)tag%Tags.values().length);
		this.length=(short)value.getBytes().length;
		this.value=value;
	}
	public TVL() {}
	public short getTag() {
		return tag;
	}
	public void setTag(short tag) {
		this.tag = (short) (tag%Tags.values().length);
	}

	public short getLength() {
		return length;
	}
	public void setLength(short length) {
		this.length=length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
		this.length=(short)value.length();
	}
	
	public int lengthByte() {
		// TODO Auto-generated method stub
		int sum = ByteHandle.sizeofType(short.class)+ ByteHandle.sizeofType(int.class)+ this.length;
		return sum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Tags.values()[this.tag]+"---" + this.value;
	}
}
