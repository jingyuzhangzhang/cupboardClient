package com.nwpu.wsner.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import com.nwpu.wsner.R;
import com.nwpu.wsner.data.Fragments;
import com.nwpu.wsner.data.model.NavigationDrawerItem;
import com.nwpu.wsner.lib.CaptureActivity;
import com.nwpu.wsner.model.product_tb;
import com.nwpu.wsner.ui.fragments.FragmentAbout;
import com.nwpu.wsner.ui.fragments.FragmentOne;
import com.nwpu.wsner.ui.fragments.FragmentThree;
import com.nwpu.wsner.ui.fragments.FragmentManager;
import com.nwpu.wsner.ui.navigationdrawer.NavigationDrawerView;

import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

/**
 * Created by Michal Bialas on 19/07/14.
 *
 * @author Michal Bialas
 */


public class MainActivity extends ActionBarActivity {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private product_tb productData=null;
    private int currentSelectedPosition = 0;

    @InjectView(R.id.navigationDrawerListViewWrapper)
    NavigationDrawerView mNavigationDrawerListViewWrapper;

    @InjectView(R.id.linearDrawer)
    LinearLayout mLinearDrawerLayout;

    @InjectView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @InjectView(R.id.leftDrawerListView)
    ListView leftDrawerListView;
    @InjectView(R.id.drawerUserImage)
    ImageView UserImageView;
    @InjectView(R.id.drawerUserEmail)
    TextView UserEmailTextView;
    @InjectView(R.id.drawerUserName)
    TextView UserNameTextView;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;

    private CharSequence mDrawerTitle;

    private List<NavigationDrawerItem> navigationItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mTitle = mDrawerTitle = getTitle();
        getSupportActionBar().setIcon(R.drawable.ic_action_ab_transparent);

        Timber.tag("LifeCycles");
        Timber.d("Activity Created");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame,
                    Fragment.instantiate(MainActivity.this, Fragments.TEST.getFragment())).commit();
        } else {
            currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
        }

        navigationItems = new ArrayList<>();
        navigationItems.add(new NavigationDrawerItem(getString(R.string.fragment_one), true));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.fragment_two), true));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.fragment_three), true));
        navigationItems.add(new NavigationDrawerItem(getString(R.string.fragment_about),
                R.drawable.ic_action_about, false));

        mNavigationDrawerListViewWrapper.replaceWith(navigationItems);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_navigation_drawer, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        selectItem(currentSelectedPosition);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, currentSelectedPosition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.action_settings) {

            Intent intent =new Intent(this,CaptureActivity.class);
            startActivityForResult(intent, 0);

//            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String product_barcode;
        String product_name;
        String product_date;
        String product_valid;
        String product_function;
        String product_type;
        String formatType="yyyy-MM-dd";
        if(resultCode==RESULT_OK){
            try {
                //"productBarcode":"49325263","productName":"药用面霜","productionDate":1469462400000,"productionValid":1500998400000,"productFunction":null,"productType":"霜","productNumber":3,"skinAvailability":null,"username":"sun"}}
                JSONObject json1=new JSONObject(data.getStringExtra("jsonData"));
                JSONObject jsonObject = new JSONObject(json1.getString("product"));
                product_barcode=jsonObject.getString("productBarcode");
                product_name=jsonObject.getString("productName");

                Date date = new Date(jsonObject.getLong("productionDate"));
                product_date= new SimpleDateFormat(formatType).format(date);

                product_valid=jsonObject.getString("productionValid");
                product_function=jsonObject.getString("productFunction");
                product_type=jsonObject.getString("productType");

                productData=new product_tb(product_barcode,product_name,product_date,"3年",product_function,product_type);
                productData.save();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //    System.out.print(data.getStringExtra("jsonData"));
    }
    @OnItemClick(R.id.leftDrawerListView)
    public void OnItemClick(int position, long id) {
        if (mDrawerLayout.isDrawerOpen(mLinearDrawerLayout)) {
            mDrawerLayout.closeDrawer(mLinearDrawerLayout);
            onNavigationDrawerItemSelected(position);

            selectItem(position);
        }
    }
    @OnClick(R.id.drawerUserImage)
    public void OnClick(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentFrame,Fragment.instantiate(MainActivity.this,Fragments.LOGIN.getFragment()))
                .commit();
        mDrawerLayout.closeDrawer(mLinearDrawerLayout);
    }

    private void selectItem(int position) {

        if (leftDrawerListView != null) {
            leftDrawerListView.setItemChecked(position, true);

            navigationItems.get(currentSelectedPosition).setSelected(false);
            navigationItems.get(position).setSelected(true);

            currentSelectedPosition = position;
            getSupportActionBar()
                    .setTitle(navigationItems.get(currentSelectedPosition).getItemName());
        }

        if (mLinearDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mLinearDrawerLayout);
        }
    }

    private void onNavigationDrawerItemSelected(int position) {
        switch (position) {
            case 0:
                if (!(getSupportFragmentManager().getFragments()
                        .get(0) instanceof FragmentOne)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, Fragment
                                    .instantiate(MainActivity.this, Fragments.TEST .getFragment()))
                            .commit();
                }
                break;
            case 1:
                if (!(getSupportFragmentManager().getFragments().get(0) instanceof FragmentManager)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, Fragment
                                    .instantiate(MainActivity.this, Fragments.MANAGEMENT.getFragment()))
                            .commit();
                }
                break;
            case 2:
                if (!(getSupportFragmentManager().getFragments().get(0) instanceof FragmentThree)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, Fragment
                                    .instantiate(MainActivity.this, Fragments.FIND.getFragment()))
                            .commit();
                }
                break;
            case 3:
                if (!(getSupportFragmentManager().getFragments().get(0) instanceof FragmentAbout)) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentFrame, Fragment
                                    .instantiate(MainActivity.this, Fragments.ABOUT.getFragment()))
                            .commit();
                }
                break;
        }

    }

}
