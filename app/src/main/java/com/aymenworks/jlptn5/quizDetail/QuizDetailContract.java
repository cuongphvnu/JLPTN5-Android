package com.aymenworks.jlptn5.quizDetail;

import com.aymenworks.jlptn5.model.Question;
import io.reactivex.Observable;

/**
 * Created by Aymenworks on 20/03/2017.
 */

public interface QuizDetailContract {

    interface View {
        void showSuccess(Integer choice);
        void showWrongAnswer(Integer choice, Integer rightAnswer);
        void setNumberOfRightAnswer(Integer x);
        void enableClicks();
        void disableClicks();
        void setNumberOfWrongAnswer(Integer x);
        void updateQuestion(Question currentQuestion);
        Observable<Integer> onAnswer();
    }

    interface Presenter {
        void nextQuestion();
        Question getCurrentQuestion();
        Boolean isRightAnswer(int indexAnswer);
    }
}
