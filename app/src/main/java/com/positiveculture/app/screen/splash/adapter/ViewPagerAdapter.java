package com.positiveculture.app.screen.splash.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.positiveculture.app.R;

/**
 * Created by Chinh on 8/22/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mResources;
    public ViewPagerAdapter(Context mContext, int[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.image_screen_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_item);
        imageView.setImageResource(mResources[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
