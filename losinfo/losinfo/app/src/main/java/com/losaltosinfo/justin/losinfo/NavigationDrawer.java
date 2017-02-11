package com.losaltosinfo.justin.losinfo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.losaltosinfo.justin.losinfo.R.layout.activity_map_view_test;
import static com.losaltosinfo.justin.losinfo.R.layout.app_bar_navigation_drawer;

public class NavigationDrawer extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    private MainFragment mainFragment;
    private Map mapFragment;
    private splashPageFragment splashPageFragment;
    private CalendarFragment calendarFragment;

    public class navDrawer extends Activity {

        private String[] mPlanetTitles;
        private DrawerLayout mDrawerLayout;
        private ListView mDrawerList;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_navigation_drawer);


            mPlanetTitles = getResources().getStringArray(R.array.planets_array);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.drawer_space);

            // Set the adapter for the list view
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    app_bar_navigation_drawer, mPlanetTitles));


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Los Altos High School", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainFragment = new MainFragment();
        mapFragment = new Map();
        splashPageFragment = new splashPageFragment();
        calendarFragment = new CalendarFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        switch(id) {
            case R.id.nav_home:
                fragment = mainFragment;
                break;
            case R.id.nav_classes:
                fragment = splashPageFragment;
                break;
            case R.id.nav_map:
                fragment = mapFragment;
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(this, ScheduleView.class));
                break;
            case R.id.nav_teachers:
                startActivity(new Intent(this, teacherList.class));
                break;
            case R.id.nav_calendar:
                fragment = calendarFragment;
                break;
            default:
                fragment = new MainFragment();
        }
        if(fragment != null) {
            fragmentTransaction.replace(R.id.flcontent, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
//
//        if (id == R.id.nav_home) {
//
//        } else if (id == R.id.nav_classes) {
//            startActivity(new Intent(this, splashPage.class));
//        } else if (id == R.id.nav_map) {
//
//        } else if (id == R.id.nav_schedule) {
//            startActivity(new Intent(this, ScheduleView.class));
//        } else if (id == R.id.nav_teachers) {
//            startActivity(new Intent(this, teacherList.class));
//        } else if (id == R.id.nav_calendar) {
//            startActivity(new Intent(this, CalendarListView.class));
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    


}
