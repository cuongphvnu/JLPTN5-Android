package com.aymenworks.jlptn5.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aymenworks.jlptn5.MainActivity;
import com.aymenworks.jlptn5.R;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutFragment extends Fragment {

    @BindString(R.string.navigation_title_about) protected String title;
    @BindString(R.string.more_credits_kanji_alive) protected String kanjiAliveWebsite;
    @BindString(R.string.more_credits_flaticon) protected String flaticonWebsite;
    @BindString(R.string.more_mail_to) protected String mailTo;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = ((MainActivity)getActivity());
        activity.setTitle(title);

        View view =  inflater.inflate(R.layout.fragment_kanji_more, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.recommend) void share() {

    }

    @OnClick(R.id.send_me_email) void sendFeedbackEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailTo));
        startActivity(emailIntent);
    }

    @OnClick(R.id.kanji_alive) void showKanjiWebsite() {
        Uri uri = Uri.parse(flaticonWebsite);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.flaticon) void showFlatIconWebsite() {
        Uri uri = Uri.parse(flaticonWebsite);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
