package com.example.servicetestone;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String actionTransfer="com.example.servicetestone.transfer";
	private static final String actionTransferUpdate="com.example.servicetestone.transferUpdate";
	
	private static final String actionControl="com.example.servicetestone.service.doService";
	
	Button btnClick1;
	TextView tvShowMsg;
	
	MainActivityReceicer mar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvShowMsg=(TextView) this.findViewById(R.id.tvShow);
		
		btnClick1=(Button) this.findViewById(R.id.btnClick1);
		btnClick1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				/*
				Intent ActivitySendService=new Intent(actionTransfer);
				ActivitySendService.putExtra("showMsg", "江西省南昌市");
				sendBroadcast(ActivitySendService);
				*/
				
				Intent intent =new Intent();
				intent.setAction(actionControl);
				intent.putExtra("postMsg", "瑶湖");
				startService(intent);
			}
		});
		
	    mar=new  MainActivityReceicer();
		IntentFilter ifSetAction=new IntentFilter();
		ifSetAction.addAction(actionTransferUpdate);
		registerReceiver(mar,ifSetAction);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class MainActivityReceicer extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			String act=intent.getAction();
			if(act.equals(actionTransferUpdate)){
				tvShowMsg.setText(intent.getStringExtra("getMsg"));
			}
		}
		
	}

}
