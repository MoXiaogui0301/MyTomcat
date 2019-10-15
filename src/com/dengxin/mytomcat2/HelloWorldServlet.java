package com.dengxin.mytomcat2;

import java.io.IOException;

/**
 * @author DengXin
 * @date 2019/10/15 10:55 AM
 */
public class HelloWorldServlet extends BaseServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get:Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post:Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
