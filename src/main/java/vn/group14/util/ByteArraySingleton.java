package vn.group14.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * singleton only create one stream to process the byteStream
 * @author Hi
 *
 */
public class ByteArraySingleton {
	private static ByteArraySingleton instance = new ByteArraySingleton();
	private static ByteArrayOutputStream bytes= new ByteArrayOutputStream();
	private static DataOutputStream data = new DataOutputStream(bytes);
	private ByteArraySingleton() {}
	public synchronized static ByteArraySingleton instances() {
		return instance;
	}
	/**
	 * clear byte array stream
	 */
	public void clear() {
		bytes.reset();
	}
	
	/**
	 * add to stream with type 
	 * @param clazz
	 * @param ob
	 */
	public void add(Class<?> clazz,Object ob) {
		try {
		 if( Boolean.class == clazz || boolean.class == clazz) data.writeBoolean((Boolean) ob);
		    if( Byte.class == clazz|| byte.class == clazz ) data.writeBytes((String) ob);
		    if( Short.class == clazz ||short.class == clazz  ) data.writeShort((Short) ob );
		    if( Integer.class == clazz ||int.class == clazz) data.writeInt((Integer) ob );
		    if( Long.class == clazz ||long.class == clazz ) data.writeLong((Long) ob  );
		    if( Float.class == clazz || float.class == clazz ) data.writeFloat((Float) ob);
		    if( Double.class == clazz ||double.class == clazz) data.writeDouble((Double) ob);
		    if (String.class == clazz) data.writeUTF((String) ob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getByterArr() {
		byte[] bytearr=bytes.toByteArray();
		bytes.reset();
		return bytearr;
	}
}
