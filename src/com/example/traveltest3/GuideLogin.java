package com.example.traveltest3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GuideLogin extends Activity{
	
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_login);
		Button login = (Button)findViewById(R.id.guide_login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, GuidePortal.class);
				startActivity(i);
			}
		});
		Button signup = (Button)findViewById(R.id.guide_signup);
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, Signup.class);
				startActivity(i);
			}
		});
	}
}