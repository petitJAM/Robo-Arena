package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.firebase.client.Firebase;

import edu.rhit.petitjam_coblebj.game.BoxerGame;

// Come here after a match or from match history

public class MatchDetailsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_match_details);

		int gameMode = getIntent().getIntExtra(ArenaActivity.KEY_GAME_MODE, BoxerGame.GAME_MODE_COMPUTER);
		int computerDifficulty = getIntent().getIntExtra(ArenaActivity.KEY_COMPUTER_DIFFICULTY, -1);
		String gameId = getIntent().getStringExtra(ArenaActivity.KEY_GAME_ID);
		String playerId = getIntent().getStringExtra(ArenaActivity.KEY_PLAYER_ID);

		if (gameMode == BoxerGame.GAME_MODE_COMPUTER) {
			// ooh, we don't save these :(
			Log.d("RA", "Computer game against AI level " + computerDifficulty);

		} else if (gameMode == BoxerGame.GAME_MODE_HUMAN) {
			// TODO: get from Firebase

			// base game FB
			Firebase fb = new Firebase(getString(R.string.roboarena_firebase_games)).child(gameId);

			String remotePlayerId = playerId.equals(getString(R.string.fb_game_player_joiner)) ? getString(R.string.fb_game_player_creator)
					: getString(R.string.fb_game_player_joiner);

			Firebase localPlayerFB = fb.child(playerId);
			Firebase remotePlayerFB = fb.child(remotePlayerId);
			
			// TODO: get everything we need from FB
		}

		findViewById(R.id.done_button).setOnClickListener(this);


		// TODO: Save link to this FB game to a DB or something
	}

	@Override
	public void onClick(View v) {
		finish();
	}
}
