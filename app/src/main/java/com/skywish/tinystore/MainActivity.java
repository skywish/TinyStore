package com.skywish.tinystore;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skywish.tinystore.adapter.FoundAdapt;
import com.skywish.tinystore.adapter.LostAdapt;
import com.skywish.tinystore.bean.Found;
import com.skywish.tinystore.bean.Lost;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by skywish on 2015/3/17.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ProgressBar progressBar;
    private TextView tv_no;
    private ListView list_lost;
    private Button btn_add;
    private Button layout_found;
    private Button layout_lost;
    private LostAdapt lostAdapter;
    private FoundAdapt foundAdapt;
    private LinearLayout layout_all;
    private PopupWindow morePop;
    private TextView tv_lost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_lost = (TextView) findViewById(R.id.tv_lost);
        list_lost = (ListView) findViewById(R.id.list_lost);
        btn_add = (Button) findViewById(R.id.btn_add);
        layout_all = (LinearLayout) findViewById(R.id.layout_all);

        layout_all.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        queryLost();
        tv_lost.setTag("Lost");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_all:
                showListPop();
                break;
            case R.id.layout_found:
                changeTitle(v);
                morePop.dismiss();
//                queryFounds();
                break;
            case R.id.layout_lost:
                changeTitle(v);
                morePop.dismiss();
//                queryLost();
                break;
            case R.id.btn_add:
//                AddActivity.actionStart(MainActivity.this, tv_lost.getTag().toString());
                Intent intent = new Intent(this, AddActivity.class);
                intent.putExtra("from", tv_lost.getTag().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void queryLost() {
        BmobQuery<Lost> query = new BmobQuery<Lost>();
        query.order("-createdAt");      //按照时间降序
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(this, new FindListener<Lost>() {
            @Override
            public void onSuccess(List<Lost> losts) {
                if (losts == null || losts.size() == 0) {
                    showErrorView(0);
                    return;
                }
                progressBar.setVisibility(View.GONE);
                lostAdapter = new LostAdapt(getApplicationContext(),
                        R.layout.item_list, losts);
                list_lost.setAdapter(lostAdapter);
            }

            @Override
            public void onError(int code, String arg0) {
                showErrorView(0);
            }
        });
    }

    public void queryFounds() {
        BmobQuery<Found> query = new BmobQuery<Found>();
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Found>() {
            @Override
            public void onSuccess(List<Found> founds) {
                if (founds == null || founds.size() == 0) {
                    showErrorView(0);
                    return;
                }
                progressBar.setVisibility(View.GONE);
                foundAdapt = new FoundAdapt(getApplicationContext(),
                        R.layout.item_list, founds);
                list_lost.setAdapter(foundAdapt);
            }

            @Override
            public void onError(int code, String arg0) {
                showErrorView(0);
            }
        });
    }

    private void showErrorView(int tag) {
        progressBar.setVisibility(View.GONE);
        list_lost.setVisibility(View.GONE);
        tv_no.setVisibility(View.VISIBLE);
        if (tag == 0) {
            tv_no.setText("list_no_data_lost");
        } else {
            tv_no.setText("list_no_data_found");
        }
    }

    private void showListPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_list, null);

        layout_found = (Button) view.findViewById(R.id.layout_found);
        layout_lost = (Button) view.findViewById(R.id.layout_lost);
        layout_found.setOnClickListener(this);
        layout_lost.setOnClickListener(this);

        //PopupWindow(View contentView, int width, int height, boolean focusable)
        morePop = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        //当设置为popuWindow.setFocusable(true)的时候，点击外面和Back键才会消失。
        morePop.setFocusable(true);
        morePop.setTouchable(true);
        morePop.setOutsideTouchable(true);
        /**
         * 需要顺利让PopUpWindow dimiss（即点击PopuWindow之外的地方此或者back键PopuWindow会消失）；
         * PopUpWindow的背景不能为空。必须在popuWindow.showAsDropDown(v);
         * 或者其它的显示PopuWindow方法之前设置它的背景不为空：
         */
        morePop.setBackgroundDrawable(new ColorDrawable(0));
        morePop.showAsDropDown(layout_all, 0, 0);
    }

    private void changeTitle(View v) {
        switch (v.getId()) {
            case R.id.layout_found:
                tv_lost.setTag("Found");
                tv_lost.setText("Found");
                break;
            case R.id.layout_lost:
                tv_lost.setTag("Lost");
                tv_lost.setText("Lost");
                break;
            default:
                break;
        }

    }

}
