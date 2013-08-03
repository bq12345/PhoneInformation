package com.bq.phoneinformation;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class About extends Activity{
	private WebView webview; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
	webview = (WebView) findViewById(R.id.webview); 
    //设置WebView属性，能够执行Javascript脚本 
    webview.getSettings().setJavaScriptEnabled(true); 
    //加载需要显示的网页 
    webview.loadUrl("file:///android_asset/about.htm"); 
	}

}
