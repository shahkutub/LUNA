package com.deveyesgroup.luna.Utils;


/**
 * Created by Shohel on 2/15/2016.
 */
public class AppConstant {
    public static final String[] FACEBOOK_PERMISSION = {"email", "user_about_me", "read_stream", "user_photos", "public_profile" };
    public static String COLOR_MAIN = "#A551D0";

    public static final String COLOR_DEFAULT_MAIN = "#A551D0";
    public static final String COLOR_DEFAULT_SECONDARY = "#FFFFFF";

    public static final String COLOR_SETTINGS = "#4C436E";
    public static final String COLOR_SHARE = "#0054A5";
    public static final String BTN_COLOR_ORIGIN = "#B166D6";
    public static final int SHOW_PRO_DETAIL_CODE = 2001;
    public static String isFromSplash="isFromSplash";
    public static String GCMID="GCMID";
    public static String TOKEN="TOKEN";
    public static String fullName="fullName";
    public static String interstResponse="interstResponse";
    public static String getStaticMap(String lat,String lng){
    return "https://maps.googleapis.com/maps/api/staticmap?size=600x300&zoom=13&markers=color:red|"+lat+","+lng+"&center="+lat+","+lng;
    }
    public static String loginRespone="loginRespone";
    public static String friendId="";
    public static final String LOVE_LANE_BROADCAST_ACTION = "LOVE_LANE_BROADCAST_ACTION";
    public static final String GCM_MESSAGE_INTENT = "GcmMessageIntent";
    public static boolean isBubbleHeadClick=false;
    public static String friendLat="",frendLon="";
    public static String notificationSettings="";
    public static String vibrationSettings="";
    public static boolean isMyProfile=false;
}
