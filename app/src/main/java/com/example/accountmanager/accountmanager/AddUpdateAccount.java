package com.example.accountmanager.accountmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accountmanager.R;

public class AddUpdateAccount extends Activity {

	private String[] intentData;
	private EditText etName;
	private EditText etAccount;
	private EditText etPassword;
	private TextView tvHead;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_update_account);
		
		etName = (EditText)findViewById(R.id.etName);
		etAccount = (EditText)findViewById(R.id.etAccount);
		etPassword = (EditText)findViewById(R.id.etPassword);
		tvHead = (TextView) findViewById(R.id.newAccount);
		
		Intent acIntent = getIntent();
		intentData = acIntent.getStringArrayExtra("account");
		
		if (intentData[0].equals("D")) {

			etName.setText(intentData[1]);
			etAccount.setText(intentData[2]);
			etPassword.setText(intentData[3]);
			tvHead.setText(getString(R.string.update_accoint));
		}else {
			tvHead.setText(getString(R.string.add_new_accoint));
		}
		
		
		//confirm add new account
		Button btOk = (Button)findViewById(R.id.btOk);
		btOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String nameString = etName.getText().toString();
				String accountString = etAccount.getText().toString();
				String passwordString = etPassword.getText().toString();
				
				
//				Toast.makeText(AddUpdateAccount.this, nameString, Toast.LENGTH_SHORT).show();
				if (nameString.equals("") || accountString.equals("") || passwordString.equals("")) {
					
					Toast.makeText(AddUpdateAccount.this, getString(R.string.hint), Toast.LENGTH_SHORT).show();
					return;
					
				}
				
				AccountDao accountDao = new AccountDao(AddUpdateAccount.this);
				Account account;
				if( intentData[0].equals("M") )
				{
					//when "m" the " intentData[1](name) " is not exist
					account = new Account(nameString, accountString,passwordString);

					accountDao.Add(account);
					
					Intent intent = new Intent();
					intent.putExtra("data_return", nameString);
					setResult(RESULT_OK, intent);
					
					finish();
					
				}else if( intentData[0].equals("D") ) {
					//when "D" the " intentData[1] " is exist
					account = new Account(accountDao.GetId(intentData[1]),nameString, accountString,passwordString);
					accountDao.Update(account);

//					Intent intent = new Intent(AddUpdateAccount.this, MainActivity.class);
//					startActivity(intent);
					finish();
				}
					
				
				
				
			}
		});
		
		//cancel add new account
		Button btCancel = (Button)findViewById(R.id.btCancel);
		btCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
					finish();
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
