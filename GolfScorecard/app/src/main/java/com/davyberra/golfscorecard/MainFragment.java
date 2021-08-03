package com.davyberra.golfscorecard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

public class MainFragment extends Fragment {

    private static final String PREFS_FILE = "com.davyberra.golfscorecard.preferences";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String KEY_SCORE = "key_score_";
    private Hole[] holes = new Hole[18];

   public MainFragment() {
        // Required empty public constructor
    }

    public Hole[] getHoles() {
        return holes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        for (int i = 0; i < holes.length; i++) {
            String pref_key = KEY_SCORE + Integer.toString(i);
            holes[i] = new Hole(i);
            Hole hole = holes[i];
            int strokes = sharedPreferences.getInt(pref_key, hole.getStrokes());
            hole.setStrokes(strokes);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvHoles);

        HoleAdapter adapter = new HoleAdapter(holes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences = getContext().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        for (int i = 0; i < holes.length; i++) {
            String pref_key = KEY_SCORE + Integer.toString(i);
            Hole hole = holes[i];
            editor.putInt(pref_key, hole.getStrokes());
            editor.apply();

        }
    }
}