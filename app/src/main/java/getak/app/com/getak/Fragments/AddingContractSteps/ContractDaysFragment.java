package getak.app.com.getak.Fragments.AddingContractSteps;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Activites.AddContractActivity;
import getak.app.com.getak.Adapters.DaysAdapter;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.Model.Responses.ContractsType.ContractsTypes;
import getak.app.com.getak.Model.Responses.ContractsType.Type;
import getak.app.com.getak.Presenters.ContractsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Views.ContractsView;

import static getak.app.com.getak.Activites.AddContractActivity.DRIVERS_LIST;


public class ContractDaysFragment extends Fragment implements DaysAdapter.DaysAdapterInterAction, ContractsView {


    @BindView(R.id.days_list)
    RecyclerView daysList;
    DaysAdapter daysAdapter;
    List<Type> types =new ArrayList<>();
    public static KProgressHUD dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_days, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        daysAdapter=new DaysAdapter(getContext(),this,types);
        daysList.setAdapter(daysAdapter);
        daysList.setLayoutManager(new LinearLayoutManager(getContext()));
        daysList.setHasFixedSize(true);
        dialog=new KProgressHUD(getContext());
        ContractsPresenter.getContractsTypes(getContext(),this);
    }

    @Override
    public void onClick(Type type) {
        EventBus.getDefault().post(new ContractCreationStepsEvent(true,type,DRIVERS_LIST));
    }

    @OnClick(R.id.back_btn)
    void back(View view){
       getActivity().finish();
    }




    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
            types.clear();
            types.addAll(((ContractsTypes) obj).getTypes());
            daysAdapter.notifyDataSetChanged();
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
