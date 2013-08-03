package com.bq.phoneinformation;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


public class Launch  extends Activity{
	private int count;
	private static final int LOAD_DISPLAY_TIME = 2000;

	public void onCreate(Bundle savedInstanceState) {
	super .onCreate(savedInstanceState);
	SharedPreferences shardPerferences = getSharedPreferences("count",
			Context.MODE_PRIVATE);
	count = shardPerferences.getInt("count", 0);
	if (count == 0) {
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), Help.class);
		startActivity(intent);
		finish();
	}else {	getWindow().setFormat(PixelFormat.RGBA_8888);
	getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);	 setContentView(R.layout.load);
	new Handler().postDelayed( new Runnable() {
	 public void run() {
	/* Create an Intent that will start the Main WordPress Activity. */
	Intent mainIntent =  new Intent(Launch. this , Show. class );
	Launch. this.startActivity(mainIntent); Launch. this .finish();
	}
	}, LOAD_DISPLAY_TIME);}
	Editor editor = shardPerferences.edit();
	// 存入数据
	editor.putInt("count", ++count);
	// 提交修改
	editor.commit();

	
	}
}
