package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
        return new ContractsAdb(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractsAdb contractsAdb, int i) {

    }

    @Override
    public int getItemCount() {
        return contracts.size();
    }

    public static class ContractsAdb extends RecyclerView.ViewHolder{

        public ContractsAdb(@NonNull View itemView) {
            super(itemView);
        }
    }

}
