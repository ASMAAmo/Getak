package getak.app.com.getak.Fragments.RegisterFragments;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

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
import cc.cloudist.acplibrary.ACProgressFlower;
import de.hdodenhof.circleimageview.CircleImageView;
import getak.app.com.getak.Activites.MainActivity;
import getak.app.com.getak.Events.LoginEvent;
import getak.app.com.getak.Model.Days;
import getak.app.com.getak.Model.Responses.Driver;
import getak.app.com.getak.Model.Responses.RegisterationResponse.ClientRegisterationData;
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

public class DriverRegisterFragment extends Fragment implements AccountView {
    public static KProgressHUD dialog;
    public final String DRIVER="driver";
    @BindView(R.id.name_input)
    TextView nameInput;
    @BindView(R.id.phone_input)
    TextView phoneInput;
    @BindView(R.id.email_input)
    TextView emailInput;
    @BindView(R.id.password_input)
    TextView passwordInput;
    @BindView(R.id.re_password_input)
    TextView rePassworInput;
    @BindView(R.id.address_input)
    TextView addressInput;
    @BindView(R.id.age_in)
    TextView ageInput;
    @BindView(R.id.gender)
    RadioGroup genderGroup;
    @BindView(R.id.identity_input)
    EditText personalIdInput;
    @BindView(R.id.edcartype)
    EditText carType;
    @BindView(R.id.carmodel_input)
    EditText carmodel_input;
    @BindView(R.id.carcolor_input)
    EditText carcolor_input;
    @BindView(R.id.bankaccount_input)
    EditText bankaccount_input;
    @BindView(R.id.tripsNum_input)
    EditText tripsNum_input;
    @BindView(R.id.sp_rokhsatype)
    AppCompatSpinner licenseTypeSp;
    @BindView(R.id.licence_expiredinput)
    EditText licence_expiredinput;
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
    static HashMap<Integer,String> daysMap = new HashMap<>();

    public static CircleImageView driver_avatar;
    public static ImageView driverPersonalIdPic;
    public static ImageView driverLicenceIdPic;
    public static ImageView driverCar;

    String gender="male";
    String licenseTypeSt="ملاكي";
    public static final int PICK_DRIVER_AVATAR = 101;
    public static final int PICK_DRIVER_ID = 102;
    public static final int PICK_DRIVER_LICENCE_ID = 103;
    public static final int PIC_DRIVER_CAR = 104;
    public static String driverAvatarSelectedFilePath;
    public static String driverIdSelectedFilePath;
    public static String driverLicenceSelectedFilePath;
    public static String driverCarSelectedFilePath;
public String url="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_register, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        driver_avatar=view.findViewById(R.id.driver_avatar);
        driverPersonalIdPic=view.findViewById(R.id.img_idpic);
        driverLicenceIdPic=view.findViewById(R.id.img_idrokhsa);
        driverCar=view.findViewById(R.id.img_idcarpic);
        dialog=new KProgressHUD(getContext());
        setupDaysChooser();
        genderGroup.check(R.id.male);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.male:
                        gender = "male";
                        break;
                    case R.id.female:
                        gender = "female";
                        break;
                }
            }
        });

        final List<String> licenseType=new ArrayList<>();
        licenseType.clear();
        licenseType.add(getString(R.string.malaky));
        licenseType.add(getString(R.string.ogra));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, licenseType);
        licenseTypeSp.setAdapter(arrayAdapter);
        licenseTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 : {
                        licenseTypeSt="ملاكي";
                        break;
                    }
                    case 1 : {
                        licenseTypeSt="أجرة";
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.driver_avatar)
    void pick(){
        pickImage(PICK_DRIVER_AVATAR);
    }

    @OnClick(R.id.img_idpic)
    void picId(){
        pickImage(PICK_DRIVER_ID);
    }

    @OnClick(R.id.img_idrokhsa)
    void licinceId(){
        pickImage(PICK_DRIVER_LICENCE_ID);
    }

    @OnClick(R.id.img_idcarpic)
    void carPic(){
        pickImage(PIC_DRIVER_CAR);
    }

    @OnFocusChange(R.id.licence_expiredinput)
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
                    licence_expiredinput.setText(sdf.format(myCalendar.getTime()));
                }
            };
            new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    @OnClick(R.id.register_btn)
    void register(){
        driverRegistration();
    }




    void driverRegistration(){
        nameInput.setError(null);
        phoneInput.setError(null);
        emailInput.setError(null);
        passwordInput.setError(null);
        rePassworInput.setError(null);
        addressInput.setError(null);
        ageInput.setError(null);
        personalIdInput.setError(null);
        carType.setError(null);
        carmodel_input.setError(null);
        carcolor_input.setError(null);
        bankaccount_input.setError(null);
        tripsNum_input.setError(null);

        String name = nameInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String rePassword= rePassworInput.getText().toString();
        String address= addressInput.getText().toString();
        String age= ageInput.getText().toString();
        String personalIdNumber=personalIdInput.getText().toString();
        String carTypeText=carType.getText().toString();
        String carModel=carmodel_input.getText().toString();
        String carColor=carcolor_input.getText().toString();
        String bankAccount=bankaccount_input.getText().toString();
        String tripsNumber=tripsNum_input.getText().toString();
        String licenceExpiredDate = licence_expiredinput.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError(getString(R.string.reqage));
            focusView = passwordInput;
            cancel = true;
        }


        if (TextUtils.isEmpty(rePassword)) {
            rePassworInput.setError(getString(R.string.passreq));
            focusView = rePassworInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(address)) {
            addressInput.setError(getString(R.string.addressreq));
            focusView = addressInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(licenceExpiredDate)) {
            licence_expiredinput.setError(getString(R.string.expiredlicenceerr));
            focusView = licence_expiredinput;
            cancel = true;
        }

        if (TextUtils.isEmpty(age)) {
            ageInput.setError(getString(R.string.agereq));
            focusView = ageInput;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.emailreq));
            focusView = emailInput;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailInput.setError(getString(R.string.emailvalid));
            focusView = emailInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            nameInput.setError(getString(R.string.namereq));
            focusView = nameInput;
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
            carType.setError(getString(R.string.cartypeerr));
            focusView = carType;
            cancel = true;
        }


        if (TextUtils.isEmpty(carModel)) {
            carmodel_input.setError(getString(R.string.carmodelerr));
            focusView = passwordInput;
            cancel = true;
        }

        if (TextUtils.isEmpty(carColor)) {
            carcolor_input.setError(getString(R.string.colorerr));
            focusView = carcolor_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(bankAccount)) {
            bankaccount_input.setError(getString(R.string.bankaccounterr));
            focusView = bankaccount_input;
            cancel = true;
        }

        if (TextUtils.isEmpty(tripsNumber)) {
            tripsNum_input.setError(getString(R.string.tripserrr));
            focusView = tripsNum_input;
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
            RequestBody avatar =null;
            RequestBody driverPersonalIdPic=null;
            RequestBody driverLicensePic=null;
            RequestBody driverCarPic=null;
            if(driverAvatarSelectedFilePath!=null){
                File file = new File(driverAvatarSelectedFilePath);
                avatar = RequestBody.create(MediaType.parse("image/*"), file);
            }else {
                avatar = RequestBody.create(MediaType.parse("text/plain"), "");
            }

            if(driverIdSelectedFilePath!=null){
                File file = new File(driverIdSelectedFilePath);
                driverPersonalIdPic = RequestBody.create(MediaType.parse("image/*"), file);
            }else {
                driverPersonalIdPic = RequestBody.create(MediaType.parse("image/*"), "");
            }

            if(driverLicenceSelectedFilePath!=null){
                File file = new File(driverIdSelectedFilePath);
                driverLicensePic = RequestBody.create(MediaType.parse("image/*"), file);
            }else {
                driverLicensePic = RequestBody.create(MediaType.parse("image/*"), "");
            }

            if(driverCarSelectedFilePath!=null){
                File file = new File(driverCarSelectedFilePath);
                driverCarPic = RequestBody.create(MediaType.parse("image/*"), file);
            }else {
                driverCarPic = RequestBody.create(MediaType.parse("image/*"), "");
            }

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
                    .addFormDataPart("password",password)
                    .addFormDataPart("password_confirmation",rePassword)
                    .addFormDataPart("deviceType","Android")
                    .addFormDataPart("token",SessionHelper.getPushNotificationToken(getContext()))
                    .addFormDataPart("avatar","Avatar",avatar)
                    .addFormDataPart("driver_gender", gender)
                    .addFormDataPart("driver_age",age)
                    .addFormDataPart("driver_personal_id", personalIdNumber)
                    .addFormDataPart("driver_personal_id_pic", "driver_personal_id_pic",driverPersonalIdPic)
                    .addFormDataPart("driver_licence_id_pic", "driver_licence_id_pic",driverLicensePic)
                    .addFormDataPart("license_type", licenseTypeSt)
                    .addFormDataPart("license_expire_date", licenceExpiredDate)
                    .addFormDataPart("bank_account",bankAccount)
                    .addFormDataPart("car_pic", "car_pic",driverCarPic)
                    .addFormDataPart("car_model", carModel)
                    .addFormDataPart("car_type", carTypeText)
                    .addFormDataPart("car_date", "")
                    .addFormDataPart("car_color", carColor)
                    .addFormDataPart("trip_numbers_per_day", tripsNumber)
                    .addFormDataPart("work_days", gson.toJson(daysModel))
                    .build();
            AccountPresenter.registerDriver(getContext(),requestBody,this);
        }

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }



    void pickImage(int requestType){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        getActivity().startActivityForResult(galleryIntent,requestType);
    }







    @Override
    public void onSuccess(Object obj) {
        if(obj!=null) {
            SessionHelper.setUserType(getContext(),DRIVER);
            Driver driver = ((Result<DriverRegisterationData>)obj).getData().getDriver();
            UserModel userModel =new UserModel();
            userModel.setClientAvatar(driver.getDriverAvatar().toString());
            userModel.setId(driver.getId());
            userModel.setClientName(driver.getDriverName());
            SessionHelper.setUserSession(getContext(),userModel);
            Toast.makeText(getContext(),  ((Result<DriverRegisterationData>)obj).getMessage(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            EventBus.getDefault().post(new LoginEvent(true, ""));
            getActivity().finish();
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
}
