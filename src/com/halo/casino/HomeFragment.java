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
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.halo.casino.adaptor.SelectScrollItemAdaptor;
import com.halo.casino.listener.OnAnimCompleteListener;
import com.halo.casino.listener.OnStartAnimListener;

public class HomeFragment extends Fragment implements OnAnimCompleteListener, OnStartAnimListener{
	private final String TAG = HomeFragment.class.getSimpleName();
	
	String[] items = {"11", "26", "13", "3", "25", "14", "6", "1", "12", "18", "4", "8", "15", "30", "22", "9", "16", "2", "10", "7", "20", "5"};
	private final int LAPS_MAX_COUNT = 2;
	private ImageView mRotateImg;
	RotateAnimationController controller;
	//private TextView mP1Title, mP2Title, mP1TotalScoreTV, mP2TotalScoreTV, mP1LapsRmngCount, mP2LapsRmngCount;
	//private TextView mp1CurntLapScore, mp2CurntLapScore;
	private static int mLapCountNumber = 0, mPlayerOneTotalScore = 0, mPlayerTwoTotalScore = 0;
	private boolean isAnimInProgress = false;
	private ViewAnimator mViewAnim;

	private ProgressBar mP1ProgressVertical, mP2ProgressVertical;
	
	private CustomScrollListView p1SelectListItem, p2SelectListItem;
	private Button mP1Start, mP2Start, mRestartGame;
	
	private int PLAYER_ONE_TURN = 1, PLAYER_TWO_TURN = 2; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activity_main, container, false);
		
		//mSelectItemImg = (ImageView) view.findViewById(R.id.select_item_img);
		//mSelectItemBack = (ImageView) view.findViewById(R.id.select_item_back);
		mRotateImg = (ImageView) view.findViewById(R.id.image_view);
		controller = new RotateAnimationController(mRotateImg);
		/*mP1TotalScoreTV = (TextView) view.findViewById(R.id.player_one_total_score_tv);
		mP2TotalScoreTV = (TextView) view.findViewById(R.id.player_two_total_score_tv);
		mP1Title = (TextView) view.findViewById(R.id.player_one_title);
		mP2Title = (TextView) view.findViewById(R.id.player_two_title);
		mp2CurntLapScore = (TextView) view.findViewById(R.id.player_two_current_score_tv);
		mp1CurntLapScore = (TextView) view.findViewById(R.id.player_one_current_score_tv);*/
		mViewAnim = (ViewAnimator) view.findViewById(R.id.viewAnimator1);
		/*mP1LapsRmngCount = (TextView) view.findViewById(R.id.player_one_remng_laps_tv);
		mP2LapsRmngCount = (TextView) view.findViewById(R.id.player_two_remng_laps_tv);*/
		mP1ProgressVertical = (ProgressBar) view.findViewById(R.id.vertical_progressbar);
		mP2ProgressVertical = (ProgressBar) view.findViewById(R.id.vertical_progressbar2);
		
		p1SelectListItem = (CustomScrollListView) view.findViewById(R.id.p1_select_item_list);
		p2SelectListItem = (CustomScrollListView) view.findViewById(R.id.p2_select_item_list);
		mP1Start = (Button) view.findViewById(R.id.p1_start_btn);
		mP2Start = (Button) view.findViewById(R.id.p2_start_btn);
		mRestartGame = (Button) view.findViewById(R.id.restart_game);
		
		
		p1SelectListItem.setAdapter(new SelectScrollItemAdaptor(getActivity(), items));
		p1SelectListItem.setDisabled();
		
		p2SelectListItem.setAdapter(new SelectScrollItemAdaptor(getActivity(), items));
		p2SelectListItem.setDisabled();
		
		if(mP1Start.isEnabled()){
			mP2Start.setEnabled(false);
		}
		
		
		/*mP1LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);
		mP2LapsRmngCount.setText(mLapCountNumber+"/"+LAPS_MAX_COUNT);*/
		
		final Animation inAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_in_left);
		final Animation outAnim = AnimationUtils.loadAnimation(getActivity() ,android.R.anim.slide_out_right);
	  
		mViewAnim.setInAnimation(inAnim);
		mViewAnim.setOutAnimation(outAnim);
		
		mP1ProgressVertical.setMax(100);
		mP2ProgressVertical.setMax(100);
		
		//startP1Progress();
		//startP2Progress();
		
		
		/*mRotateImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//checkAndStartGame();
				mViewAnim.showPrevious();
				//mViewAnim.showNext();
			}
		});*/
	
		
		/*mSelectItemImg.setOnClickListener(new OnClickListener() {
			
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
		});*/
		
		mP1Start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = mP1Start.getText().toString();
				Log.d(TAG, "raja text: "+text);
				if(text.equalsIgnoreCase("Start")){
					startP1Progress();
					startP1Scroll();
					mP1Start.setText("Stop");
				}else{
					stopP1Progress();
					mP1Start.setText("Start");
					mP1Start.setEnabled(false);
					//start rotate image
					checkAndStartGame(2, PLAYER_ONE_TURN);
				}
			}
		});
		
		mP2Start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String text = mP2Start.getText().toString();
				Log.d(TAG, "raja text: "+text);
				if(text.equalsIgnoreCase("Start")){
					startP2Progress();
					startP2Scroll();
					mP2Start.setText("Stop");
				}else{
					stopP2Progress();
					mP2Start.setText("Start");
					mP2Start.setEnabled(false);
					//start rotate image 
					checkAndStartGame(2, PLAYER_TWO_TURN);
				}
			}
		});

		mRestartGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRestartGame.setVisibility(View.GONE);
				mPlayerOneTotalScore = 0;
				mPlayerTwoTotalScore = 0;
				mLapCountNumber = 0;
				mP1Start.setEnabled(true);
				mP2Start.setEnabled(false);
				
				p1SelectListItem.setScrollStop(false);
				p2SelectListItem.setScrollStop(false);
				
			}
		});
		
		return view;
	}
	
	@Override
	public void onAnimComplete(int number, int mPlayerTurn) {
		Log.d(TAG, "raja onAnimComplete....update UI score: "+ number);
		
		isAnimInProgress = false;
		
		if(mPlayerTurn == PLAYER_ONE_TURN){
			//stop p1 scroll list items
			p1SelectListItem.setScrollStop(true);
			p2SelectListItem.setScrollStop(false);
			p1SelectListItem.smoothScrollBy(0, 0);
			
			if(mLapCountNumber == LAPS_MAX_COUNT){
				//total score end laps for player one
			}else{
				
			}
			mP1Start.setEnabled(false);
			mP2Start.setEnabled(true);
		}else if(mPlayerTurn == PLAYER_TWO_TURN){
			//stop p2 scroll list items
			p2SelectListItem.setScrollStop(true);
			p1SelectListItem.setScrollStop(false);
			
			p2SelectListItem.smoothScrollBy(0, 0);
			
			if(mLapCountNumber == LAPS_MAX_COUNT){
				mRestartGame.setEnabled(true);
				mRestartGame.setVisibility(View.VISIBLE);
				//total score end laps for player one
				mP1Start.setEnabled(false);
			}else{
				mP1Start.setEnabled(true);
			}
			
			mP2Start.setEnabled(false);
		}
		
		/*if(mP1Title.isEnabled()){
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
		}*/
	}
	
	private void checkAndStartGame(int number, int playerTurn) {
		if(isAnimInProgress){
			return;
		}
		
		if(mLapCountNumber == LAPS_MAX_COUNT && playerTurn != PLAYER_TWO_TURN){
			//diplay dialog for start new Game
			Toast.makeText(getActivity(), "Game Over clearing data....", Toast.LENGTH_SHORT).show();
			/*mP1TotalScoreTV.setText("0");
			mP2TotalScoreTV.setText("0");
			mp1CurntLapScore.setText("0");
			mp2CurntLapScore.setText("0");*/
			mPlayerOneTotalScore = 0;
			mPlayerTwoTotalScore = 0;
			mLapCountNumber = 0;
			mP1Start.setEnabled(true);
			mP2Start.setEnabled(false);
			/*mP1LapsRmngCount.setText("0/"+LAPS_MAX_COUNT);
			mP2LapsRmngCount.setText("0/"+LAPS_MAX_COUNT);*/
			return;
		}
		
		isAnimInProgress = true;
		controller.startChannelAnimation(HomeFragment.this, getActivity(), number, playerTurn);
		if(playerTurn == PLAYER_ONE_TURN){
			mLapCountNumber++;
		}
	}
	
	public void startP1Scroll(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i= 0;
				while(!p1SelectListItem.isScrollStop()){
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p1SelectListItem.setSmoothScrollbarEnabled(true);
					p1SelectListItem.smoothScrollToPosition(i);
					i++;
				}		
			}
		}).start();
	}

	public void startP2Scroll(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i= 0;
				while(!p2SelectListItem.isScrollStop()){
					try {
						Thread.sleep(120);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p2SelectListItem.setSmoothScrollbarEnabled(true);
					p2SelectListItem.smoothScrollToPosition(i);
					i++;
				}		
			}
		}).start();
	}
	
	public void startP1Progress(){
		
		mP1ProgressVertical.setActivated(true);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i =100;
				while(i>=0){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!mP1ProgressVertical.isActivated()){
						break;
					}
					mP1ProgressVertical.setProgress(i);
					i -=3;
					if(i < 0){
						i = 100;
					}
					
				}
			}
		}).start();
	}
	
	public void startP2Progress(){
		mP2ProgressVertical.setActivated(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i =0;
				while(i<=100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(!mP2ProgressVertical.isActivated()){
						break;
					}
					
					mP2ProgressVertical.setProgress(i);
					i +=3;
					if(i > 100){
						i = 0;
					}
					
				}
			}
		}).start();
	}
	
	private void stopP1Progress(){
		mP1ProgressVertical.setActivated(false);
	}
	
	private void stopP1ScrollList(){
		
	}
	
	private void stopP2Progress(){
		mP2ProgressVertical.setActivated(false);
	}
	
	private void stopP2ScrollList(){
		
	}
	
	@Override
	public void onStartAnim(int number, int player) {
		checkAndStartGame(number, player);
	}
}
