import java.io.IOException;
import java.util.HashMap;

/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/

/*
 * SessionManager holds a hash map of the model objects for each session.
 */
public class SessionManager implements ViewListener {

	private HashMap<String, HoldemModel> sessions = new HashMap<String, HoldemModel>();

	/*
	 * Constructor
	 */
	public SessionManager() {
		
	}

	/*
	 * Join the given session.
	 * @param  proxy
	 * @param  session
	 * @exception  IOException
	 */
	public synchronized void join (ViewProxy proxy, String session) throws IOException {
		//System.out.println("Sesson : "+ session);
		HoldemModel model = sessions.get (session);
		if (model == null) {
			model = new HoldemModel();
			sessions.put (session, model);
		}
		model.addModelListener (proxy);
		proxy.setViewListener (model);
	}

	/**
	 * 
	 */
	public void raiseBet(int i) {
		
	}

	/**
	 * 
	 */
	public void playerFold() {		
		
	}
	
	/**
	 * 
	 */
	public void checkCall() {
		
	}
	
}