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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.Mytripsadapter;
import getak.app.com.getak.Model.Datum;
import getak.app.com.getak.Model.Datum_trip;
import getak.app.com.getak.Model.Trips;
import getak.app.com.getak.Presenters.TripsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.MyTripsview;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyTrips extends Fragment implements MyTripsview {
    @BindView(R.id.recycle_trips)
    RecyclerView recycle_trips;
    @BindView(R.id.txtnodata)
    TextView txtnodata;
     List<Datum_trip> mytrips;
    TripsPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_my_trips, container,false);
        ButterKnife.bind(this,view);
        presenter = new TripsPresenter(getActivity(),FragmentMyTrips.this);
        presenter.getTrips(String.valueOf(SessionHelper.getUserSession(getContext()).getId()));
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onSuccess(Trips mytrips) {
        if (mytrips.getData().size()==0){
            txtnodata.setVisibility(View.VISIBLE);
        }else {
            Mytripsadapter mytripsadapter = new Mytripsadapter(getActivity(),mytrips.getData()
            );
            recycle_trips.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycle_trips.setAdapter(mytripsadapter);
        }

    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getContext(),err,Toast.LENGTH_LONG).show();

    }

    @Override
    public void getTrips() {

    }
}
