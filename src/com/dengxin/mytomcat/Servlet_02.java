package com.dengxin.mytomcat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author DengXin
 * @date 2019/10/13 6:08 PM
 */
public class Servlet_02 implements Servlet {

    @Override
    public void init() {
        System.out.println("servlet_02 inited");
    }

    @Override
    public void service(InputStream is, OutputStream out) {
        System.out.println("servlet_02 executed");
    }

    @Override
    public void destroy() {
        System.out.println("servlet_02 destroyed");
    }
}
