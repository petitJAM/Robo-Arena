package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MatchDetailsActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_details);
		
		findViewById(R.id.done_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();
	}
}
