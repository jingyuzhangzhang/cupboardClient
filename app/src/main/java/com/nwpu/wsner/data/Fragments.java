package com.nwpu.wsner.data;

import android.support.v4.app.Fragment;

import com.nwpu.wsner.ui.fragments.FragmentAbout;
import com.nwpu.wsner.ui.fragments.FragmentOne;
import com.nwpu.wsner.ui.fragments.FragmentThree;
import com.nwpu.wsner.ui.fragments.FragmentManager;
import com.nwpu.wsner.ui.fragments.LoginFragment;
import com.nwpu.wsner.ui.fragments.SigninFragment;

/**
 * Created by Michal Bialas on 19/07/14.
 *
 */
public enum Fragments {

    TEST(FragmentOne.class), MANAGEMENT(FragmentManager.class), FIND(FragmentThree.class), ABOUT(
            FragmentAbout.class),LOGIN(LoginFragment.class),SIGNIN(SigninFragment.class);

    final Class<? extends Fragment> fragment;

    private Fragments(Class<? extends Fragment> fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return fragment.getName();
    }
}
