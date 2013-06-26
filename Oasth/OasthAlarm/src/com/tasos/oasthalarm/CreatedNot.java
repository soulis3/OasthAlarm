package com.tasos.oasthalarm;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CreatedNot extends ListActivity{
	String [] comments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
		
		NotificationsDatabase createdNot = new NotificationsDatabase(CreatedNot.this);
		createdNot.open();
		
		
		Cursor c = createdNot.getNotData();
		comments =new String[c.getCount()];
		
		for (int i=0;i<comments.length;i++){
			comments[i]=Utility.GetColumnValue(c, "comment");
			c.moveToNext();
		}
		startManagingCursor(c);
		setListAdapter(new ArrayAdapter<String>(CreatedNot.this, android.R.layout.simple_list_item_1, comments));
		createdNot.close();
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
		public static final String KEY_ROWID = "_id";
	public static final String KEY_LINENAME = "linename";
	public static final String KEY_LINEID = "lineid";
	public static final String KEY_STOPNAME = "stopname";
	public static final String KEY_STOPID = "stopid";
	public static final String KEY_BROUTE = "broute";
	public static final String KEY_ROUTENAME = "routename";
	public static final String KEY_SORDER = "sorder";
	public static final String KEY_TIMENOT = "timenot";
	public static final String KEY_COMMENT = "comment";
		*/
	//}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
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

}
