package com.example.newtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity{
	ImageView ed;
	RotateAnimationController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ed = (ImageView) findViewById(R.id.image_view);
		
		controller = new RotateAnimationController(ed);
		
		ed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				controller.startChannelAnimation(1, getApplicationContext());
			}
		});
	}
}
