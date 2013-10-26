package edu.rhit.petitjam_coblebj.roboarena;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.game.BoxerGame;

public class ArenaActivity extends Activity{

	// Fields
	private GestureDetector mDetector;
	private BoxerGame mGame;
	
	public TextView l_jab;
	public TextView l_hook;
	public TextView l_up;
	public TextView r_jab;
	public TextView r_hook;
	public TextView r_up;
	public TextView block;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arena);
		
		mGame = new BoxerGame(this);
		
		l_jab = (TextView)findViewById(R.id.l_jab_tv);
		l_hook = (TextView)findViewById(R.id.l_hook_tv);
		l_up = (TextView)findViewById(R.id.l_up_tv);
		r_jab = (TextView)findViewById(R.id.r_jab_tv);
		r_hook = (TextView)findViewById(R.id.r_hook_tv);
		r_up = (TextView)findViewById(R.id.r_up_tv);
//		block = (TextView)findViewById(R.id.);
		
		final Handler handler = new Handler();
		final Runnable r = new Runnable() {
			@Override
			public void run() {
				l_jab.setBackgroundColor(Color.WHITE);
				l_hook.setBackgroundColor(Color.WHITE);
				l_up.setBackgroundColor(Color.WHITE);
				r_jab.setBackgroundColor(Color.WHITE);
				r_hook.setBackgroundColor(Color.WHITE);
				r_up.setBackgroundColor(Color.WHITE);
				
				handler.postDelayed(this, 2000);
			}
		};
		
		handler.postDelayed(r, 2000);
		
		mDetector = new GestureDetector(this, new PlayerGestureDetector());
		
		mGame.startGame();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getPointerCount() > 1) {
			Log.d("PGL", "two finger event");
		}
		return mDetector.onTouchEvent(event);
	}
	
	private class PlayerGestureDetector extends GestureDetector.SimpleOnGestureListener {
		private static final String PGL = "PGL";

		/* Constants for gesture regions */
		private final int CENTER_X = ArenaActivity.this.getResources().getDisplayMetrics().widthPixels / 2;
		
		/* Constants for gesture swipe restrictions */
		private static final int SWIPE_MIN_VELOCITY = 100;
		private static final int SWIPE_MAX_OFF_PATH = 100;
		private static final int SWIPE_MIN_DISTANCE = 100;
		
		/*
		 * Handles Jabs 
		 */
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			float x = e.getX();
			float y = e.getY();
			
			if (x >= CENTER_X) {
				Log.d(PGL, "right jab");
				mGame.localRightJab();
			} else {
				Log.d(PGL, "left jab");
				mGame.localLeftJab();
			}
			
			return true;
		}
		
		/*
		 * Handles uppercuts, hooks, and blocks
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			
			float e1_x = e1.getX();
			float e2_x = e2.getX();
			float e1_y = e1.getY();
			float e2_y = e2.getY();
			
			float dx = e2_x - e1_x;
			float dy = e2_y - e1_y;
			
			if (Math.abs(velocityX) >= SWIPE_MIN_VELOCITY || Math.abs(velocityY) >= SWIPE_MIN_VELOCITY) { // Swipe fast enough
				
				if (e1_x >= CENTER_X) { // Right hits
					
					if (dx < SWIPE_MAX_OFF_PATH && dy < 0) { // swipe up
						Log.d(PGL, "right uppercut");
					} 
					else if (dy < SWIPE_MAX_OFF_PATH && dx < 0) { // swipe r->l
						Log.d(PGL, "right hook");
					} 
					else {
						Log.d(PGL, "right nothing");
					}
					
				} else { 				// Left hits
					
					if (dx < SWIPE_MAX_OFF_PATH && dy < 0) { // swipe up
						Log.d(PGL, "left uppercut");
					} 
					else if (dy < SWIPE_MAX_OFF_PATH && dx > 0) { // swipe r->l
						Log.d(PGL, "left hook");
					} 
					else {
						Log.d(PGL, "left nothing");
					}
					
				}
			}
			else {
				Log.d(PGL, "not fast enough");
			}
			
			
			return true;
		}
	}
}
