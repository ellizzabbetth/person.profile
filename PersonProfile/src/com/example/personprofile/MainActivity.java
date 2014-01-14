package com.example.personprofile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	RadioGroup radioGroup;
	private static final String TAG = "Person Profile";
	public static final String PREFS_MYRUNS = "MyPrefs";	
	private static final String PROFILE_EMAIL = "emailKey";
	private static final String PROFILE_NAME = "nameKey";
	private static final String PROFILE_GENDER = "genderKey";
	public static final String PROFILE_PHONE = "phoneKey"; 
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnCancel = ((Button)findViewById(R.id.btnCancel));   
        Button btn = ((Button ) findViewById(R.id.btnSave));
		View.OnClickListener myListener = new View.OnClickListener(){
			@Override
			public void onClick(View v){
				
				 if (v.getId()==R.id.btnCancel) 
				 {
					//	Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
						// close the activity
						finish();
						// close application
						System.exit(0);
		        } 
				 if (v.getId()== R.id.btnSave) 
				 {
		        	saveProfile();
					Toast.makeText(getApplicationContext(), "Profile saved", Toast.LENGTH_SHORT).show();
					
					finish();
		        }       			
			}
		};
		
		btnCancel.setOnClickListener(myListener);
		btn.setOnClickListener(myListener);
		loadProfile();        
    }

    
 // ****************** HELPER FUNCTIONS ***************************//
	 
    // git test
    private void loadProfile() {
    	SharedPreferences preferences = getSharedPreferences(PREFS_MYRUNS, MODE_PRIVATE);
    	
    	String prefItemStr = preferences.getString(PROFILE_NAME, "");
    	if(prefItemStr.length() > 0){
    		((EditText) findViewById(R.id.editName)).setText(prefItemStr);
    	}
    	
    	prefItemStr = preferences.getString(PROFILE_EMAIL, "");
    	if(preferences.contains(PROFILE_EMAIL)){
    		((EditText) findViewById(R.id.editEmail)).setText(prefItemStr);
    	}
    	
    	if (preferences.contains(PROFILE_PHONE))
        {
    		((EditText) findViewById(R.id.editPhoneNumber)).setText(preferences.getString(PROFILE_PHONE, ""));
        }
    	
    	int intValue = preferences.getInt(PROFILE_GENDER, -1);
        if (intValue >= 0) {
              
                    RadioButton radioBtn = (RadioButton) ((RadioGroup) findViewById(R.id.radioGender))
                                            .getChildAt(intValue);
                
                    radioBtn.setChecked(true);
               
        }    	
    }
		

         
    private void saveProfile() {

                Log.d(TAG, "savePersonProfile()");

                SharedPreferences preferences = getSharedPreferences(PREFS_MYRUNS, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
              
                
                String value = (String) ((EditText) findViewById(R.id.editName))
                        .getText().toString();
                editor.putString(PROFILE_NAME, value);
                

            
                value = (String) ((EditText) findViewById(R.id.editEmail))
                                        .getText().toString();
                editor.putString(PROFILE_EMAIL, value);
        
                
                value = (String) ((EditText) findViewById(R.id.editPhoneNumber))
                        .getText().toString();
                editor.putString(PROFILE_PHONE, value);
            
               

               
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGender);
                int intValue = radioGroup.indexOfChild(findViewById(radioGroup
                                        .getCheckedRadioButtonId()));
                editor.putInt(PROFILE_GENDER, intValue);           
                editor.commit();
              
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
