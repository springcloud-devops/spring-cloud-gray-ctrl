package demo.sdk.demo.sys.exception;

/**
 *
 * 系统异常基础类
 * Created by :Guozhihua
 * Date： 2017/12/6.
 */
public class ProBaseException extends Exception {

    private int error_code;

    public ProBaseException(String msg, int error_code) {
        super(msg);
        this.error_code = error_code;
    }

    public int getError_code() {
        return error_code;
    }

}
