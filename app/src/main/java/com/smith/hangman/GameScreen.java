package com.smith.hangman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;

public class GameScreen extends Activity implements OnClickListener {

	/** Used to count how many of the same character exist in the answer string. 
	 *  It is used in the "updateText" method. **/
	private ArrayList<Integer> numList= new ArrayList<Integer>();
	
	/** Used to track the characters the user attempted to use. It is linked to the "wrongLetters" UI component through the TextView "wrongLetter" 
	 *  (declared in the "onKeyUp" method) and the text is set in the "displayWrongLetters" method.**/
	private Set<Character> wrongList= new HashSet<Character>();
	
	/** Used to track the correct letter the user selected. It is linked to the "guessTextLabel" UI component through the TextView "text" 
	 *  (declared in the "onKeyUp" method) and the text is set in the "updateText" method.**/
	private StringBuffer guess= new StringBuffer("");
	
	/** Used to store the word that represents the answer. The value is set in the "onCreate" method and in the "onActivityResult" method. **/
	private String answer= "";
	
	private int time= 0;
	private int score= 0;
	private Timer timer= new Timer();
	private HangmanTimerTask task;
	
	private MediaPlayer correctLetterSnd= new MediaPlayer();
	private MediaPlayer wrongLetterSnd= new MediaPlayer();
	private TextView wordCountLabel;

	private RelativeLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_screen);
		
		// UI component hidden behind the keyboard. It allows the user to show the keyboard if it was hidden 
		Button showKeyboardBtn= (Button)findViewById(R.id.showKeyboardBtn);
		showKeyboardBtn.setOnClickListener(this);
		
		// Shows keyboard to User
		InputMethodManager showKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		showKeyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		
		// Get the first word from "MainActivity" and set equal to the answer
		Bundle passedData= getIntent().getExtras();
		answer= passedData.getString("words");
		
		wordCountLabel= (TextView)findViewById(R.id.wordCountLabel);
		wordCountLabel.setText(answer.valueOf(answer.length())+" letters");
		
		TextView hintLabel= (TextView)findViewById(R.id.hintLabel);
		hintLabel.setText(passedData.getString("hint"));

		dynamicBuffer_Score();
		
		// Calls "updateTime" through the "HangmanTimerTask" class every second.
		task= new HangmanTimerTask(this);
		timer.scheduleAtFixedRate(task, 0, 1000);
		
		layout= (RelativeLayout)findViewById(R.id.gameScreenLayout);
		layout.setBackgroundResource(R.drawable.hangman_base_0);
		
		// Audio controls
		correctLetterSnd= MediaPlayer.create(GameScreen.this, R.raw.game_screen_correct_letter);
		wrongLetterSnd= MediaPlayer.create(GameScreen.this, R.raw.game_screen_worng_letter2);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		//setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}
	
	/** Sets the number of underscores in the "guess" StringBuffer to the length of the "answer" string. Tests for the existence of spaces and adds
	 * then to the "guess" StringBuffer **/
	public void dynamicBuffer_Score(){
		int size= answer.length();
		for (int count= 0; count <size;count++){
			guess.append('_');
		}
		
		//for (int count= 0; count <size;count++){
			//guess.setCharAt(count, '_');
		//}
		
		int index = answer.indexOf(' ');
		while (index != -1){
			guess.setCharAt(index, ' ');
			index= answer.indexOf(' ', index+1);
		}
	}
	
	public void updateTime(){
		time+=1;
	}
	
	@Override
	// develop score system, graphics, audio 
	
	// Called when the last key the user touched is released
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// Create instance variable for the UI components
		TextView text= (TextView)findViewById(R.id.guessTextLabel);
		TextView wrongLetter= (TextView)findViewById(R.id.wrongLetters);
		
		// test to decide which key way pressed
	    switch (keyCode) {
		    case KeyEvent.KEYCODE_A:
	        	updateText('a', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_B:
	        	updateText('b', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_C:
	        	updateText('c', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_D:
	        	updateText('d', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_E:
	        	updateText('e', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_F:
	        	updateText('f', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_G:
	        	updateText('g', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_H:
	        	updateText('h', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_I:
	        	updateText('i', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_J:
	        	updateText('j', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_K:
	        	updateText('k', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_L:
	        	updateText('l', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_M:
	        	updateText('m', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_N:
	        	updateText('n', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_O:
	        	updateText('o', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_P:
	        	updateText('p', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_Q:
	        	updateText('q', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_R:
	        	updateText('r', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_S:
	        	updateText('s', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_T:
	        	updateText('t', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_U:
	        	updateText('u', text, wrongLetter);
	            return true;
	        case KeyEvent.KEYCODE_V:
	        	updateText('v', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_W:
	        	updateText('w', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_X:
	        	updateText('x', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_Y:
	        	updateText('y', text, wrongLetter);
	        	return true;
	        case KeyEvent.KEYCODE_Z:
	        	updateText('z', text, wrongLetter);
	        	return true;
	        default:
	        	// Handles numeric and special characters
	        	Toast popUp= Toast.makeText(this, "Invalid character", Toast.LENGTH_LONG);
				popUp.show();
	            return super.onKeyUp(keyCode, event);
	    }
	}
	
	/** Called to make changes to the UI components. Function obtains the index of 'c' in the "answer" string; If character exists in the "answer" String, 
	 *  add the index location of 'c' to "numList" and re-test to confirm no other instances of 'c' exist; Take indexes from "numList" and use to place 
	 *  'c' in the corresponding location in the "guess" StringBuffer in relation to the "answer" String. Function clears "numList" and calls the "winner" method if
	 *  'c' existed or the "displayWronfLetters" method if 'c' did not exist  **/
	private void updateText(char c, TextView text, TextView wrongLetter){
		// NOTE: index return -1 if 'c' is not found
		int index= answer.indexOf(c);
		// used to determine is letter is already played but is right
		int testIndex= guess.toString().indexOf(c);
		if (testIndex != -1){
			Toast popUp= Toast.makeText(this, "Character in use", Toast.LENGTH_SHORT);
			popUp.show();
		}else if (index!=-1){
    		while (index!=-1){
	    		numList.add(index);
	    		index= answer.indexOf(c, index+1);
    		}
	    	for (int counter=0; numList.size() !=counter; counter++){
	    		guess.setCharAt(numList.get(counter), c);
	    	}
	    	correctLetterSnd.start();
	   		text.setText(guess.toString());
	   		numList.clear();
	   		winner();
    	}else{
    		displayWrongLetters(c, wrongLetter);
    	}
	}
	/** Called only from the "updateText" method. Function uses the underscore character ('_') to determine if all letters have been found, if so, the 
	 * "WinnerScreen" (class) activity begins. A random seed number is passed into the activity to determine the next word.**/
	private void winner(){
		int index=0;
		int temp= guess.indexOf("_");
		while (temp!=-1){
    		index+=1;
    		temp= guess.toString().indexOf('_', temp+1);
		}
		String tempString= "";
		wordCountLabel.setText(tempString.valueOf(index)+" letters");
		if (guess.indexOf("_")== -1){
			Intent startIntent= new Intent(this, WinnerScreen.class);
			determineScore();
			// Pass the score and time to the "WinnerScreen" class
			startIntent.putExtra("Score", generateScore());
			startIntent.putExtra("Time", time);
			
			//correctLetterSnd.release();
			//wrongLetterSnd.release();
			this.startActivityForResult(startIntent, 1);
		}
	}
	private int generateScore() {
		// **********************************************************************************8
		// Update scoregenerater; add time bonus; multi letter bonus; 
		
		score= answer.length();
		return score;
	}

	/** Called only from the "updateText' method. Function adds 'c' to "wrongList", determines if user has too many wrong letters, then updates the UI component 
	 * "wrongLetters".
	 * @param	c		character**/
	private void displayWrongLetters(char c, TextView wrongLetters){
	
		Boolean go= true;
    	if (wrongList.contains(c)){
    		Toast popUp= Toast.makeText(this, "Character has been used.", Toast.LENGTH_SHORT);
			popUp.show();
			go = false;
    	}
		if (go == true){
			wrongLetterSnd.start();
			wrongList.add(c);
			if (wrongList.size()==1){
				layout.setBackgroundResource(R.drawable.hangman_base_1);
			}else if (wrongList.size()==2){
				layout.setBackgroundResource(R.drawable.hangman_base_2);
			}else if (wrongList.size()== 3){
				layout.setBackgroundResource(R.drawable.hangman_base_3);
			}else if (wrongList.size()==4){
				layout.setBackgroundResource(R.drawable.hangman_base_4);
			}
			if (wrongList.size() >= 5){
				// Go to the "LoserScreen"' class
				Intent loserIntent= new Intent(this, LoserScreen.class);
				loserIntent.putExtra("Answer", answer);
				//correctLetterSnd.release();
				//wrongLetterSnd.release();
				this.startActivityForResult(loserIntent, 1);
			}else{
				wrongLetters.setText(wrongList.toString());
			}
		}
	}
	
	public void determineScore(){
		// Create scoring system
		score= 0;
	}

	@Override
	public void onClick(View v) {
		// Allows the user to show the keyboard in case the back button hid the keyboard
		InputMethodManager showKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (showKeyboard.isAcceptingText()== false){
			showKeyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		/* Called when user clicks "New Game" in the "WinnerScreen" activity; Function update the "hintLabel" UI component via "hintLabel";
		 * The "answer" string is updated, "guess" StringBuffer is reset to match new answer */
		
		TextView hintLabel= (TextView)findViewById(R.id.hintLabel);
		
		// Get next word from calling activity and set as answer
	    answer = data.getStringExtra("words");
	 // Get next word from calling activity and set as hint
	    hintLabel.setText(data.getStringExtra("hint"));
	    wordCountLabel.setText(data.getStringExtra("count"));
	    // Reset the "guess" StringBuffer
	    guess= new StringBuffer("");
		TextView text= (TextView)findViewById(R.id.guessTextLabel);
		TextView wrongLetter= (TextView)findViewById(R.id.wrongLetters);
		text.setText("");
		wrongLetter.setText("");
		wrongList.clear();
	    dynamicBuffer_Score();
	  }
	
	@Override
	public void onBackPressed(){
		 // Disables the back button; Still allows for keyboard to be hidden; See onClick to manually show keyboard
	}
}
