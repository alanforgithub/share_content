package com.http.httpdemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private String uriGet = "http://fanyi.youdao.com/openapi.do?keyfrom=testHttpGet&key=850021564&type=data&doctype=json&version=1.1&q=good";
	private String uriPost = "http://fanyi.youdao.com/openapi.do";
	private HttpClient mHttpClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mHttpClient = new DefaultHttpClient();

		findViewById(R.id.getbutton).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				httpGet();
			}
		});
		findViewById(R.id.postbutton).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				httpPost();
			}
		});

		findViewById(R.id.httpGetbutton).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						httpClientGet();

					}
				});

		findViewById(R.id.httpPostbutton).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						httpClientPost();
					}
				});
	}

	/**
	 * httpPost
	 */
	private void httpClientPost() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpPost post = new HttpPost(uriPost);
				try {
					List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
					list.add(new BasicNameValuePair("keyfrom", "testHttpGet"));
					list.add(new BasicNameValuePair("key", "850021564"));
					list.add(new BasicNameValuePair("type", "data"));
					list.add(new BasicNameValuePair("doctype", "json"));
					list.add(new BasicNameValuePair("version", "1.1"));
					list.add(new BasicNameValuePair("q", "bad"));
					post.setEntity(new UrlEncodedFormEntity(list));

					HttpResponse response = mHttpClient.execute(post);
					String value = EntityUtils.toString(response.getEntity());
					Log.i("android", "httpClient post方式 = " + value);

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

	/**
	 * HttpGet
	 */
	private void httpClientGet() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpGet get = new HttpGet(uriGet);
				try {
					HttpResponse response = mHttpClient.execute(get);
					String value = EntityUtils.toString(response.getEntity());

					Log.i("android", "httpClient get方式 = " + value);

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	
	private void httpGet() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					URL url = new URL(uriGet);
					URLConnection connection = url.openConnection();
					InputStream is = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					StringBuffer buffer = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						buffer.append(line);
					}
					Log.i("android", "get方式 = " + buffer.toString() + "");

					br.close();
					isr.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.i("android", e + "");
				}

			}
		}).start();
	}

	private void httpPost() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					URL url = new URL(uriPost);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					connection.setDoInput(true);
					connection.setDoOutput(true);
					connection.setRequestMethod("POST");

					OutputStreamWriter osw = new OutputStreamWriter(connection
							.getOutputStream(), "utf-8");
					BufferedWriter bw = new BufferedWriter(osw);
					bw.write("keyfrom=testHttpGet&key=850021564&type=data&doctype=json&version=1.1&q=bad");
					bw.flush();

					InputStream is = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					StringBuffer buffer = new StringBuffer();
					String line;
					while ((line = br.readLine()) != null) {
						buffer.append(line);
					}
					Log.i("android", "post方式 = " + buffer.toString() + "");

					br.close();
					isr.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.i("android", e + "");
				}

			}
		}).start();
	}

}
