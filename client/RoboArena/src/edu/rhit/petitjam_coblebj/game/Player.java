package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;

import com.firebase.client.Firebase;

import edu.rhit.petitjam_coblebj.roboarena.MainMenuActivity;
import edu.rhit.petitjam_coblebj.roboarena.R;

public abstract class Player {

	private BoxerGame mGame;
	private FirebaseIOHandler mFbHandler;

	private int health;
	private boolean leftActionsAllowed;
	private boolean rightActionsAllowed;
	private boolean blocking;

	public static final int NUMBER_OF_ACTIONS = 7;
	public static final int ACTION_LEFT_JAB = 0;
	public static final int ACTION_LEFT_HOOK = 1;
	public static final int ACTION_LEFT_UPPERCUT = 2;
	public static final int ACTION_RIGHT_JAB = 3;
	public static final int ACTION_RIGHT_HOOK = 4;
	public static final int ACTION_RIGHT_UPPERCUT = 5;
	public static final int ACTION_BLOCK = 6;

	public Player(BoxerGame game) {
		mGame = game;
		health = 100;
		leftActionsAllowed = true;
		rightActionsAllowed = true;

		mFbHandler = new NullFirebaseIOHandler();
	}

	public Player(BoxerGame game, String gameId, String playerId) {
		mGame = game;
		health = 100;
		leftActionsAllowed = true;
		rightActionsAllowed = true;

		// TODO: Initial sync with Firebase
		mFbHandler = new PVPFirebaseIOHandler(gameId, playerId);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		// TODO: Sync with Firebase
		this.health = health;
		mFbHandler.setHealth(health);
	}

	public void decrementHealth(int damage) {
		// TODO: Sync with Firebase
		this.health -= damage;
		mFbHandler.setHealth(health);
	}

	public void incrementHealth(int restoredAmount) {
		// TODO: Sync with Firebase
		this.health += restoredAmount;
		mFbHandler.setHealth(health);
	}
	
	public boolean getLeftActionsAllowed() {
		return leftActionsAllowed;
	}

	public boolean getRightActionsAllowed() {
		return rightActionsAllowed;
	}

	public boolean isBlocking() {
		return this.blocking;
	}

	public void setBlocking(boolean b) {
		// TODO: Sync with Firebase
		this.blocking = b;
	}

	public void startLeftActionDelay(int timeInMillis) {
		leftActionsAllowed = false;

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO: Sync with Firebase
				leftActionsAllowed = true;
				blocking = false;
			}

		}, timeInMillis);
	}

	public void startRightActionDelay(int timeInMillis) {
		rightActionsAllowed = false;

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO: Sync with Firebase
				rightActionsAllowed = true;
				blocking = false;
			}

		}, timeInMillis);
	}

	public BoxerGame getGame() {
		return mGame;
	}

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
	}

	private class PVPFirebaseIOHandler implements FirebaseIOHandler {
		private Firebase mFb;

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
			String firebaseURL = MainMenuActivity.getContext().getResources()
					.getString(R.string.roboarena_firebase_url);
			mFb = new Firebase(firebaseURL + "/" + gameId + "/" + playerId);

			mHealthFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_health));
			mLeftActionsAllowedFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_actions_allowed));
			mRightActionsAllowedFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_actions_allowed));

			mLeftJabFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_jab));
			mLeftHookFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_hook));
			mLeftUppercutFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_left_uppercut));

			mRightJabFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_jab));
			mRightHookFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_hook));
			mRightUppercutFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_right_uppercut));

			mBlockFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_block));
			mBlockingFB = mFb.child(MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_blocking));
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
	}
}
