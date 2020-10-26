package kr.ac.ssu.kjminn.gameclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronoTimer;
    RadioGroup rg;
    RadioButton radioDate, radioTime;
    TimePicker timePick;
    CalendarView calendar1;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        chronoTimer = findViewById(R.id.chrono_timer);
        rg = findViewById(R.id.rg);
        radioDate = findViewById(R.id.radio_date);
        radioTime = findViewById(R.id.radio_time);
        timePick = findViewById(R.id.time_pick);
        calendar1 = findViewById(R.id.calendar1);
        textResult = findViewById(R.id.text_result);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnDone = findViewById(R.id.btn_done);
        btnStart.setOnClickListener(btnListener);
        btnDone.setOnClickListener(btnListener);

        radioDate.setOnClickListener(radioListener);
        radioTime.setOnClickListener(radioListener);
        calendar1.setOnDateChangeListener(dateChangeListener);

    }

    View.OnClickListener radioListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            timePick.setVisibility(View.INVISIBLE);
            calendar1.setVisibility(View.INVISIBLE);
            switch (v.getId()){
                case R.id.radio_date:
                    calendar1.setVisibility(View.VISIBLE);
                    break;
                case R.id.radio_time:
                    timePick.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    chronoTimer.setBase(SystemClock.elapsedRealtime());
                    chronoTimer.start();
                    chronoTimer.setTextColor(Color.RED);
                    break;
                case R.id.btn_done:
                    chronoTimer.stop();
                    chronoTimer.setTextColor(Color.BLUE);
                    String result = dateStr + timePick.getCurrentHour() + "시 " + timePick.getCurrentMinute() + "분";
                    textResult.setText(result);
                    break;
            }
        }
    };

    String dateStr = "";

    CalendarView.OnDateChangeListener dateChangeListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            dateStr = year + "년 " + (month+1) + "월 " + dayOfMonth + "일 ";
        }
    };
}