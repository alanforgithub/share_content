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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setContentCharset(params, HTTP.UTF_8); // 设置编码格式
		// /* 连接超时 */
		// HttpConnectionParams.setConnectionTimeout(params, 10000);
		// /* 请求超时 */
		// HttpConnectionParams.setSoTimeout(params, 10000);
		mHttpClient = new DefaultHttpClient(params);

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
				HttpPost post = new HttpPost(
						"http://www.nonghule.com/app/login.php?type=onlinedo");
				try {
					List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
					list.add(new BasicNameValuePair("useracc", "alan_nonghule"));
					list.add(new BasicNameValuePair("userpw", md5("alan123456")));
					post.setEntity(new UrlEncodedFormEntity(list,HTTP.UTF_8));

					HttpResponse response = mHttpClient.execute(post);
					String value = EntityUtils.toString(response.getEntity());

					char[] chars = value.toCharArray();
					for (int i = 0; i < chars.length; i++) {
						Log.i("android", i + " = " + chars[i]);
					}

					Log.i("android", "httpClient post方式 = " + value);

					Log.i("android", "httpClient post方式 =" + value + ",size = "
							+ value.length());

					try {
						JSONObject jsonObj = new JSONObject(value);
						Log.i("android", "数据解析成功");
					} catch (JSONException e) {
						e.printStackTrace();
						Log.i("android", "数据解析错误");
					}

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

	private String md5(String in) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5")
					.digest(in.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * HttpGet
	 */
	private void httpClientGet() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpGet get = new HttpGet(
						"http://www.nonghule.com/app/login1.php?type=onlinedo&useracc=admin&userpw=eeea66479e7b5401f23befbb494bfcde");
				try {
					HttpResponse response = mHttpClient.execute(get);
					String value = EntityUtils.toString(response.getEntity());

					//String value1 = new String( value.toString().getBytes( "utf-8" ), "gbk");
					
					Log.i("android", "httpClient get方式 =" + value.trim() + ",size = "
							+ value.length());

					try {
						JSONObject jsonObj = new JSONObject(value);
						Log.i("android", "数据解析成功");
					} catch (JSONException e) {
						e.printStackTrace();
						Log.i("android", "数据解析错误");
					}

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
