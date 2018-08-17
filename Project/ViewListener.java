import java.io.IOException;

/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/


/*
 * A ViewListener is an interface listener for
 * objects which respond to events from the view object.
 */
public interface ViewListener {
	
	/**
	 * Join the proxy class to session given.
	 * @param proxy
	 * @param session
	 * @throws IOException
	 */
	public void join(ViewProxy proxy, String session) throws IOException;
	
	/**
	 * 
	 * @throws IOException
	 */
	public void raiseBet(int amount) throws IOException;

	/**
	 * 
	 * @throws IOException
	 */
	public void playerFold() throws IOException;
	
	
	/**
	 * 
	 * @throws IOException
	 */
	public void checkCall() throws IOException;
}