package helper;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dssmv_1211066_1210939.R;

public class CustomToast extends Toast {
    /**

     Construct an empty Toast object.  You must call {@link #setView} before you
     can call {@link #show}.*
     @param context The context to use.  Usually your {@link Application}
     or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);}

    public static Toast makeText(Context context, CharSequence text, int duration) {
        Toast t = Toast.makeText(context,text,duration);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View layout = inflater.inflate(R.layout.custom_toast,null);

        TextView textView = layout.findViewById(R.id.text);
        textView.setText(text);

        t.setView(layout);


        return t;
    }

}
