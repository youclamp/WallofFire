package com.app.firewall.walloffire;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link FragmentA} subclass.
 */
public class FragmentB extends Fragment {

    Spinner Target, Ch_input, Ch_output, Ch_pre_routing, Ch_post_routing;
    Spinner Action, Protocol, Interface;
    EditText Rule_number, Port, Ip_address;
    Button Submit;
    String target, input, output, pre, post, action, protocol, interfacetxt, rule, port, ip;
    //private String URL_NEW_RULE = "http://www.animatrix.comlu.com/firewall/newRules.php";
    private String URL_NEW_RULE = "http://104.131.187.31/newRules.php";
    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Target = (Spinner) view.findViewById(R.id.spin_target);
        Ch_input = (Spinner) view.findViewById(R.id.spin_input);
        Ch_output = (Spinner) view.findViewById(R.id.spin_output);
        Ch_pre_routing = (Spinner) view.findViewById(R.id.spin_pre_routing);
        Ch_post_routing = (Spinner) view.findViewById(R.id.spin_post_routing);

        Action = (Spinner) view.findViewById(R.id.spin_action);
        Protocol = (Spinner) view.findViewById(R.id.spin_protocol);
        Interface = (Spinner) view.findViewById(R.id.spin_interface);

        Rule_number = (EditText) view.findViewById(R.id.edit_rule_number);
        Port = (EditText) view.findViewById(R.id.edit_port);
        Ip_address = (EditText) view.findViewById(R.id.edit_ip_address);

        Submit = (Button) view.findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                target = Target.getSelectedItem().toString();
                input = Ch_input.getSelectedItem().toString();
                output = Ch_output.getSelectedItem().toString();
                pre = Ch_pre_routing.getSelectedItem().toString();
                post = Ch_post_routing.getSelectedItem().toString();
                action = Action.getSelectedItem().toString();
                protocol = Protocol.getSelectedItem().toString();
                interfacetxt = Interface.getSelectedItem().toString();
                port = Port.getText().toString();
                ip = Ip_address.getText().toString();
                rule = Rule_number.getText().toString();

                Log.d("wall", "target->" + target + input + output + pre + post + action + protocol + interfacetxt + port + ip + rule);
                Intent intent = new Intent(getActivity(), FragmentB.class);
                new AddNewRule().execute(target, input, output, pre, post, action, protocol, interfacetxt, rule, port, ip);

            }
        });

        Target.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private class AddNewRule extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected Void doInBackground(String... params) {
            String chain_db = params[1];
            String action_db = params[5];
            String protocol_db = params[6];
            String port_db = params[9];
            String ipaddress_db = params[10];
            //String txt6 = params[10];

            //preparing post params
            List<NameValuePair> listparam = new ArrayList<NameValuePair>();
            listparam.add(new BasicNameValuePair("chain", chain_db));
            listparam.add(new BasicNameValuePair("action", action_db));
            listparam.add(new BasicNameValuePair("protocol", protocol_db));
            listparam.add(new BasicNameValuePair("port", port_db));
            listparam.add(new BasicNameValuePair("ipaddress", ipaddress_db));
            //listparam.add(new BasicNameValuePair("ip_address", txt6));
            //listparam.add(new BasicNameValuePair("chain", txt6));

            ServiceHandler serviceClient = new ServiceHandler();
            String json = serviceClient.makeServiceCall(URL_NEW_RULE, ServiceHandler.POST, listparam);
            Log.d("wall","create new rule request; " + listparam.toString());

            if(json != null){
                try{
                    JSONObject jsonObj = new JSONObject(json);
                    boolean error = jsonObj.getBoolean("error");
                    if(!error){
                        Log.d("wall", "Prediction was added succesfully: " + jsonObj.getString("message"));

                    }else{
                        Log.d("wall", "Add prediction error: " + jsonObj.getString("message"));
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            } else {
                Log.d("wall", "JSON data error!!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
