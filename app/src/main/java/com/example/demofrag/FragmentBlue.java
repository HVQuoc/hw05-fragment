package com.example.demofrag;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class FragmentBlue extends Fragment {
    // this fragment shows a ListView
    MainActivity main; Context context = null; String message = "";
    // data to fill-up the ListView
    private String ten[] = {"Lê Thị A","Lê Thị B","Lê Thị C","Lê Thị D","Lê Thị F","Lê Thị G","Lê Thị H","Lê Thị I","Lê Thị J","Lê Thị K",};
    private String ma[] = {"A1_1801","A2_1802","A3_1803","A4_1804","A5_1805","A6_1806","A7_1807","A8_1808","A9_1809","A10_1810" };
    private String lop[] = {"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10"};
    private String DTB[] = {"9","9","9","9","9","9","9","9","9","9"};
    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static FragmentBlue newInstance(String strArg) {
        FragmentBlue fragment = new FragmentBlue();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }
    FragmentBlue current = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// inflate res/layout_blue.xml to make GUI holding a TextView and a ListView
        LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.layout_blue, null);
// plumbing – get a reference to textview and listview
        TextView txtChoosen = (TextView) layout_blue.findViewById(R.id.txtChoosen) ;
        ListView listView = (ListView) layout_blue.findViewById(R.id.listView1Blue);
        listView.setBackgroundColor(Color.parseColor("#ffccddff"));
// define a simple adapter to fill rows of the listview
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, ma);

        CustomIconLabelAdapter custom = new CustomIconLabelAdapter(context,R.layout.custom_layout,ma);
        listView.setAdapter(custom);
// show listview from the top
        listView.setSelection(0); listView.smoothScrollToPosition(0);
// react to click events on listview’s rows
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
// inform enclosing MainActivity of the row’s position just selected
                Bundle myBundle = new Bundle();
                myBundle.putString("ten",ten[position]);
                myBundle.putString("ma",ma[position]);
                myBundle.putString("lop",lop[position]);
                myBundle.putString("DTB",DTB[position]);
                main.onMsgFromFragToMain("BLUE-FRAG", myBundle);
                txtChoosen.setText(ma[position]);
            }});
// do this for each row (ViewHolder-Pattern could be used for better performance!)
        return layout_blue;
    }// onCreateView
}// class

class CustomIconLabelAdapter extends ArrayAdapter<String> {
    Context context;
    String[] ma;

    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] ma) {
        super(context, R.layout.custom_layout, ma);
        this.context = context;
        this.ma = ma;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_layout, null);
        TextView txtMa = (TextView) row.findViewById(R.id.txtMa1);
        txtMa.setText(ma[position]);
        ImageView img = (ImageView) row.findViewById(R.id.imageView);
        img.setImageResource(R.drawable.avatar);
        return (row);
    }
}
