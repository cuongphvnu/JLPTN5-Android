package com.aymenworks.jlptn5.model;

import android.support.annotation.NonNull;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;
import java.io.Serializable;

/**
 * Created by Aymenworks on 13/03/2017.
 */

@Xml
public class Word implements Serializable {

    @PropertyElement
    @NonNull
    private String japaneseMeaning;

    @PropertyElement
    @NonNull
    private String translationMeaning;

    @PropertyElement
    @NonNull
    private String audioStringURL;

    public Word() {}

    public Word(@NonNull String japaneseMeaning, @NonNull String translationMeaning, @NonNull String audioStringURL) {
        this.japaneseMeaning = japaneseMeaning;
        this.translationMeaning = translationMeaning;
        this.audioStringURL = audioStringURL;
    }

    @NonNull
    public String getJapaneseMeaning() {
        return japaneseMeaning;
    }

    @NonNull
    public String getTranslationMeaning() {
        return translationMeaning;
    }

    @NonNull
    public String getAudioStringURL() {
        return audioStringURL;
    }

    public void setJapaneseMeaning(@NonNull String japaneseMeaning) {
        this.japaneseMeaning = japaneseMeaning;
    }

    public void setTranslationMeaning(@NonNull String translationMeaning) {
        this.translationMeaning = translationMeaning;
    }

    public void setAudioStringURL(@NonNull String audioStringURL) {
        this.audioStringURL = audioStringURL;
    }
}
