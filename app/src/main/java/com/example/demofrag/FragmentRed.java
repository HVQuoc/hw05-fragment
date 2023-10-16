package com.example.demofrag;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentRed extends Fragment implements FragmentCallbacks {
    MainActivity main; TextView txtMa,txtTen,txtLop,txtDTB;
    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle(); bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( "Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// inflate res/layout_red.xml which includes a textview and a button
        LinearLayout view_layout_red = (LinearLayout) inflater.inflate(R.layout.layout_red, null);
        txtMa = (TextView) view_layout_red.findViewById(R.id.txtMa);
        txtTen = (TextView) view_layout_red.findViewById(R.id.txtTen);
        txtLop = (TextView) view_layout_red.findViewById(R.id.txtLop);
        txtDTB = (TextView) view_layout_red.findViewById(R.id.txtDTB);
// show string argument supplied by constructor (if any!)
        try { Bundle arguments = getArguments(); txtMa.setText(arguments.getString("arg1", "")); }
        catch (Exception e) { Log.e("RED BUNDLE ERROR – ", "" + e.getMessage()); }
// clicking the button changes the time displayed and sends a copy to MainActivity

        return view_layout_red;
    }
    @Override
    public void onMsgFromMainToFragment(Bundle value) {
// receiving a message from MainActivity (it may happen at any point in time)
        String ma = value.getString("ma");
        String ten = value.getString("ten");
        String lop = value.getString("lop");
        String DTB = value.getString("DTB");
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtLop.setText(lop);
        txtDTB.setText(DTB);
    }}// FragmentRed