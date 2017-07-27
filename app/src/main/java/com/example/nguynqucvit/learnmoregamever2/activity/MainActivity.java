package com.example.nguynqucvit.learnmoregamever2.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Transition;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.commonsware.cwac.loaderex.acl.SQLiteCursorLoader;
import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper;
import com.example.nguynqucvit.learnmoregamever2.fragment.FavoriteFragment;
import com.example.nguynqucvit.learnmoregamever2.fragment.HomeFragment;

import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.NAME;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TYPE;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private HomeFragment mHomeFragment = HomeFragment.newInstance();
    private FavoriteFragment mFavoriteFragment = FavoriteFragment.newInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "No action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout); // app_bar_main layout
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                coordinatorLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.requestLayout();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addFragment(mHomeFragment);
        addFragment(mFavoriteFragment);
        hideFragment(mFavoriteFragment);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (mHomeFragment.isHidden()) {
            showFragment(mHomeFragment, false);
            return;
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

        switch (id) {
            case R.id.nav_home:
                if (mFavoriteFragment.isInLayout()) {
                    onBackPressed();
                } else {
                    showFragment(mHomeFragment, false);
                    hideFragment(mFavoriteFragment);
                }
                break;
            case R.id.nav_favorite:
                showFragment(mFavoriteFragment, true);
                SQLiteCursorLoader cursorLoader = mFavoriteFragment.getCursorLoader();
                cursorLoader.onContentChanged();
                hideFragment(mHomeFragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layoutContent, fragment)
                .commit();
    }

    private void showFragment(Fragment fragment, boolean isAddedToBackStack) {
        getSupportFragmentManager().popBackStack();
        if (isAddedToBackStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(fragment)
                    .addToBackStack("")
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(fragment)
                    .commit();
        }
    }

    private void hideFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commit();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static boolean isNetworkConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public FavoriteFragment getmFavoriteFragment() {
        return mFavoriteFragment;
    }
}
