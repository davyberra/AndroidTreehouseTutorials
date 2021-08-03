package com.example.funfact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {
    public static final String TAG = FunFactsActivity.class.getSimpleName();
    private static final String KEY_FACT = "KEY_FACT";
    private static final String KEY_COLOR = "KEY_COLOR";
    // Declare our View variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout mainLayout;
    private FactBook factBook = new FactBook();
    private ColorPicker colorPicker = new ColorPicker();
    private String fact = factBook.initializeFact();
    private int color = colorPicker.initializeColor();

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_FACT, fact);
        outState.putInt(KEY_COLOR, color);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fact = savedInstanceState.getString(KEY_FACT);
        factTextView.setText(fact);
        color = savedInstanceState.getInt(KEY_COLOR);
        mainLayout.setBackgroundColor(color);
        showFactButton.setTextColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Assign the Views from the layout file to the corresponding variables
        factTextView = findViewById(R.id.fact_text_view);
        showFactButton = findViewById(R.id.show_fact_button);
        mainLayout = findViewById(R.id.main_layout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fact = factBook.getFact();
                color = colorPicker.getColor();

                // update with new fact
                factTextView.setText(fact);
                mainLayout.setBackgroundColor(color);
                showFactButton.setTextColor(color);
            }
        };
        showFactButton.setOnClickListener(listener);

//        Toast.makeText(this, "Yay! Our activity was created!", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "We're logging from the onCreate() method!");
    }
}