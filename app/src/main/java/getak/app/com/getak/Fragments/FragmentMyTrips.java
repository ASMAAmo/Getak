package getak.app.com.getak.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import getak.app.com.getak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyTrips extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_my_trips, container, false);
    }

}
