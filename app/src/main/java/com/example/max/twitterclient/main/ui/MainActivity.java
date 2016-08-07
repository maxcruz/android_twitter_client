package com.example.max.twitterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.max.twitterclient.R;
import com.example.max.twitterclient.hashtags.ui.HashtagsFragment;
import com.example.max.twitterclient.images.ui.ImagesFragment;
import com.example.max.twitterclient.login.ui.LoginActivity;
import com.example.max.twitterclient.main.adapters.MainSectionsPagerAdapter;
import com.twitter.sdk.android.Twitter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{ new ImagesFragment(), new HashtagsFragment() };
        String[] titles = new String[]{getString(R.string.main_header_images),
                getString(R.string.main_header_hashtags)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(),
                titles, fragments);
        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.actionLogout);
        String title = String.format(getString(R.string.main_menu_action_logout), getUserName());
        menuItem.setTitle(title);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public String getUserName() {
        return Twitter.getSessionManager().getActiveSession().getUserName();
    }

}
