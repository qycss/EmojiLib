package com.gp.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//创建线程
public class SocketThread extends Thread{
	private ServerSocket serverSocket = null;
	//端口号
	int port = 56789;
	
	public SocketThread(ServerSocket serverSocket){
		try{
			if(null == serverSocket){
				this.serverSocket = new ServerSocket(port);//配置监听的端口号
				System.out.println("创建监听的socket");
			}
		}catch(Exception e){
			System.out.println("创建监听的socket出错");
			e.printStackTrace();
		}
	}
	
	/*run()方法是不需要用户来调用的，当通过start方法启动一个线程之后，
	 当线程获得了CPU执行时间，便进入run方法体去执行具体的任务。*/
	public void run(){
		while(true){
			try{				
				//没有建立用于监听的socket
				if(serverSocket == null){
					System.out.println("未建立用于监听的socket"+"\n");
					break;
				}
				//监听的socket已关闭
				else if(serverSocket.isClosed()){
					System.out.println("用于监听的socket已关闭"+"\n");
					break;
				}
				//accept()接收客户端请求，并返回新的socket用于处理通信
				//没有接收到会一直阻塞
				Socket socket = serverSocket.accept();
				//如果成功返回了新的socket（说明有客户端请求），且新的socket未关闭（关闭则通信结束）
				if(null != socket && !socket.isClosed()){
					//接收到请求
					System.out.println("接收到来自客户端的请求"+"\n");
					//开启线程，处理新socket的通信任务
					
				}
				//没有监听到客户端的请求
				else{
					//break;
					System.out.println("没有接收到来自客户端的请求"+"\n");
					continue;
				}
				System.out.println("在循环中"+"\n");
			}catch(Exception e){
				System.out.println("创建用于处理通信的socket出错");
				e.printStackTrace();
			}
		}
	}
	
	public void closeSocketServer(){
		try{
			//存在监听的socket且还未被关闭
			if(null!=serverSocket && !serverSocket.isClosed()){
				serverSocket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
