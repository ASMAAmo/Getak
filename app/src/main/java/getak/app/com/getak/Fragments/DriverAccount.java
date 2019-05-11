package getak.app.com.getak.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Model.Days;
import getak.app.com.getak.Model.Responses.Driver;
import getak.app.com.getak.Model.Responses.RegisterationResponse.DriverRegisterationData;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Model.Responses.UserModel;
import getak.app.com.getak.Presenters.AccountPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.AccountView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    String gender="male";
    static HashMap<Integer,String> daysMap = new HashMap<>();
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
        setupDaysChooser();
        final List<String> genderList=new ArrayList<>();
        dialog=new KProgressHUD(getContext());
        genderList.clear();
        genderList.add(getString(R.string.male));
        genderList.add(getString(R.string.female));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, genderList);
        genderSp.setAdapter(arrayAdapter);
        genderSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               switch(position){
                   case 0:
                       gender = "male";
                       break;
                   case 1:
                       gender = "female";
                       break;
               }
           }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        AccountPresenter.getDriverProfile(getContext(), SessionHelper.getUserSession(getContext()).getId(),this);
    }

    @Override
    public void onSuccess(Object obj) {
        if(obj!=null){
            Driver driverProfile = ((Result<DriverRegisterationData>)obj).getData().getDriver();
            UserModel driverSession = new UserModel();
            driverSession.setId(driverProfile.getId());
            driverSession.setDriverName(driverProfile.getDriverName());
            driverSession.setDriverAvatar(driverProfile.getDriverAvatar()+"");
            SessionHelper.setUserSession(getContext(),driverSession);
            EventBus.getDefault().post(new LoginEvent(true,""));
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
            bankAccountInput.setText(driverProfile.getBankAccount().toString());
            carColorInput.setText(driverProfile.getCarColor());
            tripsNumber.setText(driverProfile.getTripNumbersPerDay()+"");

            if(driverProfile.getDriverGender()!=null) {
                if (driverProfile.getDriverGender().equals("male")) {
                    genderSp.setSelection(0);
                    gender = "male";
                } else {
                    genderSp.setSelection(1);
                    gender = "female";
                }
            }

            Picasso.get().load(driverProfile.getDriverAvatar().toString()).fit().into(profile_image);
            Picasso.get().load(driverProfile.getDriverPersonalIdPic().toString()).fit().into(idPic);
            Picasso.get().load(driverProfile.getDriverLicenceIdPic().toString()).fit().into(licensePic);
            Picasso.get().load(driverProfile.getCarPic().toString()).fit().into(carPic);
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

    void updateDriverData(){
        name_input.setError(null);
        phoneInput.setError(null);
        email_input.setError(null);
        addressInput.setError(null);
        age_input.setError(null);
        personalIdInput.setError(null);
        carTypeInput.setError(null);
        carModelInput.setError(null);
        carColorInput.setError(null);
        bankAccountInput.setError(null);
        tripsNumber.setError(null);
        licenseExpiredDate.setError(null);

        String name = name_input.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = email_input.getText().toString();
        String address= addressInput.getText().toString();
        String age= age_input.getText().toString();
        String personalIdNumber=personalIdInput.getText().toString();
        String carTypeText=carTypeInput.getText().toString();
        String carModel=carModelInput.getText().toString();
        String carColor=carColorInput.getText().toString();
        String bankAccount=bankAccountInput.getText().toString();
        String tripsNumberst=tripsNumber.getText().toString();
        String licenceExpiredDate = licenseTypeInput.getText().toString();
        String licenseType=licenseTypeInput.getText().toString();


        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(address)) {
            addressInput.setError(getString(R.string.addressreq));
            focusView = addressInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(licenseType)) {
            licenseTypeInput.setError(getString(R.string.licensetype));
            focusView = licenseTypeInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(address)) {
            addressInput.setError(getString(R.string.addressreq));
            focusView = addressInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(licenceExpiredDate)) {
            licenseExpiredDate.setError(getString(R.string.expiredlicenceerr));
            focusView = licenseExpiredDate;
            cancel = true;
        }

        if (TextUtils.isEmpty(age)) {
            age_input.setError(getString(R.string.agereq));
            focusView = age_input;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_input.setError(getString(R.string.emailreq));
            focusView = email_input;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_input.setError(getString(R.string.emailvalid));
            focusView = email_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            name_input.setError(getString(R.string.namereq));
            focusView = name_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(phone)) {
            phoneInput.setError(getString(R.string.phonereq));
            focusView = phoneInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(personalIdNumber)) {
            personalIdInput.setError(getString(R.string.iderr));
            focusView = personalIdInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(carTypeText)) {
            carTypeInput.setError(getString(R.string.cartypeerr));
            focusView = carTypeInput;
            cancel = true;
        }


        if (TextUtils.isEmpty(carModel)) {
            carModelInput.setError(getString(R.string.carmodelerr));
            focusView = carModelInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(carColor)) {
            carColorInput.setError(getString(R.string.colorerr));
            focusView = carColorInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(bankAccount)) {
            bankAccountInput.setError(getString(R.string.bankaccounterr));
            focusView = bankAccountInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(tripsNumberst)) {
            tripsNumber.setError(getString(R.string.tripserrr));
            focusView = tripsNumber;
            cancel = true;
        }

        if(daysMap.isEmpty()){
            Toast.makeText(getContext(),"أختر الأيام",Toast.LENGTH_LONG).show();
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt registration and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            List<String> days = new ArrayList<String>(daysMap.values());
            Days daysModel =new Days();
            daysModel.setDays(days);
            Gson gson =new Gson();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("driver_name", name)
                    .addFormDataPart("driver_email", email)
                    .addFormDataPart("driver_phone",phone)
                    .addFormDataPart("driver_address",address)
                    .addFormDataPart("deviceType","Android")
                    .addFormDataPart("token",SessionHelper.getPushNotificationToken(getContext()))
                    .addFormDataPart("driver_gender", gender)
                    .addFormDataPart("driver_age",age)
                    .addFormDataPart("driver_personal_id", personalIdNumber)
                    .addFormDataPart("license_type", licenseType)
                    .addFormDataPart("license_expire_date", licenceExpiredDate)
                    .addFormDataPart("bank_account",bankAccount)
                    .addFormDataPart("car_model", carModel)
                    .addFormDataPart("car_type", carTypeText)
                    .addFormDataPart("car_date", "")
                    .addFormDataPart("car_color", carColor)
                    .addFormDataPart("trip_numbers_per_day", tripsNumberst)
                    .addFormDataPart("work_days", gson.toJson(daysModel))
                    .build();
            AccountPresenter.
                    updateDriverProfile(getContext(),SessionHelper.getUserSession(getContext()).getId(),requestBody,this);

        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private void setupDaysChooser() {
        saturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(1,"Saturday");
                }else {
                    daysMap.remove(1);
                }
            }
        });
        sunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(2,"Sunday");
                }else {
                    daysMap.remove(2);
                }
            }
        });
        monday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(3,"Monday");
                }else {
                    daysMap.remove(3);
                }
            }
        });
        tuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(4,"Tuesday");
                }else {
                    daysMap.remove(4);
                }
            }
        });
        wednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(5,"Wednesday");
                }else {
                    daysMap.remove(5);
                }
            }
        });
        thursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(6,"Thursday");
                }else {
                    daysMap.remove(6);
                }
            }
        });
        friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    daysMap.put(7,"Friday");
                }else {
                    daysMap.remove(7);
                }
            }
        });
    }

    @OnFocusChange(R.id.expired_date_input)
    void expiredDate(boolean focused){
        if(focused){
            final Calendar myCalendar = Calendar.getInstance(Locale.ENGLISH);
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String myFormat = "dd-MM-yyyy"; // your format
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    licenseExpiredDate.setText(sdf.format(myCalendar.getTime()));
                }
            };
            new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @OnClick(R.id.saveBtn)
    void save(){
        updateDriverData();
    }
}
