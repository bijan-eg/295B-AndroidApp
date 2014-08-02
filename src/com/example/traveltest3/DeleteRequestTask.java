package com.example.traveltest3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;

class DeleteRequestTask extends AsyncTask<String, Void, String> {
	 CallBackListener mListener;
	 
	 public void setListener(CallBackListener listener){
	     mListener = listener;
	     }
	 
		@Override
		protected String doInBackground(String... uri) {
			HttpResponse response;
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpDelete httpdel = new HttpDelete(uri[0]);

		    try {
		    	// Add authentication token in HTTP header
		    	httpdel.setHeader("Authorization", "JWT "+ uri[1]);
		        // Execute HTTP Post Request
		         response = httpclient.execute(httpdel);
		         StatusLine statusLine = response.getStatusLine();	            
		            
	            	if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	            		ByteArrayOutputStream out = new ByteArrayOutputStream();
	            		response.getEntity().writeTo(out);
	            		out.close();
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
		    return null;
		}
}