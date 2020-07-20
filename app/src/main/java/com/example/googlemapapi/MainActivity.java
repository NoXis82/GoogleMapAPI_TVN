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
    private EditText inputSearch;
    private Button searchBtn;
    private String formatUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        searchStart();
    }

    private void initView() {
        inputSearch = findViewById(R.id.input_address);
        searchBtn = findViewById(R.id.search);
    }

    private void searchStart() {
        searchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String searchData = inputSearch.getText().toString();
                Intent searchIntent = new Intent(Intent.ACTION_VIEW);
                if (!searchData.equals("")) {
                    int countOfLetters = 0;
                    for (int i = 0; i < searchData.length(); i++) {
                        if (Character.isLetter(searchData.charAt(i))) {
                            countOfLetters++;
                        }
                    }
                    if (countOfLetters == searchData.length()) {
                        formatUri = "geo:?q=" + searchData;
                    } else if (countOfLetters == 0) {
                        formatUri = "geo:" + searchData;
                    } else {
                        Toast.makeText(v.getContext(), R.string.errorFormat,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    searchIntent.setData(Uri.parse(formatUri));
                    inputSearch.setText("");
                    if (searchIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(searchIntent);
                    }

                } else {
                    Toast.makeText(v.getContext(), R.string.hint_edit_text,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}