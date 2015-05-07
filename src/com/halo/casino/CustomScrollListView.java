package com.halo.casino;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ListView;

public class CustomScrollListView extends ListView{

	protected boolean isScrollStop;

	public CustomScrollListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CustomScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollListView(Context context) {
		super(context);
	}

	@Override
	protected boolean canAnimate() {
		// TODO Auto-generated method stub
		return super.canAnimate();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return true;
	}
	
	@Override
	public void smoothScrollToPosition(int position) {
		super.smoothScrollToPosition(position);
	}
	
	public void setDisabled() {
		super.setEnabled(false);
	}
	
	public void setEnabled() {
		super.setEnabled(true);
	}

	public boolean isScrollStop() {
		return isScrollStop;
	}

	public void setScrollStop(boolean isScrollStop) {
		this.isScrollStop = isScrollStop;
	}
}
