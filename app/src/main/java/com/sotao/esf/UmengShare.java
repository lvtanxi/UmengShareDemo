package com.sotao.esf;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.DrawableRes;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/*
* User: 吕勇
* Date: 2016-07-20
* Time: 15:19
* Description:umeng分享辅助类
*/
public class UmengShare {

    private Activity mActivity;
    private String mTitle;
    private String mText;
    private String mTargetUrl;
    private UMShareListener mShareListener;
    private int mIcon;
    private UMImage mUMImage;


    /**
     * UmengShare的初始化
     * @param activity Activity实体
     */
    public UmengShare(Activity activity) {
        mActivity = activity;
        mIcon = R.mipmap.ic_launcher;
    }

    /**
     * 绑定TargetUrl
     * @param targetUrl 分享出去的连接
     */
    public UmengShare bindTargetUrl(String targetUrl) {
        mTargetUrl = targetUrl;
        return this;
    }

    /**
     * 绑定title
     * @param title 分享出去的title(注意新浪分享的title是不显示的，URL链接只能加在分享文字后显示，并且需要确保withText()不为空)
     */
    public UmengShare bindTitle(String title) {
        this.mTitle = title;
        return this;
    }

    /**
     * 绑定分享内容
     * @param text 分享内容
     */
    public UmengShare bindText(String text) {
        this.mText = text;
        return this;
    }

    /**
     * 绑定分享监听
     * @param shareListener UMShareListener
     */
    public UmengShare bindUMShareListener(UMShareListener shareListener) {
        this.mShareListener = shareListener;
        return this;
    }

    /**
     * 绑定分享icon
     * @param icon icon
     */
    public UmengShare bindIcon(@DrawableRes int icon) {
        this.mIcon = icon;
        return this;
    }

    /**
     * 默认分享
     */
    public void share() {
        new ShareAction(mActivity).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SMS)
                .withTitle(mTitle)
                .withText(mText)
                .withMedia(getUMImage())
                .withTargetUrl(mTargetUrl)
                .setCallback(mShareListener)
                .open();
    }

    /**
     * 微信分享
     */
    public void shareWeixin() {
        shareOne(SHARE_MEDIA.WEIXIN);
    }

    /**
     * 微信朋友圈分享
     */
    public void shareWeixinCircle() {
        shareOne(SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    /**
     * QQ分享
     */
    public void shareQQ() {
        shareOne(SHARE_MEDIA.QQ);
    }

    /**
     * QQ空间分享
     */
    public void shareQZone() {
        shareOne(SHARE_MEDIA.QZONE);
    }

    /**
     * 新浪分享(注意新浪分享的title是不显示的，URL链接只能加在分享文字后显示，并且需要确保withText()不为空)
     */
    public void shareSina() {
        shareOne(SHARE_MEDIA.SINA);
    }

    /**
     * 短信分享
     */
    public void shareSms() {
        shareOne(SHARE_MEDIA.SMS);
    }

    /**
     * 获取分享出去的ioc
     *
     * @return UMImage
     */
    private UMImage getUMImage() {
        if (null == mUMImage)
            mUMImage = new UMImage(mActivity, mIcon);
        return mUMImage;
    }


    /**
     * 单独分享
     *
     * @param shareMedia 分享平台
     */
    private void shareOne(SHARE_MEDIA shareMedia) {
        new ShareAction(mActivity).setPlatform(shareMedia)
                .setCallback(mShareListener)
                .withText(mText)
                .withTitle(mTitle)
                .withMedia(getUMImage())
                .withTargetUrl(mTargetUrl)
                .share();
    }

    /**
     * 初始化umeng账号信息
     */
    public static void initUmengData() {
        initUmengData(true);
    }

    /**
     * 初始化umeng账号信息
     *
     * @param showDefDialog 是否显示默认dialog
     */
    public static void initUmengData(boolean showDefDialog) {
        //前面是Key后面是Secret
        PlatformConfig.setWeixin("wxf2cfa8d26914e7b3", "ec154943ee899ba5bf884830473d1669");
        PlatformConfig.setQQZone("1104535707", "XUOjybvsCDS5UL5y");
        //PlatformConfig.setSinaWeibo("2620243246", "da12dcec2d15b755d3887b73f1e2d8fe");
        Config.dialogSwitch = showDefDialog;
        //关闭log和toast
       /* Log.LOG = false;
        Config.IsToastTip = false;*/
    }

    /**
     * 新浪认证回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void sinaAuth(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(mActivity).onActivityResult(requestCode, resultCode, data);
    }

}
