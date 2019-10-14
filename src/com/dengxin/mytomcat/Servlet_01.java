package com.dengxin.mytomcat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author DengXin
 * @date 2019/10/13 6:05 PM
 */
public class Servlet_01 implements Servlet {

    @Override
    public void init() {
        System.out.println("servlet_01被创建");
    }

    @Override
    public void service(InputStream is, OutputStream out) {
        System.out.println("servlet_01被执行");
    }

    @Override
    public void destroy() {
        System.out.println("servlet_01被销毁");
    }
}
