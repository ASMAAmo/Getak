package getak.app.com.getak.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import getak.app.com.getak.Model.Datum;
import getak.app.com.getak.Model.Datum_trip;
import getak.app.com.getak.R;


/**
 * Created by ahmed on 11/18/2017.
 */

 public class Mytripsadapter extends RecyclerView.Adapter<Mytripsadapter.ViewHolder>  {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
    List<Datum_trip> mytrips;
    boolean ischecked=false;
    Button btncall;
    String planiid;

    public   String delegateId,userid,serviceid,quat;
    //Add book url to this link and test it


    public Mytripsadapter(Context context, List<Datum_trip> mytrips) {
        this.context = context;
        this.mytrips = mytrips;

    }


    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_tripitem, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //animate(holder);
        final Datum_trip faqModel = mytrips.get(position);
       holder.txtname.setText(faqModel.getDriverName());
       holder.txtdate.setText(faqModel.getCreatedAt());
       holder.txtsal.setText(" سعر الرحله"+ " "+ String.valueOf(faqModel.getPrice()));

    }



    @Override
    public int getItemCount()
    {
        return mytrips.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ques_image;
        Button btncall;
        ImageView img_book;
        LinearLayout linall;
        View mView;
        TextView txtname,txtdate,txtsal;
        ;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtname = (TextView) itemView.findViewById(R.id.txtname);
            txtdate=(TextView)itemView.findViewById(R.id.txtdate);
            txtsal=(TextView)itemView.findViewById(R.id.txtsal);
           }
    }




}
