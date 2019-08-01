package com.chicex.PictureCloud.Objects;

public class ResponseBody {
    public int code;
    public String msg;
    public ResponseBody(int code,String message){
        this.code = code;
        this.msg = message;
    }
}
