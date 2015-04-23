package com.halo.casino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SpashScreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView splsh = new ImageView(this);
		splsh.setBackgroundResource(R.drawable.splash);
		setContentView(splsh);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(getApplicationContext(), MainTemplate.class));
				finish();
			}
		}, 3000);
	}
}
