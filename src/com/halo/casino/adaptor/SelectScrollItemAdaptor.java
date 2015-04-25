package com.halo.casino.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.halo.casino.R;

public class SelectScrollItemAdaptor extends BaseAdapter{
	private final String TAG = SelectScrollItemAdaptor.class.getSimpleName();  

	private String[] items;
	 LayoutInflater inflater;
	public SelectScrollItemAdaptor(Context context, String[] items){
		this.items = items;
		 inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		//Log.d(TAG, "raja position: "+position%items.length);
		return items[position%items.length];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHolder mViewHolder;
        
        if(convertView == null) {
                convertView = inflater.inflate(R.layout.scroll_list_item, null);
                mViewHolder = new MyViewHolder();
                mViewHolder.numbers = (TextView) convertView.findViewById(R.id.item_number_tv);
                convertView.setTag(mViewHolder);
        } else {
                mViewHolder = (MyViewHolder) convertView.getTag();
        }
        
        
        //Log.d(TAG, "raja getItem: "+String.valueOf(getItem(position)));
        mViewHolder.numbers.setText(String.valueOf(getItem(position)));
        return convertView;
	}
	
	private class MyViewHolder {
        TextView numbers;
}

}
