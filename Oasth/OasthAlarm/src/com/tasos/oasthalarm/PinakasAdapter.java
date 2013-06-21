package com.tasos.oasthalarm;

import java.io.IOException; 


import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
 
public class PinakasAdapter  
{ 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
 
    public PinakasAdapter(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 
 
    public PinakasAdapter createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public PinakasAdapter open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close()  
    { 
        mDbHelper.close(); 
    } 
 
     
     
     public SQLiteDatabase getmDb() {
		return mDb;
	}

	public void setmDb(SQLiteDatabase mDb) {
		this.mDb = mDb;
	}

	public DataBaseHelper getmDbHelper() {
		return mDbHelper;
	}

	public void setmDbHelper(DataBaseHelper mDbHelper) {
		this.mDbHelper = mDbHelper;
	}

	public static String getTag() {
		return TAG;
	}

	public Context getmContext() {
		return mContext;
	}

	
     
     public Cursor getLineNumber() 
     { 
         try 
         { 
             String sql ="SELECT num FROM busline"; 
 
             Cursor mCur = mDb.rawQuery(sql, null); 
             if (mCur!=null) 
             { 
                mCur.moveToNext(); 
             } 
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
             Log.e(TAG, "getLineNumber >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }

 	

	

	

} 

