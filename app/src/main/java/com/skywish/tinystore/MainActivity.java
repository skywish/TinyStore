package com.skywish.tinystore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skywish.tinystore.adapter.MyAdapter;
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
    private MyAdapter lostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        tv_no = (TextView) findViewById(R.id.tv_no);
        list_lost = (ListView) findViewById(R.id.list_lost);
        btn_add = (Button) findViewById(R.id.btn_add);

        queryLost();
    }

    @Override
    public void onClick(View v) {

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
                lostAdapter = new MyAdapter(getApplicationContext(),
                        R.layout.item_list, losts);
                list_lost.setAdapter(lostAdapter);
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
}
