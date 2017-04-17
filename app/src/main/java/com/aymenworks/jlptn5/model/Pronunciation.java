package com.aymenworks.jlptn5.model;

import android.support.annotation.NonNull;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;
import java.io.Serializable;

/**
 * Created by Aymenworks on 13/03/2017.
 */

@Xml
public class Pronunciation implements Serializable {

    @Element
    @NonNull
    private Onyomi onyomi;

    @Element
    @NonNull
    private Kunyomi kunyomi;

    public Pronunciation() {}

    public Pronunciation(@NonNull Onyomi onyomi, @NonNull Kunyomi kunyomi) {
        this.onyomi = onyomi;
        this.kunyomi = kunyomi;
    }

    @NonNull
    public Onyomi getOnyomi() {
        return onyomi;
    }

    @NonNull
    public Kunyomi getKunyomi() {
        return kunyomi;
    }

    public void setOnyomi(@NonNull Onyomi onyomi) {
        this.onyomi = onyomi;
    }

    public void setKunyomi(@NonNull Kunyomi kunyomi) {
        this.kunyomi = kunyomi;
    }

    @Xml
    public static class Onyomi implements Serializable {

        @PropertyElement
        @NonNull
        private String romaji;

        @PropertyElement
        @NonNull
        private String katakana;

        public Onyomi() {}

        public Onyomi(@NonNull String romaji, @NonNull String katakana) {
            this.romaji = romaji;
            this.katakana = katakana;
        }

        @NonNull
        public String getRomaji() {
            return romaji;
        }

        @NonNull
        public String getKatakana() {
            return katakana;
        }

        public void setRomaji(@NonNull String romaji) {
            this.romaji = romaji;
        }

        public void setKatakana(@NonNull String katakana) {
            this.katakana = katakana;
        }
    }

    @Xml
    public static class Kunyomi implements Serializable {

        @PropertyElement
        @NonNull
        private String romaji;

        @PropertyElement
        @NonNull
        private String hiragana;

        public Kunyomi() {}

        public Kunyomi(@NonNull String romaji, @NonNull String hiragana) {
            this.romaji = romaji;
            this.hiragana = hiragana;
        }

        @NonNull
        public String getRomaji() {
            return romaji;
        }

        @NonNull
        public String getHiragana() {
            return hiragana;
        }

        public void setRomaji(@NonNull String romaji) {
            this.romaji = romaji;
        }

        public void setHiragana(@NonNull String hiragana) {
            this.hiragana = hiragana;
        }
    }

}