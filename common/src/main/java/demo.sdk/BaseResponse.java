package demo.sdk;

import java.io.Serializable;

/**
 * Created by :Guozhihua
 * Date： 2017/11/15.
 */
public class BaseResponse implements Serializable{
    public  int code;
    public String status;

    public Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
