package com.zy.zhihuigongdi.controller;

import java.util.HashMap;
import java.util.Map;

//如果方法中有局部变量容易产生线程安全问题
public class BaseController {
    //创建一个threadlocal对象
    ThreadLocal<Map<String,Object>> threadlocal = new ThreadLocal<>();
    //创建result对象
    public void star() {
        //new一个resulet的map，
        Map<String,Object> result = new HashMap<>();
        //将创建的result结果map放入到threadlocal中
        threadlocal.set(result);
    }

    //将是否返回成功的结果放入到threadlocal中
    public void success(boolean flag) {
        threadlocal.get().put("success", flag);
    }

    //将异常信息放入到threadlocal中
    public void message(String message) {
        threadlocal.get().put("message", message);
    }

    //将data数据放入到result中
    public void data(Object data) {
        threadlocal.get().put("data", data);
    }

    //从Thread中取出当前的result
    public Map<String,Object> end(){
        Map<String, Object> result = threadlocal.get();
        return result;
    }
}
