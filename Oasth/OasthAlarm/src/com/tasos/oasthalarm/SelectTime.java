package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelectTime extends Activity implements OnClickListener{
	EditText mins;
	TextView tvST;
	Button next,save,plus,minus;
	public static int timeNot=1;
	int counter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttime);
		tvST = (TextView) findViewById(R.id.tvST);		
		String text = "Για την στάση <font color='red'>"+ShowStops.stopName+"</font>" +
				"\nτης γραμμής <font color='blue'>"+ShowLines.lineName+"</font>" +
				"\nμε προορισμό  <font color='green'>"+ShowRoute.routeName+"</font>";
		tvST.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
		//addListenerOnSpinnerItemSelection();
		//spinner1 = (Spinner) findViewById(R.id.spinnerMinutes);
		mins=(EditText) findViewById(R.id.etMins);
		plus = (Button) findViewById(R.id.bPlus);
		minus = (Button) findViewById(R.id.bMinus);
		plus.setOnClickListener(this);
		minus.setOnClickListener(this);
		next = (Button) findViewById(R.id.bNext);
		save = (Button) findViewById(R.id.bSave);
		next.setOnClickListener(this);
		save.setOnClickListener(this);
		counter = Integer.parseInt( mins.getText().toString());
		//counter = 1;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bPlus :
			counter++;			
			mins.setText( String.valueOf(counter));
			break;
		case R.id.bMinus :
			if(counter>1){
			counter--;
			mins.setText( String.valueOf(counter));
			break;
			}
			else
				break;
		case R.id.bNext :
			timeNot=Integer.parseInt( mins.getText().toString());
			//spinner1.getSelectedItemPosition()+1;
			Intent in = new Intent("com.tasos.oasthalarm.ALARMNOT");
	    	startActivity(in);
			break;
		case R.id.bSave :
			timeNot=Integer.parseInt( mins.getText().toString());
			Intent in1 = new Intent("com.tasos.oasthalarm.SAVENOT");
	    	startActivity(in1);
			break;
		}
	}

}
