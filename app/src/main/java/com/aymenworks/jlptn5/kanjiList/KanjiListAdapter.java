package com.aymenworks.jlptn5.kanjiList;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Kanji;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aymenworks on 14/03/2017.
 */

public class KanjiListAdapter extends RecyclerView.Adapter<KanjiListViewHolder>  implements Filterable {

    private List<Kanji> kanjiList;
    private List<Kanji> filteredKanjiList;
    private KanjiFilter kanjiFilter;
    private Typeface tf;

    public KanjiListAdapter(List<Kanji> itemList, Typeface tf) {
        this.tf = tf;
        this.kanjiList = new ArrayList<>(itemList);
        this.filteredKanjiList = itemList;
    }


    @Override
    public KanjiListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kanji_grid, parent, false);
        KanjiListViewHolder viewHolder = new KanjiListViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KanjiListViewHolder holder, int position) {
        holder.kanji = filteredKanjiList.get(position);
        holder.kanjiTextView.setTypeface(tf);
        holder.kanjiTextView.setText(filteredKanjiList.get(position).getCharacter());
    }

    @Override
    public int getItemCount() {
        return this.filteredKanjiList.size();
    }


    @Override
    public Filter getFilter() {
        if (kanjiFilter == null) {
            kanjiFilter = new KanjiFilter();
        }
        return kanjiFilter;
    }

    private class KanjiFilter extends Filter {
        @Override
        protected FilterResults performFiltering(final CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Kanji> tempList = new ArrayList<Kanji>(kanjiList);
                CollectionUtils.filter(tempList, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        return ((Kanji) o).getTranslation().toLowerCase().contains(constraint.toString().toLowerCase());
                    }
                });
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = kanjiList.size();
                filterResults.values = kanjiList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            while(!filteredKanjiList.isEmpty()) {
                filteredKanjiList.remove(0);
                notifyItemRemoved(0);
            }

            ArrayList<Kanji> tmp = (ArrayList<Kanji>) results.values;
            for (int i = 0; i < tmp.size(); i++) {
                filteredKanjiList.add(tmp.get(i));
                notifyItemInserted(i);
            }
        }
    }
}