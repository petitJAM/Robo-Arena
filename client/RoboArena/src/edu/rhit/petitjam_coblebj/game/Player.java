package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;

public abstract class Player {
	private int health;
	private boolean actionsAllowed;
	
	public static final int NUMBER_OF_ACTIONS = 7;
	public static final int ACTION_LEFT_JAB = 0;
	public static final int ACTION_LEFT_HOOK = 1;
	public static final int ACTION_LEFT_UPPERCUT = 2;
	public static final int ACTION_RIGHT_JAB = 3;
	public static final int ACTION_RIGHT_HOOK = 4;
	public static final int ACTION_RIGHT_UPPERCUT = 5;
	public static final int ACTION_BLOCK = 6;
	
	public Player() {
		health = 100;
		actionsAllowed = true;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean getActionsAllowed() {
		return actionsAllowed;
	}

	public void startActionDelay(int timeInMillis) {
		actionsAllowed = false;
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				actionsAllowed = true;
			}
			
		}, timeInMillis);
	}
}
