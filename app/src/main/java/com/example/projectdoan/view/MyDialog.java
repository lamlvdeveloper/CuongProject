package com.example.projectdoan.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectdoan.R;


public class MyDialog extends Dialog {
    private ImageView imgDialog;
    private TextView txtName;

    public MyDialog(Context context, Bitmap position, String name) {
        super(context);
        setContentView(R.layout.dialog_image);
        imgDialog = (ImageView) findViewById(R.id.img_dialog);
        txtName = (TextView) findViewById(R.id.txt_name);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        imgDialog.setImageBitmap(position);
        txtName.setText(name);
    }
}
