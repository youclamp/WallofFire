package com.app.firewall.walloffire.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import com.app.firewall.walloffire.LoginActivity;
import com.app.firewall.walloffire.MainActivity;
import com.app.firewall.walloffire.listeners.Listener;
import com.app.firewall.walloffire.utils.Constants;
import com.app.firewall.walloffire.utils.Logger;
import com.app.firewall.walloffire.utils.Utils;

public class SignInService {

	List<NameValuePair> nameValuePairs;
	private static final String TAG = "SignInService";
	Listener listener;
	Context context;
	Utils utils;

	public SignInService(Context context, Listener listener) {
		this.context = context;
		this.listener = listener;
		utils = new Utils(context);
	}

	public void setParams(String username, String pwd) {
		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("username", username));
		nameValuePairs.add(new BasicNameValuePair("password", pwd));
	}

	public void doSignIn() {

		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";

				try {
					Logger.logInfo(TAG, Constants.SIGN_IN);
					msg = utils.passDataToServer(Constants.SIGN_IN, nameValuePairs);
                    Log.d("BUGS", "stage 4" + nameValuePairs.toString() + " " );
				} catch (Exception ex) {
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Logger.logInfo(TAG, "Login Response From Server : " + msg);
				createMessageToHandler(msg);
                Log.d("BUGS", "stage 5" + msg );
			}

			private void createMessageToHandler(String msg) {
				Message msgObj = handler.obtainMessage();
				Bundle b = new Bundle();
				b.putString(Constants.SERVER_RESPONSE, msg);
				msgObj.setData(b);
				handler.sendMessage(msgObj);
			}

			// Define the Handler that receives messages from the thread and
			// update the progress
			@SuppressLint("HandlerLeak")
			private final Handler handler = new Handler() {

				public void handleMessage(Message msg) {
					String serverResponse = msg.getData().getString(Constants.SERVER_RESPONSE);
                    Log.d("BUGS", "stage 6" + serverResponse );
                    String find = "User Found";
					if ((null != serverResponse) && !serverResponse.equalsIgnoreCase("") && serverResponse.toLowerCase().contains(find.toLowerCase() )) {

						try {
                            //String find = "User Found";
							if(serverResponse.toLowerCase().contains(find.toLowerCase() )) {
                                Log.d("BUGS", "go to next page");
                                listener.onSuccessful("Successfully Logged in...");
                            }else {
                                listener.onSuccessful("Incorrect Information...");
                            }
							//You will receive server response here 
							// Parse the data here and store data using preferences
							// 

						} catch (Exception e) {
							//e.printStackTrace();
							listener.onError(Constants.ERROR_DATA_FROM_SERVER);
						}
					} else {
						listener.onError(Constants.NO_DATE_FROM_SERVER);
					}
				}
			};
		}.execute(null, null, null);
	}
}
