package getak.app.com.getak.Dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;

import getak.app.com.getak.R;

public class DriverButtomDialog extends BottomSheetDialog {

    public DriverButtomDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.driver_dialog_button_layout);
    }

}
