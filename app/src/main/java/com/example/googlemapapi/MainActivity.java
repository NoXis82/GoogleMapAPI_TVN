package com.example.googlemapapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText input_search;
    private Button searchBtn;
    private String formatUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        searchStart();
    }

   private void searchStart() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchData = input_search.getText().toString();
                Intent searchIntent = new Intent(Intent.ACTION_VIEW);
                if (searchData.equals("")) {
                    Toast.makeText(v.getContext(), R.string.hint_edit_text,
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (Character.isLetter(searchData.charAt(0))) {
                    formatUri = "geo:?q=" + searchData;
                    searchIntent.setData(Uri.parse(formatUri));
                } else {
                    formatUri = "geo:" + searchData;
                    searchIntent.setData(Uri.parse(formatUri));
                }
                input_search.setText("");
                if (searchIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(searchIntent);
                }
            }
        });
    }

    private void initView() {
        input_search = findViewById(R.id.input_address);
        searchBtn = findViewById(R.id.search);
    }
}