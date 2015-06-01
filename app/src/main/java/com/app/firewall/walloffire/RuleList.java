package com.app.firewall.walloffire;

/**
 * Created by Tyrell on 5/24/2015.
 */
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RuleList extends ListFragment {


    String result = null;
    InputStream is = null;

    //url to get json array
    private static String url = "http://animatrix.comlu.com/firewall/Sync2Phone.php";

    //json node names
    private static final String TAG_USER = "data";
    private static final String TAG_ID = "Id";

    JSONArray user = null;
    // Array of strings storing country names
    String[] ruleNu = new String[] {
            "1",
            "2",
            "3",
            "4",
            "5"
    };

    String[] target = new String[] {
            "accept",
            "accept",
            "reject",
            "drop",
            "reject"
    };

    String[] protocol = new String[] {
            "tcp",
            "tcp",
            "udp",
            "icmp",
            "tcp"
    };

    String[] interfaces = new String[] {
            "eth0",
            "",
            "",
            "eth0",
            ""
    };

    String[] port = new String[] {
            "23",
            "53",
            "321",
            "4324",
            "23"
    };

    String[] ipaddress = new String[] {
            "192.168.1.23",
            "107.32.178.41",
            "80.4.87.1",
            "110.71.242.122",
            "192.168.1.1"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<5;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("ruleNu",  ruleNu[i]);
            hm.put("target",  target[i]);
            hm.put("protocol", protocol[i]);
            hm.put("interface", interfaces[i]);
            hm.put("port", port[i]);
            hm.put("ipaddress", ipaddress[i]);

            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "ruleNu","target","protocol","interface","port","ipaddress" };

        // Ids of views in listview_layout
        int[] to = { R.id.txt_rule,R.id.txt_target,R.id.txt_protocol,R.id.txt_interface, R.id.txt_port, R.id.txt_ip};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.list_cell, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}