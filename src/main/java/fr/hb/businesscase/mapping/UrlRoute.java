package fr.hb.businesscase.mapping;

public class UrlRoute {

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";


    public static final String BOOKING = "/booking";
    public static final String BOOKING_NEW = BOOKING+"/new";
    public static final String BOOKING_SHOW = BOOKING+"/{id}";
    public static final String BOOKING_ACCEPT = BOOKING_SHOW+"/accept";
    public static final String BOOKING_REFUSE = BOOKING_SHOW+"/refuse";

    public static final String REVIEW = "/review";
    public static final String REVIEW_NEW = REVIEW+"/new";
    public static final String REVIEW_SHOW = REVIEW+"/{id}";

    public static final String USER = "/user";
    public static final String USER_PROFILE = USER+"/{id}";
    public static final String USER_EDIT = USER_PROFILE+"/edit";
    public static final String USER_VERIFY = USER + "/verify";

    public static final String POWER = "/power";
    public static final String POWER_NEW = POWER+"/new";

    public static final String STATION = "/station";
    public static final String STATION_NEW = STATION+"/new" ;
}
