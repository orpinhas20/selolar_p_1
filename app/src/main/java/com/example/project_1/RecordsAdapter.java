package com.example.project_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.pdac.antiquitiesauthority.R;
//import com.pdac.antiquitiesauthority.data.Employee;
//import com.pdac.antiquitiesauthority.managers.FragmentsManager;
//import com.pdac.antiquitiesauthority.managers.ViewModelManager;
//import com.pdac.antiquitiesauthority.viewmodel.NewReportViewModel;

import java.util.ArrayList;


public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {

    private ArrayList<Record> mRecords;
    private LayoutInflater mInflater;
    private Context mContext;
    private TextView TV_record;
    private TextView TV_date;
    private TextView TV_player_name;


    public RecordsAdapter() { }

    public RecordsAdapter(Context context, ArrayList<Record> records) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mRecords = records;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.record_item_layout, parent, false);
        return new RecordsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Record currentItem = mRecords.get(position);

//        holder.mItemNameTv.setText(currentItem.getAttributes().getEmployeeName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_player_name = itemView.findViewById(R.id.TV_player_name);
            TV_date = itemView.findViewById(R.id.TV_date);
            TV_record = itemView.findViewById(R.id.TV_record);
        }

    }
}