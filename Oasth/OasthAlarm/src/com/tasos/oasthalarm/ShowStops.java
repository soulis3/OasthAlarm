package com.tasos.oasthalarm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowStops extends ListActivity{

	String [] stopsTable;
	public static String stopName,stopId,stopOrder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		Stop mDbHelper = new Stop(this);         
		mDbHelper.createDatabase();       
		mDbHelper.open();
		Cursor c = mDbHelper.getStopsData(ShowLines.lineName,ShowRoute.broute);
		stopsTable =new String[c.getCount()];
		
		for (int i=0;i<stopsTable.length;i++){
			stopsTable[i]=Utility.GetColumnValue(c, "name_gr");
			c.moveToNext();
		}
		startManagingCursor(c);
		setListAdapter(new ArrayAdapter<String>(ShowStops.this, android.R.layout.simple_list_item_1, stopsTable));
		mDbHelper.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		stopName =(String)l.getItemAtPosition(position);
		Stop stopHelper= new Stop(this);
		stopHelper.createDatabase();   
		stopHelper.open();
		Cursor cS = stopHelper.getStopId(stopName,ShowLines.lineId,ShowRoute.broute);
		startManagingCursor(cS);
		stopId=Utility.GetColumnValue(cS, "id");
		Cursor cR = stopHelper.getSOrder(ShowLines.lineId,stopId, ShowRoute.broute);
		stopOrder=Utility.GetColumnValue(cR, "sorder");		
		startManagingCursor(cR);
		stopHelper.close();
		Intent in = new Intent("com.tasos.oasthalarm.SELECTTIME");
    	startActivity(in);
	}


}
