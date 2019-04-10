package demo.sdk.demo.sys;

import java.io.Serializable;

/**
 * Created by :Guozhihua
 * Date： 2017/12/6.
 */
public class WebModel implements Serializable {
    private  int  code;
    private  Object datas;
    private  String msg ;

    public WebModel() {
        this.code=200;
        this.msg="success";
    }

    public WebModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebModel(int code, Object datas, String msg) {
        this.code = code;
        this.datas = datas;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
