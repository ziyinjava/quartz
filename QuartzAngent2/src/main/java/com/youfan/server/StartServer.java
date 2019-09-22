package com.youfan.server;



import java.io.IOException;
import com.youfan.rpc.Server;
import com.youfan.rpc.ServiceCenter;
import com.youfan.service.TaskService;
import com.youfan.service.impl.TaskServiceimpl;

public class StartServer {
  public static void main(String[] args) {
	  new Thread(new Runnable() {
          public void run() {
              try {
                  Server serviceServer = new ServiceCenter("localhost",8088);
                  serviceServer.register(TaskService.class,TaskServiceimpl.class);
                  serviceServer.start();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }).start();
  }
}
