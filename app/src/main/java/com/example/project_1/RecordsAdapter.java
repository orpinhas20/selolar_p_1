package com.example.project_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {

    private ArrayList<Record> mRecords;
    private LayoutInflater mInflater;
    private Context mContext;
    private TextView TV_record;
    private TextView TV_date;
    private TextView TV_player_name;
    private ItemClickListener clickListener;

    public RecordsAdapter() { }

    public RecordsAdapter(Context context, ArrayList<Record> records) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mRecords = records;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the row layout from xml when needed:
        View view = mInflater.inflate(R.layout.record_item_layout, parent, false);
        return new RecordsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Record currentItem = mRecords.get(position);

        // Take care of empty / null data:
        String name = Optional.of(currentItem.getName()).orElse("");
        String score = Optional.of(currentItem.getScore() + "").orElse("");
        String date = Optional.ofNullable(currentItem.getDate()).map((d) -> {
            SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-YYYY");
            return dt1.format(d).toString();
        }).orElse("");

        // Update UI:
        holder.name.setText(name);
        holder.record.setText(score);
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    Record getItem(int id) {
        return this.mRecords.get(id);
    }

    // UI will implement this method to respond to click events:
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // Set callback for click events:
    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /* Adapter class represent one record in the RecyclerView list. */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Define UI elements:
        private TextView name;
        private TextView record;
        private TextView date;

        public MyViewHolder(@NonNull View view) {
            super(view);
            view.setOnClickListener(this);
            this.name = itemView.findViewById(R.id.TV_player_name);
            this.record = itemView.findViewById(R.id.TV_record);
            this.date = itemView.findViewById(R.id.TV_date);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                Log.d("CardWar","Fire click listener, Adapter position: " + getAdapterPosition());
                clickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}