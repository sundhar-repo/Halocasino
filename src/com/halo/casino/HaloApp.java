package com.halo.casino;

import android.app.Application;
import android.content.Context;

public class HaloApp extends Application{
	public static Context sContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		sContext = this;
	}
}
