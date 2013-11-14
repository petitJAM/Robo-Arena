package edu.rhit.petitjam_coblebj.roboarena;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.game.BoxerGame;
import edu.rhit.petitjam_coblebj.game.ComputerPlayer;

public class ArenaActivity extends Activity {

	protected static final String KEY_GAME_MODE = "key_game_mode";
	protected static final String KEY_COMPUTER_DIFFICULTY = "key_computer_difficulty";
	protected static final String KEY_GAME_ID = "key_firebase_ref";
	protected static final String KEY_PLAYER_ID = "key_player_id";
	protected static final String KEY_GAME_WINNER_ID = "key_game_winner_id";

	// Fields
	private GestureDetector mDetector;
	private BoxerGame mGame;

	// Images
	public ImageView rightGlove;
	public ImageView leftGlove;
	public ImageView enemyRightGlove;
	public ImageView enemyLeftGlove;

	// TextView
	public TextView l_jab;
	public TextView l_hook;
	public TextView l_up;
	public TextView r_jab;
	public TextView r_hook;
	public TextView r_up;
	public TextView block;

	public TextView player1_hp_textview;
	public TextView player2_hp_textview;

	private int mGameMode;
	private int mComputerDifficulty;

	private String mGameId;
	private String mPlayerId;

	// Actions
	private static final float jab_distance = -20;
	private static final float hook_distance = -30;
	private static final float uppercut_distance = -50;
	private static final float block_distance = -40;

	// Cooldown - half what the boxer game has because each animation is a two
	// part action
	private static final int jab_speed = 1000;
	private static final int hook_speed = 1200;
	private static final int uppercut_speed = 2000;
	private static final int block_speed = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arena);

		/* TEMP TEXTVIEWS */
		l_jab = (TextView) findViewById(R.id.l_jab_tv);
		l_hook = (TextView) findViewById(R.id.l_hook_tv);
		l_up = (TextView) findViewById(R.id.l_up_tv);
		r_jab = (TextView) findViewById(R.id.r_jab_tv);
		r_hook = (TextView) findViewById(R.id.r_hook_tv);
		r_up = (TextView) findViewById(R.id.r_up_tv);
		// block = (TextView)findViewById(R.id.);
		/* ************** */

		player1_hp_textview = (TextView) findViewById(R.id.player1_hp);
		player2_hp_textview = (TextView) findViewById(R.id.player2_hp);

		rightGlove = (ImageView) findViewById(R.id.right_glove);
		leftGlove = (ImageView) findViewById(R.id.left_glove);

		enemyRightGlove = (ImageView) findViewById(R.id.robo_right_glove);
		enemyLeftGlove = (ImageView) findViewById(R.id.robo_left_glove);

		mGameMode = getIntent().getIntExtra(ArenaActivity.KEY_GAME_MODE,
				BoxerGame.GAME_MODE_COMPUTER);
		mComputerDifficulty = getIntent().getIntExtra(KEY_COMPUTER_DIFFICULTY,
				ComputerPlayer.COMPUTER_PLAYER_DIFFICULTY_EASY);

		mGameId = getIntent().getStringExtra(KEY_GAME_ID);
		mPlayerId = getIntent().getStringExtra(KEY_PLAYER_ID);

		if (mGameMode == BoxerGame.GAME_MODE_HUMAN) {
			Log.d("RA", "Creating BoxerGame vs HUMAN");
			mGame = new BoxerGame(this, mGameId, mPlayerId);

		} else if (mGameMode == BoxerGame.GAME_MODE_COMPUTER) {
			Log.d("RA", "Creating BoxerGame vs COMPUTER");
			mGame = new BoxerGame(this, mComputerDifficulty);
		}

		// //////
		// Erases the background of the textviews every 2 seconds
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
		// //////

		mDetector = new GestureDetector(this, new PlayerGestureDetector());

		mGame.startGame();

	}

	public void gameOver(boolean localDidWin) {
		Intent endScreenIntent = new Intent(this, MatchDetailsActivity.class);

		String gameWinnerId;

		if (localDidWin) {
			gameWinnerId = mPlayerId;
		} else {
			gameWinnerId = mPlayerId
					.equals(getString(R.string.fb_game_player_joiner)) ? getString(R.string.fb_game_player_creator)
					: getString(R.string.fb_game_player_joiner);
		}

		endScreenIntent.putExtra(KEY_GAME_WINNER_ID, gameWinnerId);
		endScreenIntent.putExtra(KEY_GAME_MODE, mGameMode);
		endScreenIntent.putExtra(KEY_COMPUTER_DIFFICULTY, mComputerDifficulty);
		endScreenIntent.putExtra(KEY_GAME_ID, mGameId);
		endScreenIntent.putExtra(KEY_PLAYER_ID, mPlayerId);

		startActivity(endScreenIntent);
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGame.destroy();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mDetector.onTouchEvent(event);
	}

	/*
	 * LOCAL ANIMATIONS
	 */

	public void AnimateLocalRightJab() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(rightGlove, "translationY", 0,
				jab_distance, 0);
		a1.setDuration(jab_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalLeftJab() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(leftGlove, "translationY", 0, jab_distance,
				0);
		a1.setDuration(jab_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalRightHook() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(rightGlove, "translationX", 0,
				hook_distance, 0);
		a1.setDuration(hook_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalLeftHook() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(leftGlove, "translationX", 0,
				-hook_distance, 0);
		a1.setDuration(hook_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalRightUppercut() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(rightGlove, "translationY", 0,
				uppercut_distance, 0);
		a1.setDuration(uppercut_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalLeftUppercut() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(leftGlove, "translationY", 0,
				uppercut_distance, 0);
		a1.setDuration(uppercut_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateLocalBlocking() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1, a2;
		a1 = ObjectAnimator.ofFloat(leftGlove, "translationX", 0, -block_distance * 2, 0);
		a1.setDuration(block_speed);
		a2 = ObjectAnimator
				.ofFloat(rightGlove, "translationX", 0, block_distance * 2, 0);
		a2.setDuration(block_speed);

		aSet.playTogether(a1, a2);
		aSet.start();
	}


	/*
	 * REMOTE ANIMATIONS
	 */

	public void AnimateRemoteRightJab() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(enemyRightGlove, "translationY", 0,
				-jab_distance, 0);
		a1.setDuration(jab_speed);
		aSet.play(a1);
		aSet.start();

	}

	public void AnimateRemoteLeftJab() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(enemyLeftGlove, "translationY",
				0, -jab_distance, 0);
		a1.setDuration(jab_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateRemoteRightHook() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(enemyRightGlove, "translationX",
				0, -hook_distance / 2, 0);
		a1.setDuration(hook_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateRemoteLeftHook() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1;
		a1 = ObjectAnimator.ofFloat(enemyLeftGlove, "translationX",
				0, hook_distance / 2, 0);
		a1.setDuration(hook_speed);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateRemoteRightUppercut() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1, a2;
		a1 = ObjectAnimator.ofFloat(enemyRightGlove, "translationY",
				0, uppercut_distance / 2, 0);
		a1.setDuration(uppercut_speed);
		a2 = ObjectAnimator.ofFloat(enemyRightGlove, "translationY",
				-uppercut_distance / 2);
		a2.setDuration(uppercut_speed);

//		aSet.playSequentially(a1, a2);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateRemoteLeftUppercut() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1, a2;
		a1 = ObjectAnimator.ofFloat(enemyLeftGlove, "translationY",
				0, uppercut_distance / 2, 0);
		a1.setDuration(uppercut_speed);
		a2 = ObjectAnimator.ofFloat(enemyLeftGlove, "translationY",
				-uppercut_distance / 2);
		a2.setDuration(uppercut_speed);

//		aSet.playSequentially(a1, a2);
		aSet.play(a1);
		aSet.start();
	}

	public void AnimateRemoteBlocking() {
		AnimatorSet aSet = new AnimatorSet();
		ObjectAnimator a1, a2;
		a1 = ObjectAnimator.ofFloat(enemyLeftGlove, "translationX",
				0, block_distance / 2, 0);
		a1.setDuration(block_speed);
		a2 = ObjectAnimator.ofFloat(enemyRightGlove, "translationX",
				0, -block_distance / 2, 0);
		a2.setDuration(block_speed);

		aSet.playTogether(a1, a2);
		aSet.start();
	}

	/*
	 * Private gesture detector class
	 */

	private class PlayerGestureDetector extends
			GestureDetector.SimpleOnGestureListener {
		private static final String PGL = "PGL";

		/* Constants for gesture regions */
		private final int CENTER_X = ArenaActivity.this.getResources()
				.getDisplayMetrics().widthPixels / 2;

		/* Constants for gesture swipe restrictions */
		private static final int SWIPE_MIN_VELOCITY = 100;
		private static final int SWIPE_MAX_OFF_PATH = 100;

		/*
		 * Handles Jabs
		 */
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			float x = e.getX();

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
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			float e1_x = e1.getX();
			float e2_x = e2.getX();
			float e1_y = e1.getY();
			float e2_y = e2.getY();

			float dx = e2_x - e1_x;
			float dy = e2_y - e1_y;

			if (Math.abs(velocityX) >= SWIPE_MIN_VELOCITY
					|| Math.abs(velocityY) >= SWIPE_MIN_VELOCITY) { // Swipe
																	// fast
																	// enough

				if (e1_x >= CENTER_X) { // Right hits
					if (dy < SWIPE_MAX_OFF_PATH && dx < 0) { // swipe r->l
						Log.d(PGL, "right hook");
						mGame.localRightHook();

					} else if (dx < SWIPE_MAX_OFF_PATH && dy > 0) {
						Log.d(PGL, "block (right)");
						mGame.localBlock();

					} else if (dx < SWIPE_MAX_OFF_PATH && dy < 0) { // swipe up
						Log.d(PGL, "right uppercut");
						mGame.localRightUppercut();

					} else {
						Log.d(PGL, "right nothing");
					}

				} else { // Left hits

					if (dx < SWIPE_MAX_OFF_PATH && dy < 0) { // swipe up
						Log.d(PGL, "left uppercut");
						mGame.localLeftUppercut();

					} else if (dx < SWIPE_MAX_OFF_PATH && dy > 0) {
						Log.d(PGL, "block (left)");
						mGame.localBlock();

					} else if (dy < SWIPE_MAX_OFF_PATH && dx > 0) { // swipe
																	// r->l
						Log.d(PGL, "left hook");
						mGame.localLeftHook();

					} else {
						Log.d(PGL, "left nothing");
					}

				}
			} else {
				Log.d(PGL, "not fast enough");
			}

			return true;
		}
	}
}
