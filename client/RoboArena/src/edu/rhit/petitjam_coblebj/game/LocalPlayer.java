package edu.rhit.petitjam_coblebj.game;

import android.util.Log;

import com.firebase.client.Firebase;

import edu.rhit.petitjam_coblebj.roboarena.R;

public class LocalPlayer extends Player {

	private FirebaseIOHandler mFbHandler;
	
	// Constructor for local AI game
	public LocalPlayer(BoxerGame game) {
		super(game);
		mFbHandler = new NullFirebaseIOHandler();
	}
	
	// Constructor for PVP game
	public LocalPlayer(BoxerGame game, String gameId, String playerId) {
		super(game);
		mFbHandler = new PVPFirebaseIOHandler(gameId, playerId);
	}
	
	@Override
	public void end() {
		mFbHandler.end();
	}
	
	@Override
	public void setHealth(int health) {
		super.setHealthHelper(health);
		mFbHandler.setHealth(health);
	}
	
	@Override
	public void decrementHealth(int damage) {
		super.setHealthHelper(getHealth() - damage);
		mFbHandler.setHealth(getHealth());
	}
	
	@Override
	public void incrementHealth(int damage) {
		super.setHealthHelper(getHealth() + damage);
		mFbHandler.setHealth(getHealth());
	}
	
	@Override
	public void setLeftActionsAllowed(boolean allowed) {
		super.setLeftActionsAllowedHelper(allowed);
		mFbHandler.setLeftActionsAllowed(allowed);
	}
	
	@Override
	public void setRightActionsAllowed(boolean allowed) {
		super.setRightActionsAllowedHelper(allowed);
		mFbHandler.setRightActionsAllowed(allowed);
	}
	
	@Override
	public void setBlocking(boolean blocking) {
		super.setBlockingHelper(blocking);
		mFbHandler.setBlocking(getBlocking());
	}
	
	/* ******* */
	/* Actions */
	/* ******* */
	
	public void leftJab() {
		Log.d("RA", "leftJab in LocalPlayer");
		mFbHandler.incrementLeftJab();
	}
	
	public void leftHook() {
		Log.d("RA", "leftHook in LocalPlayer");
		mFbHandler.incrementLeftHook();
	}
	
	public void leftUppercut() {
		Log.d("RA", "leftUppercut in LocalPlayer");
		mFbHandler.incrementLeftUppercut();
	}
	
	public void rightJab() {
		Log.d("RA", "rightJab in LocalPlayer");
		mFbHandler.incrementRightJab();
	}
	
	public void rightHook() {
		Log.d("RA", "rightHook in LocalPlayer");
		mFbHandler.incrementRightHook();
	}
	
	public void rightUppercut() {
		Log.d("RA", "rightUppercut in LocalPlayer");
		mFbHandler.incrementRightUppercut();
	}
	
	public void block() {
		Log.d("RA", "block in LocalPlayer");
		mFbHandler.incrementBlock();
		mFbHandler.setBlocking(true);
	}
	
	/* ******* */

	private interface FirebaseIOHandler {
		public void setHealth(int health);

		public void setLeftActionsAllowed(boolean allowed);

		public void setRightActionsAllowed(boolean allowed);

		public void setBlocking(boolean blocking);

		/*
		 * Increment the action counters
		 */
		public void incrementLeftJab();

		public void incrementLeftHook();

		public void incrementLeftUppercut();

		public void incrementRightJab();

		public void incrementRightHook();

		public void incrementRightUppercut();

		public void incrementBlock();
		
		public void end();
	}

	private class PVPFirebaseIOHandler implements FirebaseIOHandler {
		private Firebase mFb;

		private Firebase mInfoFB;
		private Firebase mHealthFB;
		private Firebase mLeftActionsAllowedFB;
		private Firebase mRightActionsAllowedFB;

		private Firebase mLeftJabFB;
		private Firebase mLeftHookFB;
		private Firebase mLeftUppercutFB;
		private Firebase mRightJabFB;
		private Firebase mRightHookFB;
		private Firebase mRightUppercutFB;
		private Firebase mBlockFB;
		private Firebase mBlockingFB; // note the difference

		private int mLeftJabCounter = 0;
		private int mLeftHookCounter = 0;
		private int mLeftUppercutCounter = 0;
		private int mRightJabCounter = 0;
		private int mRightHookCounter = 0;
		private int mRightUppercutCounter = 0;
		private int mBlockCounter = 0;

		public PVPFirebaseIOHandler(String gameId, String playerId) {
			String firebaseURL = getString(R.string.roboarena_firebase_games);
			mFb = new Firebase(firebaseURL + "/" + gameId + "/" + playerId);
			
			mInfoFB = mFb.child(getString(R.string.fb_game_player_is_connected));

			mHealthFB = mFb.child(getString(R.string.fb_game_player_health));
			mLeftActionsAllowedFB = mFb.child(getString(R.string.fb_game_player_left_actions_allowed));
			mRightActionsAllowedFB = mFb.child(getString(R.string.fb_game_player_right_actions_allowed));

			mLeftJabFB = mFb.child(getString(R.string.fb_game_player_left_jab));
			mLeftHookFB = mFb.child(getString(R.string.fb_game_player_left_hook));
			mLeftUppercutFB = mFb.child(getString(R.string.fb_game_player_left_uppercut));

			mRightJabFB = mFb.child(getString(R.string.fb_game_player_right_jab));
			mRightHookFB = mFb.child(getString(R.string.fb_game_player_right_hook));
			mRightUppercutFB = mFb.child(getString(R.string.fb_game_player_right_uppercut));

			mBlockFB = mFb.child(getString(R.string.fb_game_player_block));
			mBlockingFB = mFb.child(getString(R.string.fb_game_player_blocking));
		}

		@Override
		public void setHealth(int health) {
			mHealthFB.setValue(Integer.valueOf(health));
		}

		@Override
		public void setLeftActionsAllowed(boolean allowed) {
			mLeftActionsAllowedFB.setValue(Boolean.valueOf(allowed));
		}

		@Override
		public void setRightActionsAllowed(boolean allowed) {
			mRightActionsAllowedFB.setValue(Boolean.valueOf(allowed));
		}

		@Override
		public void incrementLeftJab() {
			Log.d("RA", "increment left jab to " + (mLeftJabCounter + 1));
			mLeftJabFB.setValue(Integer.valueOf(++mLeftJabCounter));
		}

		@Override
		public void incrementLeftHook() {
			mLeftHookFB.setValue(Integer.valueOf(++mLeftHookCounter));
		}

		@Override
		public void incrementLeftUppercut() {
			mLeftUppercutFB.setValue(Integer.valueOf(++mLeftUppercutCounter));
		}

		@Override
		public void incrementRightJab() {
			mRightJabFB.setValue(Integer.valueOf(++mRightJabCounter));
		}

		@Override
		public void incrementRightHook() {
			mRightHookFB.setValue(Integer.valueOf(++mRightHookCounter));
		}

		@Override
		public void incrementRightUppercut() {
			mRightUppercutFB.setValue(Integer.valueOf(++mRightUppercutCounter));
		}

		@Override
		public void incrementBlock() {
			mBlockFB.setValue(Integer.valueOf(++mBlockCounter));
		}

		@Override
		public void setBlocking(boolean blocking) {
			mBlockingFB.setValue(Boolean.valueOf(blocking));
		}

		@Override
		public void end() {
			mInfoFB.setValue(Boolean.FALSE);
		}
	}

	/*
	 * Absorbs calls to sync with firebase. Used when playing local vs AI.
	 */
	private class NullFirebaseIOHandler implements FirebaseIOHandler {

		public NullFirebaseIOHandler() {}

		@Override
		public void setHealth(int health) {}

		@Override
		public void setLeftActionsAllowed(boolean allowed) {}

		@Override
		public void setRightActionsAllowed(boolean allowed) {}

		@Override
		public void incrementLeftJab() {}

		@Override
		public void incrementLeftHook() {}

		@Override
		public void incrementLeftUppercut() {}

		@Override
		public void incrementRightJab() {}

		@Override
		public void incrementRightHook() {}

		@Override
		public void incrementRightUppercut() {}

		@Override
		public void incrementBlock() {}

		@Override
		public void setBlocking(boolean blocking) {}

		@Override
		public void end() {}
	}
}
