package sockets;

import java.net.*;
import java.io.*;

public class GreetingClient
{
   public static void main(String [] args)
   {
      String serverName = "localhost";//ip address of the server
      int port = 6606;
      try
      {
         System.out.println("Connecting to " + serverName +" on port " + port);
         Socket client = new Socket(serverName, port);//client opens a socket
         System.out.println("Just connected to Server" + client.getRemoteSocketAddress());
         
         OutputStream outToServer = client.getOutputStream();//to write to client
         
         DataOutputStream out = new DataOutputStream(outToServer);
         out.writeUTF("Hello 1");
         
         out.writeUTF("Hello 2");
         
        /* InputStream inFromServer = client.getInputStream();
         DataInputStream in =new DataInputStream(inFromServer);
         System.out.println("Server says " + in.readUTF());*/
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}