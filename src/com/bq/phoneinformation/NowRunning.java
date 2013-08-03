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
import android.widget.Toast;

public class NowRunning extends Activity {
	private ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv = (ListView) findViewById(R.id.lv);

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		map1.put("title", "正在运行的任务");
		map1.put("data", "获取正在运行的任务信息");
		map2.put("title", "正在运行的服务");
		map2.put("data", "获取正在运行的服务");
		map3.put("title", "进程信息");
		map3.put("data", "点击一键清理所有");
		list.add(map1);
		list.add(map2);
		list.add(map3);
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

		public void onItemClick(AdapterView<?> aV, View v, int p, long l) {
			Intent intent = new Intent();
			switch (p) {
			case 0: {
				intent.setClass(NowRunning.this, RunningActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
			}
			case 1: {
				intent.setClass(NowRunning.this, RunningService.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
			}

			case 2: {
				intent.setClass(NowRunning.this, RunningProcess.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
				finish();
				break;
			}

			}
		}
	}
}
