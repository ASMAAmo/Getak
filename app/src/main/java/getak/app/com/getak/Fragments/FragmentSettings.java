package getak.app.com.getak.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;

public class FragmentSettings extends Fragment {

    @BindView(R.id.lang_switch)
    TabLayout langSwitch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_settings, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        langSwitch.addTab(langSwitch.newTab().setText("عربي"));
        langSwitch.addTab(langSwitch.newTab().setText("English"));
        if(SessionHelper.isArabic(getContext())){
            langSwitch.getTabAt(0).select();
        }else if(SessionHelper.isEnglish(getContext())){
            langSwitch.getTabAt(1).select();
        }

        langSwitch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : {
                        SessionHelper.setLanguageArabic(getContext(), new SessionHelper.OnSessionUpdate() {
                            @Override
                            public void refreshActivity() {
                                BaseActivity.restartApp(getContext());
                            }
                        });
                        break;
                    }

                    case 1 : {
                        SessionHelper.setLanguageEnglish(getContext(), new SessionHelper.OnSessionUpdate() {
                            @Override
                            public void refreshActivity() {
                                BaseActivity.restartApp(getContext());
                            }
                        });
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
