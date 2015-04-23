package com.halo.casino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainTemplate extends FragmentActivity{
	private String[] mPlanetTitles = {"Click"};
	private DrawerLayout mDrawerLayout;
    private ListView mLeftDrawerList, mRightDrawerList;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main_template);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mLeftDrawerList = (ListView) findViewById(R.id.left_drawer);
        mRightDrawerList = (ListView) findViewById(R.id.right_drawer);
        
        // Set the adapter for the list view
        mLeftDrawerList.setAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, mPlanetTitles));
        mRightDrawerList.setAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, mPlanetTitles));
        // Set the list's click listener
        mLeftDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mRightDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                       .replace(R.id.content_frame, fragment)
                       .commit();
    }
	

	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        //selectItem(position);
	    	
	    	if (mDrawerLayout.isDrawerOpen(mRightDrawerList)) {
                mDrawerLayout.closeDrawer(mRightDrawerList);
                mDrawerLayout.openDrawer(mLeftDrawerList);
            } else if(mDrawerLayout.isDrawerOpen(mLeftDrawerList)){
            	mDrawerLayout.closeDrawer(mLeftDrawerList);
            	mDrawerLayout.openDrawer(mRightDrawerList);
                //mDrawerLayout.openDrawer(Gravity.END);
            }
	    }
	}
}
