package edu.rhit.petitjam_coblebj.game;

import java.util.Random;

import edu.rhit.petitjam_coblebj.roboarena.R;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class ComputerPlayer extends RemotePlayer {
	
	public static final int COMPUTER_PLAYER_DIFFICULTY_EASY = 0;
	public static final int COMPUTER_PLAYER_DIFFICULTY_MEDIUM = 1;
	public static final int COMPUTER_PLAYER_DIFFICULTY_HARD = 2;
	
	private ThrowRandomHitsTask mAITask;

	private int mDifficulty;
	
	public ComputerPlayer(BoxerGame game, int difficulty) {
		super(game);
		mDifficulty = difficulty; 
	}

	@Override
	public String getUsername() {
		return getString(R.string.ai_opponent_name);
	}

	@Override
	public void setHealth(int health) {
		super.setHealthHelper(health);
	}

	@Override
	public void decrementHealth(int damage) {
		super.setHealthHelper(getHealth() - damage);
	}

	@Override
	public void incrementHealth(int restoredAmount) {
		super.setHealthHelper(getHealth() + restoredAmount);
	}

	@Override
	public void start() {
		// We are a computer, so lets throw some random hits
		mAITask = new ThrowRandomHitsTask();
		mAITask.execute(null, null, null);
	}

	@Override
	public void end() {
		mAITask.cancel(true);
	}
	
	private void doAction(int action) {
		switch (action) {
		case ACTION_BLOCK:
			getGame().remoteBlock();
			break;
		case ACTION_LEFT_JAB:
			getGame().remoteLeftJab();
			break;
		case ACTION_LEFT_HOOK:
			getGame().remoteLeftHook();
			break;
		case ACTION_LEFT_UPPERCUT:
			getGame().remoteLeftUppercut();
			break;
		case ACTION_RIGHT_JAB:
			getGame().remoteRightJab();
			break;
		case ACTION_RIGHT_HOOK:
			getGame().remoteRightHook();
			break;
		case ACTION_RIGHT_UPPERCUT:
			getGame().remoteRightUppercut();
			break;
		}
	}
	
	/**
	 * Class for a dumb AI that just throws random hits
	 * 
	 * @author petitjam
	 */
	private class ThrowRandomHitsTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			
			Random gen = new Random();
			
			// TODO: Do left and right actions separately.
			while (true) {
				if (isCancelled()) {
					Log.d("RA", "ending computer asynctask");
					return null;
				}
				int next = gen.nextInt() % NUMBER_OF_ACTIONS; // number of actions
				publishProgress(new Integer[] { next });
				SystemClock.sleep(((3 - mDifficulty) * 1000) - (200 * mDifficulty)); // time between throws is different by level
			}
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			doAction(values[0]);
		}
	}
}
