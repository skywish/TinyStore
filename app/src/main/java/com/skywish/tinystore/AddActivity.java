package com.skywish.tinystore;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skywish.tinystore.bean.Lost;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by skywish on 2015/3/17.
 */
public class AddActivity extends BaseActivity implements View.OnClickListener{
    private EditText edit_title, edit_phone, edit_describe;
    private Button btn_back, btn_true;
    private TextView tv_add;

    String old_title = "";
    String old_describe = "";
    String old_phone = "";

    String title = "";
    String describe = "";
    String phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        tv_add = (TextView) findViewById(R.id.tv_add);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_describe = (EditText) findViewById(R.id.edit_describe);
        edit_title = (EditText) findViewById(R.id.edit_title);

        btn_back.setOnClickListener(this);
        btn_true.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_true:
                addByType();
                break;
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void addByType() {
        title = edit_title.getText().toString();
        phone = edit_phone.getText().toString();
        describe = edit_describe.getText().toString();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请填写标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(describe)) {
            Toast.makeText(this, "请填写描述", Toast.LENGTH_SHORT).show();
            return;
        }
        addLost();
    }

    private void addLost() {
        Lost lost = new Lost();
        lost.setDescribe(describe);
        lost.setPhone(phone);
        lost.setTitle(title);

        lost.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(AddActivity.this, "失误信息添加成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(AddActivity.this, "失误信息添加失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
