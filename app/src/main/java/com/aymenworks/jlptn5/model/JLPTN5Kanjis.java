package com.aymenworks.jlptn5.model;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import okio.Okio;

/**
 * Created by Aymenworks on 14/03/2017.
 */

@Xml(name = "kanjis")
public class JLPTN5Kanjis {

    private static JLPTN5Kanjis SHARED = null;
    private static Resources RESOURCES = null;
    private static String PACKAGE_NAME = null;

    @Element
    @NonNull
    List<Kanji> kanjis;

    public static void setup(Resources resources, String packageName) {
        RESOURCES = resources;
        PACKAGE_NAME = packageName;
    }

    public static JLPTN5Kanjis getShared() {
        if(SHARED == null) {
            TikXml parser = new TikXml.Builder()
                    .exceptionOnUnreadXml(true)
                    .build();

            InputStream inputStream = RESOURCES.openRawResource(
                    RESOURCES.getIdentifier("kanjis_list",
                            "raw", PACKAGE_NAME));

            try {
                SHARED = parser.read(Okio.buffer(Okio.source(inputStream)), JLPTN5Kanjis.class);
                return SHARED;

            } catch (IOException e) {
                e.printStackTrace();

                return null;
            }
        }

        return  SHARED;
    }

    public JLPTN5Kanjis() {}

    public JLPTN5Kanjis(@NonNull List<Kanji> kanjis) {
        this.kanjis = kanjis;
    }

    @NonNull
    public List<Kanji> getKanjis() {
        return kanjis;
    }

    public void setKanjis(@NonNull List<Kanji> kanjis) {
        this.kanjis = kanjis;
    }

}


