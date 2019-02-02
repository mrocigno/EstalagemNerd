package br.com.mrocigno.estalagemnerd.localdata;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String PREFS_APP = "AlicizationPreferences-App";
    public static final String THEME_DATA = "theme_data";

    private SharedPreferences preferences;

    public static Preferences getInstance(Context context){
        return new Preferences(context);
    }

    private Preferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_APP, 0);
    }

    public static Preferences getInstace(Context c){
        return new Preferences(c);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setDarkTheme(boolean dark){
        getEditor().putBoolean(THEME_DATA, dark).commit();
    }

    public boolean isDarkTheme(){
        return preferences.getBoolean(THEME_DATA, false);
    }

    public void setFirstTimeAskingPermission(String permission, boolean value){
        getEditor().putBoolean(permission, value).commit();
    }

    public boolean isFirstTimeAskingPermission(String permission){
        return preferences.getBoolean(permission, true);
    }
}
