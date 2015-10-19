package org.people.utils;

import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

public class JSONUtil {

	public static <T> void writeJSonObject(T object, OutputStream jsonOut)
			throws Exception {
		writeJSonObject(object, getOutputStreamWriter(jsonOut));
	}

	public static <T> void writeJSonObject(T object, Writer jsonOut)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(jsonOut, object);
	}

	public static OutputStreamWriter getOutputStreamWriter(OutputStream jsonOut) {
		return new OutputStreamWriter(jsonOut);
	}

	public static <T> T readJSonObject(String jsonFilePath, Class<T> clazz)
			throws Exception {
		File jsonFile = getResourceFile(jsonFilePath);
		FileReader jsonReader = new FileReader(jsonFile);
		ObjectMapper mapper = new ObjectMapper();
		T object = (T) mapper.readValue(jsonReader, clazz);

		return object;
	}

	public static File getResourceFile(final String classpathResourcePath)
			throws Exception {
		return new ClassPathResource(classpathResourcePath).getFile();

	}

	public static String toJSonString(Object object) throws Exception {
		StringWriter sw = new StringWriter();
		JSONUtil.writeJSonObject(object, sw);

		return sw.toString();
	}
}
