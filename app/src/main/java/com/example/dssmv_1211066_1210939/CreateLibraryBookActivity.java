package com.example.dssmv_1211066_1210939;

import DTO.CreateLibraryBookRequestDTO;
import DTO.LibraryBookDTO;
import DTO.LibraryDTO;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import service.RequestService;

public class CreateLibraryBookActivity extends AppCompatActivity {

    private Button addBookButton;
    private boolean exception = false;
    private String exceptionMessage = "";
    private String libraryId;
    private boolean isValid1 = true;
    private boolean isValid2 = true;
    private boolean isValid3 = true;
    NumberPicker stockNumberPicker;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_library_book);

        Intent createIntent = getIntent();
        libraryId = createIntent.getStringExtra("libraryId");

        stockNumberPicker = findViewById(R.id.stockNumberPicker);
        stockNumberPicker.setMinValue(0);
        stockNumberPicker.setMaxValue(100);
        stockNumberPicker.setWrapSelectorWheel(true);


        addBookButton = (Button) findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText isbnEditText = findViewById(R.id.isbnEditText);
                String isbn = isbnEditText.getText().toString();

                NumberPicker stockNumberPicker = findViewById(R.id.stockNumberPicker);
                int stock = stockNumberPicker.getValue();


                if (isbn.isEmpty()) {
                    Toast.makeText(CreateLibraryBookActivity.this, "Check if isbn was inserted", Toast.LENGTH_SHORT).show();
                    isValid1 = false;
                }

                if (isValid1 == true){
                    CreateLibraryBookRequestDTO libraryBookRequestDTO = new CreateLibraryBookRequestDTO(stock);
                    Toast.makeText(CreateLibraryBookActivity.this, "Book added successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CreateLibraryBookActivity.this, LibraryBookActivity.class);
                    postLibraryBook2WS(libraryBookRequestDTO, libraryId, isbn);
                    i.putExtra("libraryId", libraryId);
                    startActivity(i);
                }
            }
        });
    }

    private void postLibraryBook2WS(CreateLibraryBookRequestDTO libraryBookRequestDTO, String libraryId, String isbn) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {}
                });
                RequestService.createLibraryBook(libraryBookRequestDTO, CreateLibraryBookActivity.this, libraryId, isbn);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {checkOperationResult();}
                });
            }
        }.start();
    }

    public void checkOperationResult(){
        if(exception == false) {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(CreateLibraryBookActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }

}