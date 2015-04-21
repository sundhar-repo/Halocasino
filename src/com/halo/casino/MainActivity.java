package com.halo.casino;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.halo.casino.listener.OnAnimCompleteListener;

public class MainActivity extends Activity implements OnAnimCompleteListener{
	private final String TAG = MainActivity.class.getSimpleName();
	
	private final int LAPS_MAX_COUNT = 2;
	ImageView mImageView;
	RotateAnimationController controller;
	private TextView mPlayerOneTitle, mPlayerTwoTitle, mPlayer1TotalScoreTV, mPlayer2TotalScoreTV;
	private TextView mp1LapCount, mp1LapMiddle, mp1LapScore, mp2LapScore;
	private static int mLapCopuntNumber = 0, mPlayerOneTotalScore = 0, mPlayerTwoTotalScore = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.image_view);
		mPlayer1TotalScoreTV = (TextView) findViewById(R.id.player_one_total_score_tv);
		mPlayer2TotalScoreTV = (TextView) findViewById(R.id.player_two_total_score_tv);
		mPlayerOneTitle = (TextView) findViewById(R.id.player_one_title);
		mPlayerTwoTitle = (TextView) findViewById(R.id.player_two_title);
		mp2LapScore = (TextView) findViewById(R.id.p2_lap_score_tv);
		//mp1LapCount = (TextView) findViewById(R.id.lap_count_tv);
		//mp1LapMiddle = (TextView) findViewById(R.id.lap_middle_tv);
		mp1LapScore = (TextView) findViewById(R.id.lap_score_tv);
		
		controller = new RotateAnimationController(mImageView);
		
		mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(mLapCopuntNumber == LAPS_MAX_COUNT && !mPlayerTwoTitle.isEnabled()){
					//diplay dialog for start new Game
					Toast.makeText(getApplicationContext(), "Game Over clearing data....", Toast.LENGTH_SHORT).show();
					mPlayer1TotalScoreTV.setText("0");
					mPlayer2TotalScoreTV.setText("0");
					mp1LapScore.setText("");
					mp2LapScore.setText("");
					mPlayerOneTotalScore = 0;
					mPlayerTwoTotalScore = 0;
					mLapCopuntNumber = 0;
					return;
				}
				controller.startChannelAnimation(MainActivity.this, getApplicationContext());
				if(mPlayerOneTitle.isEnabled()){
					mLapCopuntNumber++;
				}
			}
		});
	}

	@Override
	public void onAnimComplete(int number) {
		Log.d(TAG, "raja onAnimComplete....update UI score: "+ number);
		if(mPlayerOneTitle.isEnabled()){
			Log.d(TAG, "raja onAnimComplete....update Player One UI score: ");
			mPlayerOneTitle.setEnabled(false);
			mPlayerTwoTitle.setEnabled(true);
			
			//update player once score
			StringBuilder score = new StringBuilder(mp1LapScore.getText());
			Log.d(TAG, "raja Player One score: "+score);
			score.append("\n"+number);
			
			mPlayerOneTotalScore +=number;
			if(mLapCopuntNumber == LAPS_MAX_COUNT){
				score.append("\n"+mPlayerOneTotalScore);
				mPlayer1TotalScoreTV.setText(String.valueOf(mPlayerOneTotalScore));
			}
			mp1LapScore.setText(score);
			//score.r
			
		}else{
			Log.d(TAG, "raja onAnimComplete....update Player Two UI score: ");
			mPlayerOneTitle.setEnabled(true);
			mPlayerTwoTitle.setEnabled(false);
			
			//update player once score
			StringBuilder score = new StringBuilder(mp2LapScore.getText());
			Log.d(TAG, "raja Player two score: "+score);
			score.append("\n"+number);
			mPlayerTwoTotalScore += number;
			if(mLapCopuntNumber == LAPS_MAX_COUNT){
				score.append("\n"+mPlayerTwoTotalScore);
				if(mPlayerTwoTotalScore > mPlayerOneTotalScore){
					Toast.makeText(getApplicationContext(), "Game Over...Player Two won game", Toast.LENGTH_SHORT).show();
				}else if(mPlayerOneTotalScore > mPlayerTwoTotalScore){
					Toast.makeText(getApplicationContext(), "Game Over...Player One won game", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "Game Over...Match Tie...", Toast.LENGTH_SHORT).show();
				}
				mPlayer2TotalScoreTV.setText(String.valueOf(mPlayerTwoTotalScore));
			}
			mp2LapScore.setText(score);
		}
	}
}
