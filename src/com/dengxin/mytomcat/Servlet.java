package com.dengxin.mytomcat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author DengXin
 * @date 2019/10/13 6:04 PM
 */
public interface Servlet {
    void init();
    void service(InputStream is, OutputStream out);
    void destroy();
}
