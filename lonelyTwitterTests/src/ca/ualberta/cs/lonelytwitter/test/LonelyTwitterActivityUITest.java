package ca.ualberta.cs.lonelytwitter.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.lonelytwitter.IntentReaderActivity;
import ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity;
import ca.ualberta.cs.lonelytwitter.NormalTweetModel;

/*
 * generate this class with new.. JUnit Test Case
 * set superclass to ActivityInstrumentationTestCase2
 */
public class LonelyTwitterActivityUITest extends
		ActivityInstrumentationTestCase2<LonelyTwitterActivity> {

	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	
	public LonelyTwitterActivityUITest() {
		super(LonelyTwitterActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();

		textInput = ((EditText) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.body));
	}
	
	/*
	 * fills in the input text field and clicks the 'save'
	 * button for the activity under test
	 */
	private void makeTweet(String text) {
		assertNotNull(activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save));
		textInput.setText(text);
		((Button) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.save)).performClick();
	}
	
	@SuppressWarnings("unchecked")
	public void testAdapterChanged() throws Throwable{
		ListView listview = (ListView) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.oldTweetsList);
		ArrayAdapter<NormalTweetModel> adapter = (ArrayAdapter<NormalTweetModel>) listview.getAdapter();
		int count = adapter.getCount();
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				String text = "test string";
				makeTweet(text);			
			}
		});		
		assertTrue("Adapter didn't get notified", adapter.getCount() == count+1);
	}
	
	@SuppressWarnings("unchecked")
	public void testNewElementText() throws Throwable{
		ListView listview = (ListView) activity.findViewById(ca.ualberta.cs.lonelytwitter.R.id.oldTweetsList);
		ArrayAdapter<NormalTweetModel> adapter = (ArrayAdapter<NormalTweetModel>) listview.getAdapter();
		int count = adapter.getCount();
		runTestOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				String text = "test string";
				makeTweet(text);			
			}
		});	
		assertEquals("test string", adapter.getItem(count).getText());
	}
}
