package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;


// TODO: Add textviews for game name and whatever
public class LockerRoomActivity extends Activity {

	private CheckBox mPlayerOneConnectedCheckBox;
	private CheckBox mPlayerTwoConnectedCheckBox;
	private TextView mPlayerOneConnectedTextView;
	private TextView mPlayerTwoConnectedTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locker_room);

		mPlayerOneConnectedCheckBox = (CheckBox)findViewById(R.id.player_one_check_box);
		mPlayerTwoConnectedCheckBox = (CheckBox)findViewById(R.id.player_two_check_box);
		mPlayerOneConnectedTextView = (TextView)findViewById(R.id.player_one_connected);
		mPlayerTwoConnectedTextView = (TextView)findViewById(R.id.player_two_connected);

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

		// set the other player as the creator or joiner, depending on who we are
		final Firebase otherPlayer;
		if (playerId.equals(joinerId)) {
			otherPlayer = gameRef.child(creatorId);
		} else {
			otherPlayer = gameRef.child(joinerId);
		}
		
		thisPlayer.child(getString(R.string.fb_game_player_is_connected)).setValue(Boolean.TRUE);
		mPlayerOneConnectedTextView.setText(R.string.connected);

		final Button readyButton = (Button)findViewById(R.id.ready_button);
		readyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO: logic for checking that both players connected
				thisPlayer.child(getString(R.string.fb_game_player_is_ready)).setValue(Boolean.TRUE);

				readyButton.setEnabled(false);
				mPlayerOneConnectedCheckBox.setChecked(true);

				otherPlayer.child(getString(R.string.fb_game_player_is_ready)).addValueEventListener(
						new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot snap) {
								// Both players are ready
								if ((Boolean)snap.getValue()) {
									startLockerRoomActivity();
								}
							}

							@Override
							public void onCancelled() {}
						});
			}
		});
		
		// update the text on connect
		otherPlayer.child(getString(R.string.fb_game_player_is_connected)).addValueEventListener(
				new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snap) {
						if ((Boolean)snap.getValue()) {
							mPlayerTwoConnectedTextView.setText(R.string.connected);
						}
					}

					@Override
					public void onCancelled() {}
				});
		
		// update the check box on ready
		otherPlayer.child(getString(R.string.fb_game_player_is_ready)).addValueEventListener(
				new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snap) {
						if ((Boolean)snap.getValue()) {
							mPlayerTwoConnectedCheckBox.setChecked(true);
						}
					}

					@Override
					public void onCancelled() {}
				});
	}
	
	private void startLockerRoomActivity() {
		Intent arenaIntent = new Intent(LockerRoomActivity.this, ArenaActivity.class);
		// Pass along the extras
		arenaIntent.putExtras(getIntent().getExtras());
		startActivity(arenaIntent);
	}
}
