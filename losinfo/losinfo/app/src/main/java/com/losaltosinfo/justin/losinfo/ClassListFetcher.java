package com.losaltosinfo.justin.losinfo;

import android.util.Log;
import android.widget.ListView;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by justin on 2/6/2017.
 */

public class ClassListFetcher {

    private static ClassListFetcher singleton;
    private List<Course> allCourses;

    public static ClassListFetcher getInstance() {
        if(singleton == null) {
            singleton = new ClassListFetcher();
        }
        return singleton;
    }

    public boolean isDone() {
        return allCourses != null;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public void downloadCourses() {
        //Course sampleCourse = new Course("dept", "a", "b", "c", "d");
        //allCourses = ImmutableList.of(sampleCourse, sampleCourse, sampleCourse, sampleCourse, sampleCourse);
        //Log.d("CLF.downloadCourses", "done loading "+allCourses.toString());
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                Log.d("clf", "dlCOURSES DONE EE  E");
                allCourses = processJson(object);
                Log.d("clf", "dlCOURSES FINIS");
            }
        }).execute("https://spreadsheets.google.com/tq?key=1Pr9di0jFwIm_C88yp-pN3BdXB0urSxUarLU-SWEeTlU");
    }

    public static class CoursePredicate1 implements Predicate<Course> {
        private final String desired;
        public CoursePredicate1(String desired) {
            this.desired = desired;
        }
        @Override
        public boolean apply(Course course) {
            return course.getDepartment().equals(desired);
        }
    }

    private ArrayList<Course> processJson(JSONObject object) {
        ArrayList<Course> courses = new ArrayList<Course>();
        ListView listview;
        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                try {
                    JSONObject row = rows.getJSONObject(r);
                    JSONArray columns = row.getJSONArray("c");
                    String department = columns.getJSONObject(0).getString("v");
                    String courseName = columns.getJSONObject(1).getString("v");
                    String teacherName = columns.getJSONObject(3).getString("v");
                    String textbooks = columns.getJSONObject(4).getString("v");
                    String description = columns.getJSONObject(5).getString("v");


                    Course course = new Course(department, courseName, teacherName, textbooks, description);
                    courses.add(course);
                } catch (JSONException e) {
                }
            }
            return courses;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return courses;
    }


}
