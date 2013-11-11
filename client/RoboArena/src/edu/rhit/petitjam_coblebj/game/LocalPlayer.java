package edu.rhit.petitjam_coblebj.game;

import com.firebase.client.Firebase;

import edu.rhit.petitjam_coblebj.roboarena.MainMenuActivity;
import edu.rhit.petitjam_coblebj.roboarena.R;

public class LocalPlayer extends Player {

	private FirebaseIOHandler mFbHandler;
	
	// TODO: Update constructors, both here and in Player
	
	// Constructor for local AI game
	public LocalPlayer(BoxerGame game) {
		super(game);
		mFbHandler = new NullFirebaseIOHandler();
	}
	
	// Constructor for PVP game
	public LocalPlayer(BoxerGame game, String gameId, String playerId) {
		super(game, gameId, playerId);
		mFbHandler = new PVPFirebaseIOHandler(gameId, playerId);
	}
	
	@Override
	public void setHealth(int health) {
		super.setHealth(health);
		mFbHandler.setHealth(health);
	}
	
	@Override
	public void decrementHealth(int damage) {
		super.decrementHealth(damage);
		mFbHandler.setHealth(getHealth());
	}
	
	@Override
	public void incrementHealth(int damage) {
		super.decrementHealth(damage);
		mFbHandler.setHealth(getHealth());
	}
	
	@Override
	public void setLeftActionsAllowed(boolean allowed) {
		super.setLeftActionsAllowed(allowed);
		mFbHandler.setLeftActionsAllowed(allowed);
	}
	
	@Override
	public void setRightActionsAllowed(boolean allowed) {
		super.setRightActionsAllowed(allowed);
		mFbHandler.setRightActionsAllowed(allowed);
	}
	
	@Override
	public void setBlocking(boolean blocking) {
		super.setBlocking(blocking);
		mFbHandler.setBlocking(getBlocking());
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
