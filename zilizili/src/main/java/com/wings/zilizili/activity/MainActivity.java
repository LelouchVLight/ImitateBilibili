package com.wings.zilizili.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wings.zilizili.R;
import com.wings.zilizili.fragment.HistoryFragment;
import com.wings.zilizili.fragment.HomeFragment;
import com.wings.zilizili.utils.ToastUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragmentLists;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mDrawerLayout = (DrawerLayout) $(R.id.dl_main);

        mFragmentLists = new ArrayList<Fragment>() {
            {
                add(new HomeFragment());
                add(new HistoryFragment());
            }
        };
        changeFragment(0);
    }

    public void changeFragment(int position) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content_layout, mFragmentLists.get(position));
//        transaction.add(R.id.content_layout, mFragmentLists.get(position));
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_game) {
            ToastUtils.showToast(this, "game");
            System.out.println("Activity");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void closeLeftMenu() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    public void openLeftMenu() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    public boolean getLeftMenuIsOpened() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    public void changeLeftMenuState() {
        boolean state = getLeftMenuIsOpened();
        if (state) {
            closeLeftMenu();
        } else {
            openLeftMenu();
        }
    }


    private <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }
}