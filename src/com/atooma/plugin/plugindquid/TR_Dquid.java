package com.atooma.plugin.plugindquid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Trigger;
import com.dquid.clientapi.DQAccelerometerData;
import com.dquid.clientapi.DQData;
import com.dquid.clientapi.DQGpsData;
import com.dquid.clientapi.DQListenerInterface;
import com.dquid.clientapi.DQUnitManager;
import com.dquid.driver.DQDriver;
import com.dquid.driver.DQDriverEventListener;
import com.dquid.driver.DQSourceType;

public class TR_Dquid extends Trigger implements DQListenerInterface, DQDriverEventListener {

	private String ruleId;

	public TR_Dquid(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.module_name, R.string.module_name, "DQUID", "STRING", true, null);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_el_normal);
		setTitle(R.string.module_name);
	}

	@Override
	public void onInvoke(String arg0, ParameterBundle arg1) {
		this.ruleId = arg0;
		String device_id = (String) arg1.get("DQUID");

		//TODO
		device_id = "00:07:80:65:0A:AC";

		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		BluetoothDevice deviceToConnect = mBluetoothAdapter.getRemoteDevice(device_id);

		DQDriver.INSTANCE.setEventListener(this);
		DQUnitManager.INSTANCE.addListener(this);

		DQUnitManager.INSTANCE.startReceivingCarData();

		DQDriver.INSTANCE.setBtDevice(deviceToConnect);
		DQDriver.INSTANCE.enableSource(DQSourceType.BLUETOOTH_2_1);
	}

	@Override
	public void onDriverDown(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDriverReady() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionSuccessful() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnection() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDtcCodesAvailable(ArrayList<String> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDtcNumberAvailable(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFirmwareUpdateCompleted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFirmwareUpdateIncrease(double arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFirmwareUpdateNeeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFirmwareUpdateNotNeeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFirmwareUpdateStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNewData(HashMap<Long, DQData> arg0) {
		Collection<DQData> values = DQUnitManager.INSTANCE.getLastAvailable().values();
		for (DQData data : values) {
			//PRENDI DATI
			if (data.getName().equals("FuelLevel")) {
				if (data.getValue() < 3)
					trigger(ruleId, new ParameterBundle());
			}
		}
	}

	@Override
	public void onNewAccelerometerData(DQAccelerometerData arg0) {
		Log.d("ATOOMA PLUGIN DQUID", "onNewAccelerometerData - " + arg0.toString());

	}

	@Override
	public void onNewGpsData(DQGpsData arg0) {
		Log.d("ATOOMA PLUGIN DQUID", "onNewGpsData - " + arg0.toString());

	}

}
