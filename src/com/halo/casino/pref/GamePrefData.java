package com.halo.casino.pref;

import com.halo.casino.HaloApp;
import com.halo.casino.utils.HaloConstants;

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
	
	//Player One Details
	
	public void savePlayerOneName(String playerOneName){
		appPreferences.edit().putString(HaloConstants.PLAYER_ONE_NAME, playerOneName);
	}
	
	public String getPlayerOneName(){
		return appPreferences.getString(HaloConstants.PLAYER_ONE_NAME, "Player One");
	}
	
	public void savePlayerOneTotalScore(String totalScore){
		appPreferences.edit().putString(HaloConstants.PLAYER_ONE_TOTAL_SCORE, totalScore);
	}
	
	public String getPlayerOneTotalScore(){
		return appPreferences.getString(HaloConstants.PLAYER_ONE_TOTAL_SCORE, "0");
	}
	
	public void savePlayerOneRoundScore(String roundScore){
		appPreferences.edit().putString(HaloConstants.PLAYER_ONE_ROUND_SCORE, roundScore);
	}
	
	public String getPlayerOneRoundScore(){
		return appPreferences.getString(HaloConstants.PLAYER_ONE_ROUND_SCORE, "0");
	}
	
	public void savePlayerOneLaps(Integer laps){
		appPreferences.edit().putInt(HaloConstants.PLAYER_ONE_CURRENT_LAPS, laps);
	}
	
	public Integer getPlayerOneLaps(){
		return appPreferences.getInt(HaloConstants.PLAYER_ONE_CURRENT_LAPS, 0);
	}
}
