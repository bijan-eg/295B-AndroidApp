package com.example.traveltest3;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import android.os.AsyncTask;

public class deletePackageRequestTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpDelete httpdelete = new HttpDelete(params[0]);

	    try {
	        // Add your data
	        httpdelete.setHeader("Authorization", "JWT "+params[1]);	        

	        // Execute HTTP Post Request
	        httpclient.execute(httpdelete);	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    return null;
	}
}