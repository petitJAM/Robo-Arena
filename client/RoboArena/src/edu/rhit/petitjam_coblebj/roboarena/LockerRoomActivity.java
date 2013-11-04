package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LockerRoomActivity extends Activity {

	// Fields

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.locker_room);

		Button ready_button = (Button)findViewById(R.id.ready_button);
		ready_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent arenaIntent = new Intent(LockerRoomActivity.this, ArenaActivity.class);
				
				// TODO: logic for checking that both players connected
				
				// Pass along the extras
				arenaIntent.putExtras(getIntent().getExtras());
				startActivity(arenaIntent);
			}
		});
	}
}
