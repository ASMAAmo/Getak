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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.Activites.AddContractActivity;
import getak.app.com.getak.Adapters.DaysAdapter;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.R;

import static getak.app.com.getak.Activites.AddContractActivity.DRIVERS_LIST;


public class ContractDaysFragment extends Fragment implements DaysAdapter.DaysAdapterInterAction {


    @BindView(R.id.days_list)
    RecyclerView daysList;
    DaysAdapter daysAdapter;
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
        daysAdapter=new DaysAdapter(getContext(),this);
        daysList.setAdapter(daysAdapter);
        daysList.setLayoutManager(new LinearLayoutManager(getContext()));
        daysList.setHasFixedSize(true);
    }

    @Override
    public void onClick() {
        EventBus.getDefault().post(new ContractCreationStepsEvent(true,"test",DRIVERS_LIST));
    }

    @OnClick(R.id.back_btn)
    void back(View view){
       getActivity().finish();
    }
}
