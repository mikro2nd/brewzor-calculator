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
package com.brewzor.calculator;

import com.brewzor.calculator.R;
import com.brewzor.recipemanager.CreateRecipeActivity;
import com.brewzor.recipemanager.FermentableActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OptionsMenuHandler {
	/* Handles item selections */
	static public boolean showMenu(Activity activity, MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.options_menu_add_fermentable:
	    	activity.startActivity(new Intent(activity.getBaseContext(), FermentableActivity.class));
	    	return false;
	    case R.id.options_menu_add:
	    	activity.startActivity(new Intent(activity.getBaseContext(), CreateRecipeActivity.class));
	        return true;
	    case R.id.options_menu_settings:
	    	activity.startActivity(new Intent(activity.getBaseContext(), BrewzorPreferencesActivity.class));
	        return true;
	    case R.id.options_menu_about:
	    	activity.startActivity(new Intent(activity.getBaseContext(), AboutActivity.class));
	    	return true;
	    case R.id.options_menu_help:
	    	activity.startActivity(new Intent(activity.getBaseContext(), HelpActivity.class));
	    	return true;
	    }
	    return false;
	}
	
	static public boolean createFermentableMenu(Activity activity, Menu menu) {
	    MenuInflater inflater = activity.getMenuInflater();
	    inflater.inflate(R.menu.fermentable_menu, menu);
	    return true;
	}
	
	static public boolean createMenu(Activity activity, Menu menu) {
	    MenuInflater inflater = activity.getMenuInflater();
	    inflater.inflate(R.menu.options_menu, menu);
	    return true;
	}

	static public boolean createTimerMenu(Activity activity, Menu menu) {
	    MenuInflater inflater = activity.getMenuInflater();
	    inflater.inflate(R.menu.timer_menu, menu);
	    return true;
	}

}