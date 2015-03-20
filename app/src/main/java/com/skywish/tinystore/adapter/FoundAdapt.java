package com.skywish.tinystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.skywish.tinystore.R;
import com.skywish.tinystore.bean.Found;

import java.util.List;

/**
 * Created by skywish on 2015/3/20.
 */
public class FoundAdapt extends ArrayAdapter<Found> {

    private int resourceId;

    public FoundAdapt(Context context, int textViewResourceId, List<Found> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Found found = getItem(position);
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
        viewHolder.describe.setText(found.getDescribe());
        viewHolder.phone.setText(found.getPhone());
        viewHolder.title.setText(found.getTitle());
        viewHolder.time.setText(found.getCreatedAt());
        return view;
    }

    class ViewHolder {
        TextView describe;
        TextView phone;
        TextView title;
        TextView time;
    }
}
