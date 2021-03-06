package pt.drawgeo.map;

import pt.drawgeo.utility.Configurations;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.main.R;

public class NewChallenge extends Activity{

	public static Dialog dialog;
	private int replaceId =-1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle b = this.getIntent().getExtras();
        if(!(b==null) && !b.isEmpty())
        	replaceId  =  b.getInt("replaceID", -1);
        // Modo fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.newchallenge);
        
        final ImageView eButton = (ImageView) findViewById(R.id.okButton);
		eButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText description = (EditText) findViewById(R.id.description);
				EditText password = (EditText) findViewById(R.id.password);
	
				if (description.getText().toString().length() == 0 || password.getText().toString().length() == 0)
					Toast.makeText(NewChallenge.this.getApplicationContext(), "Fields cannot be empty...", Toast.LENGTH_SHORT).show();
				else
				{
					Configurations.current_description = description.getText().toString();
					Configurations.current_password = password.getText().toString();
					
					dialog = ProgressDialog.show(NewChallenge.this, "", 
							"Retrieving information...", true);
					
					GetNewWords gnw = new GetNewWords();
					gnw.activity = NewChallenge.this;
					gnw.dialog = NewChallenge.dialog;
					gnw.finish = true;
					gnw.replaceID = replaceId;
					gnw.execute();
					
				}
				
				
			}
		});
        
    }
    
 

}
