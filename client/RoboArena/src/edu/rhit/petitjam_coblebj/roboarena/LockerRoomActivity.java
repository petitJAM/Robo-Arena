package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import edu.rhit.petitjam_coblebj.game.ComputerPlayer;

public class LockerRoomActivity extends Activity {

	// Fields

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locker_room);

		final int gameMode = getIntent().getIntExtra(ArenaActivity.KEY_GAME_MODE, ArenaActivity.GAME_MODE_COMPUTER);
		final int computerDifficulty = getIntent().getIntExtra(ComputerPlayer.KEY_COMPUTER_DIFFICULTY,
				ComputerPlayer.COMPUTER_PLAYER_DIFFICULTY_EASY);

		Button ready_button = (Button)findViewById(R.id.ready_button);
		ready_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent arenaIntent = new Intent(LockerRoomActivity.this, ArenaActivity.class);

				arenaIntent.putExtra(ArenaActivity.KEY_GAME_MODE, gameMode);

				if (gameMode == ArenaActivity.GAME_MODE_COMPUTER) {
					arenaIntent.putExtra(ComputerPlayer.KEY_COMPUTER_DIFFICULTY, computerDifficulty);
				}

				Log.d(MainMenuActivity.RA, "Starting new arena with game mode " + gameMode + " and difficulty "
						+ computerDifficulty);

				startActivity(arenaIntent);

			}
		});
	}
}
