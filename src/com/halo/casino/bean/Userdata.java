package com.halo.casino.bean;


public class Userdata {
	
	private static Userdata muserData;
	private String userName;
	private String userTotalScore;
	private String reoundLapsTotalScore;
	private int totalLaps;
	
	public Userdata(){
		
	}
	
	public static Userdata getMuserData() {
		return muserData;
	}

	public static void setMuserData(Userdata muserData) {
		Userdata.muserData = muserData;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTotalScore() {
		return userTotalScore;
	}

	public void setUserTotalScore(String userTotalScore) {
		this.userTotalScore = userTotalScore;
	}

	public String getReoundLapsTotalScore() {
		return reoundLapsTotalScore;
	}

	public void setReoundLapsTotalScore(String reoundLapsTotalScore) {
		this.reoundLapsTotalScore = reoundLapsTotalScore;
	}

	public int getTotalLaps() {
		return totalLaps;
	}

	public void setTotalLaps(int totalLaps) {
		this.totalLaps = totalLaps;
	}
}
