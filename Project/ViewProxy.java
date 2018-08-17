import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/

/*
 * The proxy class of the server which is used for communication
 * between server and client input/output.
 */
public class ViewProxy implements ModelListener
	{

	private Socket socket;
	private PrintStream out;
	private BufferedReader in;
	private ViewListener viewListener;

	/**
	 * Constructor sets socket and I/O streams
	 * @param  socket
	 * @exception  IOException
	 */
	public ViewProxy (Socket socket) throws IOException {
		this.socket = socket;
		out = new PrintStream (socket.getOutputStream());
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Set view listener.
	 * @param  viewListener
	 */
	public void setViewListener (ViewListener viewListener) {
		if (this.viewListener == null) {
			this.viewListener = viewListener;
			new ReaderThread() .start();
		}
		else {
			this.viewListener = viewListener;
		}
	}


	/**
	 * Sends the Long Info String on the output stream to the client.
	 * @param info
	 * @exception  IOException
	 */
	public void boardUpdate(String info) throws IOException {
		out.println("L " + info);
		out.flush();
	}

	/**
	 * Notifies the client that the player has either won (1), lost (0), or tied (-1) the round.
	 * @exception  IOException
	 */
	public void gameResult(int winner) throws IOException {
		out.println("W " + winner);
		out.flush();
	}

	/**
	 * Threading implementation for client input.
	 */
	private class ReaderThread extends Thread {
		public void run() {
			try	{
				for (;;) {
					String b = in.readLine();
					//System.out.println(b);
					if (b.charAt(0) == 'J'){
							
							viewListener.join(ViewProxy.this, b.substring(2));
					}
					else if (b.charAt(0) =='F'){
							viewListener.playerFold();
					}
					else if(b.charAt(0) == 'C'){
							viewListener.checkCall();
					}
					else if(b.charAt(0) == 'B'){
							viewListener.raiseBet(Integer.parseInt(b.substring(2)));
					}
					else{}
				}
			}
			catch (IOException exc) {
			}catch (NullPointerException e){
			}
			finally {
				try {
					socket.close();
				}
				catch (IOException exc) {
				}
			}
		}
	}
}
