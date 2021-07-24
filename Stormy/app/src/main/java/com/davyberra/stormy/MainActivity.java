package com.davyberra.stormy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "812c6fb8e8c133a983d95b7fee239608";

        String forecastURL = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=" + apiKey;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(forecastURL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "Http get failed");
                Log.e(TAG, forecastURL);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    Log.v(TAG, response.body().string());
                    if (response.isSuccessful()) {
                    }
                    else {
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "IO Exception caught: ", e);
                }
            }
        });


    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}