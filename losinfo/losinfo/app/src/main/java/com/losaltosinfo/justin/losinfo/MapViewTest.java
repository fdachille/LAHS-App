package com.losaltosinfo.justin.losinfo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapViewTest extends BaseActivity {

//    private FragmentActivity fragmentActivity;
//    private CoordinatorLayout coordinatorLayout;
//
//    public MapViewTest() {
//        //required constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//     Bundle savedInstanceState) {
////      setTitle("LAHS Map");
//        fragmentActivity = super.getActivity();
//        coordinatorLayout = (CoordinatorLayout) inflater.inflate(R.layout.activity_map_view_test, container, false);
//        getActionBar().setTitle("LAHS Map");
//        return coordinatorLayout;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view_test);
        setTitle("LAHS Map");
    }

}
