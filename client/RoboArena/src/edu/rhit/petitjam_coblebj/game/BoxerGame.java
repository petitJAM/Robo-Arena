package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.util.Log;

public class BoxerGame {
	
	private static final String BG = "BG";
	
	private LocalPlayer local;
	private RemotePlayer remote;
	private boolean PVP;
	
	// Damage associate with each punch
	private static final int JAB_DMG = 1;
	private static final int HOOK_DMG = 2;
	private static final int UPPERCUT_DMG = 3;
	private static final int BLOCKING_DELAY = 1000; 
	private static final int JAB_COOLDOWN = 1000;
	private static final int HOOK_COOLDOWN = 1200;
	private static final int UPPERCUT_COOLDOWN = 2000;
	
	// private GameView gview;
	
	public BoxerGame(Context context) {
		local = new LocalPlayer();
		remote = new ComputerPlayer(this);
	}

	public void startGame() {
		remote.start();
	}

	public void newGame(boolean pvp) {
		this.PVP = pvp;
		this.local = new LocalPlayer();
		if(pvp){
			this.remote = new HumanPlayer();
		} else {
			this.remote = new ComputerPlayer();
		}
	}

	public void playGame() {
		// not sure on this game loop yet
		if (PVP){
			// start ai computer game
		} else {
			// receive foreign moves from server
		}
	}
	
	public void pauseGame() {
		
	}
	
	private void printHealth(){
		Log.d(BG, "Local HP: " + this.local.getHealth() + " Remote HP: " + this.remote.getHealth());
	}
	
	/* LOCAL PLAYER ACTIONS */
	
	public void localLeftJab() {
		 if (local.getActionsAllowed()) {
				Log.d(BG, "Local Left Jab");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
			 }
			 local.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localRightJab() {
		if (local.getActionsAllowed()) {
			Log.d(BG, "Local Right Jab");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
			 }
			 local.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localLeftHook() {
		if (local.getActionsAllowed()) {
			Log.d(BG, "Local Left Hook");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
			 }
			 local.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localRightHook() {
		if (local.getActionsAllowed()) {
			Log.d(BG, "Local Right Hook");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
			 }
			 local.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localLeftUppercut() {
		if (local.getActionsAllowed()) {
			Log.d(BG, "Local Left Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localRightUppercut() {
		if (local.getActionsAllowed()) {
			Log.d(BG, "Local Right Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localBlock() {
		if (!local.isBlocking()) {
			Log.d(BG, "Local Block");
			local.setBlocking(true);
			local.startActionDelay(BLOCKING_DELAY);
		}
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Left Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteRightJab() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Right Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteLeftHook() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Left Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
	} 
	
	public void remoteRightHook() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Right Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void remoteLeftUppercut() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Left Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteRightUppercut() {
		if (remote.getActionsAllowed()) {
			Log.d(BG, "Remote Righ Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteBlock() {
		if (!remote.isBlocking()) {
			Log.d(BG, "Remote Block");
			remote.setBlocking(true);
			remote.startActionDelay(BLOCKING_DELAY);
		}
	}
}
