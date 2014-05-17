package com.atooma.plugin.plugindquid;

import android.content.Context;

import com.atooma.plugin.Module;

public class PLUGINDQUID extends Module {

	public static final String MODULE_ID = "PLUGINDQUID";
	public static final int MODULE_VERSION = 1;

	public PLUGINDQUID(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void registerComponents() {
		registerTrigger(new TR_Dquid(getContext(), "TR_DQUID", 1));
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal);
		setTitle(R.string.module_name);
	}

	@Override
	public void defineAuth() {
		//Get the username autenticated from activity and define it with setAuthenticated(true, authText);
	}

	@Override
	public void clearCredentials() {
		//Clear
	}

}