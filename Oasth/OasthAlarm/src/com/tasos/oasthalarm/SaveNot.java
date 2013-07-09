package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveNot extends Activity implements OnClickListener{
	TextView tvs;
	EditText etcomment;
	Button ok,cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savenot);
		String text = "��� ��� ����� <font color='red'>"+ShowStops.stopName+"</font>" +
				"\n��� ������� <font color='blue'>"+ShowLines.lineName+"</font>" +
				"\n�� ���������  <font color='green'>"+ShowRoute.routeName+"</font>" +
				"\n���������� �� <font color='yellow'>"+SelectTime.timeNot +" �����(-�) " +"</font>" +
				"\n��� ����� :";
		tvs =(TextView) findViewById(R.id.tvSavedata);
		tvs.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
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
				Toast.makeText(SaveNot.this, "� ���������� "+comment+" ��� ������������!" +
						"���� : "+error, Toast.LENGTH_LONG).show();
			}finally{
				if(didItWork){
					Toast.makeText(SaveNot.this, "� ���������� "+comment+" ������������! ", Toast.LENGTH_LONG).show();
					Intent in = new Intent("com.tasos.oasthalarm.SELECTTIME");
			    	startActivity(in);
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
