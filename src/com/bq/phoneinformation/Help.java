package com.bq.phoneinformation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Help extends Activity {
	
	private ViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {    	
    	
        super.onCreate(savedInstanceState);        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
		Window myWindow=this.getWindow();
		myWindow.setFlags(flag,flag);
		setContentView(R.layout.help_main);		
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        
        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.help_view1, null);
        View view2 = mLi.inflate(R.layout.help_view2, null);
        View view3 = mLi.inflate(R.layout.help_view3, null);
        
        //每个页面的Title数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        
       
        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}

			

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		mViewPager.setAdapter(mPagerAdapter);
		
    }
    public void start(View view){
			Intent intent=new Intent(Help.this,Show.class);
			startActivity(intent);
			finish();
    }
}

