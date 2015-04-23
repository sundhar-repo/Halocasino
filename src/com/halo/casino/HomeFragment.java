package com.halo.casino;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.halo.casino.listener.OnAnimCompleteListener;
import com.halo.casino.listener.OnStartAnimListener;

public class HomeFragment extends Fragment implements OnAnimCompleteListener, OnStartAnimListener{
	private final String TAG = HomeFragment.class.getSimpleName();
	
	private final int LAPS_MAX_COUNT = 2;
	ImageView mImageView;
	RotateAnimationController controller;
	private TextView mPlayerOneTitle, mPlayerTwoTitle, mPlayer1TotalScoreTV, mPlayer2TotalScoreTV;
	private TextView mp1LapCount, mp1LapMiddle, mp1CurntLapScore, mp2CurntLapScore;
	private static int mLapCopuntNumber = 0, mPlayerOneTotalScore = 0, mPlayerTwoTotalScore = 0;
	private Button mStartBtn;
	private boolean isAnimInProgress = false;
	private ViewAnimator mViewAnim;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_main, container, false);
		
		mStartBtn = (Button) view.findViewById(R.id.game_start_btn);
		mImageView = (ImageView) view.findViewById(R.id.image_view);
		mPlayer1TotalScoreTV = (TextView) view.findViewById(R.id.player_one_total_score_tv);
		mPlayer2TotalScoreTV = (TextView) view.findViewById(R.id.player_two_total_score_tv);
		mPlayerOneTitle = (TextView) view.findViewById(R.id.player_one_title);
		mPlayerTwoTitle = (TextView) view.findViewById(R.id.player_two_title);
		mp2CurntLapScore = (TextView) view.findViewById(R.id.player_two_current_score_tv);
		mp1CurntLapScore = (TextView) view.findViewById(R.id.player_one_current_score_tv);
		mViewAnim = (ViewAnimator) view.findViewById(R.id.viewAnimator1);
		
		final Animation inAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_in_left);
		final Animation outAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_out_right);
	  
		mViewAnim.setInAnimation(inAnim);
		mViewAnim.setOutAnimation(outAnim);
		
		
		controller = new RotateAnimationController(mImageView);
		
		mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//checkAndStartGame();
				mViewAnim.showNext();
				//mViewAnim.showPrevious();
			}
		});
		
		mStartBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//checkAndStartGame();
				mViewAnim.showPrevious();
				//mViewAnim.showNext();
			}
		});
		
		return view;
	}
	
	@Override
	public void onAnimComplete(int number) {
		Log.d(TAG, "raja onAnimComplete....update UI score: "+ number);
		
		isAnimInProgress = false;
		mStartBtn.setEnabled(true);
		if(mPlayerOneTitle.isEnabled()){
			Log.d(TAG, "raja onAnimComplete....update Player One UI score: ");
			mPlayerOneTitle.setEnabled(false);
			mPlayerTwoTitle.setEnabled(true);
			
			//update player once score
			mp1CurntLapScore.setText(String.valueOf(number));
			//StringBuilder score = new StringBuilder(mp1LapScore.getText());
			Log.d(TAG, "raja Player One score: "+number);
			//score.append("\n"+number);
			
			mPlayerOneTotalScore +=number;
			//if(mLapCopuntNumber == LAPS_MAX_COUNT){
				//score.append("\n"+mPlayerOneTotalScore);
				mPlayer1TotalScoreTV.setText(String.valueOf(mPlayerOneTotalScore));
			//}
			//mp1LapScore.setText(score);
			//score.r
			
		}else{
			Log.d(TAG, "raja onAnimComplete....update Player Two UI score: ");
			mPlayerOneTitle.setEnabled(true);
			mPlayerTwoTitle.setEnabled(false);
			
			//update player once score
			mp2CurntLapScore.setText(String.valueOf(number));
			//StringBuilder score = new StringBuilder(mp2cLapScore.getText());
			Log.d(TAG, "raja Player two score: "+number);
			//score.append("\n"+number);
			mPlayerTwoTotalScore += number;
			if(mLapCopuntNumber == LAPS_MAX_COUNT){
				//score.append("\n"+mPlayerTwoTotalScore);
				if(mPlayerTwoTotalScore > mPlayerOneTotalScore){
					Toast.makeText(getActivity(), "Game Over...Player Two won game", Toast.LENGTH_SHORT).show();
				}else if(mPlayerOneTotalScore > mPlayerTwoTotalScore){
					Toast.makeText(getActivity(), "Game Over...Player One won game", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), "Game Over...Match Tie...", Toast.LENGTH_SHORT).show();
				}
			}
			mPlayer2TotalScoreTV.setText(String.valueOf(mPlayerTwoTotalScore));
			//mp2LapScore.setText(score);
		}
	}
	
	private void checkAndStartGame(int number) {
		if(isAnimInProgress){
			return;
		}
		
		if(mLapCopuntNumber == LAPS_MAX_COUNT && !mPlayerTwoTitle.isEnabled()){
			//diplay dialog for start new Game
			Toast.makeText(getActivity(), "Game Over clearing data....", Toast.LENGTH_SHORT).show();
			mPlayer1TotalScoreTV.setText("0");
			mPlayer2TotalScoreTV.setText("0");
			mp1CurntLapScore.setText("0");
			mp2CurntLapScore.setText("0");
			mPlayerOneTotalScore = 0;
			mPlayerTwoTotalScore = 0;
			mLapCopuntNumber = 0;
			return;
		}
		mStartBtn.setEnabled(false);
		isAnimInProgress = true;
		controller.startChannelAnimation(HomeFragment.this, getActivity(), number);
		if(mPlayerOneTitle.isEnabled()){
			mLapCopuntNumber++;
		}
	}

	@Override
	public void onStartAnim(int number, int player) {
		checkAndStartGame(number);
	}
}
