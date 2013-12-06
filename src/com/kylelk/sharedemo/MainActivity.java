package com.kylelk.sharedemo;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity
{
	private Button shareBtn;
	private EditText textToShare;
	private TextView textOutput;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		shareBtn = (Button) findViewById(R.id.shareText);	
		shareBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v)
				{

					EditText et=(EditText)findViewById(R.id.textToShare);
					int startSelection=et.getSelectionStart(); 
					int endSelection=et.getSelectionEnd();
					String selectedText = et.getText().toString().substring(startSelection, endSelection);

					if (selectedText.length() == 0)
					{
						textToShare = (EditText) findViewById(R.id.textToShare);
						String dataToShare = textToShare.getText().toString();
						selectedText = dataToShare;
					}

				Intent sendIntent = new Intent(); 
				sendIntent.setAction(Intent.ACTION_SEND); 
				sendIntent.putExtra(Intent.EXTRA_TEXT, selectedText); 
				sendIntent.setType("text/plain"); 
				startActivity(sendIntent);	

				}			
			});	

		Intent intent = getIntent(); 
		String action = intent.getAction(); 
		String type = intent.getType();
		if (Intent.ACTION_SEND.equals(action) && type != null)
		{ 
			handleSendText(intent); // Handle text being sent 
		}}
		
	public void handleSendText(Intent intent)
	{ 
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT); 
		if (sharedText != null)
		{ 
			textOutput = (TextView) findViewById(R.id.textOutput);
			textOutput.setTextIsSelectable(true);
			textOutput.setText(sharedText);
		} 
	}								
}
