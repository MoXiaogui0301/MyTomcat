package com.dengxin.mytomcat2;

import java.io.IOException;

/**
 * @author DengXin
 * @date 2019/10/15 10:53 AM
 */
public class TravelWorldServlet extends BaseServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get:Travelling world...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post:Travelling world...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
