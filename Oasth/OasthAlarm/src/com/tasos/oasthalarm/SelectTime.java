package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelectTime extends Activity implements OnClickListener{
	Spinner spinner1;
	TextView tvST;
	Button next,save;
	public static int timeNot=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttime);
		tvST = (TextView) findViewById(R.id.tvST);
		//addListenerOnButton();
		tvST.setText("Για την στάση "+ShowStops.stopName+" της γραμμής "+ShowLines.lineName+" με προορισμό "
				+ShowRoute.routeName+" ειδοποίηση σε : ");
		//addListenerOnSpinnerItemSelection();
		spinner1 = (Spinner) findViewById(R.id.spinnerMinutes);
		next = (Button) findViewById(R.id.bNext);
		save = (Button) findViewById(R.id.bSave);
		next.setOnClickListener(this);
		save.setOnClickListener(this);
	}


	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		spinner1 = (Spinner) findViewById(R.id.spinnerMinutes);
		
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bNext :
			timeNot=spinner1.getSelectedItemPosition()+1;
			Intent in = new Intent("com.tasos.oasthalarm.ALARMNOT");
	    	startActivity(in);
			break;
		case R.id.bSave :
			timeNot=spinner1.getSelectedItemPosition()+1;
			Intent in1 = new Intent("com.tasos.oasthalarm.SAVENOT");
	    	startActivity(in1);
			break;
		}
	}

	

	
	//pos = spinerMin.getSelectedItemPosition();
	//String msg = Integer.toString(pos);
	//Utility.ShowMessageBox(this,"Se tosa lepta : "+ msg);
	
	
}
