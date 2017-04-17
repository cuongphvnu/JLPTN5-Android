package com.aymenworks.jlptn5.walkthrough;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.aymenworks.jlptn5.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import butterknife.BindString;
import butterknife.ButterKnife;

public class WalkthroughActivity extends MaterialIntroActivity {

    @BindString(R.string.walkthrough_kanji_step_1_title) String walkthroughStep1Title;
    @BindString(R.string.walkthrough_kanji_step_1_description) String walkthroughStep1Description;
    @BindString(R.string.walkthrough_kanji_step_2_title) String walkthroughStep2Title;
    @BindString(R.string.walkthrough_kanji_step_2_description) String walkthroughStep2Description;
    @BindString(R.string.walkthrough_kanji_step_3_title) String walkthroughStep3Title;
    @BindString(R.string.walkthrough_kanji_step_3_description) String walkthroughStep3Description;
    @BindString(R.string.walkthrough_get_started) String getStarted;

    MessageButtonBehaviour finishWalkthrough;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        finishWalkthrough = new MessageButtonBehaviour(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, getStarted);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryGreen)
                        .buttonsColor(R.color.colorSecondaryGreen)
                        .image(R.drawable.ic_walkthrough_step1_primary)
                        .title(walkthroughStep1Title)
                        .description(walkthroughStep1Description)
                        .build(),
                        finishWalkthrough);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryGreen)
                        .buttonsColor(R.color.colorSecondaryGreen)
                        .image(R.drawable.ic_walkthrough_step2_primary)
                        .title(walkthroughStep2Title)
                        .description(walkthroughStep2Description)
                        .build(),
                        finishWalkthrough);

        addSlide(new SlideFragmentBuilder()
                        .backgroundColor(R.color.colorPrimaryGreen)
                        .buttonsColor(R.color.colorSecondaryGreen)
                        .image(R.drawable.ic_walkthrough_step3_primary)
                        .title(walkthroughStep3Title)
                        .description(walkthroughStep3Description)
                        .build(),
                        finishWalkthrough);

        hideBackButton();
        enableLastSlideAlphaExitTransition(true);
    }
}
