package com.aymenworks.jlptn5.quizDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aymenworks.jlptn5.R;
import com.aymenworks.jlptn5.model.Question;
import com.aymenworks.jlptn5.model.Quizz;
import com.aymenworks.jlptn5.model.QuizzType;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class QuizDetailActivity extends AppCompatActivity implements QuizDetailContract.View {

    @BindView(R.id.question) protected TextView question;

    @BindView(R.id.card_answer_1) protected CardView cardAnswer1;
    @BindView(R.id.card_answer_2) protected CardView cardAnswer2;
    @BindView(R.id.card_answer_3) protected CardView cardAnswer3;
    @BindView(R.id.card_answer_4) protected CardView cardAnswer4;

    @BindView(R.id.answer_1) protected TextView answer1;
    @BindView(R.id.answer_2) protected TextView answer2;
    @BindView(R.id.answer_3) protected TextView answer3;
    @BindView(R.id.answer_4) protected TextView answer4;

    @BindView(R.id.quizz_detail_right_value) protected TextView numberOfRightAnswerTextView;
    @BindView(R.id.quizz_detail_wrong_value) protected TextView numberOfWrongAnswerTextView;

    private QuizDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizz_detail);
        setupUI();

        Quizz quizz = (Quizz) getIntent().getSerializableExtra("quizz");
        presenter = new QuizDetailPresenter();
        presenter.attach(this, quizz);

        if(presenter.getQuizz().getType() == QuizzType.Words || presenter.getQuizz().getType() == QuizzType.FillInTheBlanks) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) question.getLayoutParams();
            params.setMargins(0, 40, 0, 0);
            question.setTextSize(50);
        }
    }


    void setupUI() {
        // App bar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.quizz_detail_toolbar);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.view_quizz_score, null);
        myToolbar.addView(mCustomView);

        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/NotoSansCJKjp-Thin.otf");
        question.setTypeface(tf);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    CardView getCorrectCardView(int indexRightAnswer) {
        String rightAnswerTag = String.valueOf(indexRightAnswer);

        if (cardAnswer1.getTag().equals(rightAnswerTag)) {
            return cardAnswer1;

        } else if (cardAnswer2.getTag().equals(rightAnswerTag)) {
            return cardAnswer2;

        } else if (cardAnswer3.getTag().equals(rightAnswerTag)) {
            return cardAnswer3;

        } else if (cardAnswer4.getTag().equals(rightAnswerTag)) {
            return cardAnswer4;
        }

        return null;
    }

    TextView getCorrectTextView(int indexRightAnswer) {
        String rightAnswerTag = String.valueOf(indexRightAnswer);

        if (cardAnswer1.getTag().equals(rightAnswerTag)) {
            return answer1;

        } else if (cardAnswer2.getTag().equals(rightAnswerTag)) {
            return answer2;

        } else if (cardAnswer3.getTag().equals(rightAnswerTag)) {
            return answer3;

        } else if (cardAnswer4.getTag().equals(rightAnswerTag)) {
            return answer4;
        }

        return null;
    }

    @Override
    public void showSuccess(Integer choice) {
        getCorrectCardView(choice).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryGreen));
        getCorrectTextView(choice).setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    @Override
    public void disableClicks() {
        cardAnswer1.setClickable(false);
        cardAnswer2.setClickable(false);
        cardAnswer3.setClickable(false);
        cardAnswer4.setClickable(false);
    }

    @Override
    public void enableClicks() {
        cardAnswer1.setClickable(true);
        cardAnswer2.setClickable(true);
        cardAnswer3.setClickable(true);
        cardAnswer4.setClickable(true);
    }

    @Override
    public void showWrongAnswer(Integer choice, Integer rightAnswer) {
        getCorrectCardView(choice).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryRed));
        getCorrectTextView(choice).setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        getCorrectCardView(rightAnswer).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryGreen));
        getCorrectTextView(rightAnswer).setTextColor(ContextCompat.getColor(this, R.color.colorWhite));

        YoYo.with(Techniques.Shake)
                .duration(700)
                .playOn(getCorrectCardView(choice));
    }

    @Override
    public void updateQuestion(Question currentQuestion) {
        enableClicks();

        if(presenter.getCurrentIndexQuestion() > 0) {
            YoYo.with(Techniques.FadeOutRight)
                    .duration(200)
                    .playOn(question);
        }

        cardAnswer1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        cardAnswer2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        cardAnswer3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
        cardAnswer4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));

        answer1.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        answer2.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        answer3.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        answer4.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));

        question.setText(currentQuestion.getQuestion());
        answer1.setText(currentQuestion.getPossiblesAnswers().get(0));
        answer2.setText(currentQuestion.getPossiblesAnswers().get(1));
        answer3.setText(currentQuestion.getPossiblesAnswers().get(2));
        answer4.setText(currentQuestion.getPossiblesAnswers().get(3));

        int duration = 500;
        Techniques animation = Techniques.Pulse;

        if(presenter.getCurrentIndexQuestion() > 0) {
            YoYo.with(Techniques.SlideInLeft)
                    .delay(200)
                    .duration(duration)
                    .playOn(question);
        }
        YoYo.with(animation)
                .duration(duration)
                .delay(100)
                .playOn(answer1);

        YoYo.with(animation)
                .duration(duration)
                .delay(200)
                .playOn(answer2);

        YoYo.with(animation)
                .duration(duration)
                .delay(300)
                .playOn(answer3);

        YoYo.with(animation)
                .duration(duration)
                .delay(400)
                .playOn(answer4);
    }

    @Override
    public Observable<Integer> onAnswer() {
        return Observable.merge(
                onOneAnswer(cardAnswer1),
                onOneAnswer(cardAnswer2),
                onOneAnswer(cardAnswer3),
                onOneAnswer(cardAnswer4));
    }

    private Observable<Integer> onOneAnswer(final View view) {
        return RxView.clicks(view)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(@NonNull Object object) throws Exception {
                        return Integer.parseInt((String) view.getTag());
                    }
                });
    }

    @Override
    public void setNumberOfRightAnswer(Integer x) {
        YoYo.with(Techniques.BounceInDown)
                .duration(700)
                .playOn(numberOfRightAnswerTextView);

        numberOfRightAnswerTextView.setText(String.valueOf(x));
    }

    @Override
    public void setNumberOfWrongAnswer(Integer x) {
        YoYo.with(Techniques.BounceInDown)
                .duration(700)
                .playOn(numberOfWrongAnswerTextView);

        numberOfWrongAnswerTextView.setText(String.valueOf(x));
    }
}
