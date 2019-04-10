package demo.sdk.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by :Guozhihua
 * Date： 2017/8/9.
 */
public class RandomUtils {


    public static DecimalFormat format = new DecimalFormat("#.00");
    public static Random random = new Random();

    /**
     * 返回指定个数 随机大小写英文字母
     *
     * @param number
     * @return
     */
    public static String getRandom(int number) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < number; i++) {
            int a = (int) (Math.random() * 2) + 3;
            char c = ' ';
            if (a == 3) {
                c = 'a';
            } else if (a == 4) {
                c = 'A';
            }
            c = (char) (c + (int) (Math.random() * 26));
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getUpCaseRandom(int number) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < number; i++) {
            char c = 'A';
            c = (char) (c + (int) (Math.random() * 26));
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getLowerCaseRandom(int number) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < number; i++) {
            char c = 'a';
            c = (char) (c + (int) (Math.random() * 26));
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 生产随机码  ，本例子的 区间每次增值不得超过16,超过之后的charNum自动加1
     *
     * @return
     */
    public static Set<String> getRandomCode(int length, int totalNum) {
        Set<String> result = null;
        if (totalNum > 0 & totalNum <= 5000) {
            result = new HashSet();
            do {
                result.add(getRandomCode(length));
            } while (result.size() < totalNum);
        }
        return result;
    }

    public static void main(String[] args) {
        getMonney(1,20);
    }


    /**
     * 生产字母数字随机码
     * length must be larger than 2
     *
     * @return
     */
    public static String getRandomCode(int length) {
        StringBuilder sb = new StringBuilder();
        if (length < 2) {
            return "";
        }
        String ss = "0123456789abcdefghijklmnopqrstuvwxyz0123456789";
        String r = getLowerCaseRandom(1);
        int index = random.nextInt(10);
        sb.append(r).append(index);
        for (int i = 0; i < length - 2; i++) {
            int k = random.nextInt(ss.length());
            char c = ss.charAt(k);
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 生产随机码  ，本例子的 区间每次增值不得超过16,超过之后的charNum自动加1
     *
     * @param charNum 字符个数
     * @param counter 初始系数值
     * @param index   增量值，用于外部控制增值区间 （建议不超过16）
     * @return
     */
    public static String getRandomCode(int charNum, int index, long counter) {
        if (index > 16) {
            charNum++;
        }
        counter = counter * 17 + index;
        String r = getUpCaseRandom(charNum);
        String indexString = r.concat(String.format("%07d", counter));
        return indexString;
    }

    public static Set<String> getMonney(double totalMoney, int num) {
        int allNum = num * 100;
        int maxRan=num*25;
        BigDecimal  minDecimal=new BigDecimal("0.01");
        //获取随机的数字
        List<Integer> randoms = new ArrayList<>();
        int sum1 = 0;
        while (true) {
            if (num - randoms.size() > 1) {
                int i = 0;
                if(allNum-sum1>0){
                    i = random.nextInt(allNum - sum1) ;
                    if(i>maxRan){
                        i=maxRan;
                    }
                }
                sum1 += i;
                randoms.add(i);
            }
            if (randoms.size()+1 == num) {
                break;
            }
        }
        BigDecimal total = new BigDecimal(totalMoney).subtract(new BigDecimal(0.01*num));
        BigDecimal last = new BigDecimal(totalMoney);
        for (int i = 0; i < randoms.size(); i++) {
            int j = randoms.get(i);
            BigDecimal cuttent = new BigDecimal(j).multiply(total).divide(new BigDecimal(allNum+num), 2, BigDecimal.ROUND_FLOOR);
            cuttent= cuttent.add(minDecimal);
            BigDecimal subtract = last.subtract(cuttent);
            last=subtract;

            System.out.println("第" + (i+1) + "个：金额：" + cuttent+" 剩下："+last);
        }
        System.out.println("第" + num+ "个：金额：" + last+" 剩下：0");
        return null;
    }


}
