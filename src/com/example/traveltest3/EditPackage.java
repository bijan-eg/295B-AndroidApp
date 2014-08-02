package com.example.traveltest3;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EditPackage extends Activity{
	
	final Context context = this;
	final CharSequence[] items={"Hotel","Flight","Bus Ride", "Meuseum", "Hike"};
	String servicesString = "";
	boolean[] itemsChecked = new boolean[items.length];
	ArrayList<String> selectedService = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.edit_package);
		
		Button pckgServicecs = (Button)findViewById(R.id.pkckgServices);
		pckgServicecs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder=new AlertDialog.Builder(context);
		    	builder.setTitle("Pick the package services");
		    	builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						for(int i=0;i<items.length;i++){
							if(itemsChecked[i]){
								selectedService.add(items[i].toString());
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
							selectedService.add(items[which].toString());
						    }
						    else if (selectedService.contains(which))
						    {
						                    // if the item is already selected then remove it
						    selectedService.remove(Integer.valueOf(which));
						    }
					}
				});
		    	builder.show();
			}
		});
		
		Button DoEdit = (Button)findViewById(R.id.save);
		DoEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Edited", Toast.LENGTH_LONG).show();
				
			}
		});
	}
}