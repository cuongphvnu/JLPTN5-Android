package com.aymenworks.jlptn5.kanjiDetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Word;
import java.util.List;

/**
 * Created by Aymenworks on 25/03/2017.
 */

public class KanjiDetailWordAdapter extends RecyclerView.Adapter<KanjiDetailWordViewHolder> {

    private List<Word> words;

    public KanjiDetailWordAdapter(List<Word> itemList) {
        this.words = itemList;
    }

    @Override
    public KanjiDetailWordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kanji_word, parent, false);
        KanjiDetailWordViewHolder viewHolder = new KanjiDetailWordViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KanjiDetailWordViewHolder holder, int position) {
        holder.word = words.get(position);
        holder.wordTextView.setText(words.get(position).getJapaneseMeaning());
        holder.wordTranslationTextView.setText(words.get(position).getTranslationMeaning());
    }

    @Override
    public int getItemCount() {
        return this.words.size();
    }
}