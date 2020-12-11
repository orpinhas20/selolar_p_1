package com.example.project_1;

public class Record {
        private String name;
        private int score;

        public Record() { }

        public Record(String name, int score) {
            this.name = name;
            this.score = score;
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

}
