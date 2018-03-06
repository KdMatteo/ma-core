package cn.zucc.pump.utils;

import java.util.List;

/**
 * @Author szh
 * @Email 754456231@qq.com
 * @Time 2018-02-06 19:57
 * Description:
 **/
public class TextUtil {
    /***
     * 判断string 是否是空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        if(string !=null && !string.equals("")){
            return false;
        }
        return true;
    }

    /***
     * 判断list是否是空
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(List<T> list){
        if(list != null && list.size() > 0){
            return false;
        }
        return true;
    }

}
