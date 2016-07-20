package com.sotao.esf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MainActivity extends AppCompatActivity {
    UmengShare mUmengShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUmengShare = new UmengShare(this);
    }

    public void onShare(View view) {
        mUmengShare.bindText("发出啊时代发生的发生的借款方红啊的还是法律监督还是佛教啊黑色的礼服就不哈了多少速度发我说的发生的发生的")
                .bindTargetUrl("http://baidu.com")
                .bindUMShareListener(new UMShareListener() {
                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.d("MainActivity","成功了"+share_media.toString());
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.d("MainActivity", throwable.getMessage());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.d("MainActivity","取消了"+share_media.toString());
                    }
                }).share();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mUmengShare.auth(requestCode,resultCode,data);
    }
}
