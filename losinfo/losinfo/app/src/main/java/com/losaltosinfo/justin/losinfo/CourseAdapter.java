package com.losaltosinfo.justin.losinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.client.repackaged.com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 5/11/2016.
 */
public class CourseAdapter extends ArrayAdapter<Course>{
    Context context;
    private List<Course> courses;

    public CourseAdapter(Context context, int textViewResourceId, List<Course> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.courses = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.course_list_cell, null);
        }
        Course course = courses.get(position);
        if (course != null) {
            TextView courseName = (TextView) view.findViewById(R.id.courseName);
            Preconditions.checkNotNull(courseName);
            courseName.setText(course.getCourseName());
        }
        return view;
    }

    public Course getItem(int position) {
        return courses.get(position);
    }
}
