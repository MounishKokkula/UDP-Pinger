# UDP-Pinger
We have implemented the UDP Pinger and ping server that would use UDP packets. We are well familiar that UDP uses a simple connectionless communication model with a minimum of protocol mechanism. It has no handshaking dialogues, and thus exposes the user's program to any unreliability of the underlying network, i.e. There is no guarantee of delivery, ordering, or duplicate protection.
## The Client Side
We have created a client side that will send and receive ping messages. 
The ping message that is sent includes ‘a string’ named PING with index number and system clock time in milliseconds.  
The number of ping messages that will be sent are 10. 
The client would wait for 1sec (1000msec) for the echo and would sent the next ping message irrespective if the ping message is received or not. 
Once all the 10 ping messages are sent, the client would wait for an additional five seconds. 
## Client & Server side:
The received ping message from the server includes string PING, index number and the time at which the client message is sent followed by the time at which the packet received by the server and then the time at which the server sent the packet and finally the time at which the client receives the packet. So from this we can even calculate the processing time of the message at the server (the time between the message is received at the server and retransmitted from the server).
## The Server Side
The server side receives the ping message and then obtains the port number and the IP address of the system that it received the ping message. We did not print the IP address and port number as it is not requested, but we do have it captured.
