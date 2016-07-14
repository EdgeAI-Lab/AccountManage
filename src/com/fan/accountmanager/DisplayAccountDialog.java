package com.fan.accountmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DisplayAccountDialog extends Activity {

	private String[] account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display_account_dialog);
		
		Intent intent = getIntent();
		account = intent.getStringArrayExtra("account");
		
		TextView tvName = (TextView)findViewById(R.id.tvName);
		tvName.setText(account[0]);
		
		TextView tvAccount = (TextView)findViewById(R.id.tvAccount);
		tvAccount.setText(account[1]);
		
		TextView tvPassword = (TextView)findViewById(R.id.tvPassword);
		tvPassword.setText(account[2]);
		
		
		Button btUpdate = (Button)findViewById(R.id.update);
		btUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(DisplayAccountDialog.this, AddUpdateAccount.class);
				intent.putExtra("account", new String[]{"D",account[0],account[1],account[2]});
				startActivity(intent);
				finish();
				
			}
		});
		
		Button btDelet = (Button)findViewById(R.id.delete);
		btDelet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				//Alert Dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(DisplayAccountDialog.this);
				builder.setTitle(getString(R.string.delete));
				builder.setMessage(getString(R.string.delete_alert));
				builder.setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AccountDao accountDao = new AccountDao(DisplayAccountDialog.this);
						accountDao.Delete(account[0]);
						
						Intent intent = getIntent();
						intent.putExtra("data_delete", account[0]);
						setResult(RESULT_OK, intent);
						finish();
						
					}
				});
				
				builder.setNegativeButton(getString(R.string.cancel), null);
				
				builder.show();
				
				
			}
		});
		
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
//		Log.d(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		Log.d(TAG, "onDestroy");
	}

	

}
