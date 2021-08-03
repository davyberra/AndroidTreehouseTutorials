package com.davyberra.golfscorecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        mainFragment = (MainFragment) fragmentManager.findFragmentById(R.id.mainFragment);
        Hole[] holes = mainFragment.getHoles();

        if (id == R.id.action_clear_scorecard) {
            for (int i = 0 ; i < 18; i++) {
                holes[i].setStrokes(0);
//                String pref_file = String.format("key_score_%s", i);
//                SharedPreferences sharedPreferences = getSharedPreferences(
//                        "com.davyberra.golfscorecard.preferences", Context.MODE_PRIVATE
//                );
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt(pref_file, 0);
//                editor.apply();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}