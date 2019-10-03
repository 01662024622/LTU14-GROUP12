package vn.group14.handle.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonParse<T> {

	private static ObjectMapper mapper = new ObjectMapper();
	public static <T> T partToObject(String string,Class<T> t) {
		T object = null;
			try {
				object = mapper.readValue(string, t);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return object;
	}
}
