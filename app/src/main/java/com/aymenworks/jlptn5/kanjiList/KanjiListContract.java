package com.aymenworks.jlptn5.kanjiList;

/**
 * Created by Aymenworks on 13/03/2017.
 */

/**
 * This specifies the contract between the view and the presenter.
 */
public interface KanjiListContract {

    interface View {

        void search(String text);

    }

    interface Presenter {

        void updateResultSearch();
    }
}

