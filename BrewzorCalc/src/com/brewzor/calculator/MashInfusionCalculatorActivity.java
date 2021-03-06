package com.brewzor.calculator;

import com.brewzor.calculator.preferences.Preferences;
import com.brewzor.calculator.R;
import com.brewzor.converters.Mass;
import com.brewzor.converters.Temperature;
import com.brewzor.converters.Volume;
import com.brewzor.utils.NumberFormat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class MashInfusionCalculatorActivity extends Activity {
	
	private EditText currentMashTemperatureEntry;
	private EditText grainWeightEntry;
	private EditText waterInMashVolumeEntry;
	private EditText targetMashTemperatureEntry;
	
	private TextView currentMashTemperatureUnitType;
	private TextView grainWeightUnitType;
	private TextView waterInMashVolumeUnitType;
	private TextView targetMashTemperatureUnitType;

	private TextView calculatedInfusionVolume;
	private TextView calculatedInfusionVolumeUnitType;

	private Temperature.Unit temperatureType;
	private Mass.Unit massType;
	private Volume.Unit volumeType;
	
	private Temperature InfusionWaterTemperature;
	private Temperature CurrentMashTemperature;
	private Temperature TargetMashTemperature;
	private Volume InfusionWaterVolume;
	private Volume TotalWaterInMash;
	private Mass GrainWeight;
	
	private SharedPreferences prefs;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setTitle(getString(R.string.window_title_format, getString(R.string.app_name), getString(R.string.mash_infusion)));
		setContentView(R.layout.calculator_mash_infusion);		

		prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		currentMashTemperatureEntry = (EditText) findViewById(R.id.currentMashTemperatureEntry);
		currentMashTemperatureEntry.setOnKeyListener(mKeyListener);
		grainWeightEntry = (EditText) findViewById(R.id.grainWeightEntry);
		grainWeightEntry.setOnKeyListener(mKeyListener);
		waterInMashVolumeEntry = (EditText) findViewById(R.id.waterInMashVolumeEntry);
		waterInMashVolumeEntry.setOnKeyListener(mKeyListener);
		targetMashTemperatureEntry = (EditText) findViewById(R.id.targetMashTemperatureEntry);
		targetMashTemperatureEntry.setOnKeyListener(mKeyListener);

		currentMashTemperatureUnitType = (TextView) findViewById(R.id.currentMashTemperatureUnitType);
		grainWeightUnitType = (TextView) findViewById(R.id.grainWeightUnitType);
		waterInMashVolumeUnitType = (TextView) findViewById(R.id.waterInMashVolumeUnitType);
		targetMashTemperatureUnitType = (TextView) findViewById(R.id.targetMashTemperatureUnitType);

		calculatedInfusionVolume = (TextView) findViewById(R.id.calculatedInfusionVolume);
		calculatedInfusionVolumeUnitType = (TextView) findViewById(R.id.calculatedInfusionVolumeUnitType);
		
		InfusionWaterTemperature = new Temperature(0, Temperature.Unit.FAHRENHEIT, getBaseContext(), prefs);
		CurrentMashTemperature = new Temperature(0, Temperature.Unit.FAHRENHEIT, getBaseContext(), prefs);
		TargetMashTemperature = new Temperature(0, Temperature.Unit.FAHRENHEIT, getBaseContext(), prefs);
		InfusionWaterVolume = new Volume(0, Volume.Unit.GALLON, getBaseContext(), prefs);
		TotalWaterInMash = new Volume(0, Volume.Unit.GALLON, getBaseContext(), prefs);
		GrainWeight = new Mass(0, Mass.Unit.POUND, getBaseContext(), prefs);
		
	}

	private void calculate() {
		// http://brewingtechniques.com/library/backissues/issue5.4/palmer_sb.html
		/*

		Mash infusion equation:
		
		Wa = (T2 - T1) X (0.2G + Wm) � (Tw - T2)

		where:
		Tw =  the actual temperature of the infusion water
		R =  the ratio of water to grain in quarts per pound
		T1 =  the initial temperature of the mash (or dry grain)
		T2 =  the target temperature of the mash
		Wa =  the amount of boiling water added (in quarts)
		Wm =  the total amount of water in the mash (in quarts)
		G =  the amount of grain in the mash (in pounds)

		 */

		CurrentMashTemperature.setValue(currentMashTemperatureEntry, 0);
    	TargetMashTemperature.setValue(targetMashTemperatureEntry, 0);
    	TotalWaterInMash.setValue(waterInMashVolumeEntry, 0);
    	GrainWeight.setValue(grainWeightEntry, 0);
    	
    	//Log.v("mash", String.format("currentTemp=%01.4f", CurrentMashTemperature.getValue()));
    	//Log.v("mash", String.format("targetTemp=%01.4f", TargetMashTemperature.getValue()));
    	//Log.v("mash", String.format("waterInMash=%01.4f", TotalWaterInMash.getValue()));
    	//Log.v("mash", String.format("grainWeight=%01.4f", GrainWeight.getValue()));
    	//Log.v("mash", String.format("infusionTemp=%01.4f", InfusionWaterTemperature.getValue()));
    	
		InfusionWaterVolume.setValue(
				(TargetMashTemperature.compare(Temperature.Unit.FAHRENHEIT) - CurrentMashTemperature.compare(Temperature.Unit.FAHRENHEIT)) 
				* 
				((0.2 * GrainWeight.compare(Mass.Unit.POUND)) + TotalWaterInMash.compare(Volume.Unit.QUART)) 
				/ 
				(InfusionWaterTemperature.compare(Temperature.Unit.FAHRENHEIT) - TargetMashTemperature.compare(Temperature.Unit.FAHRENHEIT)));
        InfusionWaterVolume.setType(Volume.Unit.QUART);
        InfusionWaterVolume.convert(volumeType);
        
        if (InfusionWaterVolume.getValue() > 0) {
    		calculatedInfusionVolume.setText(InfusionWaterVolume.toString());        	
        } else {
    		calculatedInfusionVolume.setText(getString(R.string.double_default));
        }
	}
	
	private void getPrefs() {
        temperatureType = InfusionWaterTemperature.typeFromPref(Preferences.GLOBAL_TEMPERATURE_UNIT, Temperature.Unit.FAHRENHEIT);
        massType = GrainWeight.typeFromPref(Preferences.BATCH_GRAIN_MASS_UNIT, Mass.Unit.POUND);
        volumeType = InfusionWaterVolume.typeFromPref(Preferences.BATCH_MASH_VOLUME_UNIT, Volume.Unit.GALLON);
        
    	InfusionWaterTemperature.setType(temperatureType);
    	InfusionWaterTemperature.setValue(NumberFormat.parseDouble(prefs.getString(Preferences.BATCH_INFUSION_WATER_TEMPERATURE, "212"), 212));

    	CurrentMashTemperature.setType(temperatureType);
    	TargetMashTemperature.setType(temperatureType);
    	TotalWaterInMash.setType(volumeType);
    	GrainWeight.setType(massType);

    	currentMashTemperatureUnitType.setText(CurrentMashTemperature.getLabelAbbr());
    	targetMashTemperatureUnitType.setText(TargetMashTemperature.getLabelAbbr());
    	waterInMashVolumeUnitType.setText(TotalWaterInMash.getLabelAbbr());
    	grainWeightUnitType.setText(GrainWeight.getLabelAbbr());

        InfusionWaterVolume.setType(volumeType);
    	calculatedInfusionVolumeUnitType.setText(InfusionWaterVolume.getLabelPlural());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getPrefs();
		calculate();
	}
	
	public OnKeyListener mKeyListener = new OnKeyListener() {
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (event.getAction() == KeyEvent.ACTION_UP) calculate();
			return false;
		}
	};

	/* Creates the menu items */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return OptionsMenuHandler.createMenu(this, menu);
	}

	/* Handles item selections */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return OptionsMenuHandler.showMenu(this, item);
	}
}
