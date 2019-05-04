package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import getak.app.com.getak.Model.Responses.AllDriversResponse.Datum;
import getak.app.com.getak.R;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.DriversVh> {
    Context context;
    DriversAdapterInterAction driversAdapterInterAction;
    List<Datum> drivers;

    public DriversAdapter(Context context, DriversAdapterInterAction driversAdapterInterAction, List<Datum> drivers) {
        this.context = context;
        this.driversAdapterInterAction = driversAdapterInterAction;
        this.drivers = drivers;
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
    public void onBindViewHolder(@NonNull DriversVh driversVh, final int i) {
        driversVh.driverName.setText(drivers.get(i).getDriverName());
        driversVh.driverAge.setText(drivers.get(i).getDriverAge()+"");
        driversVh.driverCarType.setText(drivers.get(i).getCarModel());
        driversVh.rate.setRating(drivers.get(i).getStar());
        Picasso.get().load("https://"+drivers.get(i).getDriverAvatar()).fit().into(driversVh.avatar);
        driversVh.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driversAdapterInterAction.onClick(drivers.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public  static class DriversVh extends RecyclerView.ViewHolder{
        View v;
        @BindView(R.id.driver_name_tv)
        TextView driverName;
        @BindView(R.id.driver_age_tv)
        TextView driverAge;
        @BindView(R.id.driver_car_tv)
        TextView driverCarType;
        @BindView(R.id.ratingBar)
        RatingBar rate;
        @BindView(R.id.profile_image)
        CircleImageView avatar;
        public DriversVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            v=itemView;
        }
    }

    public interface DriversAdapterInterAction{
        void onClick(Datum driver);
    }
}
