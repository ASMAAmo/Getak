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

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.ContractsAdapter;
import getak.app.com.getak.R;

public class FragmentContracts extends Fragment {
    @BindView(R.id.empty_flag)
    TextView emptyFlag;
    @BindView(R.id.contacts_list)
    RecyclerView contractsList;
    ContractsAdapter contractsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contracts, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyFlag.setVisibility(View.GONE);
        contractsAdapter=new ContractsAdapter(getContext());
        contractsList.setAdapter(contractsAdapter);
        contractsList.setLayoutManager(new LinearLayoutManager(getContext()));
        contractsList.setHasFixedSize(true);
    }
}
