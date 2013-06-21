package com.tasos.oasthalarm;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class Stop extends PinakasAdapter {
	
	String id,name_gr;
	
	public Stop(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public Cursor getStopsData(String line,String route) {
		// TODO Auto-generated method stub
		 try 
         { 
			// String l=line.toUpperCase();
			 
             String sql ="SELECT DISTINCT s.name_gr,r.stopid " +
             		"FROM busroute r,busstop s, busline l" +
             		" WHERE s.id=r.stopid AND r.lineid=l.id " +
             		"AND l.name_gr "+
             		"LIKE ('"+line+"')"+
             		"AND r.direction " +
             		"LIKE ('"+route+"')"+
             		"ORDER BY r.sorder"; 
 
             Cursor mCur = super.getmDb().rawQuery(sql, null); 
             if (mCur!=null) 
             { 
                mCur.moveToNext(); 
             } 
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
             Log.e(TAG, "getStopsForLine >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName_gr() {
		return name_gr;
	}
	public void setName_gr(String name_gr) {
		this.name_gr = name_gr;
	}
	public Cursor getStopId(String stop,String lineid,String dir) 
    { 
        try 
        { 
            String sql ="SELECT s.id " +
            		"FROM busroute r,busstop s, busline l " +
            		" WHERE s.id=r.stopid AND r.lineid=l.id"+
            		" AND l.id LIKE ('"+lineid+"')"+
            		" AND r.direction LIKE ('"+dir+"')" +
            		" AND s.name_gr LIKE  ('"+stop+"')"; 

            Cursor mCur = super.getmDb().rawQuery(sql, null); 
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getStopId >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
	public Cursor getSOrder(String lineid,String stopid,String sorder) 
    { 
        try 
        { 
        	 String sql ="SELECT DISTINCT sorder FROM busroute r " +
        	 		"WHERE lineid LIKE ('"+lineid+
        	 		"') AND  stopid LIKE ('"+stopid+
        	 		"') AND direction LIKE ('" + sorder+"')";

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
