package com.aymenworks.jlptn5.model;

import android.support.annotation.NonNull;
import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Path;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Aymenworks on 13/03/2017.
 */

/*
   The properties are not static final because the TkXml framework I use need to have access to setters for binding
   xml tags to this class.
 */
@Xml
public class Kanji implements Serializable {

    @Attribute
    @NonNull
    private String character;

    @Attribute
    private int numberOfStrokes;

    @PropertyElement
    @NonNull
    private String translation;

    @PropertyElement
    @NonNull
    private String drawingURL;

    @Element
    @NonNull
    private Pronunciation pronunciation;

    @Path("words")
    @Element
    @NonNull
    private List<Word> words;

    public Kanji() {}

    public Kanji(@NonNull String character, int numberOfStrokes, @NonNull String translation, @NonNull String drawingURL, @NonNull Pronunciation pronunciation, @NonNull List<Word> words) {
        this.character = character;
        this.numberOfStrokes = numberOfStrokes;
        this.translation = translation;
        this.drawingURL = drawingURL;
        this.pronunciation = pronunciation;
        this.words = words;
    }

    @NonNull
    public String getCharacter() {
        return character;
    }

    public int getNumberOfStrokes() {
        return numberOfStrokes;
    }

    @NonNull
    public String getDrawingURL() {
        return drawingURL;
    }

    @NonNull
    public String getTranslation() {
        return translation;
    }

    @NonNull
    public Pronunciation getPronunciation() {
        return pronunciation;
    }

    @NonNull
    public List<Word> getWords() { return words; }

    public void setCharacter(@NonNull String character) {
        this.character = character;
    }

    public void setNumberOfStrokes(int numberOfStrokes) {
        this.numberOfStrokes = numberOfStrokes;
    }

    public void setTranslation(@NonNull String translation) {
        this.translation = translation;
    }

    public void setDrawingURL(@NonNull String drawingURL) {
        this.drawingURL = drawingURL;
    }

    public void setPronunciation(@NonNull Pronunciation pronunciation) {
        this.pronunciation = pronunciation;
    }

    public void setWords(@NonNull List<Word> words) {
        this.words = words;
    }
}
