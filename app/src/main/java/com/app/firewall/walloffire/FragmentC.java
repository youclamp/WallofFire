package com.app.firewall.walloffire;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link FragmentC} subclass.
 */
public class FragmentC extends Fragment implements AdapterView.OnItemClickListener {


    TextView txt1;
    ListView list;
    CustomAdapter adapter;

    String result = null;
    InputStream is = null;

    //public arraylist to store data in async and use in adapter
    ArrayList<ListModel> ruleList = new ArrayList<ListModel>();
    ListModel lm = new ListModel();

    //url to get json array
    private static String url = "http://animatrix.comlu.com/firewall/mySQLtoJson.php";

    //json node names
    private static final String TAG_USER = "data";
    private static final String TAG_ID = "Id";

    JSONArray user = null;

    public FragmentC CustomListView = null;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    ArrayList<ListModel> searchResult = GetSearchResult();
    public ArrayList<ListModel> searchResult2 = GetSearchResult();
    public List<HashMap<String, String>> jsonlist = new ArrayList<>();

    public FragmentC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView txt1 = (TextView) container.findViewById(R.id.frg_C_text);

        View view = inflater.inflate(R.layout.fragment_c, container, false);
        new JSONParse().execute();
        //ListView listview = (ListView) view.findViewById(R.id.list);
        //return inflater.inflate(R.layout.fragment_c, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        TextView txt1 = (TextView) view.findViewById(R.id.frg_C_text);
        //txt1.setText("display this text");
        ListView listview = (ListView) view.findViewById(R.id.list);
       /* String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };*/

        //int[] res = new int[] {R.id.txt_rule, R.id.txt_target, R.id.txt_chain,
          //      R.id.txt_interface, R.id.txt_ip, R.id.txt_port, R.id.txt_protocol, R.id.txt_target2};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_cell, R.id.txt_port, values);
        //listview.setAdapter(new CustomAdapter(getActivity(), searchResult));
        listview.setAdapter(new CustomAdapter(getActivity(), ruleList));
        Log.d("wall", "-->" + ruleList.toString() );
        //CustomAdapter adapter2 = new CustomAdapter(getActivity(),  CustomListViewValuesArr, Resources.getSystem());
        //ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.id.list, values);
        //listview.setAdapter(adapter2);
        super.onViewCreated(view, savedInstanceState);
    }

    public ArrayList<ListModel>  GetSearchResult(){

        ArrayList<ListModel> result = new ArrayList<ListModel>();
        ListModel sr = new ListModel();
        sr.setRule_no("1");
        sr.setChain("filter");
        sr.setInterfaces("eth0");
        sr.setIp("192.168.1.23");
        sr.setPort("4563");
        sr.setProtocol("tcp");
        sr.setTarget("ACCEPT");
        sr.setTarget2("INPUT");
        result.add(sr);

        return result;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity() );
            pDialog.setMessage("Getting data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

            //getting json from url
            JSONObject json = jParser.getJSONFromUrl(url);
            Log.e("DEBUNK", "arrived json  " + json.toString());
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
                ruleList.clear();
                user = json.getJSONArray(TAG_USER);
                //JSONObject c = user.getJSONObject(1);
                //storing them in variable
                for(int i = 0; i <= user.length()-1; i++) {
                    JSONObject c = user.getJSONObject(i);
                    Log.d("wall", "length is " + user.length());
                    String one = c.getString("rulenum");
                    String two = c.getString("target");
                    String three = c.getString("protocol");
                    String four = c.getString("interface");
                    String five = c.getString("port");
                    String six = c.getString("ipaddress");

                    Log.e("wall", "print :  " + one + "two= " + two + three + "four" + four);

                    lm = new ListModel();
                    lm.setRule_no(one);
                    lm.setTarget(two);
                    lm.setProtocol(three);
                    lm.setInterfaces(four);
                    lm.setPort(five);
                    lm.setIp(six);
                    ruleList.add(lm);
                }
                pDialog.dismiss();



            }catch (JSONException e){
                Log.e("wall", "exception in getting json array " + e.toString() );
            }


            super.onPostExecute(json);
        }




    }

    public interface AsyncResponse{


    }
}
