package adapter;
import DTO.LibraryDTO;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.dssmv_1211066_1210939.R;
import model.Book;
import model.Library;


public class ListViewAdapterLibrary extends BaseAdapter{

    private Context context;
    private List<Library> items;

    public ListViewAdapterLibrary(Context context, List<Library> items) {
        this.context = context;
        this.items = items;
    }

    public String getName(int position) {
        return this.items.get(position).getName();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_item, null);
        } else {
            itemView = convertView;
        }

        TextView tv = (TextView) itemView.findViewById(R.id.textview_line);

        tv.setText(getName(position));

        return itemView;
    }


}