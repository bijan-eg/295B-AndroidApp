package com.example.traveltest3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PackageDetails extends Activity{
	TextView pkgName, pkgdesc, pkgSDate, pkgEDate, pkgType, pkgStatus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.package_detail);
		pkgName = (TextView)findViewById(R.id.textView12);
		pkgdesc = (TextView)findViewById(R.id.textView2);
		pkgSDate = (TextView)findViewById(R.id.textView4);
		pkgEDate = (TextView)findViewById(R.id.textView6);
		pkgType = (TextView)findViewById(R.id.textView8);
		pkgStatus = (TextView)findViewById(R.id.textView10);
		
		Intent intent = getIntent();
		pkgName.setText(intent.getStringExtra("slctdpackageName"));
		pkgdesc.setText(intent.getStringExtra("slctdpackagedesc"));
		pkgSDate.setText(intent.getStringExtra("slctdpackageSDate"));
		pkgEDate.setText(intent.getStringExtra("slctdpackageEDate"));
		pkgType.setText(intent.getStringExtra("slctdpackageType"));
		pkgStatus.setText(intent.getStringExtra("slctdpackageStatus"));
//		Toast.makeText(getActivity(), intent.getStringExtra("slctdpackageSDate"), Toast.LENGTH_LONG).show();
	}

}
