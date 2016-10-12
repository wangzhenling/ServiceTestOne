package com.example.servicetestone.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

	private static final String actionTransfer="com.example.servicetestone.transfer";
	private static final String actionTransferUpdate="com.example.servicetestone.transferUpdate";
	
	private static final String actionControl="com.example.servicetestone.control";
	
	private testMusicReceiver tMR;
	
	private String transferMsg="";
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		tMR = new testMusicReceiver();
		IntentFilter ifSetAction=new IntentFilter();
		ifSetAction.addAction(actionTransfer);
		registerReceiver(tMR,ifSetAction);
		
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		
		String getStr="";
		getStr=intent.getStringExtra("postMsg");
		//intent.getAction().equals(actionControl)			
		System.out.println(getStr);			
		
		
		Intent postIntent=new Intent();
		postIntent.setAction(actionTransferUpdate);
		postIntent.putExtra("getMsg", intent.getStringExtra("postMsg"));
		sendBroadcast(postIntent);
		
		/*
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true){
					try {
						Thread.sleep(300);
						Log.i("", "@@@@@:"+transferMsg);
						if(!transferMsg.equals("")){
							
							Intent ServiceSendActivity=new Intent(actionTransferUpdate);
							ServiceSendActivity.putExtra("getMsg", transferMsg);
							sendBroadcast(ServiceSendActivity);
							
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					super.run();
				}
			}
			
		}.start();
		*/
		
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class testMusicReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			System.out.println("------service端接收------");
			
			String act=intent.getAction();
		
			if(act.equals(actionTransfer)){
				Log.i("","service接收器接收的数据为："+intent.getStringExtra("showMsg"));
				transferMsg=intent.getStringExtra("showMsg");
			}else{
				System.out.println("+++++++++");				
			}
			
		}
		
	}

}
