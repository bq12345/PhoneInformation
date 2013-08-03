package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HardWare extends Activity {
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
        map1.put("title", "CPU信息");
        map1.put("data", "获取CPU信息");
        map2.put("title", "内存信息");
        map2.put("data", "手机内存信息");
        map3.put("title", "网络信息");
        map3.put("data", "手机网络信息");
        map4.put("title", "显示屏信息");
        map4.put("data", "手机屏幕信息");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.item, new String[] { "title", "data" }, new int[] {
						R.id.title, R.id.data });	
		lv.setAdapter(listAdapter);
		lv.setCacheColorHint(0);
		lv.setOnItemClickListener(new MyClickListener());
		
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
	public class MyClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> aV, View v, int p,
				long l) {
			Intent intent = new Intent();
			switch(p){
			case 0:{
			intent.setClass(HardWare.this, CPUData.class);
			startActivity(intent);
			overridePendingTransition(R.anim.fade, R.anim.hold);
			finish();
			break;
			}
			case 1:{
				intent.setClass(HardWare.this, MemoryData.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			
			case 2:{
				intent.setClass(HardWare.this,NetStat.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			case 3:{
				intent.setClass(HardWare.this,DisplayData.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
				}
			
			
			}		
		}
		
	}
        

}
