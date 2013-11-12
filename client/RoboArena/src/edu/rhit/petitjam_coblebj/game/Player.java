package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;
import edu.rhit.petitjam_coblebj.roboarena.MainMenuActivity;

public abstract class Player {

	private BoxerGame mGame;

	private int mHealth;
	private boolean mLeftActionsAllowed;
	private boolean mRightActionsAllowed;
	private boolean mBlocking;

	public static final int NUMBER_OF_ACTIONS = 7;
	public static final int ACTION_LEFT_JAB = 0;
	public static final int ACTION_LEFT_HOOK = 1;
	public static final int ACTION_LEFT_UPPERCUT = 2;
	public static final int ACTION_RIGHT_JAB = 3;
	public static final int ACTION_RIGHT_HOOK = 4;
	public static final int ACTION_RIGHT_UPPERCUT = 5;
	public static final int ACTION_BLOCK = 6;

	/**
	 * Create a new player in game. Uses a null connection to Firebase (no syncing to anything)
	 * 
	 * @param game
	 */
	public Player(BoxerGame game) {
		mGame = game;
		mHealth = 100;
		mLeftActionsAllowed = true;
		mRightActionsAllowed = true;
	}
	
	public abstract void end();

	public BoxerGame getGame() {
		return mGame;
	}

	public int getHealth() {
		return mHealth;
	}
	
	public void setHealthHelper(int health) {
		mHealth = health;
	}
	
	public void setLeftActionsAllowedHelper(boolean allowed) {
		mLeftActionsAllowed = allowed;
	}
	
	public void setRightActionsAllowedHelper(boolean allowed) {
		mRightActionsAllowed = allowed;
	}
	
	public void setBlockingHelper(boolean blocking) {
		mBlocking = blocking;
	}

	public abstract void setHealth(int health);
	public abstract void decrementHealth(int damage);
	public abstract void incrementHealth(int restoredAmount);
	
	public abstract void setLeftActionsAllowed(boolean allowed);
	public abstract void setRightActionsAllowed(boolean allowed);
	public abstract void setBlocking(boolean blocking);
	
	public boolean getLeftActionsAllowed() {
		return mLeftActionsAllowed;
	}

	public boolean getRightActionsAllowed() {
		return mRightActionsAllowed;
	}

	public boolean isBlocking() {
		return mBlocking;
	}
	
	public boolean getBlocking() {
		return mBlocking;
	}

	public void startLeftActionDelay(int timeInMillis) {
		setLeftActionsAllowed(false);

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				setLeftActionsAllowed(true);
				setBlocking(false);
			}

		}, timeInMillis);
	}

	public void startRightActionDelay(int timeInMillis) {
		setRightActionsAllowed(false);

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				setRightActionsAllowed(true);
				setBlocking(false);
			}

		}, timeInMillis);
	}

	public String getString(int resId) {
		return MainMenuActivity.getContext().getResources().getString(resId);
	}
}
