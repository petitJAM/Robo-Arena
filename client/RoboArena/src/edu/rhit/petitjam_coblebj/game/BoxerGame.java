package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.roboarena.ArenaActivity;

public class BoxerGame {
	
	private static final String BG = "BG";
	private static final String BG_L = "BG_L";
	private static final String BG_R = "BG_R";
	
	private ArenaActivity mArena;
	
	private LocalPlayer local;
	private RemotePlayer remote;
	private boolean PVP;
	
	// Damage associate with each punch
	private static final int JAB_DMG = 1;
	private static final int HOOK_DMG = 2;
	private static final int UPPERCUT_DMG = 3;
	private static final int BLOCKING_COOLDOWN = 1000; 
	private static final int JAB_COOLDOWN = 1000;
	private static final int HOOK_COOLDOWN = 1200;
	private static final int UPPERCUT_COOLDOWN = 2000;
	
	// private GameView gview;
	private static TextView player1_hp_textview;
	private static TextView player2_hp_textview;
	
	public BoxerGame(Context context) {
		mArena = (ArenaActivity)context;
		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
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
//		Log.d(BG, "Local HP: " + this.local.getHealth() + " Remote HP: " + this.remote.getHealth());
	}
	
	public void updateHealth(){
		int p1hp = this.local.getHealth();
		int p2hp = this.remote.getHealth();
		player1_hp_textview.setText(Integer.toString(p1hp));
		player2_hp_textview.setText(Integer.toString(p2hp));
	}	
	
	/* LOCAL PLAYER ACTIONS */
	
	public void localLeftJab() {
		 if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Left Jab");
				
			mArena.l_jab.setBackgroundColor(Color.CYAN);
				
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localRightJab() {
		if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Right Jab");
			
			mArena.r_jab.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localLeftHook() {
		if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Left Hook");
			
			mArena.l_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localRightHook() {
		if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Right Hook");
			
			mArena.r_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localLeftUppercut() {
		if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Left Uppercut");
			
			mArena.l_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localRightUppercut() {
		if (local.getActionsAllowed()) {
			Log.d(BG_L, "Local Right Uppercut");
			
			mArena.r_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localBlock() {
		if (!local.isBlocking()) {
			Log.d(BG_L, "Local Block");
			local.setBlocking(true);
			local.startActionDelay(BLOCKING_COOLDOWN);
		}
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Left Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteRightJab() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Right Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteLeftHook() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Left Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
	} 
	
	public void remoteRightHook() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Right Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void remoteLeftUppercut() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Left Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteRightUppercut() {
		if (remote.getActionsAllowed()) {
			Log.d(BG_R, "Remote Right Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteBlock() {
		if (!remote.isBlocking()) {
			Log.d(BG_R, "Remote Block");
			remote.setBlocking(true);
			remote.startActionDelay(BLOCKING_COOLDOWN);
		}
	}
}
