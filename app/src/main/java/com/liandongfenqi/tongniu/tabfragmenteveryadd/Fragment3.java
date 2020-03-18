package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.liandongfenqi.tongniu.tabfragmenteveryadd.activity.SufaceActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tongniu on 2017/8/11.
 */

public class Fragment3 extends LazyLoadFragment {
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.rg_first)
    RadioGroup rg_first;
    @BindView(R.id.cannot_play)
    RadioButton cannot_play;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.pay_issue)
    RadioButton payIssue;
    @BindView(R.id.force_closure)
    RadioButton forceClosure;
    @BindView(R.id.play_pause)
    RadioButton playPause;
    @BindView(R.id.rg_second)
    RadioGroup rgSecond;
    @BindView(R.id.other_issues)
    RadioButton otherIssues;
    @BindView(R.id.rg_third)
    RadioGroup rgThird;

    @Override
    protected int setContentView() {
        return R.layout.fragment;
    }

    @Override
    protected void lazyLoad() {
        textview.setText("cccccccc");
        String message = "Fragment3" + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据") + ">>>>>>>>>>>>>>>>>>>";
        showToast(message);
        Log.d(TAG, message);
        cannot_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, SufaceActivity.class));
            }
        });

    }

    @Override
    protected void stopLoad() {
        Log.d(TAG, "Fragment3" + "已经对用户不可见，可以停止加载数据");
    }

    protected void getimageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
     /*   intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框 intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", crop);// 输出图片大小
        intent.putExtra("outputY", crop);*/
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("aaaaaaa", "aaaaaaaaaaaaa" + requestCode + "resultCode" + resultCode);
        if (requestCode == 0) {
            Log.i("aaaaaaa", "aaaaaaaaaaaaa" + data);
            Log.i("aaaaaaa", "aaaaaaaaaaaaa" + data);
        }
    }

}
