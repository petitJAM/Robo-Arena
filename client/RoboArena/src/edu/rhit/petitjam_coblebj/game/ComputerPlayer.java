package edu.rhit.petitjam_coblebj.game;

import java.util.Random;

import android.os.AsyncTask;

public class ComputerPlayer extends RemotePlayer {

	private BoxerGame mGame;
	
	public ComputerPlayer() {
		
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
	private class ThrowRandomHitsTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			Random gen = new Random();
			
			while (true) {
				int next = gen.nextInt() % NUMBER_OF_ACTIONS; // number of actions
				doAction(next);
			}
		}
	}
}
