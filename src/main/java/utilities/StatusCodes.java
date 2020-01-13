package utilities;

public enum StatusCodes {
    OK(200),
    INTERNAL_ERROR(500),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    NOT_ACCEPTED(405);


    int code;

    public int getCode() {
        return code;
    }

    StatusCodes(int code) {
        this.code = code;
    }
}
