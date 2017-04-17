package com.aymenworks.jlptn5.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Aymenworks on 19/03/2017.
 */

public class Quizz implements Serializable {

    @NonNull  private List<Question> questions;
    @Nullable private Difficulty difficulty;
    @NonNull  private QuizzType type;

    static final List<String> KANJI_NUMBERS = Arrays.asList("一", "二", "三", "四", "五", "六", "八", "九", "十", "百", "三百", "六百", "八百", "千", "万", "十七");

    @NonNull
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(@NonNull List<Question> questions) {
        this.questions = questions;
    }

    @Nullable
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(@Nullable Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @NonNull
    public QuizzType getType() {
        return type;
    }

    public void setType(@NonNull QuizzType type) {
        this.type = type;
    }

    public Quizz(List<Question> questions, QuizzType type) {
        this.questions = questions;
        this.type = type;
    }

    public Quizz(List<Question> questions, Difficulty difficulty, QuizzType type) {
        this.questions = questions;
        this.difficulty = difficulty;
        this.type = type;
    }

    public static Quizz newInstance(Difficulty difficulty) {

        // To avoir a reference, I copy it
        List<Kanji> kanjis = new ArrayList<>(JLPTN5Kanjis.getShared().getKanjis());

        switch(difficulty) {
            case Easy:
                CollectionUtils.filter(kanjis, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        return ((Kanji) o).getNumberOfStrokes() <= 4;
                    }
                });
                break;
            case Medium:
                CollectionUtils.filter(kanjis, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        return ((Kanji) o).getNumberOfStrokes() >= 4 && ((Kanji) o).getNumberOfStrokes() <= 6;
                    }
                });

                break;
            case Hard:
                CollectionUtils.filter(kanjis, new Predicate() {
                    @Override
                    public boolean evaluate(Object o) {
                        return ((Kanji) o).getNumberOfStrokes() >= 6;
                    }
                });

                break;
        }

        Collections.shuffle(kanjis);
        List<Question> questions = new ArrayList<Question>();

        for(int i  = 0; i < kanjis.size(); i++) {

            Kanji currentKanji = kanjis.get(i);
            List<String> possiblesAnswers = new ArrayList<>();

            // Answers
            for(int j  = 0; j < 3; j++) {
                int randomIndex = new Random().nextInt(JLPTN5Kanjis.getShared().getKanjis().size());
                // We look for 3 wrong and different answers
                while(possiblesAnswers.contains(JLPTN5Kanjis.getShared().getKanjis().get(randomIndex).getTranslation())
                        ||  JLPTN5Kanjis.getShared().getKanjis().get(randomIndex).getTranslation().equals(currentKanji.getTranslation())) {
                    randomIndex = new Random().nextInt(JLPTN5Kanjis.getShared().getKanjis().size());
                }

                possiblesAnswers.add(JLPTN5Kanjis.getShared().getKanjis().get(randomIndex).getTranslation());

            }

            int rightIndexAnswer = new Random().nextInt(4);
            String rightAnswer = currentKanji.getTranslation();

            possiblesAnswers.add(rightIndexAnswer, rightAnswer);

            Question question = new Question(currentKanji.getCharacter(), possiblesAnswers, rightIndexAnswer);
            questions.add(question);
        }


        Quizz quizz = new Quizz(questions, difficulty, QuizzType.Kanji);

        return quizz;
    }

    public static Quizz newWordQuizzInstance() {

        List<Kanji> kanjis = new ArrayList<>(JLPTN5Kanjis.getShared().getKanjis());

        Collections.shuffle(kanjis);
        List<Question> questions = new ArrayList<Question>();

        // 20 questions
        for(int i  = 0; i < 20; i++) {

            List<String> possiblesAnswers = new ArrayList<>();
            Kanji currentKanji = kanjis.get(i);
            Word currentWord = currentKanji.getWords().get(new Random().nextInt(currentKanji.getWords().size()));

            // Answers
            for(int j  = 0; j < 3; j++) {
                Kanji randomKanji = kanjis.get(new Random().nextInt(kanjis.size()));
                Word randomWord = randomKanji.getWords().get(new Random().nextInt(randomKanji.getWords().size()));

                while(possiblesAnswers.contains(randomWord.getTranslationMeaning()) || randomWord.getTranslationMeaning().equals(currentWord.getTranslationMeaning())) {
                    randomKanji = kanjis.get(new Random().nextInt(kanjis.size()));
                    randomWord = randomKanji.getWords().get(new Random().nextInt(randomKanji.getWords().size()));
                }

                possiblesAnswers.add(randomWord.getTranslationMeaning());
            }

            int rightIndexAnswer = new Random().nextInt(4);
            String rightAnswer = currentWord.getTranslationMeaning();

            possiblesAnswers.add(rightIndexAnswer, rightAnswer);

            Question question = new Question(currentWord.getJapaneseMeaning(), possiblesAnswers, rightIndexAnswer);
            questions.add(question);
        }


        Quizz quizz = new Quizz(questions, QuizzType.Words);

        return quizz;
    }

    public static Quizz newNumbersQuizzInstance() {

        // To avoir a reference, I copy it
        List<Kanji> kanjis = new ArrayList<>(JLPTN5Kanjis.getShared().getKanjis());

        CollectionUtils.filter(kanjis, new Predicate() {
            @Override
            public boolean evaluate(Object o) {
                return  KANJI_NUMBERS.contains(((Kanji) o).getCharacter());
            }
        });

        Collections.shuffle(kanjis);
        List<Question> questions = new ArrayList<Question>();

        for(int i  = 0; i < kanjis.size(); i++) {

            Kanji currentKanji = kanjis.get(i);
            List<String> possiblesAnswers = new ArrayList<>();

            // Answers
            for(int j  = 0; j < 3; j++) {
                int randomNumber = new Random().nextInt(20) + 1;

                // We look for 3 wrong and different answers
                while(possiblesAnswers.contains(String.valueOf(randomNumber)) || String.valueOf(randomNumber).equals(currentKanji.getTranslation())) {
                    randomNumber = new Random().nextInt(20) + 1;
                }

                possiblesAnswers.add(String.valueOf(randomNumber));
            }

            int rightIndexAnswer = new Random().nextInt(4);
            String rightAnswer = currentKanji.getTranslation();

            possiblesAnswers.add(rightIndexAnswer, rightAnswer);

            Question question = new Question(currentKanji.getCharacter(), possiblesAnswers, rightIndexAnswer);
            questions.add(question);
        }


        Quizz quizz = new Quizz(questions, QuizzType.Numbers);

        return quizz;
    }

    private static Pair<String, Integer> generateRandomAnswer(Word word) {
        String japaneseWord = word.getJapaneseMeaning();
        Integer randomCharacterIndex = new Random().nextInt(japaneseWord.length());
        String character = String.valueOf(japaneseWord.charAt(randomCharacterIndex));

        while(character.equals("(") || character.equals(")") || character.equals(" ")) {
            randomCharacterIndex = new Random().nextInt(japaneseWord.length());
            character =  String.valueOf(japaneseWord.charAt(randomCharacterIndex));
        }

        return new Pair<>(character, randomCharacterIndex);
    }

    public static Quizz newFillBlanksQuizzInstance() {

        List<Kanji> kanjis = new ArrayList<>(JLPTN5Kanjis.getShared().getKanjis());

        Collections.shuffle(kanjis);
        List<Question> questions = new ArrayList<Question>();

        // 20 questions
        for(int i  = 0; i < 20; i++) {

            List<String> possiblesAnswers = new ArrayList<>();
            Kanji currentKanji = kanjis.get(i);
            Word currentWord = currentKanji.getWords().get(new Random().nextInt(currentKanji.getWords().size()));
            Pair<String, Integer> answer = generateRandomAnswer(currentWord);
            Pair<String, Integer> randomAnswer = generateRandomAnswer(currentWord);

            // Answers
            for(int j  = 0; j < 3; j++) {
                Kanji randomKanji = kanjis.get(new Random().nextInt(kanjis.size()));
                Word randomWord = randomKanji.getWords().get(new Random().nextInt(randomKanji.getWords().size()));

                while(possiblesAnswers.contains(randomAnswer.first) || answer.first.equals(randomAnswer.first)) {
                    randomKanji = kanjis.get(new Random().nextInt(kanjis.size()));
                    randomWord = randomKanji.getWords().get(new Random().nextInt(randomKanji.getWords().size()));
                    randomAnswer = generateRandomAnswer(randomWord);
                }

                possiblesAnswers.add(randomAnswer.first);
            }

            int rightIndexAnswer = new Random().nextInt(4);
            String rightAnswer = answer.first;
            possiblesAnswers.add(rightIndexAnswer, rightAnswer);

            // Get the word japanese meaning and replace the answer by "?"
            StringBuilder wordQuestion = new StringBuilder(currentWord.getJapaneseMeaning());
            // 0x2754 is the hexa of U+2754 which is the "?" emoji
            wordQuestion.setCharAt(answer.second,  String.valueOf(Character.toChars(0x2754)).charAt(0));
            Question question = new Question(wordQuestion.toString(), possiblesAnswers, rightIndexAnswer);
            questions.add(question);
        }

        Quizz quizz = new Quizz(questions, QuizzType.FillInTheBlanks);

        return quizz;
    }

}
