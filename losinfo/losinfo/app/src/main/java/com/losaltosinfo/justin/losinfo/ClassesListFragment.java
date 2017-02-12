package com.losaltosinfo.justin.losinfo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.losaltosinfo.justin.losinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassesListFragment extends Fragment {

    private List<Course> allCourses;
    private String password = "password";
    private static String[] mathCourses = {"Geometry", "Geometry H", "Algebra 9", "Algebra I", "Algebra II", "Alg II honors", "Calculus", "AP Calculus AB", "AP Calculus BC", "Intro Comp Sci", "AP Comp Sci", "Multivar calc", "Stats", "AP Stats", "Trig", "Trig H"};
    private static String[] englishCourses = {"American Literature Survey", "English Literature Survey", "Expository Reading and Writing I/II", "Film Analysis", "Ap English and Composition", "Global connections A/B", "AP Language and Composition", "Society and Politics in Lit", "Comp Journalism/Technalism Writing", "Survey of Composition and Literature", "World Literature", "English 10"};
    private static String[] scienceCourses = {"Bio", "Bio H", "AP Bio", "Chem", "Chem H", "AP Chem", "Physics", "AP Physics 1", "AP Physics C", "Earth Science", "AP Enviroment Science", "Forensics", "Health Science Career", "Health Science Career II", "Intro to Bio Tech", "Intro to Engineering Technology/Robotics", "Psychology", "AP Psychology", "Advanced Science Investigation", "Priciples of Engineering"};
    private static String[] historyCourses = {"World Studies", "Contemporary World Issues", "AP Euro History", "AP History of Art", "US History", "Global Connections A& B", "AP US History", "Civics", "AP US Govt. Politics", "Economics", "AP Microeconomics"};
    private static String[] langCourses = {"Spanish I", "Spanish II", "Spanish IIH", "Spanish III", "Spanish IIIH", "Spanish IV", "AP Spanish Language", "French", "French II", "French III", "French IIIH", "French AP", "Latin I", "Latin II", "Latin III", "Latin IV", "Latin AP", "Mandarin/Chinese I", "Mandarin/Chinese II", "Mandarin/Chinese III", "Mandarin/Chinese IV", "AP Mandarin/ Chinese Language/ Culture"};
    private static String[] artCourses = {"Digital Photography", "Drawing I", "Drawing II", "Drawing III", "Painting I", "Photography I", "Photography II", "AP Studio Art", "Acting I", "Acting II", "Chamber Ensemble", "Chorale", "Concert Band", "Concert Choir", "Girls Ensemble", "Girls' 21", "Jazz Dance", "Jazz Dance -Advanced", "Jazz Ensemble", "Main Street", "Orchestra", "Symphonic Band", "Varsity Mens Glee", "Wind ensemble", "Marching Band"};

//    private static java.util.Map<String, List<String>> COURSES_MAP = generateCoursesMap();

//    private ClassInfoFragment classInfoFragment;
//
//    private static java.util.Map<String, List<String>> generateCoursesMap() {
//        ImmutableMap.Builder<String, List<String>> map = ImmutableMap.builder();
//        map.put("Math", toList(mathCourses));
//        map.put("English", toList(englishCourses));
//        map.put("Science", toList(scienceCourses));
//        map.put("History", toList(historyCourses));
//        map.put("Language", toList(langCourses));
//        map.put("Art", toList(artCourses));
//        return map.build();
//    }

    private static List<String> toList (String[] values) {
        return ImmutableList.copyOf(values);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listview;

    private View rootView;

    private OnFragmentInteractionListener mListener;

    public ClassesListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClassesListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassesListFragment newInstance(String param1, String param2) {
        ClassesListFragment fragment = new ClassesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_classes_list, container, false);

        listview = (ListView) rootView.findViewById(R.id.fragmentClassesListView);

        Log.d("ClassesLF.onCreateView", "checking if class fetching done");
        while(!ClassListFetcher.getInstance().isDone()) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {

            }
        }
        Log.d("ClassesLF.onCreateView", "classes fetched");
        Log.d("create", ClassListFetcher.getInstance().getAllCourses().toString());
        allCourses = ClassListFetcher.getInstance().getAllCourses();
        populateListView();

        return rootView;
    }

    private void populateListView() {
        Log.d("pop", allCourses.toString());
        String desiredCourses = getArguments().getString(password);
        List<Course> filteredCourses = FluentIterable.from(allCourses).filter(new ClassListFetcher.CoursePredicate1(desiredCourses)).toImmutableList();

        Log.d("ClassesLF.populateListV", "filtered courses " + filteredCourses.toString());
        final CourseAdapter adapter = new CourseAdapter(rootView.getContext(), R.layout.course_list_cell, filteredCourses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course item = adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("coursename", item.getCourseName());
                bundle.putString("teachers", item.getTeacherName());
                bundle.putString("textbooks", item.getTextbooks());
                bundle.putString("description", item.getDescription());
                ClassInfoFragment classInfoFragment = new ClassInfoFragment();
                classInfoFragment.setArguments(bundle);
                Fragment fragment = null;
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragment = classInfoFragment;
                if(fragment != null) {
                    fragmentTransaction.add(R.id.flcontent, fragment, null);
                    fragmentTransaction.addToBackStack("classesList");
                    fragmentTransaction.commit();
                }


//                For non fragment
//                Intent intent = new Intent(, ClassInfo.class);
//                intent.putExtra("coursename", item.getCourseName());
//                intent.putExtra("teachers", item.getTeacherName());
//                intent.putExtra("textbooks", item.getTextbooks());
//                intent.putExtra("description", item.getDescription());
//                startActivity(intent);
           }
        });
    }

//    }

//

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    //
//    public static class CoursePredicate1 implements Predicate<Course> {
//        private final String desired;
//        public CoursePredicate1(String desired) {
//            this.desired = desired;
//        }
//        @Override
//        public boolean apply(Course course) {
//            return course.getDepartment().equals(desired);
//        }
//    private ArrayList<Course> processJson(JSONObject object) {
//        ArrayList<Course> courses = new ArrayList<Course>();
//        ListView listview;
//        try {
//            JSONArray rows = object.getJSONArray("rows");
//
//            for (int r = 0; r < rows.length(); ++r) {
//                try {
//                    JSONObject row = rows.getJSONObject(r);
//                    JSONArray columns = row.getJSONArray("c");
//                    String department = columns.getJSONObject(0).getString("v");
//                    String courseName = columns.getJSONObject(1).getString("v");
//                    String teacherName = columns.getJSONObject(3).getString("v");
//                    String textbooks = columns.getJSONObject(4).getString("v");
//                    String description = columns.getJSONObject(5).getString("v");
//
//
//                    Course course = new Course(department, courseName, teacherName, textbooks, description);
//                    courses.add(course);
//                } catch (JSONException e) {
//                }
//            }
//            return courses;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return courses;
//    }
//

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
