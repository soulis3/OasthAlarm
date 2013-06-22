package com.tasos.oasthalarm;

import com.tasos.oasthalarm.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		tvLines.setText(ShowLines.lineName);
		bvasi.setOnClickListener(this);
		bterma.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bPros:
			broute="1";
			routeName="ÙÔ ‘≈—Ã¡";
			//Intent in = new Intent("com.tasos.oasthalarm.SHOWSTOPS");
	    	//startActivity(in);
	    	break;
	    case R.id.bEpistrofi:
	    	broute="2";
	    	routeName="ÙÁÌ ¡÷≈‘«—…¡";
	    	//Intent in2 = new Intent("com.tasos.oasthalarm.SHOWSTOPS");
	    	//startActivity(in2);
	    	break;
	    	}
		Intent in = new Intent("com.tasos.oasthalarm.SHOWSTOPS");
    	startActivity(in);
	}
	
}
