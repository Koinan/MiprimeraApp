package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import miprimeraapp.android.teaching.com.myapplication.View.GameDetailView;
import miprimeraapp.android.teaching.com.myapplication.fragments.GameDetailFragment;
import miprimeraapp.android.teaching.com.myapplication.model.GameModel;
import miprimeraapp.android.teaching.com.myapplication.presenter.GameDetailPresenter;

public class GameDetailActivity extends AppCompatActivity
    implements GameDetailView {
    private GameDetailPresenter presenter;
    private int currentposition;
    private MyPagerAdapter myPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(R.string.GameDetail);
        presenter = new GameDetailPresenter();
        currentposition = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this);
        final ViewPager myViewPager = findViewById(R.id.View_pager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myPagerAdapter);
        myViewPager.setCurrentItem(currentposition);
        getSupportActionBar().setTitle(myPagerAdapter.getPageTitle(currentposition));
        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            getSupportActionBar().setTitle(myPagerAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void Website(View view) {
        String url = "https://eu.battle.net/forums/en/wow/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onGameLoaded(GameModel game) {
    }
    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int gameId = presenter.getGames().get(position).getId();
            return GameDetailFragment.newInstance(gameId);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return presenter.getGames().get(position).getName();
        }

        @Override
        public int getCount() {
            return presenter.getGames().size();
        }
    }
}
