package com.example.project_1;

import java.util.Date;

public class Record {
        private String name;
        private int score;
        private Date date;

        public Record() { }

        public Record(String name, int score, Date date) {
            this.name = name;
            this.score = score;
            this.date = date;
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

}
