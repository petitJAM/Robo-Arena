package edu.rhit.petitjam_coblebj.game;

import java.util.Random;

import android.os.AsyncTask;
import android.os.SystemClock;

public class ComputerPlayer extends RemotePlayer {
	
	public static final int COMPUTER_PLAYER_DIFFICULTY_EASY = 0;
	public static final int COMPUTER_PLAYER_DIFFICULTY_MEDIUM = 1;
	public static final int COMPUTER_PLAYER_DIFFICULTY_HARD = 2;
	public static final String KEY_COMPUTER_DIFFICULTY = "key_computer_difficulty";

	private BoxerGame mGame;
	private int mDifficulty;
	
	public ComputerPlayer(BoxerGame game, int difficulty) {
		super();
		mGame = game;
		mDifficulty = difficulty; 
	}

	@Override
	public void start() {
		// We are a computer, so lets throw some random hits
		new ThrowRandomHitsTask().execute(null, null, null);
	}
	
	private void doAction(int action) {
		switch (action) {
		case ACTION_BLOCK:
			mGame.remoteBlock();
			break;
		case ACTION_LEFT_JAB:
			mGame.remoteLeftJab();
			break;
		case ACTION_LEFT_HOOK:
			mGame.remoteLeftHook();
			break;
		case ACTION_LEFT_UPPERCUT:
			mGame.remoteLeftUppercut();
			break;
		case ACTION_RIGHT_JAB:
			mGame.remoteRightJab();
			break;
		case ACTION_RIGHT_HOOK:
			mGame.remoteRightHook();
			break;
		case ACTION_RIGHT_UPPERCUT:
			mGame.remoteRightUppercut();
			break;
		}
	}
	
	/**
	 * Class for dumb AI that just throws random hits
	 * @author petitjam
	 */
	private class ThrowRandomHitsTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			Random gen = new Random();
			
			// TODO: Do left and right actions separately.
			while (true) {
				int next = gen.nextInt() % NUMBER_OF_ACTIONS; // number of actions
				publishProgress(new Integer[] { next });
				SystemClock.sleep((3 - mDifficulty) * 1000); // time between throws is different by level
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			doAction(values[0]);
		}
	}
}
