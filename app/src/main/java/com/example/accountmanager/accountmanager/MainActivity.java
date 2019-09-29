package com.example.accountmanager.accountmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accountmanager.R;
import com.example.accountmanager.pattern.UnlockGesturePasswordActivity;


public class MainActivity extends Activity {

	private List<String> mydata = new ArrayList<String>();
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private AccountDao accountDao;
//	public static final String TAG = "MainActivity";
	
	
	//
    private static boolean isExit = false; 
   
    private static Handler mHandler = new Handler() { 
   
        @Override 
        public void handleMessage(Message msg) { 
            super.handleMessage(msg); 
            isExit = false; 
        } 
    };  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main); 
        
        //query database and add these datas into listview
        accountDao = new AccountDao(MainActivity.this);
        
        //get all name in the table
		mydata = accountDao.getAll();

        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.listview_text_style, mydata);
        
        listView = (ListView)findViewById(R.id.account_list);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					String name = mydata.get(position);
					
//					System.out.println(name);
					
//					Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
					
					accountDao = new AccountDao(MainActivity.this);
					Account account = accountDao.Find(name);
					
					Intent intent = new Intent(MainActivity.this, DisplayAccountDialog.class);
					intent.putExtra("account", new String[]{name,account.getAccount(),account.getPassword()});
					startActivityForResult(intent, 2);

			}	
		});
        
//        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				String name = mydata.get(position);
//				Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
//
//				return false;
//			}	
//		});
        
        
        Button btAdd = (Button)findViewById(R.id.add);
        btAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddUpdateAccount.class);
				intent.putExtra("account", new String[]{"M"});
				startActivityForResult(intent, 1);	
			}
		});
    }
    


	@Override
	protected void onRestart() {
		super.onRestart();
//		Log.d(TAG, "onRestart");
		//query database and add these datas into listview
        accountDao = new AccountDao(MainActivity.this);
        
        //get all name in the table
		mydata = accountDao.getAll();
		
		adapter.notifyDataSetChanged();

        adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.listview_text_style, mydata);
        
        listView = (ListView)findViewById(R.id.account_list);
        listView.setAdapter(adapter);

	}


	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case 1:// for add button
			if (resultCode == RESULT_OK) {
				
					
				mydata.add(data.getStringExtra("data_return"));
				adapter.notifyDataSetChanged();
				
			}
			break;
			
		case 2://for listview onclick
			if (resultCode == RESULT_OK) {	
				
				mydata.remove(data.getStringExtra("data_delete"));
				adapter.notifyDataSetChanged();	
			}
			break;

		default:
			break;
		}
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { 
            exit(); 
            return true; 
        }  
		return super.onKeyDown(keyCode, event);
	}
	
	private void exit() { 
        if (!isExit) { 
            isExit = true; 
            Toast.makeText(getApplicationContext(), getString(R.string.exit_app), 
                    Toast.LENGTH_SHORT).show(); 
            // ����handler�ӳٷ��͸���״̬��Ϣ 
            mHandler.sendEmptyMessageDelayed(0, 2000); 
        } else { 
            UnlockGesturePasswordActivity.mainActivityFinishFlag  = true;
            this.finish(); 
        }  
	}
	
	@Override
	protected void onStart() {
		super.onStart();
//		Log.d(TAG, "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		Log.d(TAG, "onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		Log.d(TAG, "onPause");
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
