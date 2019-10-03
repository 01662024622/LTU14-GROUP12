package vn.group14.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ParseObject<T> {

	/**
	 * convert byte[] to Object have only primitive fields
	 * @param <T>
	 * @param bytes
	 * @param t
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T toObject(byte[] bytes, Class<T> t) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		DataInputStream data = new DataInputStream(byteStream);
//		create new class reflection
		T object = createNewGeneric(t);
		Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
//				set access for field with data convert from string to default data of field
				fields[i].setAccessible(true);
				setValue(fields[i], fields[i].getType(), object, data);
			}
			return object;
	        // production code should handle these exceptions more gracefully
	}

	/**
	 * set value for field of object
	 * @param field
	 * @param clazz
	 * @param object
	 * @param data
	 */
	
	public static void setValue(Field field, Class<?> clazz,Object object,DataInputStream data) {
		try {
			if( Boolean.class == clazz ||boolean.class == clazz)field.set(object, data.readBoolean());
		    if( Byte.class == clazz||byte.class == clazz ) field.set(object, data.readByte());
		    if( Short.class == clazz ||short.class == clazz ) field.set(object, data.readShort());
		    if( Integer.class == clazz|| int.class == clazz ) field.set(object, data.readInt());
		    if( Long.class == clazz|| long.class == clazz ) field.set(object, data.readLong());
		    if( Float.class == clazz||float.class == clazz ) field.set(object, data.readFloat());
		    if( Double.class == clazz ||double.class == clazz) field.set(object, data.readDouble());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * Create new generic class
	 * @param <T>
	 * @return T
	 */
	
	@SuppressWarnings("unchecked")
	public static <T> T createNewGeneric(Class<?> t) {
		@SuppressWarnings("rawtypes")
		Constructor[]  newObject = t.getDeclaredConstructors();
		@SuppressWarnings("rawtypes")
		Constructor ctor = null;
		for (int i = 0; i < newObject.length; i++) {
			ctor = newObject[i];
		    if (ctor.getGenericParameterTypes().length == 0)
			break;
		}
	    ctor.setAccessible(true);
		T object = null;
		try {
			object = (T)ctor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    return object;
	}
}
