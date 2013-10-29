package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;

public abstract class Player {
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

	public Player() {
		// TODO: Initial sync with Firebase
		health = 100;
		leftActionsAllowed = true; 
		rightActionsAllowed = true;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		// TODO: Sync with Firebase
		this.health = health;
	}

	public void decrementHealth(int damage) {
		// TODO: Sync with Firebase
		this.health -= damage;
	}

	public void incrementHealth(int restoredAmount) {
		// TODO: Sync with Firebase
		this.health += restoredAmount;
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
}
