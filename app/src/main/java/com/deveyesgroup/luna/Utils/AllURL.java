package com.deveyesgroup.luna.Utils;

import android.annotation.SuppressLint;

import java.util.Vector;

@SuppressLint("DefaultLocale")
public class AllURL {

	private static String getcommonURLWithParamAndAction(String action,
														 Vector<KeyValue> params) {

		if (params == null || params.size() == 0) {
			return BaseURL.HTTP + action;
		} else {
			String pString = "";

			for (final KeyValue obj : params) {

				pString += obj.getKey().trim() + "=" + obj.getValue().trim()
						+ "&";
			}

			if (pString.endsWith("&")) {
				pString = pString.subSequence(0, pString.length() - 1)
						.toString();
			}

			return BaseURL.HTTP + action + "?" + UrlUtils.encode(pString);
		}
	}
	public static String getRegisterUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("register", temp);

	}
	public static String getLoginUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("login", temp);

	}

	public static String forgetPosswordUrl(String email) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("email",email));
		return getcommonURLWithParamAndAction("forgot-password", temp);

	}

	public static String changePosswordUrl(String oldpassword,String newPassword) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("oldpassword",oldpassword));
		temp.add(new KeyValue("newpassword",newPassword));
		return getcommonURLWithParamAndAction("change-password", temp);

	}

	public static String getUsrListUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("user-list", temp);

	}
	public static String addFriendUrl(String token,String UserId,String matched_id,String  passed) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		temp.add(new KeyValue("UserId",UserId));
		temp.add(new KeyValue("matched_id",matched_id));
		temp.add(new KeyValue("passed",passed));
		return getcommonURLWithParamAndAction("add-friend", temp);

	}

	public static String getChatListUrl(String token,String partner_id) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		temp.add(new KeyValue("partner_id",partner_id));
		return getcommonURLWithParamAndAction("chat-list", temp);

	}
	public static String sentMessagetUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));;
		return getcommonURLWithParamAndAction("send-msg", temp);

	}

	public static String getFriendUrl(String token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));;
		return getcommonURLWithParamAndAction("friend-list", temp);

	}

	public static String getSettingsUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("get-settings", temp);

	}
	public static String UpdateSettingsUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("settings", temp);

	}
	public static String getDeleteAccountUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("delete-account", temp);

	}

	public static String getInterestListUrl() {
		final Vector<KeyValue> temp = new Vector();
		return getcommonURLWithParamAndAction("interested-list", temp);

	}
	public static String getEditProfileUrl(String  token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("edit-profile", temp);

	}

	public static String getMessageList(String  token) {
		final Vector<KeyValue> temp = new Vector();
		temp.add(new KeyValue("token",token));
		return getcommonURLWithParamAndAction("msg-list", temp);

	}

}