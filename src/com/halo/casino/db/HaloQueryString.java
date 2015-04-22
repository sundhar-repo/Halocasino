package com.halo.casino.db;


public class HaloQueryString {

	
	public static final String CREATE_HALO_USER_TABLE = "CREATE TABLE IF NOT EXISTS "+HaloDBConstants.HALO_USER_TABLE+
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+HaloDBConstants.HALO_USER_NAME+" VARCHAR, "+HaloDBConstants.HALO_USER_TOTAL_SCORE+" VARCHAR, "
			+HaloDBConstants.HALO_USER_ROUND_SCORE+" VARCHAR, "+HaloDBConstants.HALO_TOTAL_LAPS+" VARCHAR"+");";

	

	//public static final String GET_SAVED_CARD_BY_ID = "SELECT * FROM "+DBConstants.HALLOW_SAVED_CARDS_TABLE+" WHERE  "+DBConstants.SAVED_CARD_ID+"=?"+" GROUP BY "+DBConstants.SAVED_CARD_ID;
}
