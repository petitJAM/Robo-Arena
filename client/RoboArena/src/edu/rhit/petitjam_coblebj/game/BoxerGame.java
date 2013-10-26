package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.util.Log;

public class BoxerGame {
	
	private static final String BG = "BG";
	
	private LocalPlayer local;
	private RemotePlayer remote;
	private boolean PVP;
	
	// Damage associate with each punch
	private static final int JAB_DMG = 10;
	private static final int HOOK_DMG = 20;
	private static final int UPPERCUT_DMG = 30;
	private static final int BLOCKING_DELAY = 1000; 
	private static final int JAB_COOLDOWN = 500;
	private static final int HOOK_COOLDOWN = 750;
	private static final int UPPERCUT_COOLDOWN = 1000;
	
	// private GameView gview;
	
	public BoxerGame(Context context) {
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
		Log.d(BG, "Local Left Jab");
		 if (local.getActionsAllowed()) {
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
		Log.d(BG, "Local Right Jab");
		if (local.getActionsAllowed()) {
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
		Log.d(BG, "Local Left Hook");
		if (local.getActionsAllowed()) {
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
		Log.d(BG, "Local Right Hook");
		if (local.getActionsAllowed()) {
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
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
		Log.d(BG, "Local Left Uppercut");
	}
	
	public void localRightUppercut() {
		if (local.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
		Log.d(BG, "Local Right Uppercut");
	}
	
	public void localBlock() {
		Log.d(BG, "Local Block");
		local.setBlocking(true);
		local.startActionDelay(BLOCKING_DELAY);
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
		Log.d(BG, "Remote Left Jab");
	}
	
	public void remoteRightJab() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
		Log.d(BG, "Remote Right Jab");
	}
	
	public void remoteLeftHook() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
		Log.d(BG, "Remote Left Hook");
	}
	
	public void remoteRightHook() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
		Log.d(BG, "Remote Right Hook");
	}
	
	public void remoteLeftUppercut() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
		Log.d(BG, "Remote Left Uppercut");
	}
	
	public void remoteRightUppercut() {
		if (remote.getActionsAllowed()) {
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
			 }
			 printHealth();
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
		Log.d(BG, "Remote Righ Uppercut");
	}
	
	public void remoteBlock() {
		Log.d(BG, "Remote Block");
		remote.setBlocking(true);
		remote.startActionDelay(BLOCKING_DELAY);
	}
}
