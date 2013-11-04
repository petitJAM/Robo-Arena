package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class PVPLobbyActivity extends Activity {
	
	private Firebase mRef;
	
	private EditText mCreateRoomName;
	private EditText mCreateRoomPassword;
	private EditText mJoinRoomName;
	private EditText mJoinRoomPassword;
	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pvp_lobby);
		
		mRef = new Firebase(getString(R.string.roboarena_firebase_games));
		
		mCreateRoomName = (EditText)findViewById(R.id.create_room_name);
		mCreateRoomPassword = (EditText)findViewById(R.id.create_password);
		
		Button join_button = (Button)findViewById(R.id.join_player_game_button);
		Button create_button = (Button)findViewById(R.id.create_player_game_button);
				
		join_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO: check for game existence/valid password/etc
				
				Intent lockerRoomIntent = new Intent(PVPLobbyActivity.this, LockerRoomActivity.class);
				
				Log.d(MainMenuActivity.RA, "Starting locker room by JOIN game button");
				startActivity(lockerRoomIntent);				
			}
		});
		
		create_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
				// TODO: Check if chosen name exists
				
				// Create new game
				Firebase gameRef = mRef.child("game17"); // TODO: change to child
				Log.d("GameSetup", "New game created at " + gameRef.getName());
				
				// Create info object for the game
				Firebase infoRef = gameRef.child(getString(R.string.fb_game_info));
				infoRef.child("allow_spectators").setValue(Boolean.TRUE); // TODO: get from user
				infoRef.child("password").setValue("password"); // TODO: get from user

				// Create player 1
				Firebase playerOneRef = gameRef.child(getString(R.string.fb_game_player_id_1));
				playerOneRef.child("health").setValue(Integer.valueOf(100));
				playerOneRef.child("is_connected").setValue(Boolean.FALSE);
				playerOneRef.child("actions_allowed").setValue(Boolean.FALSE);
				Log.d("GameSetup", "New player 1 created at " + playerOneRef.getName());

				// Create player 2
				Firebase playerTwoRef = gameRef.child(getString(R.string.fb_game_player_id_2));
				playerTwoRef.child("health").setValue(Integer.valueOf(100));
				playerTwoRef.child("is_connected").setValue(Boolean.FALSE);
				playerTwoRef.child("actions_allowed").setValue(Boolean.FALSE);
				Log.d("GameSetup", "New player 2 created at " + playerTwoRef.getName());
				
								
				Intent lockerRoomIntent = new Intent(PVPLobbyActivity.this, LockerRoomActivity.class);
				lockerRoomIntent.putExtra(ArenaActivity.KEY_GAME_ID, gameRef.getName());
				
				Log.d(MainMenuActivity.RA, "Starting locker room by CREATE game button");
				startActivity(lockerRoomIntent);				
			}
		});
	}
	
}
