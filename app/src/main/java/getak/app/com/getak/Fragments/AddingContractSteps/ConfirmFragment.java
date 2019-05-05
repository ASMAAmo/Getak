package getak.app.com.getak.Fragments.AddingContractSteps;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

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
import getak.app.com.getak.Activites.AddContractActivity;
import getak.app.com.getak.Dialogs.CustomPlacePicker;
import getak.app.com.getak.Model.Days;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;

import static getak.app.com.getak.Activites.AddContractActivity.createContractRequest;


public class ConfirmFragment extends Fragment implements CustomPlacePicker.PlacePickerInteraction {
    @BindView(R.id.ed_startdate)
    EditText contractStartDate;
    @BindView(R.id.ed_enddate)
    EditText contractEndDate;
    @BindView(R.id.ed_startplace)
    EditText goingPlace;
    @BindView(R.id.ed_endplace)
    EditText returnPlace;
    @BindView(R.id.ed_timego)
    EditText goingTime;
    @BindView(R.id.ed_timeleave)
    EditText returnTime;
    @BindView(R.id.ed_numofpeople)
    EditText personsNumber;
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
    CustomPlacePicker customPlacePicker;
    private static int selection=5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDaysChooser();
        if(customPlacePicker==null) {
            try {
                customPlacePicker = new CustomPlacePicker(getActivity(), this);
            }catch (Exception e){}
        }

        contractStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
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

                            contractStartDate.setText(sdf.format(myCalendar.getTime()));
                        }
                    };
                    new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        contractEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
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
                            contractEndDate.setText(sdf.format(myCalendar.getTime()));
                        }
                    };
                    new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });


        goingTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance(Locale.ENGLISH);
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            goingTime.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, false);//Yes 24 hour time
                    mTimePicker.setTitle("أختر وقت الذهاب");
                    mTimePicker.show();
                }
            }
        });


        returnTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance(Locale.ENGLISH);
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            returnTime.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, false);//Yes 24 hour time
                    mTimePicker.setTitle("أختر وقت العودة");
                    mTimePicker.show();
                }
            }
        });
    }






    void fireLocationPicker(int requestType){
        if(customPlacePicker!=null){
            customPlacePicker.show();
            selection=requestType;
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

    void createContract(){
        contractStartDate.setError(null);
        contractEndDate.setError(null);
        goingPlace.setError(null);
        returnPlace.setError(null);
        goingTime.setError(null);
        returnTime.setError(null);
        personsNumber.setError(null);

        String startDate=contractStartDate.getText().toString();
        String endDate=contractEndDate.getText().toString();
        String gonigPlaceSt=goingPlace.getText().toString();
        String returnPlaceSt=returnPlace.getText().toString();
        String goingTimeSt=goingTime.getText().toString();
        String returnTimeSt=returnTime.getText().toString();
        String personNumberSt=personsNumber.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(startDate)) {
            contractStartDate.setError("أدخل تاريخ بداية العقد");
            focusView = contractStartDate;
            cancel = true;
        }


        if (TextUtils.isEmpty(endDate)) {
            contractEndDate.setError("أدخل تاريخ إنتهاء العقد");
            focusView = contractEndDate;
            cancel = true;
        }


        if (TextUtils.isEmpty(gonigPlaceSt)) {
            goingPlace.setError("أدخل مكان الذهاب");
            focusView = goingPlace;
            cancel = true;
        }

        if (TextUtils.isEmpty(returnPlaceSt)) {
            returnPlace.setError("أدخل مكان العودة");
            focusView = returnPlace;
            cancel = true;
        }

        if (TextUtils.isEmpty(goingTimeSt)) {
            goingTime.setError("أدخل وقت الذهاب");
            focusView = goingTime;
            cancel = true;
        }

        if (TextUtils.isEmpty(returnTimeSt)) {
            returnTime.setError("أدخل وقت العودة");
            focusView = returnTime;
            cancel = true;
        }
        if (TextUtils.isEmpty(personNumberSt)) {
            personsNumber.setError("أدخل عدد الأشخاص");
            focusView = personsNumber;
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
            createContractRequest.setClient_id(SessionHelper.getUserSession(getContext()).getId());
            createContractRequest.setStart_date(startDate);
            createContractRequest.setEnd_date(endDate);
            createContractRequest.setTo_place_name(gonigPlaceSt);
            createContractRequest.setFrom_place_name(returnPlaceSt);
            createContractRequest.setGo_time(goingTimeSt);
            createContractRequest.setBack_time(returnTimeSt);
            createContractRequest.setPassengers_number(Integer.parseInt(personNumberSt));
            createContractRequest.setDays(gson.toJson(daysModel));
            AddContractActivity.createContract(getActivity());
        }
    }



    @OnFocusChange(R.id.ed_startplace)
    void goinPlace(boolean focused){
        if(focused) {
            fireLocationPicker(0);
        }
    }

    @OnFocusChange(R.id.ed_endplace)
    void returnPlace(boolean focused){
        if(focused) {
            fireLocationPicker(1);
        }
    }



    @OnClick(R.id.save_btn)
    void save (){
        createContract();
        Gson gson =new Gson();
        Log.e("Data ",gson.toJson(createContractRequest));
    }







    @Override
    public void onPlaceSelected(LatLng location, String locationName) {
        switch (selection){
            case 0 : {
                goingPlace.setText(locationName);
                createContractRequest.setTo_place_lat(location.latitude);
                createContractRequest.setTo_place_long(location.longitude);
                break;
            }
            case 1 : {
                returnPlace.setText(locationName);
                createContractRequest.setFrom_place_lat(location.latitude);
                createContractRequest.setFrom_place_long(location.longitude);
                break;
            }
        }
    }
}
