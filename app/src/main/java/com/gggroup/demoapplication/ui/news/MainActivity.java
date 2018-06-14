package com.gggroup.demoapplication.ui.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.api.ApiConstants;
import com.gggroup.demoapplication.model.News;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewsFragment.OnNewsClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            Fragment fragment = NewsFragment.instantiate(this, NewsFragment.class.getName());
            getFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = NewsFragment.instantiate(this, NewsFragment.class.getName());
        Bundle bundle = new Bundle();
        int id = item.getItemId();

        if (id == R.id.football) {
            bundle.putString(NewsFragment.CATEGORY, ApiConstants.CATEGORY_FOOTBALL);
        } else if (id == R.id.basketball) {
            bundle.putString(NewsFragment.CATEGORY, ApiConstants.CATEGORY_BASKETBALL);
        } else if (id == R.id.volleyball) {
            bundle.putString(NewsFragment.CATEGORY, ApiConstants.CATEGORY_VOLLEYBALL);
        } else if (id == R.id.hockey) {
            bundle.putString(NewsFragment.CATEGORY, ApiConstants.CATEGORY_HOCKEY);
        }
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onNewsClick(News news) {
        Fragment fragment = NewsDescriptionFragment.instantiate(this, NewsDescriptionFragment.class.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable(NewsDescriptionFragment.NEWS_CONTENT, news);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.root, fragment)
                .addToBackStack(NewsDescriptionFragment.class.getSimpleName())
                .commit();
    }
}
