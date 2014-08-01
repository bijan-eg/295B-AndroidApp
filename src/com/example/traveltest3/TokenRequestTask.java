package com.example.traveltest3;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

class TokenRequestTask extends AsyncTask<String, Void, String> {
	 CallBackListener mListener;
	 
	 public void setListener(CallBackListener listener){
	     mListener = listener;
	     }
	 
		@Override
		protected String doInBackground(String... uri) {
			HttpResponse response;
		    String responseString = null;
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost(uri[0]);

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("username", "bipul"));
		        nameValuePairs.add(new BasicNameValuePair("password", "cmpe295"));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		         response = httpclient.execute(httppost);
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
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    }
			return responseString;

		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			try { 
	            // call callback
	            mListener.tokenCallback(result);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
}