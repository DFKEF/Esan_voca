package com.gunho0406.voca;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private CustomDialogListener customDialogListener;

    public CustomDialog(@NonNull Context context) {
        super(context);
}


    interface CustomDialogListener{
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_dialog);



        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.


        // 액티비티의 타이틀바를 숨긴다.

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        Window window = getWindow();
        window.setAttributes(lp);

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        Button check = (Button) findViewById(R.id.check);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check :
                dismiss();
                break;
        }
    }
}
