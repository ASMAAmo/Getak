package getak.app.com.getak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.library.RippleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.R;


public class Fragment_account extends Fragment {
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @BindView(R.id.name_input)
    EditText name_input;
    @BindView(R.id.user_name_input)
    EditText user_name_input;
    @BindView(R.id.email_input)
    EditText email_input;
    @BindView(R.id.email_address)
    EditText email_address;
    @BindView(R.id.gender_input)
    EditText gender_input;
    @BindView(R.id.age_input)
    EditText age_input;
    @BindView(R.id.password_input)
    EditText password_input;
    @BindView(R.id.passwordconf_input)
    EditText passwordconf_input;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_account, container,
                true);
        ButterKnife.bind(getActivity());
        // Inflate the layout for this fragment
        return view;
    }
}
