package com.davyberra.sharedpreferencesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.davyberra.sharedpreferencesapp.preferences";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private EditText editText;
    private String KEY_EDITTEXT = "key_edittext";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String editTextString = sharedPreferences.getString(KEY_EDITTEXT, "");
        editText.setText(editTextString);
    }

    @Override
    protected void onPause() {
        super.onPause();

        editor.putString(KEY_EDITTEXT, editText.getText().toString());
        editor.apply();
    }
}