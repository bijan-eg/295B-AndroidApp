package com.example.traveltest3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class publishedTab extends ListFragment {
	
	protected Object mActionMode;
	public int longClickedItem = -1;		
	List<String> packageNames = new ArrayList<String>();
	List<TravelPackage> packages = new ArrayList<TravelPackage>();
	ArrayAdapter<String> adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		new RequestTask().execute("http://blooming-dusk-7345.herokuapp.com/agentapp/packages/");
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
		    public boolean onItemLongClick(AdapterView<?> av, View v, int position, long id) {
		        //Get your item here with the position
		    	if (mActionMode != null) {
		            return false;
		          }
		    	longClickedItem = position;

		          // start the CAB using the ActionMode.Callback defined above
		          mActionMode = getActivity().startActionMode(mActionModeCallback);
		          v.setSelected(true);
		          return true;
		    }
		});
		
		getListView().setOnItemClickListener(new OnItemClickListener() {// user clicking on any of the published packages in the listView

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent(view.getContext().getApplicationContext(), PackageDetails.class);
				in.putExtra("slctdpackageName", packages.get(position).getPackage_name());
                in.putExtra("slctdpackagedesc", packages.get(position).getDescription());
                in.putExtra("slctdpackageSDate", packages.get(position).getStart_date());
                in.putExtra("slctdpackageEDate", packages.get(position).getEnd_date());
                in.putExtra("slctdpackageType", packages.get(position).getPackage_type());
                in.putExtra("slctdpackageStatus", packages.get(position).getStatus());
                startActivity(in);				
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
//	      selectedItem = -1;
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
//		    Toast.makeText(getActivity(),
//		        String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
	  }  
	
	class RequestTask extends AsyncTask<String, Void,String>{

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
	            		return responseString;	            		
	            		
	            	} else{
	                //Closes the connection.
		                response.getEntity().getContent().close();
		                throw new IOException(statusLine.getReasonPhrase());
	            	}
	        } catch (ClientProtocolException e) {
	            
	        } catch (IOException e) {
	            
	        } 	         
	       return responseString;	       
	    }

	    @Override
	    protected void onPostExecute(String result) {
	       super.onPostExecute(result);
	       packages.clear();
	       try {
	    	   JSONArray jArray = new JSONArray(result);
	    	   for (int i=0; i < jArray.length(); i++)
	    	   {
	    		   TravelPackage myPackage = new TravelPackage();
	    		   try {
	    			   JSONObject jObject = jArray.getJSONObject(i);
	    			   // Pulling items from the array
	    			   if(jObject.getString("status").equalsIgnoreCase("published")){
		    			   myPackage.setId(Integer.parseInt(jObject.getString("id")));
		    			   myPackage.setPackage_name(jObject.getString("package_name"));
		    			   myPackage.setDescription(jObject.getString("description"));
		    			   myPackage.setStart_date(jObject.getString("start_date"));
		    			   myPackage.setEnd_date(jObject.getString("end_date"));
		    			   myPackage.setPackage_type(jObject.getString("package_type"));
		    			   myPackage.setflight(jObject.getBoolean("flight"));
		    			   myPackage.setRestaurant(jObject.getBoolean("restaurant"));
		    			   myPackage.setHotel(jObject.getBoolean("hotel"));
		    			   myPackage.setInsurance(jObject.getBoolean("insurance"));
		    			   myPackage.setLocalBooking(jObject.getBoolean("local_booking"));
		    			   myPackage.setStatus(jObject.getString("status"));
		    			   packages.add(myPackage);
	    			   }
			       } catch (JSONException e) {
			    	   // Oops
			       }
	    	   }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, packageNames);
	       setListAdapter(adapter);
	       
	       if(!packageNames.isEmpty())
	       {
	    	   packageNames.clear();
	    	   adapter.notifyDataSetChanged();
	    	   for(int i = 0 ; i<packages.size() ; i++){
		       		packageNames.add(packages.get(i).getPackage_name());
		       	}
	    	   adapter.notifyDataSetChanged();
	       }else{
	    	   for(int i = 0 ; i<packages.size() ; i++){
		       		packageNames.add(packages.get(i).getPackage_name());
	    	   }
	    	   adapter.notifyDataSetChanged();
	       }
       					       
		    Toast.makeText(getActivity(), 
		    		Integer.toString(packages.size())+" packages found", Toast.LENGTH_SHORT).show();
		 }
	}
}