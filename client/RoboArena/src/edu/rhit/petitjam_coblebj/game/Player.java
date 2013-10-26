package edu.rhit.petitjam_coblebj.game;

import android.os.Handler;

public abstract class Player {
	private int health;
	private boolean actionsAllowed = true;
	private boolean blocking;

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void decrementHealth(int damage){
		this.health -= damage;
	}
	
	public void incrementHealth(int restoredAmount){
		this.health += restoredAmount;
	}
	
	public boolean getActionsAllowed() {
		return actionsAllowed;
	}
	
	public boolean isBlocking(){
		return this.blocking;
	}
	
	public void setBlocking(boolean b){
		this.blocking = b;
	}

	public void startActionDelay(int timeInMillis) {
		actionsAllowed = false;
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				actionsAllowed = true;
				blocking = false;
			}
			
		}, timeInMillis);
	}
}
