package com.bq.phoneinformation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.TextView;

public class NetData  extends Activity{
	private TextView tv;
	private TextView content;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.systemdata);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("\n设备ID：" + tm.getDeviceId());
        sb.append("\n软件版本号：" + tm.getDeviceSoftwareVersion());
        sb.append("\nLine1Number：" + tm.getLine1Number());
        sb.append("\n无线网络国家代码： " + tm.getNetworkCountryIso());
        sb.append("\n无线网络代码：" + tm.getNetworkOperator());
        sb.append("\n无线网络名称：" + tm.getNetworkOperatorName());
        sb.append("\n无线数据网络类型：" + tm.getNetworkType());
        sb.append("\n无线类型 ： " + tm.getPhoneType());
        sb.append("\nSim卡服务商国家代码 = " + tm.getSimCountryIso());
        sb.append("\nSim卡服务商代码： " + tm.getSimOperator());
        sb.append("\nSim卡服务商名称 ：" + tm.getSimOperatorName());
        sb.append("\nSim卡编码 ： " + tm.getSimSerialNumber());
        sb.append("\nSim卡状态 ；" + tm.getSimState());
        sb.append("\n国际移动用户识别码： " + tm.getSubscriberId());
        sb.append("\n语音信箱号码：" + tm.getVoiceMailNumber());
        tv=(TextView) findViewById(R.id.tv);
    	content=(TextView) findViewById(R.id.content);
    	tv.setText("您的手机运营商信息：");
    	content.setText(sb);
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
