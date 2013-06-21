package com.tasos.oasthalarm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ShowLines extends ListActivity {
	
	String [] linesTable;
	
	public static String lineName,lineId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Line mDbHelper = new Line(this);         
		mDbHelper.createDatabase();       
		mDbHelper.open();
		Cursor c = mDbHelper.getLinesData();
		linesTable =new String[c.getCount()];
		
		for (int i=0;i<linesTable.length;i++){
			linesTable[i]=Utility.GetColumnValue(c, "num")+"   "+Utility.GetColumnValue(c, "name_gr");
			c.moveToNext();
		}
		startManagingCursor(c);
		setListAdapter(new ArrayAdapter<String>(ShowLines.this, android.R.layout.simple_list_item_1, linesTable));
		mDbHelper.close();
	}

	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String listline = (String)l.getItemAtPosition(position);
		Pattern pattern1 = Pattern.compile(".{2,3}\\s\\s\\s");
		Matcher	matcher = pattern1.matcher(listline);
		listline = matcher.replaceFirst("");
		lineName = listline;
		Line lineHelper= new Line(this);
		lineHelper.createDatabase();
		lineHelper.open();
		Cursor cL = lineHelper.getLineId(ShowLines.lineName);
		lineId = Utility.GetColumnValue(cL, "id");
		startManagingCursor(cL);
		lineHelper.close();
		Intent in = new Intent("com.tasos.oasthalarm.SHOWROUTE");
    	startActivity(in);
		
	}

}
