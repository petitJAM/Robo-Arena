package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.util.Log;

public class BoxerGame {
	
	private static final String BG = "BG";
	
	private LocalPlayer local;
	private RemotePlayer remote;
	
	// private GameView gview;
	
	public BoxerGame(Context context) {
		remote = new ComputerPlayer(this); 
	}

	public void startGame() {
		remote.start();
	}
	
	/* LOCAL PLAYER ACTIONS */
	
	public void localLeftJab() {
		// if (local.getActionsAllowed()) {
			// update game view
			// check what happens to other player (check if block, etc) (also, maybe if both Ljab at the same time, they hit midair?)
			// more update game view? idk
		// }
		Log.d(BG, "Local Left Jab");
	}
	
	public void localRightJab() {
		Log.d(BG, "Local Right Jab");
	}
	
	public void localLeftHook() {
		Log.d(BG, "Local Left Hook");
	}
	
	public void localRightHook() {
		Log.d(BG, "Local Right Hook");
	}
	
	public void localLeftUppercut() {
		Log.d(BG, "Local Left Uppercut");
	}
	
	public void localRightUppercut() {
		Log.d(BG, "Local Right Uppercut");
	}
	
	public void localBlock() {
		Log.d(BG, "Local Block");
	}
	
	/* REMOTE PLAYER ACTIONS */
	
	public void remoteLeftJab() {
		Log.d(BG, "Remote Left Jab");
	}
	
	public void remoteRightJab() {
		Log.d(BG, "Remote Right Jab");
	}
	
	public void remoteLeftHook() {
		Log.d(BG, "Remote Left Hook");
	}
	
	public void remoteRightHook() {
		Log.d(BG, "Remote Right Hook");
	}
	
	public void remoteLeftUppercut() {
		Log.d(BG, "Remote Left Uppercut");
	}
	
	public void remoteRightUppercut() {
		Log.d(BG, "Remote Righ Uppercut");
	}
	
	public void remoteBlock() {
		Log.d(BG, "Remote Block");
	}
}
