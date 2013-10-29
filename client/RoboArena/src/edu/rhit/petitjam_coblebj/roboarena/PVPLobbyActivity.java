package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PVPLobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pvp_lobby);
		
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
				// TODO: create new game with name (if unique)
				
				Intent lockerRoomIntent = new Intent(PVPLobbyActivity.this, LockerRoomActivity.class);
				
				Log.d(MainMenuActivity.RA, "Starting locker room by CREATE game button");
				startActivity(lockerRoomIntent);				
			}
		});
	}
	
}
