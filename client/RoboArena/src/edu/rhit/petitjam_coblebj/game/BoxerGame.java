package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.roboarena.ArenaActivity;

public class BoxerGame {

	public static final int GAME_MODE_HUMAN = 0;
	public static final int GAME_MODE_COMPUTER = 1;
	
//	private static final String BG = "BG";
	private static final String BG_L = "BG_L";
	private static final String BG_R = "BG_R";
	
	private ArenaActivity mArena;
	
	private LocalPlayer mLocal;
	private RemotePlayer mRemote;
	
	private int mGameMode;
	private int mComputerDifficulty;
	
	// Damage associate with each punch
	private static final int JAB_DMG = 1;
	private static final int HOOK_DMG = 2;
	private static final int UPPERCUT_DMG = 3;
	
	// Cooldowns for each punch
	private static final int BLOCKING_COOLDOWN = 1000; 
	private static final int JAB_COOLDOWN = 300;
	private static final int HOOK_COOLDOWN = 600;
	private static final int UPPERCUT_COOLDOWN = 900;
	
	// private GameView gview;
	private static TextView player1_hp_textview;
	private static TextView player2_hp_textview;
		
	
	// TODO: Pass the gameId through to the players
	public BoxerGame(Context context, String gameId) {
		mArena = (ArenaActivity)context;
		mGameMode = GAME_MODE_HUMAN;
		
		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
		mLocal = new LocalPlayer();
		mRemote = new HumanPlayer();
	}
	
	public BoxerGame(Context context, int computerDifficulty) {
		mArena = (ArenaActivity)context;
		mGameMode = GAME_MODE_COMPUTER;
		mComputerDifficulty = computerDifficulty;
		
		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
		mLocal = new LocalPlayer();
		mRemote = new ComputerPlayer(this, computerDifficulty);
	}

	public void startGame() {
		mRemote.start();
	}

	public void newGame(boolean pvp) {
		mLocal = new LocalPlayer();
		
		if (mGameMode == GAME_MODE_HUMAN){
			mRemote = new HumanPlayer();
			
		} else {
			// TODO: Fix this hardcoded Difficulty Easy
			mRemote = new ComputerPlayer(this, mComputerDifficulty);
		}
	}

	public void playGame() {
		// not sure on this game loop yet
		if (mGameMode == GAME_MODE_COMPUTER){
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
		int p1hp = this.mLocal.getHealth();
		int p2hp = this.mRemote.getHealth();
		player1_hp_textview.setText(Integer.toString(p1hp));
		player2_hp_textview.setText(Integer.toString(p2hp));
		
		// TODO: if either player.hp < 0, end game
	}
	
	
	
	
	
	/* LOCAL PLAYER ACTIONS */
	
	public void localLeftJab() {
		 if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Jab");
				
			mArena.l_jab.setBackgroundColor(Color.CYAN);
				
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startLeftActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localRightJab() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Jab");
			
			mArena.r_jab.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startRightActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void localLeftHook() {
		if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Hook");
			
			mArena.l_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startLeftActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localRightHook() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Hook");
			
			mArena.r_hook.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startRightActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void localLeftUppercut() {
		if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Uppercut");
			
			mArena.l_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startLeftActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localRightUppercut() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Uppercut");
			
			mArena.r_up.setBackgroundColor(Color.CYAN);
			
			 // Tell gameView to update with a Ljab
			 if(!mRemote.isBlocking()){
				 mRemote.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mLocal.startRightActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void localBlock() {
		if (!mLocal.isBlocking()) {
			Log.d(BG_L, "Local Block");
			mLocal.setBlocking(true);
			mLocal.startLeftActionDelay(BLOCKING_COOLDOWN);
			mLocal.startRightActionDelay(BLOCKING_COOLDOWN);
		}
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Jab");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startLeftActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteRightJab() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Jab");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(JAB_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startRightActionDelay(JAB_COOLDOWN);
		 }
	}
	
	public void remoteLeftHook() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Hook");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startLeftActionDelay(HOOK_COOLDOWN);
		 }
	} 
	
	public void remoteRightHook() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Hook");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(HOOK_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startRightActionDelay(HOOK_COOLDOWN);
		 }
	}
	
	public void remoteLeftUppercut() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startLeftActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteRightUppercut() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Uppercut");
			 // Tell gameView to update with a Ljab
			 if(!mLocal.isBlocking()){
				 mLocal.decrementHealth(UPPERCUT_DMG);
				 // update remote player health bar
				 printHealth();
				 updateHealth();
			 }
			 mRemote.startRightActionDelay(UPPERCUT_COOLDOWN);
		 }
	}
	
	public void remoteBlock() {
		if (!mRemote.isBlocking()) {
			Log.d(BG_R, "Remote Block");
			mRemote.setBlocking(true);
			mLocal.startLeftActionDelay(BLOCKING_COOLDOWN);
			mLocal.startRightActionDelay(BLOCKING_COOLDOWN);
		}
	}
}
