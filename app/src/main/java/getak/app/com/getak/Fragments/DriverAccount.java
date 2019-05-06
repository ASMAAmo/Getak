package getak.app.com.getak.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.Model.Responses.Driver;
import getak.app.com.getak.Model.Responses.RegisterationResponse.DriverRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;

public class DriverAccount extends Fragment implements AccountView {
    public static KProgressHUD dialog;
    @BindView(R.id.profile_image)
    de.hdodenhof.circleimageview.CircleImageView profile_image;
    @BindView(R.id.name_input)
    EditText name_input;
    @BindView(R.id.phone_input)
    EditText phoneInput;
    @BindView(R.id.email_address)
    EditText email_input;
    @BindView(R.id.address_input)
    EditText addressInput;
    @BindView(R.id.gender_sp)
    Spinner genderSp;
    @BindView(R.id.age_input)
    EditText age_input;
    @BindView(R.id.personal_id)
    EditText personalIdInput;
    @BindView(R.id.license_type_input)
    EditText licenseTypeInput;
    @BindView(R.id.expired_date_input)
    EditText licenseExpiredDate;
    @BindView(R.id.car_typeinput)
    EditText carTypeInput;
    @BindView(R.id.car_model_input)
    EditText carModelInput;
    @BindView(R.id.bank_account_input)
    EditText bankAccountInput;
    @BindView(R.id.car_color_input)
    EditText carColorInput;
    @BindView(R.id.trips_number_input)
    EditText tripsNumber;
    @BindView(R.id.saturday)
    CheckBox saturday;
    @BindView(R.id.sunday)
    CheckBox sunday;
    @BindView(R.id.monday)
    CheckBox monday;
    @BindView(R.id.tuesday)
    CheckBox tuesday;
    @BindView(R.id.wednesday)
    CheckBox wednesday;
    @BindView(R.id.thursday)
    CheckBox thursday;
    @BindView(R.id.friday)
    CheckBox friday;
    @BindView(R.id.license_pic)
    ImageView licensePic;
    @BindView(R.id.id_pic)
    ImageView idPic;
    @BindView(R.id.car_pic)
    ImageView carPic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_account, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> genderList=new ArrayList<>();
        dialog=new KProgressHUD(getContext());
        genderList.clear();
        genderList.add(getString(R.string.male));
        genderList.add(getString(R.string.female));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, genderList);
        genderSp.setAdapter(arrayAdapter);
        AccountPresenter.getDriverProfile(getContext(), SessionHelper.getUserSession(getContext()).getId(),this);
    }

    @Override
    public void onSuccess(Object obj) {
        if(obj!=null){
            Driver driverProfile = ((Result<DriverRegisterationData>)obj).getData().getDriver();
            name_input.setText(driverProfile.getDriverName());
            phoneInput.setText(driverProfile.getDriverPhone());
            email_input.setText(driverProfile.getDriverEmail());
            addressInput.setText(driverProfile.getDriverAddress());
            age_input.setText(driverProfile.getDriverAge()+"");
            personalIdInput.setText(driverProfile.getDriverPersonalId());
            licenseTypeInput.setText(driverProfile.getLicenseType());
            licenseExpiredDate.setText(driverProfile.getLicenseExpireDate());
            carTypeInput.setText(driverProfile.getCarType());
            carModelInput.setText(driverProfile.getCarModel());
            bankAccountInput.setText(driverProfile.getBankAccount());
            carColorInput.setText(driverProfile.getCarColor());
            tripsNumber.setText(driverProfile.getTripNumbersPerDay()+"");
            Picasso.get().load("https://"+driverProfile.getDriverAvatar()).fit().into(profile_image);
            Picasso.get().load("https://"+driverProfile.getDriverPersonalIdPic()).fit().into(carPic);
            Picasso.get().load("https://"+driverProfile.getDriverLicenceIdPic()).fit().into(licensePic);
            Picasso.get().load("https://"+driverProfile.getCarPic()).fit().into(carPic);
        }
    }

    @Override
    public void onFailed(String err) {
        Toast.makeText(getContext(),err,Toast.LENGTH_LONG).show();
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
}
