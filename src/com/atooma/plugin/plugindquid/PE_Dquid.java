package com.atooma.plugin.plugindquid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class PE_Dquid extends Performer {

	public PE_Dquid(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setTitle(R.string.app_name);
		setIcon(R.drawable.plugin_icon_el_normal);
	}

	@Override
	public ParameterBundle onInvoke(String arg0, ParameterBundle arg1) throws RemoteException {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://api.mygasfeed.com/stations/radius/40.819009/-73.953284/1/reg/price/54ydojauxe.json", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				try {
					JSONObject jso = new JSONObject(response);

					JSONArray jsa = jso.getJSONArray("stations");
					String lat = jsa.getJSONObject(0).getString("lat");
					String lon = jsa.getJSONObject(0).getString("lng");

					Intent speechActivity = new Intent(getContext(), SpeechActivity.class);
					speechActivity.putExtra("lat", lat);
					speechActivity.putExtra("lon", lon);
					speechActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					getContext().startActivity(speechActivity);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		return new ParameterBundle();
	}

}
