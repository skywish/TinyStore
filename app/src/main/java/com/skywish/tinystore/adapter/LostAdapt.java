package com.skywish.tinystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skywish.tinystore.R;
import com.skywish.tinystore.bean.Lost;

import java.util.List;

/**
 * Created by skywish on 2015/3/18.
 */
public class LostAdapt extends ArrayAdapter<Lost> {

    private int resourceId;

    public LostAdapt(Context context, int textViewResourceId, List<Lost> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lost lost = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.describe = (TextView) view.findViewById(R.id.tv_describe);
            viewHolder.time = (TextView) view.findViewById(R.id.tv_time);
            viewHolder.phone = (TextView) view.findViewById(R.id.tv_phone);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.describe.setText(lost.getDescribe());
        viewHolder.phone.setText(lost.getPhone());
        viewHolder.title.setText(lost.getTitle());
        viewHolder.time.setText(lost.getCreatedAt());
        return view;
    }

    class ViewHolder {
        TextView describe;
        TextView phone;
        TextView title;
        TextView time;
    }
}
