package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Model.Contract;
import getak.app.com.getak.Model.Contractmodel;
import getak.app.com.getak.R;

public class ContractsAdapter extends RecyclerView.Adapter<ContractsAdapter.ContractsAdb> {

    Context context;
    List<Contract> contracts ;

    public ContractsAdapter(Context context, List<Contract> contracts) {
        this.context = context;
        this.contracts = contracts;
    }

    @NonNull
    @Override
    public ContractsAdb onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout_contract, viewGroup, false);
        ButterKnife.bind(this,view);
        return new ContractsAdb(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractsAdb contractsAdb, int i) {
        contractsAdb.clientNameTv.setText(contracts.get(i).getClientName().toString());
        contractsAdb.driverNameTv.setText(contracts.get(i).getDriverName().toString());
        contractsAdb.targetLocation.setText(contracts.get(i).getToPlaceName());
        contractsAdb.placeReturnTv.setText(contracts.get(i).getFromPlaceName());
        contractsAdb.goingTimeTv.setText(contracts.get(i).getGoTime());
        contractsAdb.returnTimeTv.setText(contracts.get(i).getBackTime());
        contractsAdb.contractStartTv.setText(contracts.get(i).getStartDate());
        contractsAdb.contractEndTv.setText(contracts.get(i).getEndDate());
        contractsAdb.totalAmountTv.setText(contracts.get(i).getTotalPrice()+"");
        if(contracts.get(i).getDays().getDays().isEmpty()){
            contractsAdb.daysTv.setVisibility(View.GONE);
        }else {
            contractsAdb.daysTv.setVisibility(View.VISIBLE);
            StringBuilder days =new StringBuilder();
            for(String day : contracts.get(i).getDays().getDays()){
                days.append(day+" ");
            }
        }
    }

    @Override
    public int getItemCount() {
        return contracts.size();
    }

    public static class ContractsAdb extends RecyclerView.ViewHolder{
        @BindView(R.id.client_name)
        TextView clientNameTv;
        @BindView(R.id.driver_name_tv)
        TextView driverNameTv;
        @BindView(R.id.target_location_tv)
        TextView targetLocation;
        @BindView(R.id.place_return_tv)
        TextView placeReturnTv;
        @BindView(R.id.going_time_tv)
        TextView goingTimeTv;
        @BindView(R.id.return_time_tv)
        TextView returnTimeTv;
        @BindView(R.id.contract_start_tv)
        TextView contractStartTv;
        @BindView(R.id.contract_end_tv)
        TextView contractEndTv;
        @BindView(R.id.total_amount_tv)
        TextView totalAmountTv;
        @BindView(R.id.days_tv)
        TextView daysTv;

        public ContractsAdb(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
