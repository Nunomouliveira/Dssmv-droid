package com.example.dssmv_1211066_1210939;

import DTO.LibraryDTO;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import service.RequestService;

public class CreateLibraryActivity extends AppCompatActivity {

    private Button addLibraryButton;
    private boolean exception = false;
    private String exceptionMessage = "";
    private boolean isValid1 = true;
    private boolean isValid2 = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_library);


        addLibraryButton = (Button) findViewById(R.id.addLibraryButton);
        addLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameInsertView = findViewById(R.id.nameInsertView);
                String name = nameInsertView.getText().toString();

                EditText addressInsertView = findViewById(R.id.addressInsertView);
                String address = addressInsertView.getText().toString();

                EditText openInsertView = findViewById(R.id.openInsertView);
                Boolean open = Boolean.parseBoolean(openInsertView.getText().toString());

                EditText openDaysInsertView = findViewById(R.id.openDaysInsertView);
                String openDays = openDaysInsertView.getText().toString();

                EditText openStatementInsertView = findViewById(R.id.openStatementInsertView);
                String openStatement = openStatementInsertView.getText().toString();

                EditText openTimeInsertView = findViewById(R.id.openTimeInsertView);
                String openTime = openTimeInsertView.getText().toString();

                EditText closeTimeInsertView = findViewById(R.id.closeTimeInsertView);
                String closeTime = closeTimeInsertView.getText().toString();

                String openInput = openInsertView.getText().toString().toLowerCase();
                if (!openInput.equals("true") && !openInput.equals("false")) {
                    Toast.makeText(CreateLibraryActivity.this, "Introduza 'true' ou 'false'", Toast.LENGTH_SHORT).show();
                    isValid1 = false;
                } else {
                    open = Boolean.parseBoolean(openInput);

                    if (name.isEmpty() || address.isEmpty() || openDays.isEmpty() || openStatement.isEmpty() || openTime.isEmpty() || closeTime.isEmpty()) {
                        Toast.makeText(CreateLibraryActivity.this, "Algum atributo da Biblioteca ainda está por preencher", Toast.LENGTH_SHORT).show();
                        isValid2 = false;
                    } else {
                        isValid1 = true;
                        isValid2 = true;
                    }
                }

                if (isValid1 == true && isValid2 == true) {
                    LibraryDTO libraryDTO = new LibraryDTO(name, null, address, open, openDays, openStatement,openTime, closeTime);
                    postLibrary2WS(libraryDTO);
                    Toast.makeText(CreateLibraryActivity.this, "Library added successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CreateLibraryActivity.this, LibraryActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void postLibrary2WS(LibraryDTO libraryDTO) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {}
                });
                RequestService.createLibrary(libraryDTO, CreateLibraryActivity.this);
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
            Toast.makeText(CreateLibraryActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
}