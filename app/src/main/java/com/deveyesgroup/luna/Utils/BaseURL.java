package com.deveyesgroup.luna.Utils;


public class BaseURL {
	
//	public static String HTTP = "http://lovelane.aapbd.xyz/api/v1/";
	public static String HTTP = "http://love-lane.asourcer.com/api/v1/";


	/**
	 * @return the hTTP
	 */
	public static String makeHTTPURL(String param) {
		return BaseURL.HTTP + UrlUtils.encode(param);
	}

	/**
	 * @param hTTP
	 *            the hTTP to set
	 */
	public static void setHTTP(final String hTTP) {
		BaseURL.HTTP = hTTP;
	}

}
