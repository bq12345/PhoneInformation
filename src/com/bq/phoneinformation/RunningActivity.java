package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RunningActivity extends Activity implements OnItemClickListener{
	PackageManager pm;
	ActivityManager activityManager;
	// List<ActivityManager.RunningAppProcessInfo> processInfo =
	// activityManager.getRunningAppProcesses();
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setTitle("正在运行的,点击停止");
		
		showListView();
	}
	public void showListView(){
		try {
			activityManager = (ActivityManager) this
					.getSystemService(ACTIVITY_SERVICE);
			List<ActivityManager.RunningTaskInfo> mRunningTasks = activityManager
					.getRunningTasks(30);
			if (mRunningTasks.size() == 0) {
				this.setTitle("没有运行的任务");
			} else {
				for (RunningTaskInfo amTask : mRunningTasks) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name",
							getAppName(amTask.baseActivity.getPackageName()));
					map.put("pack", amTask.baseActivity.getPackageName());
					map.put("im",
							getAppIcon(amTask.baseActivity.getPackageName()));
					list.add(map);
					System.out.print(amTask.baseActivity.describeContents()
							+ "\n");
				}
			}
		} catch (Exception e) {
		}
		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.data, new String[] { "im", "name", "pack" },
				new int[] { R.id.im, R.id.name, R.id.pack });
		ListView lv = (ListView) findViewById(R.id.list);
		listAdapter.setViewBinder(new ViewBinder() {
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if (view instanceof ImageView && data instanceof Drawable) {
					ImageView iv = (ImageView) view;
					iv.setImageDrawable((Drawable) data);
					return true;
				} else
					return false;
			}
		});
		lv.setAdapter(listAdapter);
		lv.setCacheColorHint(0);
		lv.setOnItemClickListener(this);	
	}
	

		public void onItemClick(AdapterView<?> arg0, View v, int p, long l) {

			TextView tv = (TextView) v.findViewById(R.id.pack);
			activityManager.restartPackage(tv.getText().toString());
			list.clear();
			showListView();

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}

	/*
	 * private void killAll() { for (int i = 0; i < processInfo.size(); i++) {
	 * System.out.println(processInfo.get(i).processName);
	 * activityManager.restartPackage(processInfo.get(i).processName); } }
	 */
	public String getAppName(String packname) {
		try {
			pm = this.getPackageManager();
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadLabel(pm).toString();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Drawable getAppIcon(String packname) {
		try {
			ApplicationInfo info = pm.getApplicationInfo(packname, 0);
			return info.loadIcon(pm);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
