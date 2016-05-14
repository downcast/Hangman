package com.smith.hangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class WinnerScreen extends Activity implements OnClickListener{

	private String wordBank[]= new String[7];
	private String wordBankHints[]= new String[7];
	private int seed= 0;
	private int score= 0;
	private int time= 0;
	
	private MediaPlayer winnerMusic= new MediaPlayer();
	
	// move the high score button to this activity, easeir to display scores; hold ten scores; show game scores then high scores; game score shows ten
	// most recent scores; high score adds all the game scores; or create complex score system add more words and use top three scores in high score
	// not adding game scores
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winner_screen);
		
		Button newGameBtn= (Button)findViewById(R.id.loserNewGame);
		newGameBtn.setOnClickListener(this);
		
		TextView scoreLabel= (TextView)findViewById(R.id.scoreLabel);
		TextView timeLabel= (TextView)findViewById(R.id.timeLabel);
		
		// When hides the keyboard so the user can see the stats
		InputMethodManager showKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		showKeyboard.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
		
		// Get the score and time from the "GameScreen" class
		// Score is added to previous score; time is new time
		score+= getIntFromBundle("Score");
		time= getIntFromBundle("Time");
		
		// Convert score and time to strings and display
		scoreLabel.setText("Score: "+convertIntFromBundle(score));
		timeLabel.setText("Time: "+convertIntFromBundle(time)+" seconds");
		
		// Audio controls
		winnerMusic= MediaPlayer.create(WinnerScreen.this, R.raw.winner_screen_crowd_applause);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		winnerMusic.start();

	}
	
	private CharSequence convertIntFromBundle(int x) {
		// TODO Auto-generated method stub
		String temp= "";
		temp=temp.valueOf(x);
		return temp;
	}

	private int getIntFromBundle(String x) {
		// TODO Auto-generated method stub
		Bundle data= getIntent().getExtras();
		return data.getInt(x);
	}

	@Override
	public void onClick(View v) {
		// Starts a new game and passes a new word to start the game

		Random rand= new Random();
		seed= rand.nextInt(wordBank.length);
		
		generateBanks();
		Intent newGame= new Intent();
		newGame.putExtra("words", selectWord(seed));
		newGame.putExtra("hint", selectHint(seed));
		newGame.putExtra("count", getwordCount(seed));
		setResult(RESULT_OK, newGame);
		winnerMusic.release();
		finish();
	}
	
	private String getwordCount(int num) {
		// TODO Auto-generated method stub
		int temp= wordBank[num].length();
		String temp2= "";
		return temp2.valueOf(temp);
	}

	public void generateBanks(){
		
		wordBank[0]= "america";
		wordBank[1]= "angry birds";
		wordBank[2]= "foundation";
		wordBank[3]= "mouth";
		wordBank[4]= "constitution";
		wordBank[5]= "towel";
		wordBank[6]= "laughing silently in my head";
		
		wordBankHints[0]= "You are in this\nCountry";
		wordBankHints[1]= "Game of slingshot birds\n and helpless pigs";
		wordBankHints[2]= "Your house sits on this";
		wordBankHints[3]= "Teeth and toungue and\nbad breath";
		wordBankHints[4]= "Governing body of paper\n in US";
		wordBankHints[5]= "What gets wetter as it dries";
		wordBankHints[6]= "Decode this text\nLSIMY";
	}
	
	public String selectHint(int num){
		return wordBankHints[num];
	}
	
	public String selectWord(int num){
		return wordBank[num];
	}
}
