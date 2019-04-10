package demo.sdk;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookieUtils 主要是控制AI 的事件
 * Created by :Guozhihua
 * Date： 2017/8/23.
 */
public class CookieUtils {

    private static final String path = "/";


    /**
     * 设置cookie
     *
     * @param response
     * @param key
     * @param val
     */
    public static void setCookie(HttpServletResponse response, String key, String val, boolean readOnly, int time) {
        Cookie cookie = new Cookie(key, val);
        cookie.setMaxAge(time);
        cookie.setHttpOnly(readOnly);
        cookie.setPath(path);
        response.addCookie(cookie);

    }

    /**
     * 遍历获取指定路径下的cookie key-val
     *
     * @param cookies
     * @return
     */
    public static String getCookieValue(Cookie[] cookies,String cookieName) {
        Map<String, String> cookieMaps = getRequestCookie(cookies);
        return cookieMaps.get(cookieName);
    }


    /**
     * 遍历获取指定路径下的cookie key-val
     *
     * @param cookies
     * @return
     */
    public static Map<String, String> getRequestCookie(Cookie[] cookies) {
        Map<String, String> cookieMaps = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie == null) {
                    continue;
                }
                cookieMaps.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMaps;
    }

}
