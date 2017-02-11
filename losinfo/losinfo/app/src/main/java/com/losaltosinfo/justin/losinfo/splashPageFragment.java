package com.losaltosinfo.justin.losinfo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.losaltosinfo.justin.losinfo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link splashPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link splashPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class splashPageFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String classesListString;
    private String password = "password";

    ImageButton math;
    ImageButton history;
    ImageButton science;
    ImageButton art;
    ImageButton language;
    ImageButton english;

    private ClassesListFragment classesListFragment;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public splashPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment splashPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static splashPageFragment newInstance(String param1, String param2) {
        splashPageFragment fragment = new splashPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_splash_page, container, false);

        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);
        math = (ImageButton) rootView.findViewById(R.id.Math);
        math.setOnClickListener(this);

        english = (ImageButton) rootView.findViewById(R.id.English);
        english.setOnClickListener(this);

        science = (ImageButton) rootView.findViewById(R.id.Science);
        science.setOnClickListener(this);

        history = (ImageButton) rootView.findViewById(R.id.History);
        history.setOnClickListener(this);

        art = (ImageButton) rootView.findViewById(R.id.Art);
        art.setOnClickListener(this);

        language = (ImageButton) rootView.findViewById(R.id.Language);
        language.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        getActivity().setTitle("Courses");

    }
    public void onClick(View v) {

        Bundle bundle = new Bundle();

        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        classesListFragment = new ClassesListFragment();

        switch(v.getId()) {

            case R.id.Math:
                Log.d("onclick", "math");
                classesListString = "Math";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            case R.id.English:
                classesListString = "English";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            case R.id.Science:
                classesListString = "Science";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            case R.id.History:
                classesListString = "History";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            case R.id.Art:
                classesListString = "Art";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            case R.id.Language:
                classesListString = "Language";
                bundle.putString(password, classesListString);
                classesListFragment.setArguments(bundle);
                break;

            default:
                break;

        }
        fragment = classesListFragment;
        if(fragment != null) {
            fragmentTransaction.add(R.id.flcontent, fragment, null);
            fragmentTransaction.addToBackStack("classesList");
            fragmentTransaction.commit();
        }
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

    public void onMathButtonClick (View view) {
        classesListString = "Math";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }


    public void onEnglishButtonClick (View view) {
        classesListString = "English";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }

    public void onHistoryButtonClick (View view) {
        classesListString = "History";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }

    public void onLanguageButtonClick (View view) {
        classesListString = "Language";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }

    public void onScienceButtonClick (View view) {
        classesListString = "Science";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }

    public void onArtButtonClick (View view) {
        classesListString = "Art";
        Bundle bundle = new Bundle();
        bundle.putString(password, classesListString);
        ClassesListFragment classesListFragment = new ClassesListFragment();
        classesListFragment.setArguments(bundle);
    }
}
