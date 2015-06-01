package com.app.firewall.walloffire;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tyrell on 5/4/2015.
 */
public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    //declare variables
    private Activity activity;
    private ArrayList data;
    private static ArrayList<ListModel> searchArrayList;
    private static LayoutInflater inflator = null;
    public Resources res;
    ListModel tempValues = null;
    int i = 0;


    //CustomAdapter constructor
    public CustomAdapter(Context context, ArrayList<ListModel> result){
        //take passed values
        searchArrayList = result;
        inflator = LayoutInflater.from(context);
        //activity = a;
        //data = d;
        //res = resLocal;
        //layout inflator to call external xml layout
        //inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
       return searchArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        Log.v("DEBUNK", "====Row Button Clicked====");
    }

    //create a holder class to contain inflated xml file elements
    public static class ViewHolder{
        public TextView text, text2, text4, text6;
        public TextView text1, text3, text5, text7;
        public TextView textWide;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if(convertView == null){
            //inflate tabitem.xml file for each row
            vi = inflator.inflate(R.layout.list_cell, null);

            //view holder object to contain tabitem.xml file elements

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.txt_rule);
            holder.text1 = (TextView) vi.findViewById(R.id.txt_target);
            holder.text2 = (TextView) vi.findViewById(R.id.txt_chain);
            holder.text3 = (TextView) vi.findViewById(R.id.txt_interface);
            holder.text4 = (TextView) vi.findViewById(R.id.txt_ip);
            holder.text5 = (TextView) vi.findViewById(R.id.txt_port);
            holder.text6 = (TextView) vi.findViewById(R.id.txt_protocol);
            holder.text7 = (TextView) vi.findViewById(R.id.txt_target2);

            //set holder layout inflator
            vi.setTag(holder);
        }else
            holder = (ViewHolder) vi.getTag();
        if(searchArrayList.size() <= 0){
            holder.textWide.setText("No data");
        }else{
            //tempValues = null;
            //tempValues = (ListModel) searchArrayList.get(position);

            //set model values in holder elements
            //holder.textWide.setText(tempValues.getRule_no() );


            //set item click listener for layoutinflator for each row
            vi.setOnClickListener(new OnItemClickListener(position));
        }

        holder.text.setText(searchArrayList.get(position).getRule_no());
        holder.text1.setText(searchArrayList.get(position).getTarget());
        holder.text2.setText(searchArrayList.get(position).getProtocol());
        holder.text3.setText(searchArrayList.get(position).getInterfaces());
        holder.text4.setText(searchArrayList.get(position).getPort());
        holder.text5.setText(searchArrayList.get(position).getIp());
        holder.text6.setText(searchArrayList.get(position).getChain());
        holder.text7.setText(searchArrayList.get(position).getTarget2());



        return vi;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }


        @Override
        public void onClick(View v) {
            //MainActivity frg = (MainActivity)activity;
            //FragmentC frg = FragmentC
            //call onItemClick method inside .... class
            //frg.onItemClick(mPosition);
        }
    }
}
