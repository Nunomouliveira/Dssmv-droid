package com.example.dssmv_1211066_1210939;

import DTO.LibraryBookDTO;
import adapter.ListViewAdapterLibrary;
import adapter.ListViewAdapterLibraryBook;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Library;
import model.LibraryBook;
import service.RequestService;

import java.util.ArrayList;
import java.util.List;

public class LibraryBookActivity extends AppCompatActivity{

    private ListView lv;
    private List<LibraryBook> libraryBooksList;
    private List<LibraryBook> libraryBookDTOS;
    private ListViewAdapterLibraryBook adapter;
    private String libraryId;

    private String libraryBookIsbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_book);

        getLibraryBooksFromWs();

        Intent intent = getIntent();
        libraryId = intent.getStringExtra("libraryId");

        lv = findViewById(R.id.libraryBooksListView);
        libraryBooksList = new ArrayList<>();

        adapter = new ListViewAdapterLibraryBook(this, libraryBooksList);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);


    }

    private void getLibraryBooksFromWs() {
        new Thread() {
            public void run() {

                libraryBookDTOS = RequestService.getLibraryBooks(LibraryBookActivity.this, libraryId);
                libraryBooksList.clear();
                if (libraryBookDTOS == null){
                    return;
                }
                libraryBooksList.addAll(libraryBookDTOS);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu_library_book, menu);
        menu.setHeaderTitle("Select The Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        LibraryBook libraryBook = (LibraryBook) lv.getItemAtPosition(position);
        libraryBookIsbn = libraryBook.getBook().getIsbn();

        if (item.getItemId() == R.id.stock) {
            Intent i = new Intent(LibraryBookActivity.this, EditStockActivity.class);
            i.putExtra("libraryBookIsbn", libraryBookIsbn);
            i.putExtra("libraryId", libraryId);
            startActivity(i);
            return true;
        }
        return super.onContextItemSelected(item);
    }
}