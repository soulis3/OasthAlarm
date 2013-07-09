package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CreatedNot extends Activity {
	String [] comments;
	ListView lv;
	Long dlRow;
	AlertDialog.Builder dlgAlert;
	TextView tvsn ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cretednot);
		tvsn = (TextView) findViewById(R.id.tvSavedNots);
		lv = (ListView) findViewById(R.id.listView1);
		dlgAlert  = new AlertDialog.Builder(this);
		NotificationsDatabase createdNot = new NotificationsDatabase(CreatedNot.this);
		createdNot.open();
		Cursor c = createdNot.getNotData();
		if(c.getCount()>0){
		comments =new String[c.getCount()];
		tvsn.setText("Υπάρχουν " +comments.length+ " αποθηκευμένες ειδοποιήσεις. Για να διαγράψετε μία ειδοποίηση απλά πατήστε παρατεταμένα πάνω της");
		for (int i=0;i<comments.length;i++){
			comments[i]=Utility.GetColumnValue(c, "comment");
			c.moveToNext();
			}
		//startManagingCursor(c);
	
		//lv = this.getListView();
		ListAdapter adapter = new ArrayAdapter<String>(CreatedNot.this, android.R.layout.simple_list_item_1, comments);
		lv.setAdapter(adapter);
		lv.setBackgroundColor(Color.BLACK);
		
		//Toast.makeText(this, "ειδοποιήσεις: "+c.getCount(), Toast.LENGTH_LONG).show();
		}
		else {
			tvsn.setText("Δεν ύπαρχουν αποθηκευμένες ειδοποιήσεις.");
		}
		lv.setLongClickable(true);
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v,
					int pos, long id) {
				// TODO Auto-generated method stub
				final String dlRow =  (String)lv.getItemAtPosition(pos);//Long.parseLong(listComment);
				dlgAlert.setMessage("Είστε σίγουρος οτι θέλετε να διαγράψετε την ειδοποίηση με όνομα : "+dlRow);
				dlgAlert.setTitle("OasthAlarm");
				dlgAlert.setPositiveButton("Ok",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					          //dismiss the dialog  
					        	NotificationsDatabase createdNot = new NotificationsDatabase(CreatedNot.this);
								createdNot.open();
								createdNot.deleteEntry(dlRow);
								
								Toast.makeText(CreatedNot.this, "Η ειδοποιήση "+dlRow+" διαγράφηκε! ", Toast.LENGTH_LONG).show();
								//Cursor c = createdNot.getNotData();
								//Toast.makeText(CreatedNot.this, "Η ειδοποιήση "+c.getCount(), Toast.LENGTH_LONG).show();
								createdNot.close();
								onResume();
					        }
					    });
				dlgAlert.setNegativeButton("’κυρο",
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int which) {
					          //dismiss the dialog  
					        	dialog.dismiss();
					        }
					    });
				dlgAlert.setCancelable(true);
				dlgAlert.create().show();
				
				return true;
			}
			
		});
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> l, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				//super.onListItemClick(l, v, position, id);
				
				dlRow =  l.getItemIdAtPosition(position);
				NotificationsDatabase createdNot = new NotificationsDatabase(CreatedNot.this);
				createdNot.open();	
				Cursor c = createdNot.getNotData();
				ShowLines.lineName = Utility.GetColumnValue(c, "linename");
				ShowLines.lineId = Utility.GetColumnValue(c, "lineid");
				ShowStops.stopName = Utility.GetColumnValue(c, "stopname");
				ShowStops.stopId = Utility.GetColumnValue(c, "stopid");
				ShowStops.stopOrder=Utility.GetColumnValue(c, "sorder");
				ShowRoute.routeName = Utility.GetColumnValue(c, "routename");
				ShowRoute.broute = Utility.GetColumnValue(c, "broute");
				SelectTime.timeNot=Utility.GetColumnIntValue(c, "timenot");
				Intent in = new Intent("com.tasos.oasthalarm.ALARMNOT");
				startManagingCursor(c);
				createdNot.close();
		    	startActivity(in);
			
		}
			
		});
		startManagingCursor(c);
		createdNot.close();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.onCreate(null);
	}
	
	
	
	/*	comments=createdNot.getNotNameComments();
		if(comments!=null){
			
			
			setListAdapter(new ArrayAdapter<String>(CreatedNot.this, android.R.layout.simple_list_item_1, comments));
			
		}
		else{
			dlgAlert.setMessage("Δεν υπάρχει αποθηκευμένη ειδοποίηση");
			dlgAlert.setTitle("App Title");
			dlgAlert.setPositiveButton("Ok",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				          //dismiss the dialog  
				        	finish();
				        }
				    });
			dlgAlert.setCancelable(true);
			dlgAlert.create().show();
		}
		//startManagingCursor(c);
		createdNot.close();
*/
}
