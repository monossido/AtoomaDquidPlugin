package com.atooma.plugin.plugindquid;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public class SpeechActivity extends Activity implements OnInitListener {

	private int VOICE_RECOGNITION_REQUEST_CODE = 12345;
	private TextToSpeech tts;

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
			tts = new TextToSpeech(this, this);

			// TODO

			for (Object match : matches) {
				Log.v("DQUIDPLUGIN", "match=" + match);
				String stringMatch = (String) match;
				LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
				if (stringMatch.equalsIgnoreCase("sì")) {
					String latitudineArrivo = getIntent().getExtras().getString("lat");
					String longitudineArrivo = getIntent().getExtras().getString("lon");
					Location posizione = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					String longitudinePartenza = Location.convert(posizione.getLongitude(), Location.FORMAT_DEGREES);

					String url = "http://maps.google.com/maps?saddr=" +
							longitudinePartenza + "," +
							posizione.getLongitude() + "&daddr=" +
							latitudineArrivo + "," + longitudineArrivo;
					Intent navigatore = new Intent(Intent.ACTION_VIEW);
					navigatore.setData(Uri.parse(url));
					startActivity(navigatore);
				}

			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onInit(int status) {
		tts.setLanguage(Locale.ITALY);
		tts.speak("Benzina sotto il 20 per cento, vuoi essere guidato al distributore più vicino?", TextToSpeech.QUEUE_ADD, null);
	}

}
