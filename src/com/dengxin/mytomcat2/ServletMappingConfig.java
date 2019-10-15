package com.dengxin.mytomcat2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DengXin
 * @date 2019/10/15 11:03 AM
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("travelWorld","/travel","com.dengxin.mytomcat2.TravelWorldServlet"));
        servletMappingList.add(new ServletMapping("helloWorld","/hello","com.dengxin.mytomcat2.HelloWorldServlet"));
    }
}
