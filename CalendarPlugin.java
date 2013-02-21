package com.tenforwardconsulting.phonegap.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

public class CalendarPlugin extends CordovaPlugin {
	public static final String NATIVE_ACTION_STRING="addToCalendar"; 
	public static final String SUCCESS_PARAMETER="success"; 
	public static final Integer RESULT_CODE_CREATE=0;
	private CallbackContext callback;
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if (NATIVE_ACTION_STRING.equals(action)) { 
				JSONObject arg_object = args.getJSONObject(0);
		        callback = callbackContext;
		        Intent calIntent = new Intent(Intent.ACTION_EDIT)
		        	.setType("vnd.android.cursor.item/event")
		            .putExtra("beginTime", arg_object.getLong("startTimeMillis"))
			        .putExtra("endTime", arg_object.getLong("endTimeMillis"))
			        .putExtra("title", arg_object.getString("title"))
			        .putExtra("description", arg_object.getString("description"))
			        .putExtra("eventLocation", arg_object.getString("eventLocation"));
		        
		        this.cordova.startActivityForResult(this, calIntent, RESULT_CODE_CREATE);
		        return true;
			}
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return false;
		}     
	
	return false;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == RESULT_CODE_CREATE) {
			if(resultCode == Activity.RESULT_OK) {
				callback.success();
			} else {
				callback.error("Activity result code " + resultCode);
			}
		} else {
			callback.error("Activity request code " + requestCode + " does not exist.");
		}
	}
}