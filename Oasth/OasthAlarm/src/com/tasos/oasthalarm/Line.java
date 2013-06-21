package com.tasos.oasthalarm;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class Line extends PinakasAdapter{
	
	private String id,num,name_gr;
	
	
	public Line(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public Cursor getLinesData() 
    { 
        try 
        { 
            String sql ="SELECT * FROM busline"; 

            Cursor mCur = super.getmDb().rawQuery(sql, null); 
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getLineData >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName_gr() {
		return name_gr;
	}
	public void setName_gr(String name_gr) {
		this.name_gr = name_gr;
	}
	public Cursor getLineId(String line) 
    { 
        try 
        { 
            String sql ="SELECT id FROM busline WHERE name_gr LIKE ('"+line+"')"; 

            Cursor mCur = super.getmDb().rawQuery(sql, null); 
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getLineName >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
	
}
