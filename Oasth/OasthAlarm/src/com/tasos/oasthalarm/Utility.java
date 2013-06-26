package com.tasos.oasthalarm;

import android.content.Context;
import android.database.Cursor;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Utility {

 	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}
 	
 	public static int GetColumnIntValue(Cursor cur, String ColumnName) {
		try {
			return cur.getInt(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return 0;
		}
	}
	
	public static void ShowMessageBox(Context cont, String msg) {
		Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
		// toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static int getLCount(Cursor cur, String ColumnName) {
		// TODO Auto-generated method stub
		
		try {
			return cur.getCount();
		} catch (Exception ex) {
			return 0;
		}
		
	}

	public static int getSCount(Cursor cur) {
		// TODO Auto-generated method stub
		try {
			return cur.getCount();
		} catch (Exception ex) {
			return 0;
		}
	}
}
