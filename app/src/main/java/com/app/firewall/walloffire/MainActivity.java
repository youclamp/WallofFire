package com.app.firewall.walloffire;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    ActionBar actionBar;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager() ) );
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.d("DEBUNK", "onPageScrolled at " + "position " + position + " from " + positionOffset + " with number of pixels= " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //Log.d("DEBUNK", "onPageSelected at " + "position " + position);
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE)
                {
                    //Log.d("DEBUNK", "onPageScrollStateChange idle");
                }
                if(state == ViewPager.SCROLL_STATE_DRAGGING)
                {
                    //Log.d("DEBUNK", "onPageScrollStateChange dragging");
                }
                if(state == ViewPager.SCROLL_STATE_SETTLING)
                {
                    //Log.d("DEBUNK", "onPageScrollStateChange settling");
                }

            }
        });//closing tags for viewPager.setOnPageChangeListener(ananymous class with overridden methods implementation)

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("Tab 1");
        tab1.setTabListener(this);

        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("INSERT");
        tab2.setTabListener(this);

        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText("LIST");
        tab3.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

    }//closing tag for onCreate method


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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Log.d("DEBUNK", "onTabSelected at "+" position " + tab.getPosition() + " name" + tab.getText() );
        viewPager.setCurrentItem(tab.getPosition() );
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Log.d("DEBUNK", "onTabUnSelected at "+" position " + tab.getPosition() + " name" + tab.getText() );
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Log.d("DEBUNK", "onTabReSelected at "+" position " + tab.getPosition() + " name" + tab.getText() );
    }
}

class MyAdapter extends FragmentPagerAdapter{

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
       Fragment fragment = null;
        if(arg0 == 0)
        {
            fragment = new FragmentA();
        }
        if(arg0 == 1)
        {
            fragment = new FragmentB();
        }
        if(arg0 == 2)
        {
            fragment = new FragmentC();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
