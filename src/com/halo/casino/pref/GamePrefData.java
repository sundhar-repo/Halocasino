package com.halo.casino.pref;

import com.halo.casino.HaloApp;

import android.content.SharedPreferences;

public class GamePrefData {
	private static GamePrefData mGamePref;
	public static SharedPreferences appPreferences;
	
	public static GamePrefData getInstance(){
		if(mGamePref == null){
			mGamePref = new GamePrefData();
		}
		appPreferences = HaloApp.sContext.getSharedPreferences("appPref", HaloApp.MODE_PRIVATE);
		return mGamePref;
	}
	
}
