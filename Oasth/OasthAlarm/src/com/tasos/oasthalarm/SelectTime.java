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

public class SelectTime extends Activity {
	Spinner spinner1;
	TextView tvST;
	Button btn;
	public static int timeNot;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttime);
		tvST = (TextView) findViewById(R.id.tvST);
		addListenerOnButton();
		tvST.setText("��� ��� ����� "+ShowStops.stopName+" ��� ������� "+ShowLines.lineName+" �� ��������� "
				+ShowRoute.routeName+" ���������� �� : ");
		//addListenerOnSpinnerItemSelection();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		spinner1 = (Spinner) findViewById(R.id.spinnerMinutes);
		btn = (Button) findViewById(R.id.bNext);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				timeNot=spinner1.getSelectedItemPosition()+1;
				Intent in = new Intent("com.tasos.oasthalarm.ALARMNOT");
		    	startActivity(in);
			/*	Toast.makeText(SelectTime.this,
						"OnClickListener : " + 
				                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
				                "\nlepta se Int : "+ String.valueOf(timeNot),
							Toast.LENGTH_SHORT).show();
			*/		  
			}
			
		});
	}

	private void addListenerOnSpinnerItemSelection() {
		// TODO Auto-generated method stub
		spinner1 = (Spinner) findViewById(R.id.spinnerMinutes);
		
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
	}

	

	
	//pos = spinerMin.getSelectedItemPosition();
	//String msg = Integer.toString(pos);
	//Utility.ShowMessageBox(this,"Se tosa lepta : "+ msg);
	
	
}
