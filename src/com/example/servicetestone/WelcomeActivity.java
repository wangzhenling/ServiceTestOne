package com.example.servicetestone;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		Intent intent=new Intent();
		intent.setAction("com.example.servicetestone.service.doService");
		intent.putExtra("postMsg", "");
		startService(intent);
		
		doChange();
	}

	private void doChange(){
		Intent intent=new Intent();
		intent.setClass(WelcomeActivity.this, MainActivity.class);
		startActivity(intent);		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}



