package getak.app.com.getak.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.R;
import getak.app.com.getak.Views.ContactView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSupport extends Fragment implements ContactView, View.OnClickListener {
    KProgressHUD dialog;
    @BindView(R.id.user_name_input)
    EditText user_name_input;
    @BindView(R.id.email_input)
    EditText email_input;
    @BindView(R.id.messg_input)
    EditText messg_input;
    @BindView(R.id.send_btn)
    Button send_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_support, container,false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
       // dialog=new KProgressHUD(getContext());
    }
    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
          //  Data_contact contactmodel = (Result<Data_contact>)obj.g;
            //phoneInput.setText();
        }

    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getActivity(),err,Toast.LENGTH_LONG).show();

    }

    @Override
    public void loading(boolean status) {
        if(status){
            dialog.show();
        }else {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_btn:
                if (user_name_input.getText().toString().equals("")){
                   user_name_input.setError(getResources().getString(R.string.phonereq));
                }else if (email_input.getText().toString().equals("")){
                    email_input.setError(getResources().getString(R.string.emailreq));

                }else if (messg_input.getText().toString().equals("")){
                    messg_input.setError(getResources().getString(R.string.msgrequired));

                }else {
                   // ContactPresenter.contactUs(getActivity(),,this);
                }
                break;
        }
    }
}
