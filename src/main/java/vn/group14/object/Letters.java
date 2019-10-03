package vn.group14.object;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import vn.group14.util.ByteArraySingleton;
import vn.group14.util.ByteHandle;

public class Letters {
	private Headers header;
	private List<TVL> tvls;

	public Letters(Headers header, List<TVL> tvls) {
		this.header = header;
		this.tvls = tvls;
		this.setLMS();
	}

	public Headers getHeader() {
		return header;
	}

	public void setHeader(Headers header) {
		this.header = header;
	}

	public List<TVL> getTvl() {
		return tvls;
	}

	public void setTvl(List<TVL> tvls) {
		this.tvls = tvls;
	}
	private void setLMS() {
		int sum=0;
		int lengthOfPrimitive = ByteHandle.sizeofObject(TVL.class);
		for (Iterator iterator = tvls.iterator(); iterator.hasNext();) {
			TVL tvl = (TVL) iterator.next();
			sum += tvl.getLength();
			sum += lengthOfPrimitive;
		}

		sum+= GetLengthByteHeader.lengthByte();
		this.header.setLengthOfMessage(sum);
	}
	public byte[] parseByte() {
		Field[] fields = header.getClass().getDeclaredFields();
		try {
//			add header to stream
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				ByteArraySingleton.instances().add(fields[i].getType(), fields[i].get(header));
			}
//			add list tvl to stream
			for (Iterator iterator = tvls.iterator(); iterator.hasNext();) {
				TVL tvl = (TVL) iterator.next();
				fields = tvl.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					if (!fields[i].getName().equals("length")) {
						ByteArraySingleton.instances().add(fields[i].getType(), fields[i].get(tvl));
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ByteArraySingleton.instances().getByterArr();
	}
}
