package com.example.traveltest3;

import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class AgentPortal extends Activity{
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab created = actionBar.newTab().setText("Created");
		ActionBar.Tab reserved = actionBar.newTab().setText("Reserved");
		ActionBar.Tab published = actionBar.newTab().setText("Published");
		Fragment Fcreated = new createdTab();
		Fragment Freserved = new reservedTab();
		Fragment Fpublished = new publishedTab();
		created.setTabListener(new MyTabListener(Fcreated));
		reserved.setTabListener(new MyTabListener(Freserved));
		published.setTabListener(new MyTabListener(Fpublished));
		actionBar.addTab(created);
		actionBar.addTab(reserved);
		actionBar.addTab(published);
	};
	
//	private class StableArrayAdapter extends ArrayAdapter<String> {
//
//	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//	    public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
//	      super(context, textViewResourceId, objects);
//	      for (int i = 0; i < objects.size(); ++i) {
//	        mIdMap.put(objects.get(i), i);
//	      }
//	    }
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected class MyTabListener implements ActionBar.TabListener{
		private Fragment fragment;
		
		public MyTabListener(Fragment fragment){
			this.fragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.add(R.id.fragment_place, fragment, null);			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);
			
		} 
	}
}