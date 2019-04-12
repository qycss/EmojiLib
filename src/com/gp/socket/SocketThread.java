package com.gp.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//�����߳�
public class SocketThread extends Thread{
	private ServerSocket serverSocket = null;
	//�˿ں�
	int port = 56789;
	
	public SocketThread(ServerSocket serverSocket){
		try{
			if(null == serverSocket){
				this.serverSocket = new ServerSocket(port);//���ü����Ķ˿ں�
				System.out.println("����������socket");
			}
		}catch(Exception e){
			System.out.println("����������socket����");
			e.printStackTrace();
		}
	}
	
	/*run()�����ǲ���Ҫ�û������õģ���ͨ��start��������һ���߳�֮��
	 ���̻߳����CPUִ��ʱ�䣬�����run������ȥִ�о��������*/
	public void run(){
		while(true){
			try{				
				//û�н������ڼ�����socket
				if(serverSocket == null){
					System.out.println("δ�������ڼ�����socket"+"\n");
					break;
				}
				//������socket�ѹر�
				else if(serverSocket.isClosed()){
					System.out.println("���ڼ�����socket�ѹر�"+"\n");
					break;
				}
				//accept()���տͻ������󣬲������µ�socket���ڴ���ͨ��
				//û�н��յ���һֱ����
				Socket socket = serverSocket.accept();
				//����ɹ��������µ�socket��˵���пͻ������󣩣����µ�socketδ�رգ��ر���ͨ�Ž�����
				if(null != socket && !socket.isClosed()){
					//���յ�����
					System.out.println("���յ����Կͻ��˵�����"+"\n");
					//�����̣߳�������socket��ͨ������
					
				}
				//û�м������ͻ��˵�����
				else{
					//break;
					System.out.println("û�н��յ����Կͻ��˵�����"+"\n");
					continue;
				}
				System.out.println("��ѭ����"+"\n");
			}catch(Exception e){
				System.out.println("�������ڴ���ͨ�ŵ�socket����");
				e.printStackTrace();
			}
		}
	}
	
	public void closeSocketServer(){
		try{
			//���ڼ�����socket�һ�δ���ر�
			if(null!=serverSocket && !serverSocket.isClosed()){
				serverSocket.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
