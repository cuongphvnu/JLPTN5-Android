package com.aymenworks.jlptn5.kanjiList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aymenworks.jlptn5.MainActivity;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.JLPTN5Kanjis;
import com.aymenworks.jlptn5.model.Kanji;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KanjiListFragment extends Fragment implements KanjiListContract.View  {

    private static final String TAG = "KanjiListFragment";
    private static final int NUMBER_OF_COLUMN = 4;
    private KanjiListAdapter kanjiListAdapter;

    @BindView(R.id.recycler_view) protected RecyclerView recyclerView;
    @BindString(R.string.navigation_title_home) protected String title;
    @BindString(R.string.home_search_kanji_hint) protected String searchHint;

    public KanjiListFragment() {}


    // Provide a layout for a fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_kanji_list, container, false);
        ButterKnife.bind(this, rootView);

        MainActivity activity = ((MainActivity)getActivity());
        activity.setTitle(title);

        JLPTN5Kanjis.setup(getResources(), getActivity().getPackageName());
        List<Kanji> kanjis = JLPTN5Kanjis.getShared().getKanjis();

        final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NotoSansCJKjp-Thin.otf");
        kanjiListAdapter = new KanjiListAdapter(kanjis, tf);
        recyclerView.setAdapter(kanjiListAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), NUMBER_OF_COLUMN);

        recyclerView.setLayoutManager(layoutManager);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.kanji_search_item, menu);
        MenuItem item = menu.findItem(R.id.search_item);

        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS );
        MenuItemCompat.setActionView(item, searchView);
        searchView.setQueryHint(searchHint);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
    }

    @Override
    public void search(String text) {
        kanjiListAdapter.getFilter().filter(text);
    }
}
