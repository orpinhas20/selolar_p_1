package com.example.project_1;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App extends Application {

    public static App instance;
    private ArrayList<Record> recordsList;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

//    public ArrayList<Record> getRecordsList() {
//        if (recordsList == null) return new ArrayList<Record>();
//        return recordsList;
//    }
//
//    public void setRecordsList(ArrayList<Record> recordsList) {
//        this.recordsList = recordsList;
//    }

    public void saveRecordToSP(Record record) {

        recordsList = getSavedRecordsFromSP();
        if (recordsList != null) {
            if (recordsList.size() < Const.RECORDS_TOP_TEN) {
                recordsList.add(record);
            } else {
                int curScore = record.getScore();

                if (curScore > recordsList.get(recordsList.size() - 1).getScore()) {

                    recordsList.set(recordsList.size() - 1, record);
                }
        }
        }

        sortRecordList();
        Gson gson = new Gson();
        String json = gson.toJson(recordsList);
        Log.d("json", "json to save = " + json);
        SharedPreference.saveString(Const.RECORDS_LIST_KEY, json);
    }

    public ArrayList<Record> getSavedRecordsFromSP() {

        Type type = new TypeToken<ArrayList<Record>>() {
        }.getType();
        Gson gson = new Gson();
        String json = SharedPreference.getSavedString(Const.RECORDS_LIST_KEY);

        if (!json.isEmpty()) {
            recordsList = gson.fromJson(json, type);
        } else {
            recordsList = new ArrayList<>();
        }

        return recordsList;
    }

    public void sortRecordList() {
        Collections.sort(recordsList, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return Integer.valueOf(o2.getScore()).compareTo(Integer.valueOf(o1.getScore()));
            }
        });

    }
}