package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import getak.app.com.getak.R;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysVh> {

    Context context;
    DaysAdapterInterAction daysAdapterInterAction;

    public DaysAdapter(Context context, DaysAdapterInterAction daysAdapterInterAction) {
        this.context = context;
        this.daysAdapterInterAction = daysAdapterInterAction;
    }


    @NonNull
    @Override
    public DaysVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout_contract_days, viewGroup, false);
        ButterKnife.bind(this,view);
        return new DaysAdapter.DaysVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysVh daysVh, int i) {

        daysVh.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysAdapterInterAction.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class DaysVh extends RecyclerView.ViewHolder{
        View v;
        public DaysVh(@NonNull View itemView) {
            super(itemView);
            v=itemView;

        }
    }

    public interface DaysAdapterInterAction{
        void onClick();
    }
}
