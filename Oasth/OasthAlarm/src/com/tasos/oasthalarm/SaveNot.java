package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SaveNot extends Activity implements OnClickListener{
	TextView tvs;
	EditText etcomment;
	Button ok,cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savenot);
		tvs =(TextView) findViewById(R.id.tvSavedata);
		etcomment = (EditText) findViewById(R.id.etComment);
		ok = (Button) findViewById(R.id.bsOK);
		cancel = (Button) findViewById(R.id.bsCancel);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bsOK :
			//eisagvgi eidopoihshs sthn vasi
			boolean didItWork=true;
			String comment = etcomment.getText().toString();
			try{
				NotificationsDatabase entry = new NotificationsDatabase(SaveNot.this);
				entry.open();
				entry.createEntry(ShowLines.lineName, ShowLines.lineId, ShowStops.stopName, ShowStops.stopId,
						ShowRoute.routeName, ShowRoute.broute, ShowStops.stopOrder, SelectTime.timeNot, comment);
				entry.close();
			}catch(Exception e){
				didItWork=false;
				String error=e.toString();
				Dialog d=new Dialog(this);
				d.setTitle("Dang it");
				TextView tv= new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{
				if(didItWork){
					Dialog d=new Dialog(this);
					d.setTitle("Heck yea");
					TextView tv= new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bsCancel :
			//epistrofi sth proigoumeni activity
			Intent in = new Intent("com.tasos.oasthalarm.SELECTTIME");
	    	startActivity(in);
			break;
		}
	}

}
