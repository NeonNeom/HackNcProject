package com.example.deandre.hackncapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class EventFeed extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<EventObject> arrObj;
    ArrayList<EventObject> hold;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feed);
        x = 0;
        arrObj = new ArrayList<>();
        EventObject event1 = new EventObject();
        event1.setName("Block Party");
        event1.setDate("10/11/2017");
        arrObj.add(event1);

        EventObject event2 = new EventObject();
        event2.setName("Barbque Lit Day");
        event2.setDate("10/15/2017");
        arrObj.add(event2);

        EventObject event3 = new EventObject();
        event3.setName("Thanksgiving Bash");
        event3.setDate("11/24/2017");
        arrObj.add(event3);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton menu = (FloatingActionButton) findViewById(R.id.menuFab);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView listView = (ListView) findViewById(R.id.listV);
        ArrayAdapter<EventObject> customAdapter = new CustomListAdapter(this, 0 , arrObj);
        listView.setAdapter(customAdapter);

        FloatingActionButton search = (FloatingActionButton) findViewById(R.id.searchFab);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMethod();
            }
        });



    }



    public void searchMethod(){
        FloatingActionButton search = (FloatingActionButton) findViewById(R.id.searchFab);
        EditText searchBar = (EditText)findViewById(R.id.editText);
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(x == 0){
            searchBar.setVisibility(View.VISIBLE);
            searchBar.requestFocus();
            imm.showSoftInput(searchBar, InputMethodManager.SHOW_IMPLICIT);
            search.setImageResource(R.drawable.send);
            x++;
        }else if(x == 1){
            search.setImageResource(R.drawable.search);
            searchBar.setVisibility(View.INVISIBLE);

            try {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }
            String s = searchBar.getText().toString().toLowerCase();
            hold = new ArrayList<>();

            for(int x = 0; x < arrObj.size(); x++){
                try {
                    hold.add((EventObject) arrObj.get(x).clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

            if(!(s.isEmpty())){
                for(int x = 0; x < hold.size();x++){
                    if(hold.get(x).getName().toLowerCase().contains(s)){

                        EventObject h = null;
                        try {
                            h = (EventObject) hold.get(x).clone();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        if (h != null) {
                            h.setHighlight(2);
                        }
                        hold.remove(x);
                        hold.add(0,h);
                    }else{
                        hold.get(x).setHighlight(0);
                    }
                }
                ListView listView = (ListView) findViewById(R.id.listV);
                ArrayAdapter<EventObject> customAdapter2 = new CustomListAdapter(this, 0 , hold);
                listView.setAdapter(customAdapter2);
            }else{
                ListView listView = (ListView) findViewById(R.id.listV);
                ArrayAdapter<EventObject> customAdapter = new CustomListAdapter(this, 0 , arrObj);
                listView.setAdapter(customAdapter);
            }




            searchBar.setText(null);
            x = 0;
        }


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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.myAccount) {
            // Handle the camera action
        } else if (id == R.id.myCalendar) {

        } else if (id == R.id.academicCat) {

        } else if (id == R.id.athleticCat) {

        } else if (id == R.id.communCat) {

        } else if (id == R.id.techCat) {

        } else if (id == R.id.artCat) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
