package com.aymenworks.jlptn5.quizList;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aymenworks.jlptn5.MainActivity;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Difficulty;
import com.aymenworks.jlptn5.model.Quizz;
import com.aymenworks.jlptn5.quizDetail.QuizDetailActivity;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizListFragment extends Fragment {

    private static final String TAG = "QuizListFragment";
    @BindString(R.string.navigation_title_about) protected String title;

    public QuizListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = ((MainActivity)getActivity());
        activity.setTitle(title);

        View rootView = inflater.inflate(R.layout.fragment_kanji_quizzes, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({ R.id.quizz_easy, R.id.quizz_medium, R.id.quizz_difficult,
            R.id.quizz_words, R.id.quizz_fill_blanks, R.id.quizz_numbers})
    public void displayQuizz(LinearLayout layout) {
        Quizz quizz = null;
        switch(layout.getId()) {
            case R.id.quizz_easy: quizz = Quizz.newInstance(Difficulty.Easy); break;
            case R.id.quizz_medium: quizz = Quizz.newInstance(Difficulty.Medium); break;
            case R.id.quizz_difficult: quizz = Quizz.newInstance(Difficulty.Hard); break;
            case R.id.quizz_words: quizz = Quizz.newWordQuizzInstance(); break;
            case R.id.quizz_fill_blanks: quizz = Quizz.newFillBlanksQuizzInstance(); break;
            case R.id.quizz_numbers: quizz = Quizz.newNumbersQuizzInstance(); break;
            default: break;
        }

        Intent intent = new Intent(getActivity(), QuizDetailActivity.class);
        intent.putExtra("quizz", quizz);
        startActivity(intent);
    }
}
