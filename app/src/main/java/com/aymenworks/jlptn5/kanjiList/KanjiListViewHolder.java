package com.aymenworks.jlptn5.kanjiList;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.aymenworks.jlptn5.MainActivity;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Kanji;
import com.aymenworks.jlptn5.kanjiDetail.KanjiDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aymenworks on 14/03/2017.
 */

class KanjiListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public Kanji kanji;
    @BindView(R.id.kanji) public TextView kanjiTextView;

    public KanjiListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MainActivity activity = ((MainActivity)view.getContext());
        Intent intent = new Intent(activity, KanjiDetailActivity.class);
        intent.putExtra("kanji", kanji);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, kanjiTextView, "kanjiTransition");
        activity.startActivity(intent, options.toBundle());
    }
}
