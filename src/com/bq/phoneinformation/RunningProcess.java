package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class RunningProcess extends Activity {
	ActivityManager activityManager;
	List<RunningAppProcessInfo> runningAppProcessInfos;
	ArrayList<RunningAppProcessInfo> userRunningAppProcessInfos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo outInfo =    new ActivityManager.MemoryInfo();
		am.getMemoryInfo(outInfo);
		long availMemorySize = outInfo.availMem;
		String strMemorySize = availMemorySize/1024/1024 + "MB";
		Toast.makeText(getApplicationContext(), "剩余内存："+strMemorySize, Toast.LENGTH_SHORT).show();
		initData();
		killAll();
		finish();
	}

	protected void initData() {
		activityManager =(ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
		runningAppProcessInfos = activityManager.getRunningAppProcesses(); // 获取所有的进程
		userRunningAppProcessInfos = new ArrayList<ActivityManager.RunningAppProcessInfo>();// 获取用户的进程
		for (int i = 0; i < runningAppProcessInfos.size(); i++) {
			if ("system".equals(runningAppProcessInfos.get(i).processName)
					|| "android.process.media".equals(runningAppProcessInfos
							.get(i).processName)
					|| "android.process.acore".equals(runningAppProcessInfos
							.get(i).processName)) {
				continue;
			}
			userRunningAppProcessInfos.add(runningAppProcessInfos.get(i));
		}
	}
	private void killAll() {
	    for (int i = 0 ; i< userRunningAppProcessInfos.size();i++){
	    	
	        activityManager.restartPackage(userRunningAppProcessInfos.get(i).processName);
	        
	    }
	    Toast.makeText(getApplicationContext(), "为您关闭了"+userRunningAppProcessInfos.size()+"个后台程序", Toast.LENGTH_SHORT).show();
	    new Thread(){
	        @Override
	        public void run() {
	            initData();
	        }
	    }.start();
	}

}
