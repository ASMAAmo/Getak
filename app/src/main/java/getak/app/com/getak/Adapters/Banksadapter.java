package getak.app.com.getak.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import getak.app.com.getak.Model.Datum;
import getak.app.com.getak.R;


/**
 * Created by ahmed on 11/18/2017.
 */

 public class Banksadapter extends RecyclerView.Adapter<Banksadapter.ViewHolder>  {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
    List<Datum> faqModels;
    boolean ischecked=false;
    Button btncall;
    String planiid;

    public   String delegateId,userid,serviceid,quat;
    //Add book url to this link and test it


    public Banksadapter(Context context, List<Datum> faqModels) {
        this.context = context;
        this.faqModels = faqModels;

    }


    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_bankitem, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //animate(holder);
      //  final Datum faqModel = faqModels.get(position);
      // holder.txtbookname.setText(faqModel.getName());








    }



    @Override
    public int getItemCount()
    {
        return 6;
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
        TextView txtbookname,txtbookauthor,txtvediodate;
        ;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtbookname = (TextView) itemView.findViewById(R.id.txtbankname);
           }
    }




}
