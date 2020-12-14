package com.example.project_1;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class Record {
        private String name;
        private int score;
        private Date date;
        private LatLng location;

        public Record() { }

        public Record(String name, int score, Date date, LatLng location) {
            this.name = name;
            this.score = score;
            this.date = date;
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public Record setName(String name) {
            this.name = name;
            return this;
        }

        public int getScore() {
            return score;
        }

        public Record setScore(int score) {
            this.score = score;
            return this;
        }

        public Date getDate(){
            return this.date;
        }

        public Record setDate(Date date){
            this.date = date;
            return this;
        }

        public LatLng getLocation() { return this.location; }

        public Record setLocation(LatLng location){
            this.location = location;
            return this;
        }
}
