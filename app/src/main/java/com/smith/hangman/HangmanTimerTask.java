package com.smith.hangman;

import java.util.TimerTask;

public class HangmanTimerTask extends TimerTask {

	GameScreen gameScreen;
	
	public HangmanTimerTask(GameScreen gameScreen){
		this.gameScreen= gameScreen;
	}
	
	@Override
	public void run() {
		gameScreen.updateTime();

	}

}
