package com.nalazoocare.customcalt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-04-21
 */
public class CalendarAdapter extends BaseAdapter {


    private ArrayList<DayInfo> mDayList;
    private Context mContext;
    private int mResource;
    private LayoutInflater mInflater;


    public CalendarAdapter(Context context, int textResource, ArrayList<DayInfo> dayList) {
        this.mContext = context;
        this.mResource = textResource;
        this.mDayList = dayList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        DayInfo day = mDayList.get(position);
        DayViewHolde dayViewHolder;

        if (view == null) {
            view = mInflater.inflate(mResource,null);

            if (position % 7 == 6) {
                view.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()+getRestCellWidthDP(), getCellHeightDP()));
            } else {
                view.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(),getCellHeightDP()));
            }

            dayViewHolder = new DayViewHolde();
            dayViewHolder.llBackground = view.findViewById(R.id.day_cell_ll_background);
            dayViewHolder.tvDay = view.findViewById(R.id.day_cell_tv_day);

            view.setTag(dayViewHolder);

        } else {
            dayViewHolder = (DayViewHolde) view.getTag();
        }

        if (day!= null) {
            dayViewHolder.tvDay.setText(day.getDay());
            if (day.isInMonth()) {
                if (position % 7 ==0) {
                    dayViewHolder.tvDay.setTextColor(Color.RED);
                } else if (position % 7 == 6) {
                    dayViewHolder.tvDay.setTextColor(Color.BLUE);
                } else {
                    dayViewHolder.tvDay.setTextColor(Color.BLACK);
                }
            } else {
                dayViewHolder.tvDay.setTextColor(Color.GRAY);
            }
        }

        return view;
    }
    public class DayViewHolde
    {
        public LinearLayout llBackground;
        public TextView tvDay;

    }



    private int getCellWidthDP() {
        int cellWidth = 480/7;
        return cellWidth;
    }


    private int getRestCellWidthDP() {
        int cellWidth = 480%7;
        return cellWidth;
    }

    private int getCellHeightDP() {
        int cellHeight = 480/6;
        return cellHeight;
    }

}
