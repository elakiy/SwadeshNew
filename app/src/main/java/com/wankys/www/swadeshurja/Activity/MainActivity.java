package com.wankys.www.swadeshurja.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wankys.www.swadeshurja.Fragments.Address;
import com.wankys.www.swadeshurja.Fragments.Home;
import com.wankys.www.swadeshurja.Fragments.Place_order;
import com.wankys.www.swadeshurja.Fragments.Profile;
import com.wankys.www.swadeshurja.Fragments.SearchProducts;
import com.wankys.www.swadeshurja.Fragments.ShopByCategory;
import com.wankys.www.swadeshurja.Fragments.Shoppingcart;
import com.wankys.www.swadeshurja.Models.UserSessionManager;
import com.wankys.www.swadeshurja.R;
import com.wankys.www.swadeshurja.Utils.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_Email;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserId;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.Key_UserName;
import static com.wankys.www.swadeshurja.Models.UserSessionManager.PREFER_NAMEUserdata;

/**
 * Created by Elakiya on 5/15/2018.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Button  shopbycategory;
    EditText searchtext;
    FrameLayout frameLayout ;
    public static LinearLayout tool;
    UserSessionManager sessionManager;
    public static LinearLayout toolbarContainer;
    public static View  searchLayout;
    boolean doubleBackToExitPressedOnce = false;
    public static ImageView menu,  cart;
    public static DrawerLayout drawerLayout;
    public static String userId, name,email;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    public static TextView title, cartCount;
    int count;
    public static ProgressBar progressBar;
    TextView nav_user, nav_useremail;
    Toolbar toolbar;
    View hView;
    RelativeLayout relativeLayout;
    AppCompatButton button;
    public static String currency = " ₹";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ButterKnife.bind(this);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sessionManager = new UserSessionManager(this);
        initViews();
        getUserData();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFragment(new Home(), false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        searchtext.setOnClickListener(this);
        shopbycategory.setOnClickListener(this);
    }

    private void getUserData() {
        SharedPreferences preferences1 = getApplicationContext().getSharedPreferences(PREFER_NAMEUserdata, Context.MODE_PRIVATE);
        userId = preferences1.getString(Key_UserId, null);
        name = preferences1.getString(Key_UserName, null);
        email = preferences1.getString(Key_Email, null);
        nav_user.setText(name);
        nav_useremail.setText(email);
    }

    private void initViews() {
        hView =  navigationView.getHeaderView(0);
        nav_user = hView.findViewById(R.id.name_id);
        nav_useremail = hView.findViewById(R.id.email_id);
        tool= (LinearLayout) findViewById(R.id.tool_id);
        shopbycategory= (Button) findViewById(R.id.shopby_id);
        shopbycategory.setText(Html.fromHtml(getResources().getString(R.string.shopby)));
        searchtext = (EditText) findViewById(R.id.search_id);
        frameLayout= (FrameLayout) findViewById(R.id.framelayout_id);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        if (tool.getVisibility() == View.VISIBLE) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
        } else {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back once more to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemView=menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(itemView, R.layout.cart);
        relativeLayout = (RelativeLayout) MenuItemCompat.getActionView(itemView);
        button= relativeLayout.findViewById(R.id.button3);
        cartCount=relativeLayout.findViewById(R.id.badge_notification_3);
        progressBar=relativeLayout.findViewById(R.id.progressBar);
        Config.getCartList(this, true);
        button.setOnClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            tool.setVisibility(View.VISIBLE);
            loadFragment(new Home(),true);
        }
        else if (id == R.id.nav_about) {

        }
        else if(id == R.id.nav_profile)
        {   tool.setVisibility(View.GONE);
           loadFragment(new Profile(),true);
        }
        else if (id == R.id.nav_address) {
            tool.setVisibility(View.GONE);
            Bundle bundle1 = new Bundle();
            bundle1.putString("nodeliver","nodeliver");
            Address address = new Address();
            address.setArguments(bundle1);
         loadFragment(address,true);
         }
         else if (id == R.id.nav_contact) {
        }
        else if (id == R.id.nav_sell) {

        }
        else if (id == R.id.nav_share) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"text to share");
            startActivity(Intent.createChooser(intent,"share via"));
        } else if (id == R.id.nav_send) {
            sessionManager.logoutUser();
            }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopby_id:
                tool.setVisibility(View.GONE);
                if (!Home.swipeRefreshLayout.isRefreshing())
                    loadFragment(new ShopByCategory(), true);
                break;
            case R.id.search_id:
                tool.setVisibility(View.GONE);
                if (!Home.swipeRefreshLayout.isRefreshing())
                    loadFragment(new SearchProducts(), true);
                break;
            case R.id.button3:
                tool.setVisibility(View.GONE);
                if (!Home.swipeRefreshLayout.isRefreshing())
                    loadFragment(new Shoppingcart(), true);
                break;
        }
    }

    public void removeCurrentFragmentAndMoveBack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        /*FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragment);
        trans.commit();*/
        fragmentManager.popBackStack();
    }

    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout_id, fragment);
        if (bool) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
