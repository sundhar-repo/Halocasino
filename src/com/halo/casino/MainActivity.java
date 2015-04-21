package com.halo.casino;

import com.halo.casino.listener.OnAnimCompleteListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnAnimCompleteListener{
	private final String TAG = MainActivity.class.getSimpleName();
	
	ImageView mImageView;
	RotateAnimationController controller;
	private TextView mPlayerOne;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.image_view);
		mPlayerOne = (TextView) findViewById(R.id.player_one);
		
		controller = new RotateAnimationController(mImageView);
		
		mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				controller.startChannelAnimation(MainActivity.this, getApplicationContext());
			}
		});
	}

	@Override
	public void onAnimComplete(int number) {
		Log.d(TAG, "raja onAnimComplete....update UI score: "+ number);
		if(mPlayerOne.isEnabled()){
			Log.d(TAG, "raja onAnimComplete....update Player One UI score: ");
			mPlayerOne.setEnabled(false);
		}else{
			Log.d(TAG, "raja onAnimComplete....update Player Two UI score: ");
			mPlayerOne.setEnabled(true);
		}
	}
}
