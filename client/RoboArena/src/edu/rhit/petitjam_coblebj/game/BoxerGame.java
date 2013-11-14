package edu.rhit.petitjam_coblebj.game;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import edu.rhit.petitjam_coblebj.roboarena.ArenaActivity;
import edu.rhit.petitjam_coblebj.roboarena.MainMenuActivity;
import edu.rhit.petitjam_coblebj.roboarena.R;

public class BoxerGame {

	public static final int GAME_MODE_HUMAN = 0;
	public static final int GAME_MODE_COMPUTER = 1;

	// private static final String BG = "BG";
	private static final String BG_L = "BG_L";
	private static final String BG_R = "BG_R";

	private ArenaActivity mArena;

	private LocalPlayer mLocal;
	private RemotePlayer mRemote;
	
	// TODO: Use the game_running in /games/gameId/info

	// Damage associate with each punch
	private static final int JAB_DMG = 5;
	private static final int HOOK_DMG = 7;
	private static final int UPPERCUT_DMG = 10;

	// Cooldowns for each punch
	private static final int BLOCKING_COOLDOWN = 4000;
	private static final int JAB_COOLDOWN = 1000;
	private static final int HOOK_COOLDOWN = 1200;
	private static final int UPPERCUT_COOLDOWN = 2000;

	// private GameView gview;
	private static TextView player1_hp_textview;
	private static TextView player2_hp_textview;

	public BoxerGame(Context context, String gameId, String localPlayerId) {
		mArena = (ArenaActivity)context;

		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;
		
		// Set the remote player's ID
		String creatorId = MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_creator);
		String joinerId = MainMenuActivity.getContext().getResources().getString(R.string.fb_game_player_joiner);
		String remotePlayerId = localPlayerId.equals(creatorId) ? joinerId : creatorId;

		mLocal = new LocalPlayer(this, gameId, localPlayerId);
		mRemote = new HumanPlayer(this, gameId, remotePlayerId);
	}

	public BoxerGame(Context context, int computerDifficulty) {
		mArena = (ArenaActivity)context;

		player1_hp_textview = mArena.player1_hp_textview;
		player2_hp_textview = mArena.player2_hp_textview;

		mLocal = new LocalPlayer(this);
		mRemote = new ComputerPlayer(this, computerDifficulty);
	}
	
	public Context getContext() {
		return mArena;
	}

	public void startGame() {
		mRemote.start();
	}
	
	public void updateHealth(){
		int p1hp = this.mLocal.getHealth();
		int p2hp = this.mRemote.getHealth();
		player1_hp_textview.setText(Integer.toString(p1hp));
		player2_hp_textview.setText(Integer.toString(p2hp));
		
		// if either player < 0 or time is out, end game
		if(p1hp <= 0 || p2hp <= 0){
			mLocal.setLeftActionsAllowed(false);
			mLocal.setRightActionsAllowed(false);
			mRemote.setLeftActionsAllowed(false);
			mRemote.setRightActionsAllowed(false);
			mArena.gameOver(p2hp <= 0); // check winners
			this.destroy();
		}
	}

	/**
	 * Tell Players to close Firebase connections
	 */
	public void destroy() {
		// TODO: destroy firebase connections
		mLocal.end();
		mRemote.end();
	}


	/* ********************* */
	/* LOCAL PLAYER ACTIONS  */
	/* ********************* */
	
	// TODO: Track stats
	// 			including 

	public void localLeftJab() {
		if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Jab");
			
			mLocal.leftJab(); // tell the local it punched

			mArena.l_jab.setBackgroundColor(Color.CYAN);
			mLocal.startLeftActionDelay(JAB_COOLDOWN);
			mArena.AnimateLocalLeftJab();

			// TODO: TRACK -- move was attempted
			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(JAB_DMG);
				// update players' health bars
				updateHealth();
			} else {
				// TODO: TRACK -- move was blocked
			}
			
		}
	}

	public void localRightJab() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Jab");
			
			mLocal.rightJab(); // tell the local it punched

			mArena.r_jab.setBackgroundColor(Color.CYAN);
			mLocal.startRightActionDelay(JAB_COOLDOWN);
			mArena.AnimateLocalRightJab();
			
			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(JAB_DMG);
				// update players' health bars
				updateHealth();
			}
			
		}
	}

	public void localLeftHook() {
		if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Hook");
			
			mLocal.leftHook(); // tell the local it punched

			mArena.l_hook.setBackgroundColor(Color.CYAN);
			mLocal.startLeftActionDelay(HOOK_COOLDOWN);
			mArena.AnimateLocalLeftHook();

			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(HOOK_DMG);
				// update players' health bars
				updateHealth();
			}
			
		}
	}

	public void localRightHook() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Hook");
			
			mLocal.rightHook(); // tell the local it punched

			mArena.r_hook.setBackgroundColor(Color.CYAN);
			mLocal.startRightActionDelay(HOOK_COOLDOWN);
			mArena.AnimateLocalRightHook();

			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(HOOK_DMG);
				// update players' health bars
				updateHealth();
			}
			
		}
	}

	public void localLeftUppercut() {
		if (mLocal.getLeftActionsAllowed()) {
			Log.d(BG_L, "Local Left Uppercut");
			
			mLocal.leftUppercut(); // tell the local it punched

			mArena.l_up.setBackgroundColor(Color.CYAN);
			mLocal.startLeftActionDelay(UPPERCUT_COOLDOWN);
			mArena.AnimateLocalLeftUppercut();

			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(UPPERCUT_DMG);
				// update players' health bars
				updateHealth();
			}
			
		}
	}

	public void localRightUppercut() {
		if (mLocal.getRightActionsAllowed()) {
			Log.d(BG_L, "Local Right Uppercut");
			
			mLocal.rightUppercut(); // tell the local it punched

			mArena.r_up.setBackgroundColor(Color.CYAN);
			mLocal.startRightActionDelay(UPPERCUT_COOLDOWN);
			mArena.AnimateLocalRightUppercut();

			if (!mRemote.isBlocking()) {
				mRemote.decrementHealth(UPPERCUT_DMG);
				// update players' health bars
				updateHealth();
			}
			
		}
	}

	public void localBlock() {
		if (!mLocal.isBlocking()) {
			Log.d(BG_L, "Local Block");
			
			mLocal.block(); 
			mArena.AnimateLocalBlocking();
			mLocal.setBlocking(true);
			mLocal.startLeftActionDelay(BLOCKING_COOLDOWN);
			mLocal.startRightActionDelay(BLOCKING_COOLDOWN);
		}
		//

	}

	/* ********************* */
	/* REMOTE PLAYER ACTIONS */
	/* ********************* */
	
	// TODO: Move the startActionDelays into the computer player to keep the responsibility on the remote

	public void remoteLeftJab() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Jab");
			mArena.AnimateRemoteLeftJab();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(JAB_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startLeftActionDelay(JAB_COOLDOWN);
		}
	}

	public void remoteRightJab() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Jab");
			mArena.AnimateRemoteRightJab();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(JAB_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startRightActionDelay(JAB_COOLDOWN);
		}
	}

	public void remoteLeftHook() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Hook");
			mArena.AnimateRemoteLeftHook();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(HOOK_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startLeftActionDelay(HOOK_COOLDOWN);
		}
	}

	public void remoteRightHook() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Hook");
			mArena.AnimateRemoteRightHook();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(HOOK_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startRightActionDelay(HOOK_COOLDOWN);
		}
	}

	public void remoteLeftUppercut() {
		if (mRemote.getLeftActionsAllowed()) {
			Log.d(BG_R, "Remote Left Uppercut");
			mArena.AnimateRemoteLeftUppercut();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(UPPERCUT_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startLeftActionDelay(UPPERCUT_COOLDOWN);
		}
	}

	public void remoteRightUppercut() {
		if (mRemote.getRightActionsAllowed()) {
			Log.d(BG_R, "Remote Right Uppercut");
			mArena.AnimateRemoteRightUppercut();
			if (!mLocal.isBlocking()) {
				mLocal.decrementHealth(UPPERCUT_DMG);
				// update players' health bars
				updateHealth();
			}
			mRemote.startRightActionDelay(UPPERCUT_COOLDOWN);
		}
	}

	public void remoteBlock() {
		// start blocking
		if (!mRemote.isBlocking()) {
			Log.d(BG_R, "Remote Block");
			mArena.AnimateRemoteBlocking();
			mRemote.setBlocking(true);
			mLocal.startLeftActionDelay(BLOCKING_COOLDOWN);
			mLocal.startRightActionDelay(BLOCKING_COOLDOWN);
		}

	}
}
