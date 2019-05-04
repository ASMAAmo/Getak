package getak.app.com.getak.Activites;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Events.ContractCreationStepsEvent;
import getak.app.com.getak.Fragments.AddingContractSteps.ConfirmFragment;
import getak.app.com.getak.Fragments.AddingContractSteps.ContractDaysFragment;
import getak.app.com.getak.Fragments.AddingContractSteps.DriversListFragment;
import getak.app.com.getak.Model.Requests.CreateContractRequest;
import getak.app.com.getak.Model.Responses.AllDriversResponse.Datum;
import getak.app.com.getak.Model.Responses.ContractsType.Type;
import getak.app.com.getak.Model.Responses.Result;
import getak.app.com.getak.Presenters.ContractsPresenter;
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;
import getak.app.com.getak.Views.ContractsView;

public class AddContractActivity extends BaseActivity {
    public static final int CONTRACT_DAYS=0;
    public static final int DRIVERS_LIST=1;
    public static final int CONFIRM=2;
    public static int pageIndex=-1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView title;
    ContractDaysFragment contractDaysFragment;
    DriversListFragment driversListFragment;
    ConfirmFragment confirmFragment;
    public static CreateContractRequest createContractRequest;
    public static KProgressHUD dialog;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //register EventBus (this activity is a subscriber)
        EventBus.getDefault().register(this);
        contractDaysFragment =new ContractDaysFragment();
        driversListFragment=new DriversListFragment();
        confirmFragment=new ConfirmFragment();
        switchToPage(CONTRACT_DAYS);
        createContractRequest=new CreateContractRequest();
        dialog=new KProgressHUD(this);
    }





    public void switchToPage(int page) {
        FragmentTransaction transaction = getTransaction();
        switch (page) {
            case CONTRACT_DAYS:
                transaction.replace(R.id.contract_creation_container, contractDaysFragment);
                pageIndex=CONTRACT_DAYS;
                toolbar.setVisibility(View.GONE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                transaction.commit();
                break;

            case DRIVERS_LIST :
                transaction.replace(R.id.contract_creation_container, driversListFragment);
                toolbar.setVisibility(View.VISIBLE);
                title.setText("أختر قائد");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                pageIndex=DRIVERS_LIST;
                transaction.commit();
                break;

            case CONFIRM:
                transaction.replace(R.id.contract_creation_container, confirmFragment);
                toolbar.setVisibility(View.VISIBLE);
                title.setText("عقد جديد");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                pageIndex=CONFIRM;
                transaction.commit();
                break;
        }
    }







    @Override
    public void onBackPressed() {
       switch (pageIndex){
           case CONTRACT_DAYS : {
               super.onBackPressed();
               break;
           }
           case DRIVERS_LIST : {
               switchToPage(CONTRACT_DAYS);
               break;
           }
           case CONFIRM : {
               switchToPage(DRIVERS_LIST);
               break;
           }
       }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (pageIndex){
            case CONTRACT_DAYS : {
                switchToPage(CONTRACT_DAYS);
                break;
            }
            case DRIVERS_LIST : {
                switchToPage(DRIVERS_LIST);
                break;
            }
            case CONFIRM : {
                switchToPage(CONFIRM);
                break;
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregister event bus here
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ContractCreationStepsEvent event){
        if (event.isSucess()){
            switch (event.getPageIndex()){
                case CONTRACT_DAYS :{
                    switchToPage(CONTRACT_DAYS);
                    break;
                }
                case DRIVERS_LIST :{
                    switchToPage(DRIVERS_LIST);
                    createContractRequest.setPeriod(((Type)event.getPayload()).getId());
                    break;
                }
                case CONFIRM :{
                    switchToPage(CONFIRM);
                    createContractRequest.setDriver_id(((Datum)event.getPayload()).getId());
                    break;
                }
            }
        }
    }

    public static void createContract(final Context context){
        ContractsPresenter.createContract(context, createContractRequest, new ContractsView() {
            @Override
            public void onSuccess(Object obj) {
               Toast.makeText(context, ((Result<Object>)obj).getMessage()+"",Toast.LENGTH_LONG).show();
                ((Activity)context).finish();
            }

            @Override
            public void onFailed(String err) {
                Toast.makeText(context, err,Toast.LENGTH_LONG).show();
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
        });
    }


}
