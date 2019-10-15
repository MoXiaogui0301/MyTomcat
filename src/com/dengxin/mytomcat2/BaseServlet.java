package com.dengxin.mytomcat2;

/**
 * @author DengXin
 * @date 2019/10/15 10:46 AM
 */
public abstract class BaseServlet {
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);
    public void service(MyRequest myRequest, MyResponse myResponse) {
        if ("POST".equalsIgnoreCase(myRequest.getMethod())) {
            doPost(myRequest,myResponse);
        } else if ("GET".equalsIgnoreCase(myRequest.getMethod())) {
            doGet(myRequest, myResponse);
        }
    }
}
