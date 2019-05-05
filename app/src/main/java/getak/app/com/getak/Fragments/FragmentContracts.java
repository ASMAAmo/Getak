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

import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.ContractsAdapter;
import getak.app.com.getak.Model.Contract;
import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.Presenters.ContactPresenter;
import getak.app.com.getak.Presenters.ContractsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.ContractsView;

public class FragmentContracts extends Fragment implements ContractsView {
    @BindView(R.id.empty_flag)
    TextView emptyFlag;
    @BindView(R.id.contacts_list)
    RecyclerView contractsList;
    ContractsAdapter contractsAdapter;
    List<Contract> contracts =new ArrayList<>();
    public static KProgressHUD dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contracts, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        HashMap id =new HashMap();
        if(SessionHelper.isDriver(getContext())) {
            id.put("driver_id", SessionHelper.getUserSession(getContext()).getId());
        }else {
            id.put("client_id", SessionHelper.getUserSession(getContext()).getId());
        }
        ContractsPresenter.getContracts(getContext(),id,this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emptyFlag.setVisibility(View.GONE);
        dialog=new KProgressHUD(getContext());
        contractsAdapter=new ContractsAdapter(getContext(),contracts);
        contractsList.setAdapter(contractsAdapter);
        contractsList.setLayoutManager(new LinearLayoutManager(getContext()));
        contractsList.setHasFixedSize(true);
    }

    @Override
    public void onSuccess(Object obj) {
        if(obj!=null){
            contracts.clear();
            contracts.addAll(((Contractmodel)obj).getContracts());
            contractsAdapter.notifyDataSetChanged();
        }

        if(contracts.isEmpty()){
            emptyFlag.setVisibility(View.VISIBLE);
        }else {
            emptyFlag.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getContext(),err,Toast.LENGTH_LONG).show();

    }

    @Override
    public void loading(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
