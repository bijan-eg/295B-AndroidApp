package com.example.traveltest3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	final Context context = this;
	Button guide,agent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_page);
		guide = (Button)findViewById(R.id.guide);
		agent = (Button)findViewById(R.id.agent);
		guide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i1 = new Intent(context, GuideLogin.class);
				startActivity(i1);
			}
		});
		agent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i2 = new Intent(context, AgentLogin.class);
				startActivity(i2);
			}
		});
	}
}