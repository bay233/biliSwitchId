package com.bay.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.experimental.theories.Theories;


public class switchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	static{
		URL path = switchServlet.class.getClassLoader().getResource("python/ScoketBili.py");
		ScriptState state = new ScriptState(path);
		if (!state.ScriptState){
			Thread t = new Thread(state);
			t.setDaemon(true);
			t.start();
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");
		if ("query".equals(op)) {
			queryOp(request, response);
		}
	}

	private void queryOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String parameter = request.getParameter("parameter");
		if (parameter == null || "".equals(parameter)){
			super.writeJson(0, response);
			return ;
		}
		String[] split = parameter.split("=");
		if (!"aid".equals(split[0]) && !"bvid".equals(split[0])){
			super.writeJson(0, response);
			return ;
		}
		String id = "";
		id = getId(parameter);
		if("0".equals(id)){
			super.writeJson(0, response);
			return ;
		}
		super.writeJson(id, response);
	}
	
	private String getId(String key) throws IOException{
		String host = "localhost";
		Integer port = 33222;  //连接端口
		Socket client = new Socket(host, port);
		OutputStream outToServer = client.getOutputStream();
		outToServer.write(key.getBytes());
		
		InputStream inFromServer = client.getInputStream();
		BufferedReader inRead = new BufferedReader(new InputStreamReader(inFromServer));
		String Id = inRead.readLine();
		//System.out.println(Id);
		client.close();
		return Id;
	}

}
