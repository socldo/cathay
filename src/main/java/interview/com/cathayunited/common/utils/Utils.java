package interview.com.cathayunited.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utils {
	public static String getDateFormatVN(Date date) {
		if (date == null) {
			return "01/01/1970";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
		}
	}

	public static <T> List<T> convertJsonStringToListObject(String jsonString, Class<T[]> objectclass)
			throws Exception {
		jsonString = Strings.isNullOrEmpty(jsonString) ? "[]" : jsonString;
		ObjectMapper mapper = new ObjectMapper();
		List<T> result = Arrays.asList(mapper.readValue(jsonString, objectclass));
		return result;
	}

	public static <T> T convertJsonStringToObject(String jsonString, Class<T> objectclass) throws Exception {
		if (Strings.isNullOrEmpty(jsonString)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		T result = mapper.readValue(jsonString, objectclass);
		return result;
	}

	public static String getDatetimeFormatVN(String dateString) {
		if (dateString == null || dateString.isEmpty()) {
			return "";
		} else {
			try {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
				Date date = inputFormat.parse(dateString);
				SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				return outputFormat.format(date);
			} catch (ParseException e) {
				e.printStackTrace(); // Handle parsing exception as needed
				return ""; // Return empty string or handle error appropriately
			}
		}
	}

	public static String getDatetimeFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}

}
