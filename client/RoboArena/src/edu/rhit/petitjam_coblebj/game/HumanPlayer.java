package edu.rhit.petitjam_coblebj.game;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import edu.rhit.petitjam_coblebj.roboarena.MainMenuActivity;
import edu.rhit.petitjam_coblebj.roboarena.R;

/*
 * This remote player listens for value events on actions & actions_allowed
 * 
 * It does NOT listen for changes in its own health. The BoxerGame on this client will set its health.
 */
public class HumanPlayer extends RemotePlayer {

	private Firebase mFb;

	private Firebase mHealthFB;

	private String mUsername;

	private int mBlockCounter = 0;
	private int mLeftJabCounter = 0;
	private int mLeftHookCounter = 0;
	private int mLeftUppercutCounter = 0;
	private int mRightJabCounter = 0;
	private int mRightHookCounter = 0;
	private int mRightUppercutCounter = 0;

	public HumanPlayer(BoxerGame game, String gameId, String remotePlayerId) {
		super(game);

		String firebaseURL = getString(R.string.roboarena_firebase_games);
		mFb = new Firebase(firebaseURL + "/" + gameId + "/" + remotePlayerId);

		mHealthFB = mFb.child(getString(R.string.fb_game_player_health));

		/* **************** */
		/* ACTION LISTENERS */
		/* **************** */

		// actions allowed

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_actions_allowed))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						setLeftActionsAllowed((Boolean)snap.getValue());
					}

					@Override
					public void onCancelled() {}
				});

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_actions_allowed))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						setRightActionsAllowed((Boolean)snap.getValue());
					}

					@Override
					public void onCancelled() {}
				});

		// blocking

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_blocking))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						setBlocking((Boolean)snap.getValue());
					}

					@Override
					public void onCancelled() {}
				});

		// left

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_jab))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mLeftJabCounter = ((Long)snap.getValue()).intValue();
						if (mLeftJabCounter != 0) {
							getGame().remoteLeftJab();
							Log.d("RA", "RemotePlayer used left jab - " + mLeftJabCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_hook))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mLeftHookCounter = ((Long)snap.getValue()).intValue();
						if (mLeftHookCounter != 0) {
							getGame().remoteLeftHook();
							Log.d("RA", "RemotePlayer used left hook - " + mLeftHookCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_uppercut))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mLeftUppercutCounter = ((Long)snap.getValue()).intValue();
						if (mLeftUppercutCounter != 0) {
							getGame().remoteLeftUppercut();
							Log.d("RA", "RemotePlayer used left uppercut - " + mLeftUppercutCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		// right

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_jab))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mRightJabCounter = ((Long)snap.getValue()).intValue();
						if (mRightJabCounter != 0) {
							getGame().remoteRightJab();
							Log.d("RA", "RemotePlayer used right jab - " + mRightJabCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_hook))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mRightHookCounter = ((Long)snap.getValue()).intValue();
						if (mRightHookCounter != 0) {
							getGame().remoteRightHook();
							Log.d("RA", "RemotePlayer used right hook - " + mRightHookCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_uppercut))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mRightUppercutCounter = ((Long)snap.getValue()).intValue();
						if (mRightUppercutCounter != 0) {
							getGame().remoteRightUppercut();
							Log.d("RA", "RemotePlayer used right uppercut - " + mRightUppercutCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		// block

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_block))
				.addValueEventListener(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						mBlockCounter = ((Long)snap.getValue()).intValue();
						if (mBlockCounter != 0) {
							getGame().remoteBlock();
							Log.d("RA", "RemotePlayer used block - " + mBlockCounter);
						}
					}

					@Override
					public void onCancelled() {}
				});

		// username

		mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_name))
				.addListenerForSingleValueEvent(new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot snap) {
						if (snap != null) {
							mUsername = (String)snap.getValue();
						} else {
							mUsername = getString(R.string.default_opponent_name);
						}
					}

					@Override
					public void onCancelled() {}
				});
	}

	@Override
	public void incrementHealth(int restoredAmount) {
		super.setHealthHelper(getHealth() + restoredAmount);
		mHealthFB.setValue(getHealth());
	}

	@Override
	public void decrementHealth(int damage) {
		super.setHealthHelper(getHealth() - damage);
		mHealthFB.setValue(Integer.valueOf(getHealth()));
	}

	@Override
	public void setHealth(int health) {
		super.setHealthHelper(health);
		mHealthFB.setValue(Integer.valueOf(getHealth()));
	}

	@Override
	public void start() {
		// Do nothing?
	}

	@Override
	public void end() {
		mFb.child(getString(R.string.fb_game_player_is_connected)).setValue(Boolean.FALSE);
	}

	@Override
	public String getUsername() {
		return mUsername;
	}
}
