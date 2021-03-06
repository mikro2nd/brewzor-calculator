/*
    This file is part of Brewzor.

    Copyright (C) 2010 James Whiddon

    Brewzor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Brewzor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Brewzor.  If not, see <http://www.gnu.org/licenses/>.

*/
package com.brewzor.calculator.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
	
	public static final int DOUBLE_PRECISION = 2;
	public static final int SG_PRECISION = 3;
	
	public static final String GLOBAL_INIT = "global.init";
	public static final String GLOBAL_EULA_ACCEPT = "global.eula_accept";
	public static final String GLOBAL_UNIT_CHANGE = "global.unit_change";
	public static final String GLOBAL_TEMPERATURE_UNIT = "global.temperature_unit";
	public static final String GLOBAL_GRAVITY_UNIT = "global.gravity_unit";
	public static final String GLOBAL_BEER_COLOR_UNIT = "global.beer_color_unit";
	public static final String GLOBAL_EXTRACT_MASS_UNIT = "global.extract_mass_unit";
	public static final String GLOBAL_HYDROMETER_CALIBRATION_TEMPERATURE = "global.hydrometer_calibration_temperature";
	public static final String GLOBAL_PRESSURE_UNIT = "global.pressure_unit";
	public static final String GLOBAL_REFRACTOMETER_CORRECTION_FACTOR = "global.refractometer_correction_factor";
	public static final String GLOBAL_BEG_FOR_REVIEW = "global.beg_for_review";
	
	public static final String BATCH_VOLUME_UNIT = "batch.volume_unit";
	public static final String BATCH_MASH_VOLUME_UNIT = "batch.mash_volume_unit";
	public static final String BATCH_FINAL_VOLUME = "batch.final_volume";
	public static final String BATCH_GRAIN_MASS_UNIT = "batch.grain_mass_unit";
	public static final String BATCH_WATER_TO_GRAIN_RATIO = "batch.water_to_grain_ratio";
	public static final String BATCH_BOIL_MINUTES = "batch.boil_minutes";
	public static final String BATCH_MASH_MINUTES = "batch.mash_minutes";
	public static final String BATCH_GRAIN_ABSORPTION_RATIO = "batch.grain_absorption_ratio";
	public static final String BATCH_GRAIN_VOLUME_RATIO = "batch.grain_volume_ratio";
	public static final String BATCH_INFUSION_WATER_TEMPERATURE = "batch.infusion_water_temperature";
	
	public static final String KETTLE_DISTANCE_UNIT = "kettle.distance_unit";
	public static final String KETTLE_DIAMETER = "kettle.diameter";
	public static final String KETTLE_FALSE_BOTTOM_HEIGHT = "kettle.false_bottom_height";
	public static final String KETTLE_EVAPORATION_RATE = "kettle.evaporation_rate";
	public static final String KETTLE_COOLING_LOSS = "kettle.cooling_loss";
	public static final String KETTLE_CORRECT_FOR_EXPANSION = "kettle.correct_for_expansion";
	public static final String KETTLE_EQUIPMENT_LOSS = "kettle.equipment_loss";

	public static final String VERSION_1_1_0 = "version.1.1.0";
	public static final String VERSION_1_2_0 = "version.1.2.0";
	public static final String VERSION_1_3_0 = "version.1.3.0";
	public static final String VERSION_1_3_1 = "version.1.3.1";
	public static final String VERSION_1_4_0 = "version.1.4.0";
	
	public static final void init(SharedPreferences prefs) {
        Editor editor = prefs.edit();
        
        if (prefs.getBoolean(Preferences.GLOBAL_INIT, false) == false) {
	        editor.putBoolean(Preferences.GLOBAL_INIT, true);
	        editor.putString(Preferences.GLOBAL_TEMPERATURE_UNIT, "FAHRENHEIT");
	        editor.putString(Preferences.GLOBAL_GRAVITY_UNIT, "SG");
	        editor.putString(Preferences.GLOBAL_EXTRACT_MASS_UNIT, "OUNCE");
	        editor.putString(Preferences.GLOBAL_HYDROMETER_CALIBRATION_TEMPERATURE, "60");
	        editor.putString(Preferences.BATCH_VOLUME_UNIT, "GALLON");
	        editor.putString(Preferences.BATCH_FINAL_VOLUME, "6");
	        editor.putString(Preferences.BATCH_GRAIN_MASS_UNIT, "POUND");
	        editor.putString(Preferences.BATCH_WATER_TO_GRAIN_RATIO, ".31");
	        editor.putString(Preferences.BATCH_BOIL_MINUTES, "60");
	        editor.putString(Preferences.KETTLE_DISTANCE_UNIT, "INCH");
	        editor.putString(Preferences.KETTLE_DIAMETER, "0");
	        editor.putString(Preferences.KETTLE_FALSE_BOTTOM_HEIGHT, "0");
	        editor.putString(Preferences.KETTLE_EVAPORATION_RATE, "10");
	        editor.putString(Preferences.KETTLE_COOLING_LOSS, "4");
	        editor.putBoolean(Preferences.KETTLE_CORRECT_FOR_EXPANSION, false);
        }
        
        if (prefs.getBoolean(Preferences.VERSION_1_1_0, false)) {
        	editor.putBoolean(Preferences.VERSION_1_1_0, true);
	        editor.putString(Preferences.BATCH_GRAIN_ABSORPTION_RATIO, ".13");
	        editor.putString(Preferences.BATCH_GRAIN_VOLUME_RATIO, ".08");
	        editor.putString(Preferences.KETTLE_EQUIPMENT_LOSS, "0");
        }

        if (prefs.getBoolean(Preferences.VERSION_1_2_0, false)) {
        	editor.putBoolean(Preferences.VERSION_1_2_0, true);
	        editor.putString(Preferences.GLOBAL_UNIT_CHANGE, "US");        	
	        editor.putString(Preferences.BATCH_INFUSION_WATER_TEMPERATURE, "212");        	
	        editor.putString(Preferences.GLOBAL_PRESSURE_UNIT, "PSI");        	
       	}

        if (prefs.getBoolean(Preferences.VERSION_1_3_0, false)) {
        	editor.putBoolean(Preferences.VERSION_1_3_0, true);
	        editor.putString(Preferences.GLOBAL_REFRACTOMETER_CORRECTION_FACTOR, "1.04");
        }

        if (prefs.getBoolean(Preferences.VERSION_1_3_1, false)) {
        	editor.putBoolean(Preferences.VERSION_1_3_1, true);
	        editor.putString(Preferences.BATCH_MASH_VOLUME_UNIT, prefs.getString(Preferences.BATCH_VOLUME_UNIT, "GALLON"));
        }

    	editor.putString(Preferences.GLOBAL_BEER_COLOR_UNIT, "SRM");
    	editor.putString(Preferences.BATCH_MASH_MINUTES, "60");
        if (prefs.getBoolean(Preferences.VERSION_1_4_0, false)) {
        	editor.putBoolean(Preferences.VERSION_1_4_0, true);
        }
        
        editor.commit();	

	}
	
}
