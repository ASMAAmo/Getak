package getak.app.com.getak.Fragments;

import android.content.Context;
import android.net.Uri;
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
import getak.app.com.getak.Adapters.Banksadapter;
import getak.app.com.getak.Model.Datum;
import getak.app.com.getak.R;


public class FragmentMessages extends Fragment {
    @BindView(R.id.recycle_banks)
    RecyclerView recycle_banks;
    List<Datum> faqModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_messages, container,
                false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Banksadapter banksadapter = new Banksadapter(getActivity(),faqModels);
        recycle_banks.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_banks.setAdapter(banksadapter);
    }
}
