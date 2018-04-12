


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName           : Ognl
 * @Description         : Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写
 *  					  Ognl访问静态方法的表达式为: @class@method(args)
 * @Author              : Jason@senthink.com
 * @CreationDate        : 2015年9月18日 上午10:07:01
 * @Version             : v0.0.1
 *
 */
public class Ognl {
    
    /**
     * 屏蔽构造器
     */
    private Ognl(){
    }
    /**
     * 可以用于判断String,Map,Collection,Array是否为空
     * @param o 需要判断的对象
     * @return 对象是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        if(o == null) {
            return true;
        }

        if(o instanceof String) {
            if(((String)o).length() == 0){
                return true;
            }
        } else if(o instanceof Collection) {
            if(((Collection)o).isEmpty()){
                return true;
            }
        } else if(o.getClass().isArray()) {
            if(Array.getLength(o) == 0){
                return true;
            }
        } else if(o instanceof Map) {
            if(((Map)o).isEmpty()){
                return true;
            }
          else if(o instanceof Date) {
        	if(((Collection)o).isEmpty()){
        		return true;
        	}
        
        }else {
            return false;
        }
    }
		return false;
  }
    /**
     * 可以用于判断 Map,Collection,String,Array是否不为空
     * @param o 对象 
     * @return 对象是否不为空
     */ 
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
    
    /**
     * 判断对象是否不为null
     * @param o 对象
     * @return 是否不为null
     */
    public static boolean isNotBlank(Object o) {
        return !isBlank(o);
    }
    
    /**
     * 判断对象是否为Number类型
     * @param o 对象
     * @return 是否为Number类型
     */
    public static boolean isNumber(Object o) {
        if(o == null) {
            return false;
        }
        if(o instanceof Number) {
            return true;
        }
        if(o instanceof String) {
            String str = (String)o;
            if(str.length() == 0) {
                return false;
            }
            if(str.trim().length() == 0) {
                return false;
            }
            return StringUtils.isNumeric(str);
        }
        return false;
    }
    
    /**
     * 对象是否为null
     * @param o 对象
     * @return 是否为null
     */
    public static boolean isBlank(Object o) {
        if(o == null){
            return true;
        }
        if(o instanceof String) {
            String str = (String)o;
            return isBlank(str);
        }
        return false;
    }

    /**
     * 判断String是否为null或0长度
     * @param str 字符串
     * @return 是否为null或0长度
     */
    public static boolean isBlank(String str) {
        if(str == null || str.length() == 0) {
            return true;
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断Str与targetStr是否相同
     * @param str
     * @param targetStr
     * @return
     */
    public static boolean equalsStr(String str, String targetStr) {
        return StringUtils.equalsIgnoreCase(str,targetStr);
    }

    public static boolean equalsNumber(Integer n1, Integer n2) {
        return ObjectUtils.equals(n1,n2);
    }

}

