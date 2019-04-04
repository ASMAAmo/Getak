package getak.app.com.getak.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.Mytripsadapter;
import getak.app.com.getak.Model.Datum;
import getak.app.com.getak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyTrips extends Fragment {
    @BindView(R.id.recycle_trips)
    RecyclerView recycle_trips;
    List<Datum> faqModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_my_trips, container,false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Mytripsadapter mytripsadapter = new Mytripsadapter(getActivity(),faqModels);
        recycle_trips.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_trips.setAdapter(mytripsadapter);
    }
}
