package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityTwo";

	// Lifecycle counters
    private int mCreate = 0;
    private int mResume = 0;
    private int mStart = 0;
    private int mRestart = 0;


    private TextView mTvCreate;
    private TextView mTvResume;
    private TextView mTvRestart;
    private TextView mTvStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

        mTvCreate = (TextView) findViewById(R.id.create);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mTvResume = (TextView) findViewById(R.id.resume);
        mTvStart = (TextView) findViewById(R.id.start);


		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                finish();
			}
		});

		// Check for previously saved state
		if (savedInstanceState != null) {

            mCreate = savedInstanceState.getInt(CREATE_KEY);
            mRestart = savedInstanceState.getInt(RESTART_KEY);
            mResume = savedInstanceState.getInt(RESUME_KEY);
            mStart = savedInstanceState.getInt(START_KEY);
		}

        Log.i(TAG,"App Status: 'onCreate'");




        mCreate++;
        displayCounts();

	}

	// Lifecycle callback methods overrides

	@Override
	public void onStart() {
		super.onStart();

        Log.i(TAG, "The activity is visible and about to be started.");


        mStart++;
        displayCounts();

	}

	@Override
	public void onResume() {
		super.onResume();

        Log.i(TAG, "The activity is and has focus (it is now \"resumed\")");

        mResume++;
        displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

        Log.i(TAG, "Another activity is taking focus (this activity is about to be \"paused\")");
	}

	@Override
	public void onStop() {
		super.onStop();

        Log.i(TAG, "The activity is stopped");
	}

	@Override
	public void onRestart() {
		super.onRestart();

        Log.i(TAG, "The activity is visible and about to be restarted.");


        mRestart++;
        displayCounts();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

        Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");

	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(RESTART_KEY, mRestart);
        savedInstanceState.putInt(RESUME_KEY, mResume);
        savedInstanceState.putInt(CREATE_KEY, mCreate);
        savedInstanceState.putInt(START_KEY, mStart);
	}

	// Updates the displayed counters
	public void displayCounts() {

		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
