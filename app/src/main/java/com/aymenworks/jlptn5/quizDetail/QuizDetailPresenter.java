package com.aymenworks.jlptn5.quizDetail;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.aymenworks.jlptn5.model.Question;
import com.aymenworks.jlptn5.model.Quizz;
import io.reactivex.functions.Consumer;

/**
 * Created by Aymenworks on 20/03/2017.
 */

public class QuizDetailPresenter implements QuizDetailContract.Presenter {

    private static final String TAG = "QuizDetailPresenter";
    @NonNull private Quizz quizz;
    public Integer currentIndexQuestion = 0; // Make observable
    Integer numberOfRightAnswer = 0;
    Integer numberOfWrongAnswer = 0;
    private QuizDetailContract.View view;

    public QuizDetailPresenter() { }

    public void attach(final QuizDetailContract.View view, @NonNull Quizz quizz) {
        this.view = view;
        this.quizz = quizz;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.updateQuestion(getCurrentQuestion());
            }
        }, 400);

        view.onAnswer()
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Integer indexAnswer) throws Exception {
                view.disableClicks();
                Boolean isRight = isRightAnswer(indexAnswer);
                if (isRight) {
                    numberOfRightAnswer++;
                    view.showSuccess(indexAnswer);
                    view.setNumberOfRightAnswer(numberOfRightAnswer);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    }, 1000);

                } else {
                    numberOfWrongAnswer++;
                    view.showWrongAnswer(indexAnswer, getCurrentQuestion().getRightAnswerIndex());
                    view.setNumberOfWrongAnswer(numberOfWrongAnswer);

                    // 2 instead 1 seconds so the user can analyse the right answer
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();
                        }
                    }, 2000);
                }
            }
        });
    }

    @Override
    public void nextQuestion() {
        currentIndexQuestion += 1;

        if (currentIndexQuestion > quizz.getQuestions().size() - 1) {
            switch(quizz.getType()) {
                case Words: quizz = Quizz.newWordQuizzInstance(); break;
                case Numbers: quizz = Quizz.newNumbersQuizzInstance(); break;
                case Kanji: quizz = Quizz.newInstance(quizz.getDifficulty()); break;
                case FillInTheBlanks: quizz = Quizz.newFillBlanksQuizzInstance(); break;
            }

            currentIndexQuestion = 0;
        }

        view.updateQuestion(getCurrentQuestion());
    }

    @Override
    public Boolean isRightAnswer(int indexAnswer) {
        return indexAnswer == getCurrentQuestion().getRightAnswerIndex();
    }

    @NonNull
    public Quizz getQuizz() {
        return quizz;
    }

    public int getCurrentIndexQuestion() {
        return currentIndexQuestion;
    }

    public Question getCurrentQuestion() {
        return quizz.getQuestions().get(currentIndexQuestion);
    }

    public void setCurrentIndexQuestion(int currentIndexQuestion) {
        this.currentIndexQuestion = currentIndexQuestion;
    }

    public void setQuizz(@NonNull Quizz quizz) {
        this.quizz = quizz;
    }

}
