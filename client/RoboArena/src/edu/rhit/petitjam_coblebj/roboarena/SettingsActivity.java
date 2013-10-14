package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		findViewById(R.id.cancel_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(MainMenuActivity.RA, "Cancelled settings");
				finish(); // just end it
			}
		});
		
		findViewById(R.id.save_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(MainMenuActivity.RA, "Saving settings (not really)");
				// TODO: this is where all the preference saving will happen
				
				finish(); // then end it!
			}
		});

		Spinner gloveSpinner = (Spinner)findViewById(R.id.glove_color_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.glove_colors,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gloveSpinner.setAdapter(adapter);
		
		
		// TODO: initialize everything to whatever is in preferences
	}
}
