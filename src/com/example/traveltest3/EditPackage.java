package com.example.traveltest3;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class EditPackage extends Activity implements CallBackListener{
	
	final Context context = this;
	final CharSequence[] items={"Hotel","Flight","Bus Ride", "Meuseum", "Hike"};
	boolean[] itemsChecked = new boolean[items.length];
	ArrayList<String> selectedServices = new ArrayList<String>();
	public String token = "";
	EditText pkgName, pkgDesc;
	DatePicker pkgSDate, pkgEDate;
	Button editPackage, editpkckgServices;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.edit_package);		
		editPackage = (Button)findViewById(R.id.save);
		editpkckgServices = (Button)findViewById(R.id.pkckgServices);
		pkgName = (EditText)findViewById(R.id.editText1);
		pkgDesc = (EditText)findViewById(R.id.editText2);
		pkgSDate = (DatePicker)findViewById(R.id.datePicker1);
		pkgEDate = (DatePicker)findViewById(R.id.datePicker2);
		
		editPackage.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				editPackage();
				Intent i = new Intent(context.getApplicationContext(), AgentPortal.class);
				startActivity(i);
			}
		});

		editpkckgServices.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder=new AlertDialog.Builder(context);
		    	builder.setTitle("Pick the package services");
		    	builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						for(int i=0;i<items.length;i++){
							if(itemsChecked[i]){
								selectedServices.add(items[i].toString());
							}
						}
					}
				});
		    	
		    	builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//							itemsChecked[which]=isChecked;
							
							if(isChecked)
						    {
						      // If user select a item then add it in selected items
								selectedServices.add(items[which].toString());
						    }
						    else if (selectedServices.contains(which))
						    {
						                    // if the item is already selected then remove it
						    	selectedServices.remove(Integer.valueOf(which));
						    }
					}
				});
		    	builder.show();
			}
		});
	}

	protected void editPackage() {
		TokenRequestTask tokenTask = new TokenRequestTask();
		tokenTask.setListener(this);
		tokenTask.execute("http://blooming-dusk-7345.herokuapp.com/api-token-auth/");
//		Toast.makeText(context.getApplicationContext(),
//  		        getIntent().getStringExtra("pkgID"), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void tokenCallback(String result) {
		try {
			JSONObject obj = new JSONObject(result);
			token = obj.getString("token");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Log.e("tttttttttttttttttttttttttttt", token);
			editPackageRequestTask editTask = new editPackageRequestTask();
			editTask.execute("http://blooming-dusk-7345.herokuapp.com/agentapp/packages/"+getIntent().getStringExtra("pkgID")+"/",
					token, pkgName.getText().toString(), pkgDesc.getText().toString(), Integer.toString(pkgSDate.getMonth()), Integer.toString(pkgSDate.getDayOfMonth()), Integer.toString(pkgSDate.getYear()), Integer.toString(pkgEDate.getMonth()), Integer.toString(pkgEDate.getDayOfMonth()), Integer.toString(pkgEDate.getYear()));
			Toast.makeText(context.getApplicationContext(),
		  		        "Package Edited!", Toast.LENGTH_SHORT).show();
	}
}
//intent.getStringExtra("pkgID")