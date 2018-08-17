import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * HoldemServer is the main server class. It handles the command
 * line arguments as well as binds socket connections and creates
 * listener objects.
 */
public class HoldemServer
	{
	
	/** Main
	 * @param args
	 */
	public static void main	(String[] args) throws Exception {
		if (args.length != 2) usage();
		String host = args[0];
		int port = 0;
		// try/catch block for parsing port
		try{
			port  = Integer.parseInt(args[1]);
		}
		catch (Exception e) {
			System.err.println("Unable to parse port number as integer");
			System.exit(0);
		}
		
		// try/catch block for setting up socket connection
		try {
			ServerSocket serversocket = new ServerSocket();
			serversocket.bind (new InetSocketAddress (host, port));
			SessionManager manager = new SessionManager();

			for (;;) {
				Socket socket = serversocket.accept();
				ViewProxy proxy = new ViewProxy (socket);
				proxy.setViewListener (manager);
				
			}
		}
		catch (UnknownHostException e) {
			System.err.println("Error: Unknown host exception for " + host);
			System.exit(0);
		}
		catch (IOException e) {
			System.err.println("Error: I/O connection failed for " + host);
			System.exit(0);
		}
	}

	/**
	 * Error message for bad usage.
	 */
	private static void usage() {
		System.err.println ("Usage: java HoldemServer <host> <port>");
		System.exit (1);
	}
}

