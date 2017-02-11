package com.losaltosinfo.justin.losinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;


public class ClassesList extends AppCompatActivity {
    private List<Course> allCourses;
    private String password = "password";
    private static String[] mathCourses = {"Geometry", "Geometry H", "Algebra 9", "Algebra I", "Algebra II", "Alg II honors", "Calculus", "AP Calculus AB", "AP Calculus BC", "Intro Comp Sci", "AP Comp Sci", "Multivar calc", "Stats", "AP Stats", "Trig", "Trig H"};
    private static String[] englishCourses = {"American Literature Survey", "English Literature Survey", "Expository Reading and Writing I/II", "Film Analysis", "Ap English and Composition", "Global connections A/B", "AP Language and Composition", "Society and Politics in Lit", "Comp Journalism/Technalism Writing", "Survey of Composition and Literature", "World Literature", "English 10"};
    private static String[] scienceCourses = {"Bio", "Bio H", "AP Bio", "Chem", "Chem H", "AP Chem", "Physics", "AP Physics 1", "AP Physics C", "Earth Science", "AP Enviroment Science", "Forensics", "Health Science Career", "Health Science Career II", "Intro to Bio Tech", "Intro to Engineering Technology/Robotics", "Psychology", "AP Psychology", "Advanced Science Investigation", "Priciples of Engineering"};
    private static String[] historyCourses = {"World Studies", "Contemporary World Issues", "AP Euro History", "AP History of Art", "US History", "Global Connections A& B", "AP US History", "Civics", "AP US Govt. Politics", "Economics", "AP Microeconomics"};
    private static String[] langCourses = {"Spanish I", "Spanish II", "Spanish IIH", "Spanish III", "Spanish IIIH", "Spanish IV", "AP Spanish Language", "French", "French II", "French III", "French IIIH", "French AP", "Latin I", "Latin II", "Latin III", "Latin IV", "Latin AP", "Mandarin/Chinese I", "Mandarin/Chinese II", "Mandarin/Chinese III", "Mandarin/Chinese IV", "AP Mandarin/ Chinese Language/ Culture"};
    private static String[] artCourses = {"Digital Photography", "Drawing I", "Drawing II", "Drawing III", "Painting I", "Photography I", "Photography II", "AP Studio Art", "Acting I", "Acting II", "Chamber Ensemble", "Chorale", "Concert Band", "Concert Choir", "Girls Ensemble", "Girls' 21", "Jazz Dance", "Jazz Dance -Advanced", "Jazz Ensemble", "Main Street", "Orchestra", "Symphonic Band", "Varsity Mens Glee", "Wind ensemble", "Marching Band"};

    private static Map<String, List<String>> COURSES_MAP = generateCoursesMap();

    private static Map<String, List<String>> generateCoursesMap() {
        ImmutableMap.Builder<String, List<String>> map = ImmutableMap.builder();
        map.put("Math", toList(mathCourses));
        map.put("English", toList(englishCourses));
        map.put("Science", toList(scienceCourses));
        map.put("History", toList(historyCourses));
        map.put("Language", toList(langCourses));
        map.put("Art", toList(artCourses));
        return map.build();
    }

    private static List<String> toList (String[] values) {
        return ImmutableList.copyOf(values);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                allCourses = processJson(object);
                populateListView();
            }
        }).execute("https://spreadsheets.google.com/tq?key=1Pr9di0jFwIm_C88yp-pN3BdXB0urSxUarLU-SWEeTlU");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//         ATTENTION: This was auto-generated to implement the App Indexing API.
//         See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void populateListView() {
        Intent intent = getIntent();
        String desiredCourses = intent.getStringExtra(password);
        List<Course> filteredCourses = FluentIterable.from(allCourses).filter(new CoursePredicate(desiredCourses)).toImmutableList();
        ListView list = (ListView) findViewById(R.id.listViewMain);
        final CourseAdapter adapter = new CourseAdapter(this, R.layout.course_list_cell, filteredCourses);
        list.setAdapter(adapter);
        final AppCompatActivity thisThis = this;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course item = adapter.getItem(position);
                Intent intent = new Intent(thisThis, ClassInfo.class);
                intent.putExtra("coursename", item.getCourseName());
                intent.putExtra("teachers", item.getTeacherName());
                intent.putExtra("textbooks", item.getTextbooks());
                intent.putExtra("description", item.getDescription());
                startActivity(intent);
            }
        });
    }

    private static class CoursePredicate implements Predicate<Course>  {
        private final String desired;
        public CoursePredicate(String desired) {
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


