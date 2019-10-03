package vn.group14.util;

import java.lang.reflect.Field;

public class ByteHandle {
	
	public static int sizeofType(Class<?> dataType)
	{
	    if (dataType == null) throw new NullPointerException();
	    if (dataType == int.class    || dataType == Integer.class)   return 4;
	    if (dataType == short.class  || dataType == Short.class)     return 2;
	    if (dataType == byte.class   || dataType == Byte.class)      return 1;
	    if (dataType == char.class   || dataType == Character.class) return 2;
	    if (dataType == long.class   || dataType == Long.class)      return 8;
	    if (dataType == float.class  || dataType == Float.class)     return 4;
	    if (dataType == double.class || dataType == Double.class)    return 8;
	    return 0; // 32-bit memory pointer... 
	              // (I'm not sure how this works on a 64-bit OS)
	}
	public static final String typeData = "byte";
	public static <T> int sizeofObject(Class<T> t) {
		Field[] fields = t.getDeclaredFields();
		int sum = 0;
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			sum +=sizeofType(fields[i].getType());
		}
		return sum;
	}
	
}
