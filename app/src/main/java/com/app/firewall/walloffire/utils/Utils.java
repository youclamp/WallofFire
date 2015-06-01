package com.app.firewall.walloffire.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.util.List;

public class Utils {

	static Context context;
	public static final String TAG = "Utils";
	public static final String PREF_FILE_NAME = "pref";

	HttpPost httppost;
	HttpGet httpget;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;

	ProgressDialog pb;

	public Utils(Context context) {
		Utils.context = context;
	}

	public void removePreferences(String key) {
		SharedPreferences myPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.remove(key);
		prefsEditor.commit();
	}

	public Boolean containsKey(String key) {
		SharedPreferences myPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
		return myPrefs.contains(key);
	}

	public void savePreferences(String key, String value) {
		SharedPreferences myPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.putString(key, value);
		prefsEditor.commit();
	}

	public String getFromPreferences(String key) {
		SharedPreferences myPrefs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
		return (myPrefs.getString(key, "0"));
	}

	public static void showToast(final String txt, final int length) {
		((Activity) context).runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(context, txt, length).show();
			}
		});
	}

	public void showPB(final String message) {
        Log.d("BUGS", "stage 2" + message);
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				pb = new ProgressDialog(context);
				pb.setCancelable(false);
				pb.setMessage(message);
				pb.show();
			}
		});
	}

	public void hidePB() {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (pb != null && pb.isShowing())
					pb.dismiss();
			}
		});
	}

	public void updatePBMessage(final String newMessage) {
		((Activity) context).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (pb != null && pb.isShowing())
					pb.setMessage(newMessage);
			}
		});
	}

	public void setPageTitle(String title) {
		((Activity) context).setTitle(title);
	}

	public String passDataToServer(String url, List<NameValuePair> nameValuePairs) throws ClientProtocolException, IOException {
        Log.d("BUGS", "stage 3" + url + " ");
		HttpParams httpParameters = new BasicHttpParams();
		// Set the timeout in milliseconds until a connection is
		// established.
		// The default value is zero, that means the timeout is not used.
		int timeoutConnection = 30000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
		// Set the default socket timeout (SO_TIMEOUT)
		// in milliseconds which is the timeout for waiting for data.
		int timeoutSocket = 30000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		httpclient = new DefaultHttpClient(httpParameters);
		httppost = new HttpPost(url);
		if (nameValuePairs != null && nameValuePairs.size() > 0)
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		return httpclient.execute(httppost, responseHandler);
	}

	public String passGETDataToServer(String url) throws ClientProtocolException, IOException {
		httpclient = new DefaultHttpClient();
		httpget = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		return httpclient.execute(httpget, responseHandler);
	}

	
}
