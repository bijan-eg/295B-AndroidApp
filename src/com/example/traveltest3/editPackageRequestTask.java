package com.example.traveltest3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class editPackageRequestTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPut httpput = new HttpPut(params[0]);
		
		try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("package_name", params[2]));
	        nameValuePairs.add(new BasicNameValuePair("description", params[3]));
	        nameValuePairs.add(new BasicNameValuePair("start_date", params[4]+"/"+params[5]+"/"+params[6]));
	        nameValuePairs.add(new BasicNameValuePair("end_date", params[7]+"/"+params[8]+"/"+params[9]));
	        nameValuePairs.add(new BasicNameValuePair("package_type", "VC"));
	        nameValuePairs.add(new BasicNameValuePair("flight", "false"));
	        nameValuePairs.add(new BasicNameValuePair("hotel", "true"));
	        nameValuePairs.add(new BasicNameValuePair("insurance", "false"));
	        nameValuePairs.add(new BasicNameValuePair("restaurant", "false"));
	        nameValuePairs.add(new BasicNameValuePair("local_booking", "false"));
	        httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        httpput.setHeader("Authorization", "JWT "+params[1]);	        

	        // Execute HTTP Put Request
	         httpclient.execute(httpput);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }		
		Log.e("tttttttttttttttttttttttttttt", "executed!");
		return null;
	}
}