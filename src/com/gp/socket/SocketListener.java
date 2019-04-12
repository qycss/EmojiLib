package com.gp.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//将socket service随tomcat启动
public class SocketListener implements ServletContextListener{
	private SocketThread socketThread;

	//关闭线程，释放监听端口
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if(null!=socketThread && !socketThread.isInterrupted()){
			socketThread.closeSocketServer();
			socketThread.interrupt();
		}
	}

	//开启线程，启动socket监听
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if(null==socketThread){
			//新建线程
			socketThread = new SocketThread(null);
			//启动线程
			socketThread.start();
		}
	}
	
}
