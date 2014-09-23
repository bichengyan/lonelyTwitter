package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	private ArrayList<LonelyTweetModel> tweets;
	private ArrayAdapter<LonelyTweetModel> adapter;
	public static int tweets_size;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.countTweetsButton);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				tweets_size = tweets.size();
				String text = ""+bodyText.getText().toString()+" "+tweets_size+"";
				tweets.add(new LonelyTweetModel(text));
				adapter.notifyDataSetChanged();
				saveInFile();
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		if (tweets == null)
			tweets = new ArrayList<LonelyTweetModel>();
		tweets_size = tweets.size();
		adapter = new ArrayAdapter<LonelyTweetModel>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			Type ListType = new TypeToken<ArrayList<LonelyTweetModel>>() {}.getType();
			tweets = gson.fromJson(in, ListType);
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,0);
			Gson gson = new Gson();
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(tweets, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void changeView(View v){
		Toast.makeText(this, "change to Counting page", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(LonelyTwitterActivity.this,TweetsCounter.class);
		startActivity(intent);
	}
	
	public static int getTweetsSize(){
		return tweets_size;
	}
}