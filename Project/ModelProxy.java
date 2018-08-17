import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/

/**
 * A ModelProxy is an class based on the Interface View Listener to
 * communicate with the server
 */
public class ModelProxy implements ViewListener {

	
	private Socket socket;
	private PrintStream out;
	private DataInputStream in;
	private ModelListener modelListener;
	
	
	/**
	 * Construct a new model proxy.
	 *
	 * @param socket Socket.
	 *
	 * @throws IOException Thrown if I/O exception occurs.
	 */
	public ModelProxy(Socket socket) throws IOException{
		this.socket = socket;
		out = new PrintStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
	}
	
	public void setModelListener(ModelListener modelListener){
        this.modelListener = modelListener;
        new ReaderThread() .start();
		
	}
	
	
	
	///The Interface classes
	
    public void join (ViewProxy proxy, String session)
    	throws IOException
    	{
    		String s = "J " +session;
			//System.out.println(s);
			out.println(s);
			//out.writeChar('\n');
			out.flush();
    		
    	}
    
    
    public void raiseBet(int amount) throws IOException{
    	out.println("B "+amount);
		out.flush();
    }
   
    public void checkCall()
    	throws IOException{
    	out.println("C");
		out.flush();
    	
    }
    
    public void playerFold()
    		throws IOException{
    	out.println("F");
		out.flush();
    	
    }
    	
	
	
	
	
	
	private class ReaderThread
		extends Thread{
		public void run(){
			try{
				for(;;){
					BufferedReader z = new BufferedReader(new InputStreamReader(in));
					String s = z.readLine();
					if (s.charAt(0) == 'L'){
						modelListener.boardUpdate(s);
					}
					else if (s.charAt(0)== 'W'){
						modelListener.gameResult(Character.digit(s.charAt(2), 10));
					}
					else{}
				}
				
			}catch(IOException e ){
			
			
			}finally{
				try{
					socket.close();
				}catch(IOException e){}
			}
		}
		
	}
	
	
}
