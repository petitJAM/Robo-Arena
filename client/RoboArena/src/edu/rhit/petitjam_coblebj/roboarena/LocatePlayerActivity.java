package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LocatePlayerActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locate_player_game);
		
		Button join_button = (Button)findViewById(R.id.join_player_game_button);
		Button create_button = (Button)findViewById(R.id.create_player_game_button);
				
		join_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent LockerRoomIntent = new Intent(LocatePlayerActivity.this, LockerRoomActivity.class);
				startActivity(LockerRoomIntent);				
			}
		});
		
		create_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent LockerRoomIntent = new Intent(LocatePlayerActivity.this, LockerRoomActivity.class);
				startActivity(LockerRoomIntent);				
			}
		});
	}
	
}
