import java.io.IOException;


/*******Abygail Stiekman********/
/****COP 3252 Adv Java**********/
/******Semester Proj************/
/***********aes15d**************/


/**
 * A ModelListener is an interface listener for objects which
 * respond to events from the model object.
 */
public interface ModelListener {

	/**
	 * 
	 * @exception  IOException
	 */
	public void boardUpdate(String info) throws IOException;

	/**
	 * 
	 * @exception  IOException
	 */
	public void gameResult(int winner) throws IOException;
}