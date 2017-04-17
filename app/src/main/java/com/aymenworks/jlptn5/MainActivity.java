package com.aymenworks.jlptn5;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aymenworks.jlptn5.about.AboutFragment;
import com.aymenworks.jlptn5.kanjiList.KanjiListFragment;
import com.aymenworks.jlptn5.quizList.QuizListFragment;
import com.aymenworks.jlptn5.walkthrough.WalkthroughActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String MY_SHARED_PREFERENCES = "JLPTN5_PREFERENCES";
    public static final String MY_SHARED_PREFERENCES_LAUNCHED_KEY = "ALREADY_LANUCHED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_kanji);

        ButterKnife.bind(this);

        // App bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        // Bottom Navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment kanjiListFragment = new KanjiListFragment();
        replaceFragment(kanjiListFragment);

        showWalkthroughIfFirstTime();
    }

    private void showWalkthroughIfFirstTime() {
        SharedPreferences preferences = getSharedPreferences(MY_SHARED_PREFERENCES, MODE_PRIVATE);
        Boolean hasBeenAlreadyLaunched = preferences.getBoolean(MY_SHARED_PREFERENCES_LAUNCHED_KEY, false);

        if(!hasBeenAlreadyLaunched) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(MY_SHARED_PREFERENCES_LAUNCHED_KEY, true);
            editor.commit();
            Intent intent = new Intent(this, WalkthroughActivity.class);
            startActivity(intent);
        }
    }


    public void replaceFragment(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment kanjiListFragment = new KanjiListFragment();
                    replaceFragment(kanjiListFragment);

                    return true;
                case R.id.navigation_quizzes:
                    Fragment kanjiQuizz = new QuizListFragment();
                    replaceFragment(kanjiQuizz);

                    return true;
                case R.id.navigation_more:
                    Fragment kanjiMore = new AboutFragment();
                    replaceFragment(kanjiMore);

                    return true;
            }
            return false;
        }
    };
}
