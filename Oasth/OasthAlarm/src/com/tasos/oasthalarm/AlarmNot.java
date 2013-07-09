package com.tasos.oasthalarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmNot extends Activity implements OnClickListener{
	final static private long ONE_SECOND = 1000;
	final static private long  ONE_MINUTE = ONE_SECOND * 60;
	
	Button confirm,cancel;
	
	PendingIntent pi;
	BroadcastReceiver br;
	AlarmManager am;
	NotificationManager nm;
	static final int uniqueID = 1394885;
	int exactMin;
	int notMin;
	//MediaPlayer ourSong;
	Ringtone ringtone;
	boolean NotExists;
	boolean isRinging;
	//int table[]  = {2,5,7,10,12};
	ArivalTimes ar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		confirm= (Button) findViewById(R.id.bConfirm);
		cancel= (Button) findViewById(R.id.bCancel);
		confirm.setOnClickListener(this);
		cancel.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//nm.cancel(uniqueID);
		//NotExists=false;
		isRinging=false;
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
		TextView tvAr = (TextView) findViewById(R.id.tvArivalTimes);
		if(isOnline()){
		ar=new ArivalTimes();
		String text = "Για την στάση <font color='red'>"+ShowStops.stopName+"</font>" +
				"\nτης γραμμής <font color='blue'>"+ShowLines.lineName+"</font>" +
				"\nμε προορισμό  <font color='green'>"+ShowRoute.routeName+"</font>" + 
				System.getProperty("line.separator")+"’φιξη Οχήματος σε : "+
				System.getProperty("line.separator")+
				"<font color='yellow'>"+ar.sdata+"</font>";
		tvAr.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);;
		
		//elegxos gia to an einai o pinakas kenos -den yparxei oxima
				//if(table==null){
				if(ar.arTimes==null){	

					dlgAlert.setMessage("Δεν υπάρχουν οχήματα αυτή τη στιγμή, δοκιμάστε αργότερα");
					dlgAlert.setTitle("OasthAlarm");
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
				else{
					//elegxos gia to an o xronos pou epilextike einai megaliteros apo ton monadiko xrono afiksis
					//if(table[0]<=SelectTime.timeNot && table.length==1){
					if(ar.arTimes[0]<=SelectTime.timeNot && ar.arTimes.length==1){
						//dlgAlert.setMessage("Επειλέξτε μεγαλύτερο χρόνο ειδοποίησης των "+table[0]+" λεπτών");
						dlgAlert.setMessage("Επειλέξτε μεγαλύτερο χρόνο ειδοποίησης των "+ar.arTimes[0]+" λεπτών");
						dlgAlert.setTitle("OasthAlarm");
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
					//elegxos gia to an o xronos pou epilextike ksepernaei to megalitero xrono afiksis
					//if(table[table.length-1]<=SelectTime.timeNot){
					if(ar.arTimes[ar.arTimes.length-1]<=SelectTime.timeNot){
						//dlgAlert.setMessage("Επειλέξτε μικρότερο χρόνο ειδοποίησης των "+table[table.length-1]+" λεπτών");
						dlgAlert.setMessage("Επειλέξτε μικρότερο χρόνο ειδοποίησης των "+ar.arTimes[ar.arTimes.length-1]+" λεπτών");
						dlgAlert.setTitle("OasthAlarm");
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
					
				}
		}
		else {
			dlgAlert.setMessage("Θα πρέπει να υπάρχει μια ενεργή σύνδεση δεδομένων. Μετάβαση στις ρυθμίσεις δικτύου τώρα ;");
			dlgAlert.setTitle("Απαιτείται σύνδεση στο Internet");
			dlgAlert.setPositiveButton("Ok",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				          //dismiss the dialog 
				        	finish();
				        	startActivityForResult(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS), 0);
				        }
				    });
			dlgAlert.setCancelable(true);
			dlgAlert.setNegativeButton("’κυρο",
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				          //dismiss the dialog  
				        	finish();
				        }
				    });
			dlgAlert.create().show();
			
		}
	
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bConfirm :
			if(NotExists){
				unregisterReceiver(br);				
				nm.cancel(uniqueID);
				NotExists=false;
				if(isRinging){
				    	//ourSong.release();
				    	ringtone.stop();
				    }
				Intent intent = getIntent();
				finish();
				startActivity(intent);
				
				//Toast.makeText(this, "Η ειδοποιήση esvise ", Toast.LENGTH_LONG).show();
			}
			else{
				createAlarm();
				confirm.setText("Αναβολή");
				//Toast.makeText(this, "Η ειδοποιήση καταχωρήθηκε ", Toast.LENGTH_LONG).show();
			}
			
		
			break;
		case R.id.bCancel:
			if(NotExists){
				nm.cancel(uniqueID);
				am.cancel(pi);
			    unregisterReceiver(br);
			    if(isRinging){
			    	//ourSong.release();
			    	ringtone.stop();
			    	finish();
			    }
			    else
			    	finish();
			}else{
				nm.cancel(uniqueID);				
				finish();
			}
			confirm.setText("Επιβεβαίωση");
			break;
		}
	}
	private void createAlarm() {
		// TODO Auto-generated method stub
		createNotification();
		br = new BroadcastReceiver(){

			@Override
			public void onReceive(Context c, Intent i) {
				// TODO Auto-generated method stub
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
				ringtone.play();
				
				/*ourSong = MediaPlayer.create(AlarmNot.this, R.raw.splashsound);
				ourSong.start();
				*/
				isRinging=true;
				
			}
			
		};
		registerReceiver(br, new IntentFilter("com.tasos.oasthalarm") );
        pi = PendingIntent.getBroadcast( this, 0, new Intent("com.tasos.oasthalarm"), 0 );
        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
       
       //createNotification();
		
		
		//exactMin = getExactMin(table);
		exactMin = getExactMin(ar.arTimes);
		notMin=exactMin-SelectTime.timeNot;
		am.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + (notMin * ONE_MINUTE), pi );
	}

	
	private int getExactMin(int[] arTimes) {
		// TODO Auto-generated method stub
		int i=-1;
		do{
			i++;
		}while(SelectTime.timeNot>=arTimes[i]);
		return arTimes[i];
	}
	private void createNotification() {
		// TODO Auto-generated method stub
			Intent notifyIntent = new Intent(this,AlarmNot.class);
			notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent pi = PendingIntent.getActivity(this, 0, notifyIntent, 0);
	        String body = "Έχeτε δημιουργήσει μια ειδοποίηση για την γραμμή "+ShowLines.lineName;
			String title= "Ειδοποίηση OasthAlarm";
			Notification n = new Notification (R.drawable.ic_launcher,body,System.currentTimeMillis());
			n.setLatestEventInfo(this, title, body , pi);
			n.defaults = Notification.DEFAULT_ALL;
			NotExists=true;
			nm.notify(uniqueID,n);
			//finish();
	}
	private boolean isOnline() {
		// TODO Auto-generated method stub
		
		    ConnectivityManager cm =
		        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
		        return true;
		    }
		    return false;
		
	}

	
}
