package com.losaltosinfo.justin.losinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.FadeTransformer;
import com.losaltosinfo.justin.losinfo.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mapString = "201 Almond Ave, Los Altos, CA 94022";

    SliderLayout sliderShow;

    private long SLIDER_SHOW_DURATION = 5;

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        getActivity().setTitle("Home");

        ClassListFetcher.getInstance().downloadCourses();
        Log.d("d", "doje");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


//        Button mapButton = (Button) rootView.findViewById(R.id.mapButton);
//        int unicode = 0x1F4CD;
//        String emoji = Character.toChars(unicode).toString();
//        mapButton.setText(emoji + mapString);
//        sliderShow = (SliderLayout) rootView.findViewById(R.id.slider);
//        TextSliderView textSliderView = new TextSliderView(getActivity());
//        TextSliderView textSliderView1 = new TextSliderView(getActivity());
//        TextSliderView textSliderView2 = new TextSliderView(getActivity());
//        textSliderView.image(R.drawable.lahsbackground1);
//        textSliderView1.image(R.drawable.lahsbackground2);
//        textSliderView2.image(R.drawable.lahsbackground3);
//        sliderShow.addSlider(textSliderView);
//        sliderShow.addSlider(textSliderView1);
//        sliderShow.addSlider(textSliderView2);
//        sliderShow.setDuration(SLIDER_SHOW_DURATION);
//        sliderShow.setPresetTransformer("Accordion");
//        sliderShow.setEnabled(false);

        Button button = (Button) rootView.findViewById(R.id.mapButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("d", "d");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:geo:0,0?q=Los+Altos+High+School"));
                getActivity().startActivity(intent);
            }
        });
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

    @Override
    public void onStop() {
//        sliderShow.stopAutoCycle();
        super.onStop();
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
