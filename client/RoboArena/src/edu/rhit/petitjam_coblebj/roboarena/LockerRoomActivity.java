package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;


// TODO: Add textviews for game name and whatever
public class LockerRoomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locker_room);

		String creatorId = getString(R.string.fb_game_player_creator);
		String joinerId = getString(R.string.fb_game_player_joiner);

		String playerId = getIntent().getStringExtra(ArenaActivity.KEY_PLAYER_ID);

		StringBuilder b = new StringBuilder();
		b.append(getString(R.string.roboarena_firebase_games));
		b.append("/");
		b.append(getIntent().getStringExtra(ArenaActivity.KEY_GAME_ID));
		Log.d("RA", "URL: " + b.toString());
		Log.d("RA", "playerId: " + playerId);
		
		Firebase gameRef = new Firebase(b.toString());
		final Firebase thisPlayer = gameRef.child(playerId);

		final Firebase otherPlayer;
		if (playerId.equals(joinerId)) {
			otherPlayer = gameRef.child(creatorId);
		} else {
			otherPlayer = gameRef.child(joinerId);
		}

		final Button readyButton = (Button)findViewById(R.id.ready_button);
		readyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO: logic for checking that both players connected
				thisPlayer.child(getString(R.string.fb_game_player_is_ready)).setValue(Boolean.TRUE);
				
				readyButton.setEnabled(false);

				otherPlayer.child(getString(R.string.fb_game_player_is_ready)).addValueEventListener(
						new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot snap) {
								// Both players are ready
								if ((Boolean)snap.getValue()) {
									Intent arenaIntent = new Intent(LockerRoomActivity.this, ArenaActivity.class);
									// Pass along the extras
									arenaIntent.putExtras(getIntent().getExtras());
									startActivity(arenaIntent);
								}
							}

							@Override
							public void onCancelled() {}
						});
			}
		});
	}
}
