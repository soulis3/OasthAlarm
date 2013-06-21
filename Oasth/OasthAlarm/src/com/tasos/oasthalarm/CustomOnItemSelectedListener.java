package com.tasos.oasthalarm;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
 
public class CustomOnItemSelectedListener implements OnItemSelectedListener {
 
  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
	Toast.makeText(parent.getContext(), 
		"Item : " + parent.getItemAtPosition(pos).toString() +" "+parent.getSelectedItemPosition(),
		Toast.LENGTH_SHORT).show();
  }
  
/* public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
	Toast.makeText(parent.getContext(), 
		"Item : " + parent.getItemAtPosition(pos).toString() +" "+parent.getSelectedItemPosition(),
		Toast.LENGTH_SHORT).show();
  }
 */
  public int getPos(AdapterView<?> parent, View view, int pos,long id){
	  return pos;
  }
  @Override
  public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
  }
 
}
