package com.bq.phoneinformation;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Send extends Activity implements OnClickListener {
	 private EditText content;
	 public void onCreate(Bundle savedInstanceState) { 
	        super.onCreate(savedInstanceState); 
	        setContentView(R.layout.send);
	        Button sendBtn=(Button) findViewById(R.id.send_btn); 
	        //注册事件 
	        sendBtn.setOnClickListener(this); 
	        content=(EditText) findViewById(R.id.send_data);
	    } 
	    public void onClick(View v) { 
	        //信息管理对象 
	        SmsManager smsManager=SmsManager.getDefault(); 
	        //意图      后三个参数所代表的     请求码      普通的意图       状态 
	        PendingIntent intent=PendingIntent.getBroadcast(Send.this, 0, new Intent(), 0); 
	        //发送信息 
	        smsManager.sendTextMessage("18740414439", null, content.getText().toString(), 
	                intent, null); 
	        //提示信息发送成功 
	        Toast.makeText(Send.this, "信息发送成功，感谢您的参与", Toast.LENGTH_LONG).show(); 
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
