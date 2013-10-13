package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainMenuActivity extends Activity implements OnClickListener {
	static final String RA = "RA"; // Debug tag

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		findViewById(R.id.player_vs_ai_button).setOnClickListener(this);
		findViewById(R.id.player_vs_player_button).setOnClickListener(this);
		findViewById(R.id.spectate_button).setOnClickListener(this);
		findViewById(R.id.match_history_button).setOnClickListener(this);
		findViewById(R.id.settings_button).setOnClickListener(this);
		findViewById(R.id.exit_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.player_vs_ai_button:
			Log.d(RA, "Player vs AI");
			break;
			
		case R.id.player_vs_player_button:
			Log.d(RA, "Player vs Player");
			break;
			
		case R.id.spectate_button:
			Log.d(RA, "Spectate");
			break;
			
		case R.id.match_history_button:
			Log.d(RA, "Match History");
			Intent matchHistoryIntent = new Intent(this, MatchHistoryActivity.class);
			startActivity(matchHistoryIntent);
			break;
			
		case R.id.settings_button:
			Log.d(RA, "Settings");
			break;
			
		case R.id.exit_button:
			Log.d(RA, "Exit");
//			finish();
			break;
		}
	}
}
