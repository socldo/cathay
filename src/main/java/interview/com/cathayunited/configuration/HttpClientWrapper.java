package interview.com.cathayunited.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class HttpClientWrapper {

	private static HttpURLConnection connection;

	public HttpClientWrapper() throws IOException {
		URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
		if (HttpClientWrapper.connection == null) {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

		} else
			connection.connect();
	}

	public String getResponse() throws IOException {
		InputStream inputStream = connection.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		StringBuilder response = new StringBuilder();
		while (scanner.hasNext()) {
			response.append(scanner.nextLine());
		}
		scanner.close();
		return response.toString();
	}

	public HttpURLConnection getConnection() {
		return connection;
	}

	public void closeConnection() {
		connection.disconnect();
	}
}