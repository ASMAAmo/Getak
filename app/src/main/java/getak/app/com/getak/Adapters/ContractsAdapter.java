package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import getak.app.com.getak.R;

public class ContractsAdapter extends RecyclerView.Adapter<ContractsAdapter.ContractsAdb> {

    Context context;

    public ContractsAdapter(Context context) {
        this.context = context;
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
        return 10;
    }

    public static class ContractsAdb extends RecyclerView.ViewHolder{

        public ContractsAdb(@NonNull View itemView) {
            super(itemView);
        }
    }

}
