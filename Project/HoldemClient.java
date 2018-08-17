/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/



import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 */

/**
 * Class HoldemClient is the client main program for the Network
 * Texas Holdem game.  The Command line arguments specify the 
 * host and port to which the server is listening for connections.
 */
public class HoldemClient {

	/**
	 * Main Program
	 * @param args
	 */
	public static void main(String[] args) 
		throws Exception{
		
		if (args.length != 3) usage();
        String host = args[0];
        int port = Integer.parseInt (args[1]);
        String session = args[2];

        Socket socket = new Socket();
        socket.connect (new InetSocketAddress (host, port));
        
        HoldemUI view = new HoldemUI();
        ModelProxy proxy = new ModelProxy(socket);
        view.setViewListener(proxy);
        proxy.setModelListener(view);
        proxy.join(null, session);
		
        
	}
	
	public static void usage(){
		System.err.println("Usage: HoldemClient <host> <port> <session>");
		System.exit(1);
		
	}

}
