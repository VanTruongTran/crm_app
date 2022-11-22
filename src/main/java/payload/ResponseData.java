package payload;

public class ResponseData {
    private boolean success;//API thực hiện thành công hoặc thất bại
    private Object payload;//dữ liệu được API trả về
    private String message;//thông báo nếu xảy ra lỗi

    /* CONSTRUCTORS */
    public ResponseData() {

    }

    public ResponseData(boolean success, Object payload, String message) {
        this.success = success;
        this.payload = payload;
        this.message = message;
    }

    /* GETTERS & SETTERS */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
