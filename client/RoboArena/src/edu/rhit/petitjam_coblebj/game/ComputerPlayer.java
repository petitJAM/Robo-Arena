package edu.rhit.petitjam_coblebj.game;

import java.util.Random;

import android.os.AsyncTask;
import android.os.SystemClock;

public class ComputerPlayer extends RemotePlayer {

	private BoxerGame mGame;
	
	public ComputerPlayer() {
		super();
	}
	
	public ComputerPlayer(BoxerGame game) {
		super();
		mGame = game; 
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
			
			while (true) {
				int next = gen.nextInt() % NUMBER_OF_ACTIONS; // number of actions
				publishProgress(new Integer[] { next });
				SystemClock.sleep(3000);
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			doAction(values[0]);
		}
	}
}
