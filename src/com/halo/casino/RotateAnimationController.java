package com.halo.casino;

import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Animation controller for rotary knob channel change
 * @author roshan.d
 */
public class RotateAnimationController implements AnimationListener{
	private final String TAG = RotateAnimationController.class.getSimpleName();
	
	private Context mContext;
	private AlphaAnimation mAlphaAnimation;
	private AnimationSet mAnimationSet;
	private ImageView mRotaryImage;
	private Animation mAnimationRotateCenter;
	private float mAnimationDegree;
	private int mPrevChannel = 0;
	int i = 0;

	private int DEGREES_TO_ROTATE;
	private static final long ANIMATION_DURATION = 10000;
	private static final int DIRECTION_LOWER_THRESHOLD = 4;
	private static final int DIRECTION_UPPER_THRESHOLD = 6;
	private static final float PIVOT_VALUE = 0.5f;
	private static final float FROM_ALPHA = 0.8f;
	private static final float TO_ALPHA = 1.0f;
	int IMAGE_COUNT = 8;

	public RotateAnimationController(final ImageView rotaryImage) {
		mRotaryImage = rotaryImage;
	}

	public void startChannelAnimation(final int nextChannel, Context context) {
		mContext = context;
		DEGREES_TO_ROTATE = 360;
		Log.d(TAG, "raja startChannelAnimation : next = " + nextChannel + " prev = " + mPrevChannel);
		//if(mPrevChannel != nextChannel) {
			Log.d(TAG, "raja [ANIM] (n, p) = (" + nextChannel + "," + mPrevChannel + ")");
			rotate(0, nextChannel);
			mPrevChannel = nextChannel;
		//}
	}

	private void rotate(final int prev, final int next) {
		float angle = computeCurrentRotationAngle(prev, next);
		mAlphaAnimation = new AlphaAnimation(FROM_ALPHA, TO_ALPHA);
		mAnimationRotateCenter = new RotateAnimation(mAnimationDegree,
				mAnimationDegree + angle, Animation.RELATIVE_TO_SELF, PIVOT_VALUE,
				Animation.RELATIVE_TO_SELF, PIVOT_VALUE);
		mAnimationRotateCenter.setAnimationListener(this);
		mAnimationSet = new AnimationSet(true);
		mAnimationSet.addAnimation(mAlphaAnimation);
		mAnimationSet.addAnimation(mAnimationRotateCenter);
		mAnimationSet.setFillEnabled(true);
		mAnimationSet.setInterpolator(new DecelerateInterpolator());
		mAnimationSet.setFillAfter(true);
		mAnimationSet.setDuration(ANIMATION_DURATION);
		mRotaryImage.startAnimation(mAnimationSet);
		//computeNextRotationAngle(angle);
	}

	private float computeCurrentRotationAngle(final int prev, final int next) {
		/*float steps = (IMAGE_COUNT + 1 + next - prev)
				% (IMAGE_COUNT + 1);
		if (steps >= DIRECTION_LOWER_THRESHOLD && steps <= DIRECTION_UPPER_THRESHOLD) {
			steps = (IMAGE_COUNT + 1 - steps);
		} else {
			steps = steps * -1;
		}*/
		float stopDegree = getRandomNumberFrom(0, 35) * 10;
		float steps = 10 * -1;
		Log.d(TAG, "raja computeCurrentRotationAngle steps ="+ steps + " stopDegree: "+stopDegree);
		return (steps * DEGREES_TO_ROTATE) + stopDegree;
	}

	private void computeNextRotationAngle(final float angle) {
		mAnimationDegree = (mAnimationDegree + angle);
		if (mAnimationDegree > 360) {
			float offset = (mAnimationDegree) % 360;
			if (offset >= (DEGREES_TO_ROTATE / 2)
					&& offset <= (DEGREES_TO_ROTATE + (DEGREES_TO_ROTATE / 2))) {
				mAnimationDegree = DEGREES_TO_ROTATE;
			} else {
				mAnimationDegree = offset;
			}
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Log.d(TAG, "raja onAnimationEnd");	
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		Log.d(TAG, "raja onAnimationRepeat.."+animation.getRepeatCount());
	}

	@Override
	public void onAnimationStart(Animation animation) {
		Log.d(TAG, "raja onAnimationStart");
	}
	
	public int getRandomNumberFrom(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt((max + 1) - min) + min;
        Log.d(TAG, "raja randomNumber: "+randomNumber);
        return randomNumber;

    }
	
	private class MyInterpolator extends DecelerateInterpolator {

	    public float getInterpolation (float input) {
	    	float val = (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
	    	Log.d(TAG, "raja1 getInterpolation: "+val);
	        return val;
	        
	    }
	}
}
