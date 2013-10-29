package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.roboarena.ArenaActivity;

public class BoxerGame {

	public static final int GAME_MODE_HUMAN = 0;
	public static final int GAME_MODE_COMPUTER = 1;
	
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
	
	// Cooldowns for each punch
	private static final int BLOCKING_COOLDOWN = 1000; 
	private static final int JAB_COOLDOWN = 1000;
	private static final int HOOK_COOLDOWN = 1200;
	private static final int UPPERCUT_COOLDOWN = 2000;
	
	// private GameView gview;
	private static TextView player1_hp_textview;
	private static TextView player2_hp_textview;
	
	public BoxerGame(Context context, int gameMode) {
		mArena = (ArenaActivity)context;
		
		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
		local = new LocalPlayer();
		
		if (gameMode == GAME_MODE_COMPUTER) {
			remote = new ComputerPlayer(this, ComputerPlayer.COMPUTER_PLAYER_DIFFICULTY_EASY);
		} else {
			// TODO: Pass whatever params are needed
			remote = new HumanPlayer();
		}
	}
	
	public BoxerGame(Context context, int gameMode, int computerDifficulty) {
		mArena = (ArenaActivity)context;
		
		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
		local = new LocalPlayer();
		
		if (gameMode == GAME_MODE_COMPUTER) {
			remote = new ComputerPlayer(this, computerDifficulty);
		} else {
			// TODO: Pass whatever params are needed
			remote = new HumanPlayer();
		}
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
			// TODO: Fix this hardcoded Difficulty Easy
			this.remote = new ComputerPlayer(this, ComputerPlayer.COMPUTER_PLAYER_DIFFICULTY_EASY);
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
		
		// if either player < 0, end game
	}	
	
	/* LOCAL PLAYER ACTIONS */
	
	public void localLeftJab() {
		 if (local.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Jab");
				
			mArena.l_jab.setBackgroundColor(Color.CYAN);
				
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startLeftActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localRightJab() {
		if (local.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Jab");
			
			mArena.r_jab.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startRightActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localLeftHook() {
		if (local.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Hook");
			
			mArena.l_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startLeftActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localRightHook() {
		if (local.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Hook");
			
			mArena.r_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startRightActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localLeftUppercut() {
		if (local.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Uppercut");
			
			mArena.l_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startLeftActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localRightUppercut() {
		if (local.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Uppercut");
			
			mArena.r_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!remote.isBlocking()){
				 remote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 local.startRightActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localBlock() {
		if (!local.isBlocking()) {
			Log.d(BG_L, "Local Block");
			local.setBlocking(true);
			local.startLeftActionDelay(BLOCKING_COOLDOWN);
			local.startRightActionDelay(BLOCKING_COOLDOWN);
		}
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		if (remote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startLeftActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteRightJab() {
		if (remote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Jab");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startRightActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteLeftHook() {
		if (remote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startLeftActionDelay(HOOK_COOLDOWN);
		 }
	} 
	
	public void remoteRightHook() {
		if (remote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Hook");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startRightActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void remoteLeftUppercut() {
		if (remote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startLeftActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteRightUppercut() {
		if (remote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!local.isBlocking()){
				 local.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 remote.startRightActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteBlock() {
		if (!remote.isBlocking()) {
			Log.d(BG_R, "Remote Block");
			remote.setBlocking(true);
			local.startLeftActionDelay(BLOCKING_COOLDOWN);
			local.startRightActionDelay(BLOCKING_COOLDOWN);
		}
	}
}
