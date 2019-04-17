package getak.app.com.getak.Activites;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import getak.app.com.getak.R;
import getak.app.com.getak.Session.SessionHelper;

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
            switchToPage(event.getPageIndex());
        }
    }
}
