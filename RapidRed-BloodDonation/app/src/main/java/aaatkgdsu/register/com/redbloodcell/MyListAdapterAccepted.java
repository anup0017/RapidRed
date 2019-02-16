package aaatkgdsu.register.com.redbloodcell;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapterAccepted extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name1;
    private final String[] blood_group;
    private  final String[] phone;
    //private  final String[] age;


    public MyListAdapterAccepted(Activity context, String[] name, String[] blood_group, String[] phone) {
        super(context, R.layout.list_item_accepted, name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name1=name;
        this.blood_group = blood_group;
        this.phone = phone;
        //this.age = age;

    }
    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item_accepted, null,true);

        TextView nametv = (TextView) rowView.findViewById(R.id.name);
        TextView bgtv=(TextView) rowView.findViewById(R.id.blood_group);
        TextView phonetv=(TextView)rowView.findViewById(R.id.phone);

        nametv.setText(name1[position]);
        bgtv.setText(blood_group[position]);

        phonetv.setText(phone[position]);

        return rowView;

    }
}
