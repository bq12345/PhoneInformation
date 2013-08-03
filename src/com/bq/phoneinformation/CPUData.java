package com.bq.phoneinformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class CPUData  extends Activity{
	private TextView tv;
	private TextView content;
	@Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.systemdata);
	tv=(TextView) findViewById(R.id.tv);
	content=(TextView) findViewById(R.id.content);
	try {
		execCommand("cat /proc/cpuinfo");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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

public void execCommand(String command) throws IOException {

	Runtime runtime = Runtime.getRuntime();
	Process proc = runtime.exec(command);
	try {
		if (proc.waitFor() != 0) {
			System.err.println("exit value = " + proc.exitValue());
			
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			stringBuffer.append(line+"\n");
		}
		System.out.println(stringBuffer.toString());
		tv.setText("您的手机CPU信息：");
		content.setText(stringBuffer.toString());
	} catch (InterruptedException e) {
		System.err.println(e);
	}
}
}
