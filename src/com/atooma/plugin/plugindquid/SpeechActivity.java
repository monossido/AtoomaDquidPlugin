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
import android.speech.tts.*;
import android.content.Context;

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
			TextToSpeech tts = null;
			tts.setLanguage(Locale.ITALY);
			tts.speak("Benzina sotto il 20 per cento, vuoi essere guidato al distributore più vicino?", TextToSpeech.QUEUE_ADD, null);
			// TODO
			
			for (Object match : matches) {
				String stringMatch = (String) match;
				LocationManager locationManager = null;
				if (stringMatch.equalsIgnoreCase("si")){
					String latitudineArrivo = getIntent().getExtras().getString("lat");
					String longitudineArrivo = getIntent().getExtras().getString("lon");
					Location posizione = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					String longitudinePartenza = Location.convert(posizione.getLongitude(), Location.FORMAT_DEGREES);
					locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);
					
					String url = "http://maps.google.com/maps?saddr=" +
							longitudinePartenza + "," +
							posizione.getLongitude()+ "&daddr=" +
							latitudineArrivo + "," + longitudineArrivo;
					Intent navigatore = new Intent(Intent.ACTION_VIEW);
					navigatore.setData(Uri.parse(url));
					startActivity(navigatore);
					
				}
					
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
