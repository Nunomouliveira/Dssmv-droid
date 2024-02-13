package com.example.dssmv_1211066_1210939;

import DTO.LibraryDTO;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Library;
import service.RequestService;

public class EditLibraryActivity extends AppCompatActivity {

    private boolean exception = false;
    private String exceptionMessage = "";
    private ListView lv;

    private String libraryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_library);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button updateLibraryButton = (Button) findViewById(R.id.updateLibraryButton);

        Intent i = getIntent();
        libraryId = i.getStringExtra("libraryId");


        getLibraryFromWS(EditLibraryActivity.this, libraryId);


        updateLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameInsertView = findViewById(R.id.nameUpdateInsertView);
                String name = nameInsertView.getText().toString();

                String id = libraryId;

                EditText addressInsertView = findViewById(R.id.addressUpdateInsertView);
                String address = addressInsertView.getText().toString();

                EditText openInsertView = findViewById(R.id.openUpdateInsertView);
                Boolean open = Boolean.parseBoolean(openInsertView.getText().toString());

                EditText openDaysInsertView = findViewById(R.id.openDaysUpdateInsertView);
                String openDays = openDaysInsertView.getText().toString();

                EditText openStatementInsertView = findViewById(R.id.openStatementUpdateInsertView);
                String openStatement = openStatementInsertView.getText().toString();

                EditText openTimeInsertView = findViewById(R.id.openTimeUpdateInsertView);
                String openTime = openTimeInsertView.getText().toString();

                EditText closeTimeInsertView = findViewById(R.id.closeTimeUpdateInsertView);
                String closeTime = closeTimeInsertView.getText().toString();


                LibraryDTO libraryDTO = new LibraryDTO(name, id, address, open, openDays, openStatement,openTime, closeTime);
                putLibrary2WS(libraryDTO.getId(), libraryDTO);
                Toast.makeText(EditLibraryActivity.this, "Library updated successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditLibraryActivity.this, LibraryActivity.class);
                startActivity(i);
            }
        });
    }

    private void getLibraryFromWS(Activity activity, String libraryId) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {}
                });
                Library library= RequestService.getLibrary(activity, libraryId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EditText nameInsertView = findViewById(R.id.nameUpdateInsertView);
                        nameInsertView.setText(library.getName());

                        EditText addressInsertView = findViewById(R.id.addressUpdateInsertView);
                        addressInsertView.setText(library.getAddress());

                        EditText openInsertView = findViewById(R.id.openUpdateInsertView);
                        openInsertView.setText(String.valueOf(library.getOpen()));

                        EditText openDaysInsertView = findViewById(R.id.openDaysUpdateInsertView);
                        openDaysInsertView.setText(library.getOpenDays());

                        EditText openStatementInsertView = findViewById(R.id.openStatementUpdateInsertView);
                        openStatementInsertView.setText(library.getOpenStatement());

                        EditText openTimeInsertView = findViewById(R.id.openTimeUpdateInsertView);
                        openTimeInsertView.setText(library.getOpenTime());

                        EditText closeTimeInsertView = findViewById(R.id.closeTimeUpdateInsertView);
                        closeTimeInsertView.setText(library.getCloseTime());
                    }
                });
            }
        }.start();
    }

    private void putLibrary2WS(String id, LibraryDTO libraryDTO) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {}
                });

                RequestService.updateLibrary(id, libraryDTO, EditLibraryActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        checkOperationResult();
                    }
                });
            }
        }.start();
    }

    private void checkOperationResult(){
        if(exception == false) {
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(EditLibraryActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
        }
    }
}
