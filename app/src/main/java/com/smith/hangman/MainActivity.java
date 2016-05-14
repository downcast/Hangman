package com.smith.hangman;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends Activity implements OnClickListener {

	private String wordBank[]= new String[22];
	private String wordBankHints[]= new String[22];
	private int seed= 0;
	
	private MediaPlayer ThemeMusic= new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Button start= (Button)findViewById(R.id.startBtn);
		start.setOnClickListener(this);
		
		generateBanks();
		
		Random rand= new Random();
		seed= rand.nextInt(wordBank.length);
		
		ThemeMusic= MediaPlayer.create(MainActivity.this, R.raw.theme_music_dark_tension);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		ThemeMusic.start();
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
        	case R.id.startBtn:
        		Intent startIntent= new Intent(this, GameScreen.class);
        		startIntent.putExtra("words", selectWord(seed));
        		startIntent.putExtra("hint", selectHint(seed));
        		ThemeMusic.stop();
        		ThemeMusic.release();
        		this.startActivity(startIntent);
        		return;
        	default:
        		return;
		}
	}
	
	public void generateBanks(){
		
		wordBank[0]= "america";
		wordBank[1]= "angry birds";
		wordBank[2]= "foundation";
		wordBank[3]= "mouth";
		wordBank[4]= "constitution";
		wordBank[5]= "towel";
		wordBank[6]= "laughing silently in my head";
		wordBank[7]= "mountain";
		wordBank[8]= "aretha franklin";
		wordBank[9]= "jamaica";
		wordBank[10]= "taliaferro";
		wordBank[11]= "new york";
		wordBank[12]= "berry gordy";
		wordBank[13]= "peanuts";
		wordBank[14]= "w c handy";
		wordBank[15]= "laughing hyena";
		wordBank[16]= "laughing gas";
		wordBank[17]= "boldness or daring or courage";
		wordBank[18]= "mother tongue";
		wordBank[19]= "green bay";
		wordBank[20]= "you lose";
		wordBank[21]= "rubber band";

		wordBankHints[0]= "You are in this\nCountry";
		wordBankHints[1]= "Game of slingshot birds\n and helpless pigs";
		wordBankHints[2]= "Your house sits on this";
		wordBankHints[3]= "Where are teeth, tongue and\nbad breath";
		wordBankHints[4]= "Governing body of paper\n in US";
		wordBankHints[5]= "What gets wetter as it dries";
		wordBankHints[6]= "Decode this text\nLSIMH";
		wordBankHints[7]= "The highest thing in the world that is in the ground";
		wordBankHints[8]= "Who is the Queen of Soul";
		wordBankHints[9]= "Marcus Garvey was born in what country";
		wordBankHints[10]= "What does 'T' in Booker T. Washington mean";
		wordBankHints[11]= "In what state is the Apollo Theater";
		wordBankHints[12]= "Founder of The Supremes";
		wordBankHints[13]= "George Washington Carver made ink from what";
		wordBankHints[14]= "Father of the Blues";
		wordBankHints[15]= "Animal with a cackling howl";
		wordBankHints[16]= "What is nitrous oxide";
		wordBankHints[17]= "Define hardihood";
		wordBankHints[18]= "One's native language";
		wordBankHints[19]= "Home of the Packers";
		wordBankHints[20]= "Complete the phrase: 'You snooze'";
		wordBankHints[21]= "Stretchy office supply";
	}
	
	public String selectHint(int num){
		return wordBankHints[num];
	}
	
	public String selectWord(int num){
		return wordBank[num];
	}
}
