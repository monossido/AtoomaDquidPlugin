package com.atooma.plugin.plugindquid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

public class SpeechActivity extends Activity {

	private int VOICE_RECOGNITION_REQUEST_CODE = 12345;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Select an application"); // user hint
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH); // setting recognition model, optimized for short phrases – search queries
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1); // quantity of results we want to receive

		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

			ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			for (Object match : matches) {
				String stringMatch = (String) match;
				if (stringMatch.equalsIgnoreCase("si"))//start maps navigation TODO
					;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
