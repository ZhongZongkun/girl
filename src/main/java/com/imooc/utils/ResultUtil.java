package com.imooc.utils;

import com.imooc.entity.Result;
import com.imooc.enums.ResultEnum;

import java.util.List;

public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);

        return result;
    }


    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }



}
