package com.sotao.esf;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.DrawableRes;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * 友盟分享
 */
public class UmengShare {

	private Activity mActivity;
	private String mTitle;
	private String mText;
	private String mTargetUrl;
	private UMShareListener mShareListener;
	private int mIcon;
	private UMImage mUMImage;



	public UmengShare(Activity activity) {
		mActivity = activity;
		mIcon=R.mipmap.ic_launcher;
	}

	public UmengShare bindTargetUrl(String targetUrl){
		mTargetUrl=targetUrl;
		return this;
	}

	public UmengShare bindTitle(String title){
		this.mTitle=title;
		return this;
	}

	public UmengShare bindText(String text){
		this.mText=text;
		return this;
	}

	public UmengShare bindUMShareListener(UMShareListener shareListener){
		this.mShareListener=shareListener;
		return this;
	}

	public UmengShare bindIcon(@DrawableRes int icon){
		this.mIcon=icon;
		return this;
	}

	public void share(){
		new ShareAction(mActivity).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SMS)
				.withTitle(mTitle)
				.withText(mText)
				.withMedia(getUMImage())
				.withTargetUrl(mTargetUrl)
				.setCallback(mShareListener)
				.open();
	}

	public void shareWeixin(){
		shareOne(SHARE_MEDIA.WEIXIN);
	}
	public void shareWeixinCircle(){
		shareOne(SHARE_MEDIA.WEIXIN_CIRCLE);
	}
	public void shareQQ(){
		shareOne(SHARE_MEDIA.QQ);
	}
	public void shareQZone(){
		shareOne(SHARE_MEDIA.QZONE);
	}
	public void shareSina(){
		shareOne(SHARE_MEDIA.SINA);
	}
	public void shareSms(){
		shareOne(SHARE_MEDIA.SMS);
	}

	private UMImage getUMImage() {
		if(null==mUMImage)
			mUMImage=new UMImage(mActivity,mIcon);
		return mUMImage;
	}

	private void shareOne(SHARE_MEDIA shareMedia){
		new ShareAction(mActivity).setPlatform(shareMedia)
				.setCallback(mShareListener)
				.withText(mText)
				.withTitle(mTitle)
				.withMedia(getUMImage())
				.withTargetUrl(mTargetUrl)
				.share();
	}

	public static void initUmengData(){
		//前面是Key后面是Secret
		PlatformConfig.setWeixin("wxf2cfa8d26914e7b3", "ec154943ee899ba5bf884830473d1669");
		PlatformConfig.setQQZone("1104535707", "XUOjybvsCDS5UL5y");
		PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
		//Config.dialogSwitch=false;
	}

	public void auth(int requestCode, int resultCode, Intent data){
		UMShareAPI.get(mActivity).onActivityResult(requestCode, resultCode, data);
	}

}
