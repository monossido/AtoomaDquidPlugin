package com.atooma.plugin.plugindquid;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

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
		Intent speechActivity = new Intent(getContext(), SpeechActivity.class);
		getContext().startActivity(speechActivity);
		return new ParameterBundle();
	}

}
