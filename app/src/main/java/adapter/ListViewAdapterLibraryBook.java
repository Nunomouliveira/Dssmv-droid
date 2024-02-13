package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.dssmv_1211066_1210939.R;
import model.Library;
import model.LibraryBook;

import java.util.List;

public class ListViewAdapterLibraryBook extends BaseAdapter {

    private Context context;
    private List<LibraryBook> items;

    public ListViewAdapterLibraryBook(Context context, List<LibraryBook> items) {
        this.context = context;
        this.items = items;
    }


    public int getCount() {
        return this.items.size();
    }


    public Object getItem(int position) {
        return this.items.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_item, null);
        }

        TextView tv = itemView.findViewById(R.id.textview_line);

        // Get the LibraryBook instance at the specified position
        LibraryBook libraryBook = (LibraryBook) getItem(position);

        // Use the LibraryBook instance to set the text (adjust this based on your LibraryBook properties)
        if (libraryBook != null) {
            // For example, if you want to display ISBN, change the property accordingly
            tv.setText("Book Title: " + libraryBook.getBook().getTitle() + "\nISBN: " + libraryBook.getIsbn() + "\nByStatement: " + libraryBook.getBook().getByStatement() + "\nDescription: " + libraryBook.getBook().getDescription() + "\nNumber of Pages:" + libraryBook.getBook().getNumberOfPages() + "\nPublish Date:" + libraryBook.getBook().getPublishDate() + "\nStock: " + libraryBook.getStock());

        }

        return itemView;
    }



}
