package com.halo.casino;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.halo.casino.listener.OnAnimCompleteListener;
import com.halo.casino.listener.OnStartAnimListener;

public class HomeFragment extends Fragment implements OnAnimCompleteListener, OnStartAnimListener{
	private final String TAG = HomeFragment.class.getSimpleName();
	
	private final int LAPS_MAX_COUNT = 2;
	private ImageView mRotateImg, mSelectItemImg, mSelectItemBack;
	RotateAnimationController controller;
	private TextView mP1Title, mP2Title, mP1TotalScoreTV, mP2TotalScoreTV, mP1LapsRmngCount, mP2LapsRmngCount;
	private TextView mp1CurntLapScore, mp2CurntLapScore;
	private static int mLapCountNumber = 0, mPlayerOneTotalScore = 0, mPlayerTwoTotalScore = 0;
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
		
		mSelectItemImg = (ImageView) view.findViewById(R.id.select_item_img);
		mSelectItemBack = (ImageView) view.findViewById(R.id.select_item_back);
		mRotateImg = (ImageView) view.findViewById(R.id.image_view);
		mP1TotalScoreTV = (TextView) view.findViewById(R.id.player_one_total_score_tv);
		mP2TotalScoreTV = (TextView) view.findViewById(R.id.player_two_total_score_tv);
		mP1Title = (TextView) view.findViewById(R.id.player_one_title);
		mP2Title = (TextView) view.findViewById(R.id.player_two_title);
		mp2CurntLapScore = (TextView) view.findViewById(R.id.player_two_current_score_tv);
		mp1CurntLapScore = (TextView) view.findViewById(R.id.player_one_current_score_tv);
		mViewAnim = (ViewAnimator) view.findViewById(R.id.viewAnimator1);
		mP1LapsRmngCount = (TextView) view.findViewById(R.id.player_one_remng_laps_tv);
		mP2LapsRmngCount = (TextView) view.findViewById(R.id.player_two_remng_laps_tv);
		
		mP1LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);
		mP2LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);
		
		final Animation inAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_in_left);
		final Animation outAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_out_right);
	  
		mViewAnim.setInAnimation(inAnim);
		mViewAnim.setOutAnimation(outAnim);
		
		
		controller = new RotateAnimationController(mRotateImg);
		
		/*mRotateImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//checkAndStartGame();
				mViewAnim.showNext();
				//mViewAnim.showPrevious();
			}
		});*/
		
		/*mRotateImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//checkAndStartGame();
				mViewAnim.showPrevious();
				//mViewAnim.showNext();
			}
		});*/
	
		
		mSelectItemImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSelectItemBack.setAlpha(0f);
				mSelectItemBack.setVisibility(View.VISIBLE);
				mSelectItemBack.animate()
							  .alpha(1f);
				
				mSelectItemImg.animate()
	            .alpha(0f)
	            .setListener(new AnimatorListenerAdapter() {
	                @Override
	                public void onAnimationEnd(Animator animation) {
	                	mSelectItemImg.setVisibility(View.GONE);
	                }
	            });
				
							  
				//mSelectItemImg.startAnimation(anim);
			}
		});
		
		mSelectItemBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSelectItemImg.setAlpha(0f);
				mSelectItemImg.setVisibility(View.VISIBLE);
				mSelectItemImg.animate()
							  .alpha(1f);
				
				mSelectItemBack.animate()
	            .alpha(0f)
	            .setListener(new AnimatorListenerAdapter() {
	                @Override
	                public void onAnimationEnd(Animator animation) {
	                	mSelectItemBack.setVisibility(View.GONE);
	                }
	            });
			}
		});
		
		return view;
	}
	
	@Override
	public void onAnimComplete(int number) {
		Log.d(TAG, "raja onAnimComplete....update UI score: "+ number);
		
		isAnimInProgress = false;
		
		if(mP1Title.isEnabled()){
			Log.d(TAG, "raja onAnimComplete....update Player One UI score: ");
			mP1Title.setEnabled(false);
			mP2Title.setEnabled(true);
			
			//update player once score
			mp1CurntLapScore.setText(String.valueOf(number));
			//StringBuilder score = new StringBuilder(mp1LapScore.getText());
			Log.d(TAG, "raja Player One score: "+number);
			//score.append("\n"+number);
			
			mPlayerOneTotalScore +=number;
			//if(mLapCopuntNumber == LAPS_MAX_COUNT){
				//score.append("\n"+mPlayerOneTotalScore);
				mP1TotalScoreTV.setText(String.valueOf(mPlayerOneTotalScore));
			//}
			//mp1LapScore.setText(score);
			//score.r
			mP1LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);
		}else{
			Log.d(TAG, "raja onAnimComplete....update Player Two UI score: ");
			mP1Title.setEnabled(true);
			mP2Title.setEnabled(false);
			
			//update player once score
			mp2CurntLapScore.setText(String.valueOf(number));
			//StringBuilder score = new StringBuilder(mp2cLapScore.getText());
			Log.d(TAG, "raja Player two score: "+number);
			//score.append("\n"+number);
			mPlayerTwoTotalScore += number;
			if(mLapCountNumber == LAPS_MAX_COUNT){
				//score.append("\n"+mPlayerTwoTotalScore);
				if(mPlayerTwoTotalScore > mPlayerOneTotalScore){
					Toast.makeText(getActivity(), "Game Over...Player Two won game", Toast.LENGTH_SHORT).show();
				}else if(mPlayerOneTotalScore > mPlayerTwoTotalScore){
					Toast.makeText(getActivity(), "Game Over...Player One won game", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), "Game Over...Match Tie...", Toast.LENGTH_SHORT).show();
				}
			}
			mP2TotalScoreTV.setText(String.valueOf(mPlayerTwoTotalScore));
			mP2LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);
			//mp2LapScore.setText(score);
		}
	}
	
	private void checkAndStartGame(int number) {
		if(isAnimInProgress){
			return;
		}
		
		if(mLapCountNumber == LAPS_MAX_COUNT && !mP2Title.isEnabled()){
			//diplay dialog for start new Game
			Toast.makeText(getActivity(), "Game Over clearing data....", Toast.LENGTH_SHORT).show();
			mP1TotalScoreTV.setText("0");
			mP2TotalScoreTV.setText("0");
			mp1CurntLapScore.setText("0");
			mp2CurntLapScore.setText("0");
			mPlayerOneTotalScore = 0;
			mPlayerTwoTotalScore = 0;
			mLapCountNumber = 0;
			mP1LapsRmngCount.setText("0/"+LAPS_MAX_COUNT);
			mP2LapsRmngCount.setText("0/"+LAPS_MAX_COUNT);
			return;
		}
		
		isAnimInProgress = true;
		controller.startChannelAnimation(HomeFragment.this, getActivity(), number);
		if(mP1Title.isEnabled()){
			mLapCountNumber++;
		}
	}

	@Override
	public void onStartAnim(int number, int player) {
		checkAndStartGame(number);
	}
}
