package edu.rhit.petitjam_coblebj.game;

public abstract class RemotePlayer extends Player {


	public RemotePlayer(BoxerGame game) {
		super(game);
	}
	
	// Anything that the Game needs to know how to call on a remote player
	
	public abstract void start();
	
	@Override
	public void setLeftActionsAllowed(boolean allowed) {
		setLeftActionsAllowedHelper(allowed);
	}

	@Override
	public void setRightActionsAllowed(boolean allowed) {
		setRightActionsAllowedHelper(allowed);
	}

	@Override
	public void setBlocking(boolean blocking) {
		setBlockingHelper(blocking);
	}
}
