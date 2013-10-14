package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class computerDifficultyActivity extends Activity {

	// Fields
	private SeekBar difficulty_bar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.computer_difficulty);
		
		// Set seekerbar listener
		difficulty_bar = (SeekBar)findViewById(R.id.difficulty_bar);
		
		difficulty_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// TODO - finish the correct logic for this function
			int progressChanged = 0;
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Toast.makeText(computerDifficultyActivity.this, "Delta change: "+progressChanged, Toast.LENGTH_SHORT).show();				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progressChanged = progress;
				
			}
		});
		
		
		// Set box listener
		Button start_box_button = (Button)findViewById(R.id.start_box_button);
		start_box_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent arenaIntent = new Intent(computerDifficultyActivity.this, ArenaActivity.class);
				startActivity(arenaIntent);
			}
		});
	}
}
