package com.gp.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//��socket service��tomcat����
public class SocketListener implements ServletContextListener{
	private SocketThread socketThread;

	//�ر��̣߳��ͷż����˿�
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if(null!=socketThread && !socketThread.isInterrupted()){
			socketThread.closeSocketServer();
			socketThread.interrupt();
		}
	}

	//�����̣߳�����socket����
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		if(null==socketThread){
			//�½��߳�
			socketThread = new SocketThread(null);
			//�����߳�
			socketThread.start();
		}
	}
	
}
