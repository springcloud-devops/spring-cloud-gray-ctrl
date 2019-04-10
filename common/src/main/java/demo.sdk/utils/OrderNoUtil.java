package demo.sdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNoUtil {
    private static int orderIndex = 0;
    private static Date n = new Date();
    private  static  SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 生成订单编号
     *
     * @param preFixString
     * @return
     */
    public  synchronized  static String getOrderNumber(String preFixString) {
        Date today = new Date();
        String currTime = outFormat.format(today);
        if (orderIndex > 0) {
            if (n.getYear() == today.getYear() && n.getMonth() == today.getMonth() && n.getDay() == today.getDay()) {
                orderIndex += 1;
            } else {
                n = today;
                orderIndex = 1;
            }
        } else {
            n = today;
            orderIndex = 1;
        }
        if (orderIndex > 9999999) {
            orderIndex = 1;
        }
        int a = (int) ((Math.random() * 9 + 1) * 1000) ;
        String indexString = String.format("%s%06d", currTime+a, orderIndex);
        String orderNumberString = preFixString + indexString;
        return orderNumberString;
    }

    /**
     * 生成流水号
     *
     * @return
     */
    public  static String getSerialNumber() {
        Date today = new Date();
        String currTime = outFormat.format(today);
        int a = (int) ((Math.random() * 9 + 1) * 10000) ;
        String indexString = String.format("%s%06d", currTime, a);
        return indexString;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<20;i++){
                    System.out.println(getSerialNumber());
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<20;i++){
                    System.out.println(getSerialNumber());
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i<20;i++){
                    System.out.println(getSerialNumber());
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
