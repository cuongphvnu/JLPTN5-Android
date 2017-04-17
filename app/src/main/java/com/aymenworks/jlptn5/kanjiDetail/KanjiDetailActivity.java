package com.aymenworks.jlptn5.kanjiDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Kanji;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KanjiDetailActivity extends AppCompatActivity {

    public Kanji kanji;
    public static final String KANJI_KEY = "kanji";
    public boolean alreadyPlayedVideo = false;

    @BindView(R.id.kanji) protected TextView kanjiTextView;
    @BindView(R.id.translation) protected TextView translationTextView;
    @BindView(R.id.number_of_strokes) protected TextView numberOfStrokesTextView;
    @BindView(R.id.onyomi) protected TextView onyomiTextView;
    @BindView(R.id.kunyomi) protected TextView kunyomiTextView;
    @BindView(R.id.word_recycler_view) protected RecyclerView recyclerView;
    @BindView(R.id.video) protected VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        setTitle("");
        kanji = (Kanji) getIntent().getSerializableExtra(KANJI_KEY);

        kanjiTextView.setText(kanji.getCharacter());
        translationTextView.setText(kanji.getTranslation());
        numberOfStrokesTextView.setText(String.valueOf(kanji.getNumberOfStrokes()));
        onyomiTextView.setText(kanji.getPronunciation().getOnyomi().getKatakana());
        kunyomiTextView.setText(kanji.getPronunciation().getKunyomi().getHiragana());

        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/NotoSansCJKjp-Thin.otf");
        kanjiTextView.setTypeface(tf);

        recyclerView.setAdapter(new KanjiDetailWordAdapter(kanji.getWords()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void finishAfterTransition() {
        super.finishAfterTransition();
        Log.d("KANJI_DETAIL_ACTIVITY", "FINISH");
    }

    @OnClick(R.id.play_video) void playVideo() {
        if(videoView.isPlaying()) {
            videoView.setVisibility(View.INVISIBLE);

        } else {
            if(!alreadyPlayedVideo) {
                alreadyPlayedVideo = true;
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.setVideoPath(kanji.getDrawingURL());
            }
            videoView.setVisibility(View.VISIBLE);
            videoView.start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
