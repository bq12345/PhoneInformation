package com.bq.phoneinformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class BaseData extends Activity {
	private TextView tv;
	private TextView content;
	StringBuilder builder=new StringBuilder();
	private String result;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.systemdata);
    	tv=(TextView) findViewById(R.id.tv);
    	content=(TextView) findViewById(R.id.content);
    	result=getSystemProperty();
    	tv.setText("您的手机基本信息：");
    	content.setText(result);
        
	}
	public String getSystemProperty(){
		init("Url","java.vendor.url");
		init("java.class.version","java.class.version");
		init("系统版本","os.version");
		init("vendor属性","java.vendor");
		init("用户路径","user.dir");
		init("系统名称","os.name");
		init("系统架构","os.arch");
		init("用户名","user.name");
		init("java.home","java.home");
		init("java.version","java.version");
				
		return builder.toString();
	}
	private String init(String desc,String pro){
		if(builder==null){
			builder=new StringBuilder();
		}
		builder.append(desc).append(":");
		builder.append(System.getProperty(pro)).append("\n");
		return builder.toString();
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
