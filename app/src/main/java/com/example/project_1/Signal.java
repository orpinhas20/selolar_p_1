package com.example.project_1;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class Signal {


        private static Signal instance;
        private Context context;

        public static Signal getInstance() {
            return instance;
        }

        private Signal(Context context) {
            this.context = context.getApplicationContext();
        }

        public static void init(Context context) {
            if (instance == null) {
                instance = new Signal(context);
            }
        }

        public void vibrate() {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }

    }
}
