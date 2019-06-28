package com.example.mybook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.security.keystore.KeyProperties;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzf.mybook.R;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.lzf.mybook.R.id.tablayout;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView window;
    private TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        getWindow().setBackgroundDrawable(null);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.addDrawerListener(toggle);
        //toggle.syncState();
        //Message.obtain();

        TabLayout tabLayout = findViewById(tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("电影"));
        tabLayout.addTab(tabLayout.newTab().setText("电视剧"));
        tabLayout.addTab(tabLayout.newTab().setText("综艺"));
        tabLayout.addTab(tabLayout.newTab().setText("书籍"));
        Looper.myLooper();

        //window = findViewById(R.id.window);
        //home = findViewById(R.id.home);
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
        viewPager = findViewById(R.id.mViewPager);
        viewPager.setOffscreenPageLimit(2);
        fragmentList.add(new WindowFragment());
        fragmentList.add(new HomeFragment());
        viewPager.setAdapter(new AppFragmentPageAdapter(getSupportFragmentManager(), fragmentList));
        //viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    //window.setTextColor(getResources().getColor(R.color.black,getTheme()));
                    //home.setTextColor(getResources().getColor(R.color.darkgray,getTheme()));
                } else if (position == 1) {
                    //window.setTextColor(getResources().getColor(R.color.darkgray,getTheme()));
                    //home.setTextColor(getResources().getColor(R.color.black,getTheme()));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }*/
    public void test() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return chain.proceed(chain.request());
                    }
                })
                .build();
        Request request = new Request.Builder().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response){

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();*/
    }
}
