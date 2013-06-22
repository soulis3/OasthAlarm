package com.tasos.oasthalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StopSnooze extends Activity implements OnClickListener{
	TextView tvInfo;
	Button stop,snooze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stopsnooze);
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		stop = (Button)findViewById(R.id.bStop);
		snooze = (Button)findViewById(R.id.bSnooze);
		stop.setOnClickListener(this);
		snooze.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bStop:
			break;
		case R.id.bSnooze:
			break;
		}
	}
	
}
