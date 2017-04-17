package com.aymenworks.jlptn5.kanjiDetail;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Word;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aymenworks on 25/03/2017.
 */

class KanjiDetailWordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Word word;
    @BindView(R.id.word) public TextView wordTextView;
    @BindView(R.id.word_translation) public TextView wordTranslationTextView;
    public Boolean alreadyPlayedSound = false;
    MediaPlayer mPlayer;

    public KanjiDetailWordViewHolder(View itemView) {
        super(itemView);

        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            if(!alreadyPlayedSound) {
                mPlayer.setDataSource(word.getAudioStringURL());
                mPlayer.prepare();
            }
            mPlayer.start();
            alreadyPlayedSound = true;
        } catch (IllegalArgumentException e) {
            Toast.makeText(view.getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(view.getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(view.getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}