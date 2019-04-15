package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import getak.app.com.getak.R;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.DriversVh> {
    Context context;
    DriversAdapterInterAction driversAdapterInterAction;

    public DriversAdapter(Context context, DriversAdapterInterAction driversAdapterInterAction) {
        this.context = context;
        this.driversAdapterInterAction = driversAdapterInterAction;
    }

    @NonNull
    @Override
    public DriversVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout_driver, viewGroup, false);
        ButterKnife.bind(this,view);
        return new DriversAdapter.DriversVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriversVh driversVh, int i) {
        driversVh.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driversAdapterInterAction.onClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public  static class DriversVh extends RecyclerView.ViewHolder{
        View v;
        public DriversVh(@NonNull View itemView) {
            super(itemView);
            v=itemView;
        }
    }

    public interface DriversAdapterInterAction{
        void onClick();
    }
}
