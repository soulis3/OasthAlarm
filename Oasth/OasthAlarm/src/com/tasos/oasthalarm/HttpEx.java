package com.tasos.oasthalarm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpEx extends Activity{
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpex);
		tv = (TextView) findViewById(R.id.tvHttp);
		ArivalTimes ar = new ArivalTimes();
		tv.setText(ar.sdata);
	}

}
