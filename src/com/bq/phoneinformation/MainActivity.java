package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ActivityGroup{
	SlidingMenuView slidingMenuView;
	ViewGroup tabcontent;
	private ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        lv=(ListView) findViewById(R.id.lv);

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        HashMap<String, String> map3 = new HashMap<String, String>();
        HashMap<String, String> map4 = new HashMap<String, String>();
        HashMap<String, String> map5 = new HashMap<String, String>();
        HashMap<String, String> map6 = new HashMap<String, String>();
        HashMap<String, String> map7 = new HashMap<String, String>();
        HashMap<String, String> map9 = new HashMap<String, String>();
        map1.put("title", "系统信息");
        map1.put("data", "系统版本，运营商及系统信息");
        map2.put("title", "硬件信息");
        map2.put("data", "CPU，内存等硬件信息");
        map3.put("title", "软件管理");
        map3.put("data", "已安装的软件和系统软件");        
        map4.put("title", "运行时信息");
        map4.put("data", "正在运行的。。。");        
        map5.put("title", "文件浏览");
        map5.put("data", "提供一个文件浏览的功能");
        map6.put("title", "手机");
        map6.put("data", android.os.Build.MODEL);
        map7.put("title", "操作系统版本");
        map7.put("data", android.os.Build.VERSION.RELEASE);
        map9.put("title", "开机时间");
        map9.put("data", getTimes());
        
        
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map9);
        
        SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.item, new String[] { "title", "data" }, new int[] {
						R.id.title, R.id.data });	
		lv.setAdapter(listAdapter);
		lv.setCacheColorHint(0);
		lv.setOnItemClickListener(new MyClickListener());
		
	}
   
	public class MyClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> aV, View v, int p,
				long l) {
			Intent intent = new Intent();
			switch(p){
			case 0:{
			intent.setClass(MainActivity.this, SystemData.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade, R.anim.hold);
			finish();
			break;
			}
			case 1:{
				intent.setClass(MainActivity.this, HardWare.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			
			case 2:{
				intent.setClass(MainActivity.this, GetappinfoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			case 3:{
				intent.setClass(MainActivity.this, NowRunning.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			case 4:{
				intent.setClass(MainActivity.this,com.android.FileBrowser.Main.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			
			}		
		}
		
	}
        
	private String getTimes() {  
	    long ut = SystemClock.elapsedRealtime() / 1000;  
	    if (ut == 0) {  
	        ut = 1;  
	    }  
	    int m = (int) ((ut / 60) % 60);  
	    int h = (int) ((ut / 3600));  
	    return h + " " + "小时" + m+"分";
	}
	 
	public void about() {
		new AlertDialog.Builder(MainActivity.this)
				.setTitle("关于开发者")
				.setMessage("欢迎您使用小Q系统信息v1.0版\n     Designed by：白强")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// finish();
					}
				})
				.setNegativeButton("返回", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

					}

				}).show();
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
