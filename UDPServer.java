import java.net.*;

class UDPServer
{
   static String[] msgTime;
   static StringBuilder sentence;
   private static DatagramSocket serverSocket;
   
   public static void main(String args[]) throws Exception
      {
         serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[5096];
            byte[] sendData = new byte[5096];
            
            while(true)
               {
                  StringBuilder str = new StringBuilder();
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  sentence = new StringBuilder( new String(receivePacket.getData()).trim());
                  str = str.append(sentence);
                  str = addTime(str);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  StringBuilder pingMsg = str;
                  pingMsg = addTime(pingMsg);
                  sendData = pingMsg.toString().getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
                  
               }
      }
   public static StringBuilder addTime(StringBuilder sntnce){
       sntnce = sntnce.append(":").append(System.currentTimeMillis());
       return sntnce;
   }
}