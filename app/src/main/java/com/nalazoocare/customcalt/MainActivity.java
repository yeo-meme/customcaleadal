package com.nalazoocare.customcalt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.nalazoocare.customcalt.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    public static int SUNDAY = 1;
    public static int MONDAY = 2;
    public static int TUESDAY = 3;
    public static int WEDNSESDAY = 4;
    public static int THURSDAY = 5;
    public static int FRIDAY = 6;
    public static int SATURDAY = 7;

    private ArrayList<DayInfo> mDayList;
    private CalendarAdapter mCalendarAdapter;


    private Calendar mLastMonthCalendar;
    private Calendar mThisMonthCalendar;
    private Calendar mNextMonthCalendar;


    ActivityMainBinding binding;
    private TextView mTvCalendarTitle;
    private GridView mGvCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button bLastMonth = binding.gvCalendarActivityBLast;
        Button bNextMonth = binding.gvCalendarActivityBNext;

        mTvCalendarTitle = binding.gvCalendarActivityTvTitle;
        mGvCalendar = binding.gvCalendarActivityGvCalendar;

        bLastMonth.setOnClickListener(this);
        bNextMonth.setOnClickListener(this);
        mGvCalendar.setOnItemClickListener(this);
        mDayList = new ArrayList<DayInfo>();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mThisMonthCalendar = Calendar.getInstance();
        Log.d("meme","meme resume calendar :" + mThisMonthCalendar);
        ; //Dayofmonth 현재월의 날짜 31일 현재날짜를 1일로 설정합니다
        mThisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonthCalendar);
    }

    private void getCalendar(Calendar calendar) {
        //현재 날짜 1일

        Log.d("meme","meme getCalendar :" + calendar);
        /**
         * day of month 월의 일  ex)3/1
         */
        int lastMonthStartDay;

        /**
         * // 오늘의 날짜를 7의 상수로 반환 7일의 대한 요일
         */
        int dayOfMonth;

        /**
         * //월의 일 day = 요일
         */
        int thisMonthLastDay;

        mDayList.clear();


        //일1 ~토7
        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK); // 오늘의 날짜를 7의 상수로 반환 7일의 대한 요일
        Log.e("meme", "dayOfMonth :" + calendar.get(Calendar.DAY_OF_WEEK) + "");

        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //월의 일 day = 요일

        calendar.add(Calendar.MONTH, -1); //month 월
        Log.e("meme", "meme month -1 :" + calendar.get(Calendar.MONTH) + "");

        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // day of month 월의 일  ex)3/1
        Log.e("meme", "meme month max Dayof month: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "");


        // DAY_OF_WEEK 주일 체크
        if (dayOfMonth == SUNDAY) {
            Log.d("meme","dayofMonth constant check"+ dayOfMonth);
            dayOfMonth += 7;
            //각주일 체크?
            Log.d("meme","dayofMonth constant after check"+ dayOfMonth);
        }

        //-1 차이값
        // 31  = 2
        lastMonthStartDay -= (dayOfMonth -1)-1;
        Log.d("meme","lastMonthStartDay - contans-1-1 : " + lastMonthStartDay);



        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");


        DayInfo day;
        Log.e("dayofmonth", "dayofmonth meme:" + dayOfMonth + "");

        //dayOfMonth = 3;
        for (int i = 0; i < dayOfMonth - 1; i++) {
            //29
            int date = lastMonthStartDay + i;
            //29   i=0;
            //30    i=1;
            //31    i=2;

            day = new DayInfo();
            day.setDay(Integer.toString(date));
            day.setInMonth(false);
            mDayList.add(day);
        }

        // today 21   //구한값  i = 0 ~20
        for (int i = 1; i < thisMonthLastDay; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(true);
            mDayList.add(day);
        }

        // 42- (20 +3 -1) +1 =23    size = 19
        for (int i = 1; i < 42 - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
            day = new DayInfo();
            day.setDay(Integer.toString(i));
            day.setInMonth(false);
            mDayList.add(day);
        }
        initCalendarAdapter();
    }

    private void initCalendarAdapter() {
        mCalendarAdapter = new CalendarAdapter(this, R.layout.day, mDayList);
        mGvCalendar.setAdapter(mCalendarAdapter);
    }


    private Calendar getLastMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "�� "
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "��");
        return calendar;
    }

    private Calendar getNextMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        mTvCalendarTitle.setText(mThisMonthCalendar.get(Calendar.YEAR) + "년"
                + (mThisMonthCalendar.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.gv_calendar_activity_b_last:
                mThisMonthCalendar = getLastMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
            case R.id.gv_calendar_activity_b_next:
                mThisMonthCalendar = getNextMonth(mThisMonthCalendar);
                getCalendar(mThisMonthCalendar);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        parent.getChildAt(position).setBackgroundColor(Color.YELLOW);


    }
}
