package com.bq.phoneinformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.TextView;

public class DisplayData  extends Activity{
	private TextView tv;
	private TextView content;
	@Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.systemdata);
	tv=(TextView) findViewById(R.id.tv);
	content=(TextView) findViewById(R.id.content);
	DisplayMetrics metric=new DisplayMetrics();
	getWindowManager().getDefaultDisplay().getMetrics(metric);
	int width=metric.widthPixels;//屏幕宽度[像素]
	int height=metric.heightPixels;//屏幕高度[像素]
	float density=metric.density;//屏幕密度[0.75|1.0|1.5]
	int densityDpi=metric.densityDpi;//屏幕密度[120|160|240]
	tv.setText("您的手机显示屏信息：");
	content.setText("屏幕宽度："+String.valueOf(width)+"像素\n"+"屏幕高度："+String.valueOf(height)+"像素\n"+"屏幕密度："+String.valueOf(density)+"\n"+"屏幕密度："+String.valueOf(densityDpi)+"（每英寸有多少个显示点）");
	
}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
				Intent inte= new Intent(this,Show.class);
				startActivity(inte);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();// 强制退出
			
			return true;// 设置成false让back失效 ，true表示 不失效
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}