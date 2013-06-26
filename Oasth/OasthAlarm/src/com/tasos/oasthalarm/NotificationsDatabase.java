package com.tasos.oasthalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotificationsDatabase {
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
	
	private static final String NOTDATABASE_NAME = "Notdb";
	private static final String NOTDATABASE_TABLE = "notTable";
	private static final int NOTDATABASE_VERSION = 1;
	
	private NotDbHelper notHelper;
	private final Context notContext;
	private SQLiteDatabase notDatabase;
	
	private static class NotDbHelper extends SQLiteOpenHelper{

		public NotDbHelper(Context context) {
			super(context, NOTDATABASE_NAME, null, NOTDATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + NOTDATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_LINENAME + " TEXT NOT NULL, " +
					KEY_LINEID + " TEXT NOT NULL, " +
					KEY_STOPNAME + " TEXT NOT NULL, " +
					KEY_STOPID + " TEXT NOT NULL, " +
					KEY_ROUTENAME + " TEXT NOT NULL, " +
					KEY_BROUTE + " TEXT NOT NULL, " +
					KEY_SORDER + " TEXT NOT NULL, " +
					KEY_TIMENOT + " INT NOT NULL, " +
					KEY_COMMENT + " TEXT NOT NULL);"
			);	
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + NOTDATABASE_TABLE);
			onCreate(db);
		}
		
	}
	public NotificationsDatabase(Context c){
		notContext=c;
	}
	public NotificationsDatabase open() throws SQLException{
		notHelper = new NotDbHelper(notContext);
		notDatabase = notHelper.getWritableDatabase();
		return this;
	}
	 public void close(){
		 notHelper.close();
	 }

	public long createEntry(String linename, String lineid,String stopname, String stopid,
			String routename, String broute,String sorder,int timenot, String comment) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_LINENAME, linename);
		cv.put(KEY_LINEID, lineid);
		cv.put(KEY_STOPNAME, stopname);
		cv.put(KEY_STOPID, stopid);
		cv.put(KEY_ROUTENAME, routename);
		cv.put(KEY_BROUTE, broute);
		cv.put(KEY_SORDER, sorder);
		cv.put(KEY_TIMENOT, timenot);
		cv.put(KEY_COMMENT, comment);
		return notDatabase.insert(NOTDATABASE_TABLE, null, cv);
	}
	public Cursor getNotData() 
    { 
        try 
        { 
            String sql ="SELECT * FROM "+ NOTDATABASE_TABLE; 

            Cursor mCur = notDatabase.rawQuery(sql, null); 
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e("error", "getLineData >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }

}
