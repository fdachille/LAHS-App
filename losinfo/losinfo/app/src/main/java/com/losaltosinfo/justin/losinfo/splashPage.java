package com.losaltosinfo.justin.losinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class splashPage extends AppCompatActivity {
    public String password = "password";
    public String classesListString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  switch(item.getItemId()) {
            case R.id.Map:
                Intent intent = new Intent(this, MapView.class);
                startActivity(intent)
        }
*/      //View view = findViewById()
        int id = item.getItemId();
      /*  if(id == R.id.Map) {
            mapChange();
        };
        if(id == R.id.Schedule) {
            scheduleChange();
        }

        if(id == R.id.Menu) {
            menuChange();
        }
*/
        return false;
    }
/*
    public void menuChange() { startActivity(new Intent(this, NavigationDrawer.class)); }
    public void scheduleChange() {
        startActivity(new Intent(this, ScheduleView.class));
    }
    public void mapChange () {
        startActivity(new Intent(this, MapViewTest.class));
    }
*/
//
//    public void onMathButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "Math";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
//
//
//    public void onEnglishButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "English";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
//
//    public void onHistoryButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "History";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
//
//    public void onLanguageButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "Language";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
//
//    public void onScienceButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "Science";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
//
//    public void onArtButtonClick (View view) {
//        Intent intent = new Intent(this, ClassesList.class);
//        classesListString = "Art";
//        intent.putExtra(password, classesListString);
//        startActivity(intent);
//    }
}
