import java.net.*;

class UDPClient
{
   static byte[] sendData = new byte[1024];
   static byte[] receiveData = new byte[1024];
   static String sentence = "ping";
   static InetAddress IPAddress;
   static DatagramSocket clientSocket;
   static DatagramPacket receivePacket;
   static String millis;
   static int fail = 0;
   public static void main(String args[]) throws Exception
   {
           createAndCloseConnection();
           System.out.println("Total packet loss = "+fail);
           System.out.println("Ping efficiency is : "+((double)(10 - fail)/10)*100 + "%");
   }
   public static void createAndCloseConnection() throws Exception{
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName("10.138.4.64");  
            pingMessage();
           
            if(!clientSocket.isClosed()){
            clientSocket.setSoTimeout(5000);
        try{
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            if(clientSocket.toString() != null){
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            }
           }
        catch(SocketTimeoutException e){
        }
            }
            clientSocket.close();
   }
   public static void pingMessage() throws Exception{
        clientSocket.setSoTimeout(1000);
        int counter = 0;
        boolean continueSend = true;
       while(continueSend && counter<10){
       sentence="ping";
       sentence = sentence+" "+counter+":"+System.currentTimeMillis();
       sendData = sentence.getBytes();
       System.out.println(sentence);	
       DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
       clientSocket.send(sendPacket);
        System.out.println("Ping"+ counter);
       counter++;
        try{
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
       clientSocket.receive(receivePacket);
      
        StringBuilder modifiedSentence = new StringBuilder(new String(receivePacket.getData()).trim());
        modifiedSentence = modifiedSentence.append(":").append(System.currentTimeMillis());
       millis = modifiedSentence.toString();
	System.out.println(millis);
       String[] res=millis.split(":");
       System.out.println("Time to receive "+res[0]+" is "+((Long.parseLong(res[4])-Long.parseLong(res[1])))+ "ms");
       }
        catch(SocketTimeoutException e){
            fail++;
        }
       }
   }
}