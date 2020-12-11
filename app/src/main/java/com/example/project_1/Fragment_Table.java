package com.example.project_1;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;

public class Fragment_Table extends Fragment {


    private TextView list_LBL_name;
    private Button list_BTN_update;
    private CallBack_Table callBack_top;

    public void setCallBack_top(CallBack_Table _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_table, container, false);
        findViews(view);
        initViews();

        return view;
    }

    private void initViews() {
        list_BTN_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack_top != null) {
                    callBack_top.displayLocation(32.05889116392735, 34.811619248137916);
                }
            }
        });
    }

    public void updateTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy\nHH:mm:ss");
        String date = format.format(System.currentTimeMillis());

        list_LBL_name.setText(date);
    }

    private void findViews(View view) {
        //list_LBL_name = view.findViewById(R.id.list_LBL_name);
        //list_BTN_update = view.findViewById(R.id.list_BTN_update);
    }
}