package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ActivityGroup;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Show extends ActivityGroup {

	SlidingMenuView slidingMenuView;
	private ListView lv;
	ViewGroup tabcontent;
	private boolean flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main);
		slidingMenuView = (SlidingMenuView) findViewById(R.id.sliding_menu_view);
		tabcontent = (ViewGroup) slidingMenuView
				.findViewById(R.id.sliding_body);
		lv = (ListView) findViewById(R.id.menu_lv);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		HashMap<String, String> map4 = new HashMap<String, String>();
		HashMap<String, String> map5 = new HashMap<String, String>();
		HashMap<String, String> map6 = new HashMap<String, String>();
		HashMap<String, String> map7 = new HashMap<String, String>();
		HashMap<String, String> map8 = new HashMap<String, String>();
		
		map1.put("name", "显示主页");
		map2.put("name", "系统信息");
		map3.put("name", "硬件信息");
		map4.put("name", "正在运行");
		map5.put("name", "反馈信息");
		map6.put("name", "分享软件");
		map7.put("name", "关于软件");
		map8.put("name", "退出");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.menu_item, new String[] { "name" },
				new int[] { R.id.menu_name });
		lv.setAdapter(listAdapter);
		lv.setCacheColorHint(0);
		lv.setOnItemClickListener(new MyClickListener());
		showDefaultTab();

	}

	public class MyClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View v, int p, long l) {
			switch (p) {
			case 0:
				showDefaultTab();
				hideMenu();
				break;
			case 1:
				changeTab1();
				hideMenu();
				break;
			case 2:
				changeTab2();
				hideMenu();
				break;
			case 3:
				changeTab3();
				hideMenu();
				break;
			case 4:
				changeTab4();
				hideMenu();
				break;
			case 5:
				changeTab5();
				hideMenu();
				break;
			case 6:
				changeTab6();
				hideMenu();
				break;
			case 7:
				finish();// 强制退出
				onDestroy();
				System.exit(0);
				android.os.Process.killProcess(android.os.Process.myPid());
				break;
			}

		}

	}

	public void hideMenu() {
		slidingMenuView.scrollRight();
		flag=false;
	}

	public void showMenu() {
		slidingMenuView.scrollLeft();
		flag=true;
	}

	void showDefaultTab() {
		Intent i = new Intent(this, MainActivity.class);
		View v = getLocalActivityManager().startActivity(
				MainActivity.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	public void changeTab1() {
		Intent i = new Intent(this, SystemData.class);
		View v = getLocalActivityManager().startActivity(
				SystemData.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	public void changeTab2() {
		Intent i = new Intent(this, HardWare.class);
		View v = getLocalActivityManager().startActivity(
				HardWare.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	public void changeTab3() {
		Intent i = new Intent(this, NowRunning.class);
		View v = getLocalActivityManager().startActivity(
				NowRunning.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	public void changeTab4() {
		Intent i = new Intent(this, Send.class);
		View v = getLocalActivityManager().startActivity(Send.class.getName(),
				i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	public void changeTab5() {
		Intent intent = new Intent();
		Uri uri;
		String data;
		data = "smsto:";
		uri = Uri.parse(data);
		intent.setAction(Intent.ACTION_SENDTO);
		intent.setData(uri);
		intent.putExtra("sms_body",
				"我正在使用一款系统查看软件->小Q系统信息查看，你要不也试试？(Powered By BaiQiang)");
		startActivity(intent);
	}

	public void changeTab6() {
		Intent i = new Intent(this,About.class);
		View v = getLocalActivityManager().startActivity(
				About.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}
	public void changeTab7() {
		Intent i = new Intent(this,Help.class);
		View v = getLocalActivityManager().startActivity(
				Help.class.getName(), i).getDecorView();
		tabcontent.removeAllViews();
		tabcontent.addView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 82) {
			if(flag==false){
			showMenu();
		}
		else{
			hideMenu();
		}
			return true;
	}
		return false;

	}

}
