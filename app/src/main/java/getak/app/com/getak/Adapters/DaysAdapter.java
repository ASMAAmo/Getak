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
import getak.app.com.getak.Model.Responses.ContractsType.Type;
import getak.app.com.getak.R;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysVh> {

    Context context;
    DaysAdapterInterAction daysAdapterInterAction;
    List<Type> types;

    public DaysAdapter(Context context, DaysAdapterInterAction daysAdapterInterAction, List<Type> types) {
        this.context = context;
        this.daysAdapterInterAction = daysAdapterInterAction;
        this.types = types;
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
    public void onBindViewHolder(@NonNull DaysVh daysVh, final int i) {

        daysVh.typeName.setText(types.get(i).getPeriod()+" "+"أشهر");
        daysVh.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daysAdapterInterAction.onClick(types.get(i));
            }
        });
    }



    @Override
    public int getItemCount() {
        return types.size();
    }

    public static class DaysVh extends RecyclerView.ViewHolder{
        View v;

        @BindView(R.id.type_name)
        TextView typeName;

        public DaysVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            v=itemView;

        }
    }

    public interface DaysAdapterInterAction{
        void onClick(Type type);
    }
}
