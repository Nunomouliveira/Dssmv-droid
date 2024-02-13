package com.example.dssmv_1211066_1210939;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import service.RequestService;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        EditText searchEditText = findViewById(R.id.searchView);

        Button getBookButton = findViewById(R.id.getBookButton);

        getBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookIsbn = searchEditText.getText().toString();

            }
        });

    }



}




















