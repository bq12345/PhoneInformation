package com.bq.phoneinformation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class GetappinfoActivity extends Activity {

	protected static final int LOADFINISH = 11;
	private ListView lv_main_app_info;
	private myListViewAdapter adapter;
	private PakageInfoProvider mPakageInfoProvider;
	private List<AppInfo> appInfos;
	private List<AppInfo> userApps;
	private List<AppInfo> sysApps;
	private LinearLayout ll_pb;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOADFINISH:
				initAdapters();
				ll_pb.setVisibility(View.INVISIBLE);
				lv_main_app_info.setAdapter(adapter);
				break;
			}
		};
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.software);
		lv_main_app_info = (ListView) findViewById(R.id.lv_main_app_info);
		mPakageInfoProvider = new PakageInfoProvider(this);
		adapter = new myListViewAdapter();

		ll_pb = (LinearLayout) findViewById(R.id.ll_pb);
		ll_pb.setVisibility(View.VISIBLE);
		loadData();
	}

	private void loadData() {
		new Thread() {
			public void run() {
				appInfos = mPakageInfoProvider.getAppInfo();
				Message msg = Message.obtain();
				msg.what = LOADFINISH;
				handler.sendMessage(msg);
			}
		}.start();
	}

	/*
	 * 把应用程序分为两类去处理，分别是用户程序和系统程序
	 */
	private void initAdapters() {
		userApps = new ArrayList<AppInfo>();
		sysApps = new ArrayList<AppInfo>();
		for (AppInfo appInfo : appInfos) {
			if (appInfo.getIsUserApp()) {
				userApps.add(appInfo);
			} else {
				sysApps.add(appInfo);
			}
		}
	}

	private class myListViewAdapter extends BaseAdapter {
		public int getCount() {
			return appInfos.size() + 2;// 多加了两个标签 一个是用户程序的标签一个是系统程序的标签
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0) {
				TextView tv_userApp = new TextView(getApplicationContext());
				tv_userApp.setText("用户程序(" + userApps.size() + ")");
				tv_userApp.setTextSize(23);
				// tv_userApp.setTextColor(R.color.text_tile_color);
				return tv_userApp;
			} else if (position <= userApps.size()) {
				View view;
				ViewHolder holder;
				if (convertView == null || convertView instanceof TextView) {
					view = View.inflate(getApplicationContext(), R.layout.app_info_item, null);
					holder = new ViewHolder();
					holder.tv_name = (TextView) view.findViewById(R.id.tv_app_info_item_name);
					holder.tv_version = (TextView) view.findViewById(R.id.tv_app_info_item_version);
					holder.iv_icon = (ImageView) view.findViewById(R.id.iv_app_info_item);
					holder.bt = (Button) view.findViewById(R.id.btn_app_info_item);
					view.setTag(holder);
				} else {
					view = convertView;
					holder = (ViewHolder) view.getTag();
				}

				int location = position - 1;
				AppInfo info = userApps.get(location);
				holder.iv_icon.setImageDrawable(info.getDrawable());
				holder.tv_name.setText(info.getAppName());
				holder.tv_version.setText("版本:" + info.getAppVersion());
				holder.bt.setVisibility(View.VISIBLE);
				// packname = info.getPackname();
				holder.bt.setTag(info.getPackageName());
				holder.bt.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						String packname = (String) v.getTag();
						/*
						 * <intent-filter> <action
						 * android:name="android.intent.action.VIEW" /> <action
						 * android:name="android.intent.action.DELETE" />
						 * <category
						 * android:name="android.intent.category.DEFAULT" />
						 * <data android:scheme="package" /> </intent-filter>
						 */

						Intent intent = new Intent();
						intent.setAction("android.intent.action.DELETE");
						intent.addCategory("android.intent.category.DEFAULT");
						intent.setData(Uri.parse("package:" + packname));
						startActivityForResult(intent, 0);
					}
				});
				return view;
			} else if (position == userApps.size() + 1) {
				TextView tv_sysApp = new TextView(getApplicationContext());
				tv_sysApp.setText("系统应用(" + sysApps.size() + ")");
				tv_sysApp.setTextSize(23);
				// tv_sysApp.setTextColor(R.color.text_tile_color);
				return tv_sysApp;
			} else { // 这里注意else和esle if用法区别
				View view;
				ViewHolder holder;
				if (convertView == null || convertView instanceof TextView) {
					view = View.inflate(getApplicationContext(), R.layout.app_info_item, null);
					holder = new ViewHolder();
					holder.tv_name = (TextView) view.findViewById(R.id.tv_app_info_item_name);
					holder.tv_version = (TextView) view.findViewById(R.id.tv_app_info_item_version);
					holder.iv_icon = (ImageView) view.findViewById(R.id.iv_app_info_item);
					holder.bt = (Button) view.findViewById(R.id.btn_app_info_item);
					view.setTag(holder);
				} else {
					view = convertView;
					holder = (ViewHolder) view.getTag();
				}

				int location = position - (userApps.size() + 2);
				AppInfo info = sysApps.get(location);
				holder.iv_icon.setImageDrawable(info.getDrawable());
				holder.tv_name.setText(info.getAppName());
				holder.tv_version.setText("版本:" + info.getAppVersion());
				holder.bt.setVisibility(View.INVISIBLE);
				return view;
			}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		loadData();

	}

	static class ViewHolder {
		TextView tv_name;
		TextView tv_version;
		ImageView iv_icon;
		Button bt;
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