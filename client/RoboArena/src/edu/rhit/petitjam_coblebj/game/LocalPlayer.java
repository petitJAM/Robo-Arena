package edu.rhit.petitjam_coblebj.game;

public class LocalPlayer extends Player {
	
	// Constructor for local AI game
	public LocalPlayer(BoxerGame game) {
		super(game);
	}
	
	// Constructor for PVP game
	public LocalPlayer(BoxerGame game, String gameId, String playerId) {
		super(game, gameId, playerId);
	}
}
