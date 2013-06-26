package com.tasos.oasthalarm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener  {
	
	Button bLines,bNotView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bLines = (Button) findViewById(R.id.bCreate);
        bLines.setOnClickListener(this);
        bNotView = (Button) findViewById(R.id.bNotView);
        bNotView.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bCreate:
			
	    	//Utility.ShowMessageBox(this, "Number: "+ name);
	    	Intent in = new Intent("com.tasos.oasthalarm.SHOWLINES");
	    	startActivity(in);
	    	break;
		case R.id.bNotView:
			Intent in1 = new Intent("com.tasos.oasthalarm.CREATEDNOT");
	    	startActivity(in1);
	    	break;
	    }
	}
	
	
}
