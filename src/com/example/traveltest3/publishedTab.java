package com.example.traveltest3;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class publishedTab extends ListFragment {
	
	protected Object mActionMode;
	public int selectedItem = -1;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "Package1", "Package2", "Package3" };
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> av, View v, int position, long id) {
		        //Get your item here with the position
		    	if (mActionMode != null) {
		            return false;
		          }
		          selectedItem = position;

		          // start the CAB using the ActionMode.Callback defined above
		          mActionMode = getActivity().startActionMode(mActionModeCallback);
		          v.setSelected(true);
		          return true;
		    }
		});
	}
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // called when the action mode is created; startActionMode() was called
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	      // Inflate a menu resource providing context menu items
	      MenuInflater inflater = mode.getMenuInflater();
	      // assumes that you have "contexual.xml" menu resources
	      inflater.inflate(R.menu.cab_published, menu);
	      return true;
	    }

	    // the following method is called each time 
	    // the action mode is shown. Always called after
	    // onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	      return false; // Return false if nothing is done
	    }


	    // called when the user exits the action mode
	    public void onDestroyActionMode(ActionMode mode) {
	      mActionMode = null;
	      selectedItem = -1;
	    }

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			 switch (item.getItemId()){
			 case R.id.publishedmenuitem_assign:
				 assign();
				 mode.finish();
				 return true;
			 default:
				 return false;
			 }
		}
	  };
	
	  private void assign() {
		    Toast.makeText(getActivity(),
		        String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
	  }  
	
	public void onListItemClick(ListView l, View v, int position, long id) {
			    
	}
}