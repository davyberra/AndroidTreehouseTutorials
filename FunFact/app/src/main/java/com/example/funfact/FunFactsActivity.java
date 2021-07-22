package com.example.funfact;

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
    // Declare our View variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout mainLayout;
    private FactBook factBook = new FactBook();
    private ColorPicker colorPicker = new ColorPicker();

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
                String fact = factBook.getFact();
                int color = colorPicker.getColor();

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