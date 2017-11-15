package com.hanergy.sharesample;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mob.MobSDK;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobSDK.init(getApplicationContext(), "22449a07fdeea", "9d0e39fdcd7a00d51f6932343593bc39");
//        showShare();
        //初始化权限
        initPermission();
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }


    public void share( View view ) {
        showShare();
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
//        oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("share");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.png");//确保SDcard下面存在此张图片
//        oks.setImageUrl("https://static2.yulinapp.com/2016/1208/06/58489313afd774f276ab.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        //是否显示编辑页面
        oks.setSilent(true);

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare( Platform platform,
                                 cn.sharesdk.framework.Platform.ShareParams paramsToShare ) {
                // TODO Auto-generated method stub
                if (platform.getName().equals("SinaWeibo")) {
//                    paramsToShare.setUrl(null);
//                    paramsToShare.setText("分享文本链接  http://www.baidu.com  ");

                } else if (platform.getName().equals("Wechat")) {
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);

                }

            }
        });

        // 启动分享GUI
        oks.show(this);
    }

}
