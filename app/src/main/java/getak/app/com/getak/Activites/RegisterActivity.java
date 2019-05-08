package getak.app.com.getak.Activites;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import getak.app.com.getak.BaseActivity;
import getak.app.com.getak.Fragments.RegisterFragments.DriverRegisterFragment;
import getak.app.com.getak.Fragments.RegisterFragments.UserRegisterFragment;
import getak.app.com.getak.R;
import getak.app.com.getak.Utils.ImageUtil;

import static getak.app.com.getak.Fragments.RegisterFragments.DriverRegisterFragment.driverAvatarSelectedFilePath;
import static getak.app.com.getak.Fragments.RegisterFragments.DriverRegisterFragment.driverCarSelectedFilePath;
import static getak.app.com.getak.Fragments.RegisterFragments.DriverRegisterFragment.driverIdSelectedFilePath;
import static getak.app.com.getak.Fragments.RegisterFragments.DriverRegisterFragment.driverLicenceSelectedFilePath;
import static getak.app.com.getak.Fragments.RegisterFragments.UserRegisterFragment.selectedFilePath;

public class RegisterActivity extends BaseActivity{
    public static final int USER_REGISTER = 0;
    public static final int DRIVER_REGISTER = 1;
    int pageIndex=0;
    @BindView(R.id.reg_type_tab)
    TabLayout regTypeSwitch;
    private static final int PICK_CLIENT_AVATAR = 2;
    public static final int PICK_DRIVER_AVATAR = 101;
    public static final int PICK_DRIVER_ID = 102;
    public static final int PICK_DRIVER_LICENCE_ID = 103;
    public static final int PIC_DRIVER_CAR = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        regTypeSwitch.addTab(regTypeSwitch.newTab().setText(R.string.cust));
        regTypeSwitch.addTab(regTypeSwitch.newTab().setText(R.string.driv));
        switchToPage(USER_REGISTER,null,"");
        regTypeSwitch.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0 : {
                        switchToPage(USER_REGISTER,null,"");
                        break;
                    }

                    case 1 : {
                        switchToPage(DRIVER_REGISTER,null,"");
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //Switch fragments
    public void switchToPage(int page, Bundle bundle, String name) {
        FragmentTransaction transaction = getTransaction();
        switch (page) {
            case USER_REGISTER : {
                UserRegisterFragment userRegisterFragment =new UserRegisterFragment();
                transaction.replace(R.id.register_container, userRegisterFragment);
                pageIndex = USER_REGISTER;
                transaction.commit();
                break;
            }

            case DRIVER_REGISTER: {
                DriverRegisterFragment driverRegisterFragment =new DriverRegisterFragment();
                transaction.replace(R.id.register_container, driverRegisterFragment);
                pageIndex = DRIVER_REGISTER;
                transaction.commit();
                break;
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_CLIENT_AVATAR){
            if(resultCode==RESULT_OK){
                Uri selectedFileUri = data.getData();
                selectedFilePath = ImageUtil.getPath(this,selectedFileUri);
                Log.i("TAG","Selected File Path:" + selectedFilePath);
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                Bitmap bitmap=null;
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(imgDecodableString);
                UserRegisterFragment.avatar.setImageBitmap(bitmap);
            }
        }

        if(requestCode==PICK_DRIVER_AVATAR){
            if(resultCode==RESULT_OK){
                Uri selectedFileUri = data.getData();
                driverAvatarSelectedFilePath = ImageUtil.getPath(this,selectedFileUri);
                Log.i("TAG","Selected File Path:" + driverAvatarSelectedFilePath);
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                Bitmap bitmap=null;
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(imgDecodableString);
                DriverRegisterFragment.driver_avatar.setImageBitmap(bitmap);
            }
        }

        if(requestCode==PICK_DRIVER_ID){
            if(resultCode==RESULT_OK){
                Uri selectedFileUri = data.getData();
                driverIdSelectedFilePath = ImageUtil.getPath(this,selectedFileUri);
                Log.i("TAG","Selected File Path:" + driverIdSelectedFilePath);
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                Bitmap bitmap=null;
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(imgDecodableString);
                DriverRegisterFragment.driverPersonalIdPic.setImageBitmap(bitmap);
            }
        }

        if(requestCode==PICK_DRIVER_LICENCE_ID){
            if(resultCode==RESULT_OK){
                Uri selectedFileUri = data.getData();
                driverLicenceSelectedFilePath = ImageUtil.getPath(this,selectedFileUri);
                Log.i("TAG","Selected File Path:" + driverLicenceSelectedFilePath);
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                Bitmap bitmap=null;
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(imgDecodableString);
                DriverRegisterFragment.driverLicenceIdPic.setImageBitmap(bitmap);
            }
        }

        if(requestCode==PIC_DRIVER_CAR){
            if(resultCode==RESULT_OK){
                Uri selectedFileUri = data.getData();
                driverCarSelectedFilePath = ImageUtil.getPath(this,selectedFileUri);
                Log.i("TAG","Selected File Path:" + driverCarSelectedFilePath);
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                Bitmap bitmap=null;
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(imgDecodableString);
                DriverRegisterFragment.driverCar.setImageBitmap(bitmap);
            }
        }
    }
}
