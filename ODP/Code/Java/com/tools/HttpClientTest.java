package com.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientTest {
	public HttpClientTest() {

	}

	public String getResponse() {
		String out = "";
		CloseableHttpClient http = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet("http://mardou.dyndns.org/hp.nsf/index.xsp");
			CloseableHttpResponse response = http.execute(request);

			try {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line;
				while ((line = rd.readLine()) != null) {
					out += line;
				}

			} catch (Exception e) {

				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}
