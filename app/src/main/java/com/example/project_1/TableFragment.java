package com.example.project_1;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TableFragment extends Fragment implements RecordsAdapter.ItemClickListener {

    private RecyclerView last_RV_table;
    private RecordsAdapter adapter;

    public TableFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Bind the fragment class with the fragment layout:
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {

        // Set RecyclerView layout manager:
        this.last_RV_table.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get data from SharedPreferences:
        ArrayList<Record> data = App.instance.getSavedRecordsFromSP();

        // Set the RecyclerView adapter:
        this.adapter = new RecordsAdapter(getContext(), data);
        this.adapter.setClickListener(this);

        // Bind the RecyclerView with the RecordAdapter:
        this.last_RV_table.setAdapter(adapter);
    }

    /* Find UI element of given view. */
    private void findViews(View view) {
        this.last_RV_table = view.findViewById(R.id.last_RV_table);
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: Remove !!!
        Toast.makeText(getContext(), "Location: " + this.adapter.getItem(position).getLocation().toString(), Toast.LENGTH_SHORT).show();
    }
}