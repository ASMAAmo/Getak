package getak.app.com.getak.Fragments.AddingContractSteps;

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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.DriversAdapter;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.R;

import static getak.app.com.getak.Activites.AddContractActivity.CONFIRM;

public class DriversListFragment extends Fragment implements DriversAdapter.DriversAdapterInterAction {

    @BindView(R.id.drivers_list)
    RecyclerView driversList;
    DriversAdapter driversAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drivers_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        driversAdapter=new DriversAdapter(getContext(),this);
        driversList.setAdapter(driversAdapter);
        driversList.setLayoutManager(new LinearLayoutManager(getContext()));
        driversList.setHasFixedSize(true);
    }

    @Override
    public void onClick() {
        EventBus.getDefault().post(new ContractCreationStepsEvent(true,"test",CONFIRM));
    }
}
