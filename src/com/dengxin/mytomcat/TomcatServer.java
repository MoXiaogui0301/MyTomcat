package com.dengxin.mytomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author DengXin
 * @date 2019/10/13 9:51 AM
 */
public class TomcatServer {

    private static final String WEB_ROOT=System.getProperty("user.dir")+File.separator+"WebContent";
    private static String url;
    private static final String SHUTDOWN_COMMAND="SHUTDOWN";
    private static boolean shutdown = false;
    private static Map<String,String> map = new HashMap<>();

    /**
     * 服务器启动时就加载配置文件
     */
    static{
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(WEB_ROOT+File.separator+"conf.properties"));
            Set set = prop.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = prop.getProperty(key);
                map.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MyTomcat启动程序
     * @param args
     */
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket = null;
        InputStream is = null;
        OutputStream out = null;
        try {
            serverSocket = new ServerSocket(8081);
            while (!shutdown) {
                socket = serverSocket.accept();
                is = socket.getInputStream();
                out = socket.getOutputStream();
                parse(is);
                if (null!=url) {
                    if (!url.equals(SHUTDOWN_COMMAND)) {
                        if (url.contains(".")) {
                            sendStaticResource(out);
                        } else {
                            sendDynamicResource(is,out);
                        }
                    }
                    else {
                        shutdown = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != socket) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析Http请求
     */
    private static void parse(InputStream is) throws IOException {
        StringBuffer httpRequest = new StringBuffer(2048);
        byte[] bytes = new byte[2048];
        int len = is.read(bytes);
        httpRequest.append(new String(bytes,0,len));
        parseurl(httpRequest);
        System.out.println(httpRequest);
    }

    /**
     * 解析url
     */
    private static void parseurl(StringBuffer httprequest) {
        int index1,index2;
        index1 = httprequest.indexOf(" ");
        if (index1 != -1) {
            index2 = httprequest.indexOf(" " , index1+1);
            if (index2 > index1) {
                url = httprequest.substring(index1+2,index2);
            }
            System.out.println(url);
        }
    }

    /**
     * 发送静态资源文件
     */
    private static void sendStaticResource(OutputStream out) {
        byte[] bytes = new byte[2048];
        FileInputStream fis = null;
        try {
            File file = new File(WEB_ROOT,url);
            //send file to client if exists
            if (file.exists()) {
                out.write("HTTP/1.1 200 OK\n".getBytes());
                out.write("Server:apache-Coyote/1.1\n".getBytes());
                out.write("Content-Type:text/html;charset=tuf-8\n".getBytes());
                out.write("\n".getBytes());
                fis = new FileInputStream(file);
                int len =fis.read(bytes);
                while (len != -1) {
                    out.write(bytes,0,len);
                    len = fis.read(bytes);
                }
            } else {
                out.write("HTTP/1.1 404 not found\n".getBytes());
                out.write("Server:apache-Coyote/1.1\n".getBytes());
                out.write("Content-Type:text/html;charset=utf-8\n".getBytes());
                out.write("\n".getBytes());
                String errorMessage = "file not found";
                out.write(errorMessage.getBytes());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送动态资源文件
     */
    private static void sendDynamicResource(InputStream is, OutputStream out) throws IOException,ClassNotFoundException,IllegalAccessException,InstantiationException {
        out.write("HTTP/1.1 200 OK\n".getBytes());
        out.write("Server:apache-Coyote/1.1\n".getBytes());
        out.write("Content-Type:text/html;charset=tuf-8\n".getBytes());
        out.write("\n".getBytes());
        //如果配置文件中的键匹配则创建Servlet实例并执行方法
        if (map.containsKey(url)) {
            String value = map.get(url);
            Class clazz = Class.forName(value);
            Servlet servlet = (Servlet) clazz.newInstance();
            servlet.init();
            servlet.service(is,out);
        }
    }
}
