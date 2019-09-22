package org.quartz.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Iphosts {
    public static String getIphosts(){
        InetAddress addr=null;
        String ip="";    
        String address="";    
        try{
            addr = InetAddress.getLocalHost();  
            ip = addr.getHostAddress().toString(); //获得机器IP　　    
            address = addr.getHostName().toString(); //获得机器名称
        }catch(Exception e){    
            e.printStackTrace();    
        }
        return ip + "|" + address;
    }      
}  