package com.losaltosinfo.justin.losinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.losaltosinfo.justin.losinfo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ClassInfoFragment() {
        // Required empty public constructor
    }



    public static ClassInfoFragment newInstance(String param1, String param2) {
        ClassInfoFragment fragment = new ClassInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.class_details, container, false);

        TextView courseName = (TextView) rootView.findViewById(R.id.courseNameThing);
        TextView teachers = (TextView) rootView.findViewById(R.id.Teachers);
        TextView textbooks = (TextView) rootView.findViewById(R.id.Textbooks);
        TextView description = (TextView) rootView.findViewById(R.id.Description);

        String courseString = getArguments().getString("coursename");
        String teacherString = getArguments().getString("teachers");
        String textbooksString = getArguments().getString("textbooks");
        String descriptionString = getArguments().getString("description");

        courseName.setText(courseString);
        teachers.setText(teacherString);
        textbooks.setText(textbooksString);
        description.setText(descriptionString);

        return rootView;
    }

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
