package org.songdan.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author Songdan
 * @date 2016/5/25
 */
public class StringUtils{

    /**
     * 去除字符串末尾连续的00
     */
    public static String trimContinuousZero(String num) {
        // String num = "1000011000";
        String reg = "(00)+$";
        return num.replaceAll(reg, "");
    }

    private static final Pattern NUMBER_REGEX = Pattern.compile("^-?(([1-9]\\d+)|\\d)(\\.\\d+)?$");

    /**
     * 校验字符串是否是数字的方法<br>
     * </br>
     * ex:<br>
     * </br>
     * <t></t> -110.2345678 --> true<br>
     * </br>
     * <t></t> 1.1 -->true<br>
     * </br>
     * <t></t> 00. -->false<br>
     * </br>
     * <t></t> 01 -->false<br>
     * </br>
     * 
     * @param num
     * @return
     */
    public static boolean checkNum(String num) {
        return NUMBER_REGEX.matcher(num).find();
    }

    @Deprecated
    public static boolean checkDouble(String num) {
        String reg = "^\\d(\\.\\d+)?$";
        return Pattern.compile(reg).matcher(num).find();
    }

    public static void main(String[] args) {
        System.out.println(trimContinuousZero("0"));
        System.out.println(checkNum("-110.2345678"));
        System.out.println(checkNum("1.2345678"));
        System.out.println(checkNum("01.2345678"));
        System.out.println(checkNum("1."));
        System.out.println(checkNum("01."));
        System.out.println(checkNum("0."));
        System.out.println(checkNum("00"));
    }

    /**
     * 把对象转换为字符串
     * 
     * @param value
     * @return
     */
    public static String convert2Str(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            return value.toString();
        }
    }

    /**
     * 字符串混淆
     * 
     * @param value 目标字符串
     * @return 混淆后的字符串
     */
    public static String confusion(String value) {
        /*
        1、计算混淆比例
        2、混淆
         */
        int mixlength = computeConfusion(value.length());
        String mixStr = buildConfusionStr(mixlength);
        return mixStr(value, mixStr);
    }

    private static String mixStr(String value, String mixStr) {
        int length = value.length();
        int mixLength = mixStr.length();
        BigDecimal decimal = new BigDecimal((length - mixLength));
        BigDecimal before = decimal.divide(new BigDecimal(2)).setScale(0, RoundingMode.FLOOR);
        BigDecimal after = decimal.divide(new BigDecimal(2)).setScale(0, RoundingMode.CEILING);
        String target = value.substring(0, before.intValue()) + mixStr + value.substring(length - after.intValue());
        return target;
    }

    private static String buildConfusionStr(int mixlength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mixlength; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static int computeConfusion(int length) {
        double ceil = Math.ceil(length * 0.3);
        return new BigDecimal(ceil).intValue();
    }

}
