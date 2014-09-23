package ca.ualberta.cs.lonelytwitter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TweetsCounter extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tweets_counter_activity);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweets_counter, menu);
		return true;
	}

	public void countTweets(View v){
		Toast.makeText(this, "Counting tweets", Toast.LENGTH_SHORT).show();
		TextView tweetsCounter = (TextView) findViewById(R.id.tweetsCounter);	
		int counter = LonelyTwitterActivity.getTweetsSize();
		tweetsCounter.setText("Tweets: "+counter+"");
	}
}
