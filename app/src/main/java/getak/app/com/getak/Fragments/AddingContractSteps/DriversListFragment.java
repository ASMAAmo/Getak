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
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Adapters.DriversAdapter;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.Model.Responses.AllDriversResponse.AllDrivers;
import getak.app.com.getak.Model.Responses.AllDriversResponse.Datum;
import getak.app.com.getak.Presenters.DriversPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Views.AllDriversView;

import static getak.app.com.getak.Activites.AddContractActivity.CONFIRM;

public class DriversListFragment extends Fragment implements DriversAdapter.DriversAdapterInterAction, AllDriversView {

    @BindView(R.id.drivers_list)
    RecyclerView driversList;
    DriversAdapter driversAdapter;
    List<Datum> driversRes = new ArrayList<>();
    public static KProgressHUD dialog;



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
        driversAdapter=new DriversAdapter(getContext(),this,driversRes);
        driversList.setAdapter(driversAdapter);
        driversList.setLayoutManager(new LinearLayoutManager(getContext()));
        driversList.setHasFixedSize(true);
        dialog=new KProgressHUD(getContext());
        DriversPresenter.getAllDrivers(getContext(),this,1);

    }

    @Override
    public void onClick(Datum driver) {
        EventBus.getDefault().post(new ContractCreationStepsEvent(true,driver,CONFIRM));
    }


    @Override
    public void onGetAllDrivers(AllDrivers drivers) {
        driversRes.clear();
        driversRes.addAll(drivers.getDrivers().getData());
        driversAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetAllDriversError(String err) {
        Toast.makeText(getContext(),err,Toast.LENGTH_LONG).show();
    }

    @Override
    public void progress(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
