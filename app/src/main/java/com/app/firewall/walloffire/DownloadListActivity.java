package com.app.firewall.walloffire;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DownloadListActivity extends ActionBarActivity {

    String result = null;
    InputStream is = null;

    //url to get json array
    private static String url = "http://animatrix.comlu.com/firewall/Sync2Phone.php";

    //json node names
    private static final String TAG_USER = "data";
    private static final String TAG_ID = "Id";

    JSONArray user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.e("DEBUNK", "Error before callin JSONParse " + e.toString());
        new JSONParsse().execute();
        //RetrieveFeed RF = new RetrieveFeed();
        //RF.execute();

/*
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://animatrix.comlu.com/firewall/Sync2Phone.php");
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();Log.e("DEBUNK", "response is" + response.toString() );

            Log.e("DEBUNK", "connection succes");
        }catch (Exception e){
            Log.e("DEBUNK", "Error in http connection" + e.toString() );
        }

        try{
            if(is == null){
                Log.e("DEBUNK", "checking to sww inpuststreamreader" + is.toString()  );
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8 );
            StringBuilder sb = new StringBuilder();
            Log.e("DEBUNK", "Whats in inputstreamreader" + is.toString()  );
            String line = null;
            while( (line = reader.readLine() ) != null){
                sb.append(line + "\n");
            }
            is.close();
            Log.e("DEBUNK", "Error showing line " + line );
            result = sb.toString();
        }  catch (Exception e) {
            Log.e("DEBUNK", "Error converting result buffer " + e.toString() );
        }*/
/*
        try{
            JSONArray jArray = new JSONArray(result);
            TableLayout tv = (TableLayout) findViewById(R.id.table);
            tv.removeAllViewsInLayout();
            int flag = 1;
            for(int i = -1; i < jArray.length() - 1; i++){
                TableRow tr = new TableRow(MainActivity.this);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT
                ));
                if(flag == 1) {
                    TextView b6 = new TextView(MainActivity.this);
                            b6.setText("Id");
                            b6.setTextColor(Color.BLUE);
                            b6.setTextSize(15);
                            tr.addView(b6);
                    TextView b19 = new TextView(MainActivity.this);
                            b19.setPadding(10, 0, 0, 0);
                            b19.setText("Name");
                            b19.setTextColor(Color.BLUE);
                            b19.setTextSize(15);
                            tr.addView(b19);
                    TextView b29 = new TextView(MainActivity.this);
                            b29.setPadding(10, 0, 0, 0);
                            b29.setText("Status");
                            b29.setTextColor(Color.BLUE);
                            b29.setTextSize(15);
                            tr.addView(b29);
                            tv.addView(tr);
                    final View vline = new View(MainActivity.this);
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                    vline.setBackgroundColor(Color.BLUE);
                    tv.addView(vline);
                    flag = 0;
                }else {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Log.i("DEBUNK", "id: " + json_data.getInt("Id") + ", Username: " +
                    json_data.getString("username") + ", No: " +
                    json_data.getString("comment"));
                    TextView b = new TextView(MainActivity.this);
                    String stime = String.valueOf(json_data.getInt("Id"));
                    b.setText(stime);
                    b.setTextColor(Color.RED);
                    b.setTextSize(15);
                    tr.addView(b);
                    TextView b1 = new TextView(MainActivity.this);
                    b1.setPadding(10, 0, 0, 0);
                    b1.setTextSize(15);
                    String stime1 = json_data.getString("username");
                    b1.setText(stime1);
                    b1.setTextColor(Color.BLACK);
                    tr.addView(b1);
                    TextView b2 = new TextView(MainActivity.this);
                    b2.setPadding(10, 0, 0, 0);
                    String stime2 = json_data.getString("comment");
                    b2.setText(stime2);
                    b2.setTextColor(Color.BLACK);
                    b2.setTextSize(15);
                    tr.addView(b2);
                    tv.addView(tr);
                    final View vline1 = new View(MainActivity.this);
                    vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                    vline1.setBackgroundColor(Color.WHITE);
                    tv.addView(vline1);
                }
            }
        }catch(JSONException e){

        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class JSONParsse extends AsyncTask<String, String, JSONObject>{
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            //pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

            //getting json from url
            JSONObject json = jParser.getJSONFromUrl(url);
            Log.e("DEBUNK", "arrived json  " + json.toString() );
            return json;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }



        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try{
                //getting json array
                user = json.getJSONArray(TAG_USER);
                JSONObject c = user.getJSONObject(1);
                //storing them in variable
                String one = c.getString(TAG_ID);
                String two = c.getString("Name");
                String three = c.getString("Status");

                Log.e("DEBUNK", "print :  " + one + "two= " + two + three );
                
            }catch (JSONException e){
                Log.e("DEBUNK", "exception in getting json array " + e.toString() );
            }
            super.onPostExecute(json);
        }
    }
}

