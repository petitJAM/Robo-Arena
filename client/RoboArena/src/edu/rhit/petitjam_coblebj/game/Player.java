package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;

public abstract class Player {
	private int health;
	private boolean actionsAllowed = true;

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
