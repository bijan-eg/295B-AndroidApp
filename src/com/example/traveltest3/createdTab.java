package com.example.traveltest3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
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

public class createdTab extends ListFragment {
	
	protected Object mActionMode;
	public int selectedItem = -1;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		String[] values = new String[] { "Package1", "Package2", "Package3",
//		        "Package4", "Package5", "Package6" };
		new RequestTask().execute("http://mighty-lowlands-2957.herokuapp.com/agentapp/packages/");
//	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
//		setListAdapter(adapter);
		
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
	      inflater.inflate(R.menu.cab_created, menu);
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
			 case R.id.createdmenuitem_delete:
				 delete();
				 mode.finish();
				 return true;
			 case R.id.createdmenuitem_edit:
				 edit();
				 mode.finish();
				 return true;
			 case R.id.createdmenuitem_reserve:
				 reserve();
				 mode.finish();
				 return true;
			 default:
				 return false;
			 }
		}
	  };
	
	  private void delete() {
		    Toast.makeText(getActivity(),
		        "package deleted", Toast.LENGTH_LONG).show();
	  }  
	
	  private void edit() {
		    Toast.makeText(getActivity(), "edit", Toast.LENGTH_LONG).show();
		    Intent i = new Intent(getActivity(), EditPackage.class);
		    startActivity(i);
	  }
	  
	  private void reserve() {
		    Toast.makeText(getActivity(),
		        "reserve", Toast.LENGTH_LONG).show();
	  }
	public void onListItemClick(ListView l, View v, int position, long id) {
			    
	}
	
	class RequestTask extends AsyncTask<String, String, String>{

	    @Override
	    protected String doInBackground(String... uri) {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                //Closes the connection.
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	            //TODO Handle problems..
	        } catch (IOException e) {
	            //TODO Handle problems..
	        }
	        return responseString;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	        //Do anything with response..
	    }
	}
}