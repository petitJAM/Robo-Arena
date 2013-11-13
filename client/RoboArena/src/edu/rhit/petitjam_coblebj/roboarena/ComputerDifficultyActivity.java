package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import edu.rhit.petitjam_coblebj.game.BoxerGame;

public class ComputerDifficultyActivity extends Activity {

	// Fields
	private SeekBar difficulty_bar;
	private int selectedDifficulty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_computer_difficulty);

		// Set seekerbar listener
		difficulty_bar = (SeekBar)findViewById(R.id.difficulty_bar);

		selectedDifficulty = difficulty_bar.getProgress();

		difficulty_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			// TODO - finish the correct logic for this function
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.d("RA", "Set computer difficulty to " + selectedDifficulty);
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				selectedDifficulty = progress;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
		});

		// Set box listener
		Button start_box_button = (Button)findViewById(R.id.start_box_button);
		start_box_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(MainMenuActivity.RA, "Starting locker room from computer difficulty with " + selectedDifficulty);
				
				Intent arenaIntent = new Intent(ComputerDifficultyActivity.this, ArenaActivity.class);
				arenaIntent.putExtra(ArenaActivity.KEY_GAME_MODE, BoxerGame.GAME_MODE_COMPUTER);
				arenaIntent.putExtra(ArenaActivity.KEY_COMPUTER_DIFFICULTY, selectedDifficulty);
				startActivity(arenaIntent);
				finish();
			}
		});
	}
}
