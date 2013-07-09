package com.tasos.oasthalarm;

import com.tasos.oasthalarm.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowRoute extends Activity implements OnClickListener{
	
	Button bterma,bvasi;
	TextView tvLines;
	public static String broute,routeName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);
		bterma=(Button) findViewById(R.id.bPros);
		bvasi=(Button) findViewById(R.id.bEpistrofi);
		tvLines = (TextView) findViewById(R.id.tvLine);
		String text = "<font color='green'>"+ShowLines.lineName+"</font>";
		tvLines.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
		bvasi.setOnClickListener(this);
		bterma.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bPros:
			broute="1";
			routeName="�� �����";
			
	    	break;
	    case R.id.bEpistrofi:
	    	broute="2";
	    	routeName="��� ��������";
	    	
	    	break;
	    	}
		Intent in = new Intent("com.tasos.oasthalarm.SHOWSTOPS");
    	startActivity(in);
	}
	
}
