package getak.app.com.getak.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.Favsadapter;
import getak.app.com.getak.Adapters.Mytripsadapter;
import getak.app.com.getak.Model.Data_fav;
import getak.app.com.getak.Model.Favorite;
import getak.app.com.getak.Presenters.FavPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.FavView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavPlaces extends Fragment implements FavView {
    @BindView(R.id.recycle_fav)
    RecyclerView recycle_fav;
    @BindView(R.id.txtnodata)
    TextView txtnodata;
    FavPresenter presenter;
    List<Favorite> favorites;

    public FragmentFavPlaces() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_fav_places, container,false);
        ButterKnife.bind(this,view);
        presenter= new FavPresenter(getActivity(),FragmentFavPlaces.this);
        presenter.getFav("1");
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onSuccess(List<Favorite> favorites) {
        if (favorites.size()==0){
            recycle_fav.setVisibility(View.GONE);
            txtnodata.setVisibility(View.VISIBLE);
        }else {
            Favsadapter mytripsadapter = new Favsadapter(getActivity(),favorites);
            recycle_fav.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycle_fav.setAdapter(mytripsadapter);
        }

    }
}
