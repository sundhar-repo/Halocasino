package com.halo.casino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainTemplate extends FragmentActivity{
	private String[] mPlanetTitles = {"1","10","5","15","25","6","30","17","11","4","19","8",};
	private DrawerLayout mDrawerLayout;
    private GridView mLeftDrawerList, mRightDrawerList;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main_template);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mLeftDrawerList = (GridView) findViewById(R.id.left_drawer);
        mRightDrawerList = (GridView) findViewById(R.id.right_drawer);
        
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
                       .replace(R.id.content_frame, fragment, "home_fragment_tag")
                       .commit();
    }
	

	
	private class DrawerItemClickListener implements GridView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        //selectItem(position);
	    	
	    	if (mDrawerLayout.isDrawerOpen(mRightDrawerList)) {
                mDrawerLayout.closeDrawer(mRightDrawerList);
                HomeFragment mainActivity = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home_fragment_tag");
                if(mainActivity != null){
                	mainActivity.onStartAnim(2, 2);
                }
                //mDrawerLayout.openDrawer(mLeftDrawerList);
            } else if(mDrawerLayout.isDrawerOpen(mLeftDrawerList)){
            	mDrawerLayout.closeDrawer(mLeftDrawerList);
            	HomeFragment mainActivity = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home_fragment_tag");
                if(mainActivity != null){
                	mainActivity.onStartAnim(30, 1);
                }
            	//mDrawerLayout.openDrawer(mRightDrawerList);
                //mDrawerLayout.openDrawer(Gravity.END);
            }
	    }
	}
}
