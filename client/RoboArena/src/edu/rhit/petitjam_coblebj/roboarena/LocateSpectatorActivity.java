package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LocateSpectatorActivity extends Activity{

	// Fields
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locate_spectator_game);
		
		Button join_button = (Button)findViewById(R.id.join_button);
		join_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent spectatorArenaIntent = new Intent(LocateSpectatorActivity.this, SpectatorArenaActivity.class);
				startActivity(spectatorArenaIntent);				
			}
		});
	}
}
