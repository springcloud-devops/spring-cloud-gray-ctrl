package demo.sdk.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

/**
 * Created by :Guozhihua
 * Date： 2017/9/30.
 */
public class NameUitls {

    public static final String getName(){
                String[] firsname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
                "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕",
                "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢",
                "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任",
                "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于",
                "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟",
                "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛",
                "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋",
                "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮",
                "蓝", "闽", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童",
                "颜", "郭", "梅", "盛", "林", "刁", "锺", "徐", "丘", "骆", "高", "夏",
                "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管",
                "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣",
                "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉",
                "扶", "堵", "冉", "宰", "郦", "雍", "郤", "璩", "桑", "桂", "濮", "牛",
                "寿", "通", "边", "扈", "燕", "冀", "郏", "浦", "尚", "农", "温", "别",
                "墨", "哈", "谯", "笪", "年", "爱", "阳", "佟" };

            int a = (int) Math.abs(firsname.length * Math.random());
            String name= firsname[a] + getRandomLengthChiness(1,2);
            System.out.println(name);
        return  name ;
    }

    public static void main(String[] args) {

        getName();
    }



    public static String getChinese() {
        String str = null;
        int highPos, lowPos;
        Random random = getRandomInstance();
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (new Integer(highPos)).byteValue();
        b[1] = (new Integer(lowPos)).byteValue();
        try {
            str = new String(b, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
    private static Random getRandomInstance() {
        if (random == null) {
            random = new Random(new Date().getTime());
        }
        return random;
    }
    public static String getRandomLengthChiness(int start, int end) {
        String str = "";
        int length = new Random().nextInt(end + 1);
        if (length < start) {
            str = getRandomLengthChiness(start, end);
        } else {
            for (int i = 0; i < length; i++) {
                str = str + getChinese();
            }
        }
        return str;
    }

    private static Random random = null;
}
