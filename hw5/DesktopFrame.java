//Abygail Stiekman
//COP3252 HW #5

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class DesktopFrame extends JFrame 
{
   private JDesktopPane theDesktop;
   private boolean dragc = false;
	
   // set up GUI
   public DesktopFrame()
   {
      super( "Homework 5" );

      JMenuBar bar = new JMenuBar(); // create menu bar
      JMenu createMenu = new JMenu( "Create" ); // create Add menu
      JMenuItem newFrame = new JMenuItem( "Picture Frame" );
      JMenuItem newFrame2 = new JMenuItem( "Movable PictureFrame" );
      
      createMenu.add( newFrame ); // add new frame item to create menu
      createMenu.add( newFrame2 ); // add new frame item to create menu
      bar.add( createMenu ); // add create menu to menu bar
      setJMenuBar( bar ); // set menu bar for this application
      
      JMenu quitMenu = new JMenu( "Quit" ); // create Add menu
      JMenuItem exit = new JMenuItem( "Quit Program" );
      exit.addActionListener(new exitApp());;

      quitMenu.add( exit ); // add new frame item to Add menu
      bar.add( quitMenu ); // add Add menu to menu bar
      setJMenuBar( bar ); // set menu bar for this application

      theDesktop = new JDesktopPane(); // create desktop pane
      add( theDesktop ); // add desktop pane to frame

      
      
      
      
      
      // set up listener for newFrame menu item
      newFrame.addActionListener(

         new ActionListener() // anonymous inner class
         {  
            // display new internal window
            public void actionPerformed( ActionEvent event ) 
            {
               // create internal frame
               JInternalFrame frame = new JInternalFrame(
                  "Picture Frame", true, true, true, true );
               frame.setSize(400, 400); 
               setcolor(Color.BLUE);
                  // pack();

               MyJPanel panel = new MyJPanel(); // create new panel
               frame.add( panel, BorderLayout.CENTER ); // add panel
               //frame.pack(); // set internal frame to size of contents

               theDesktop.add( frame ); // attach internal frame
               frame.setVisible( true ); // show internal frame
              //theDesktop.setSize(400,400);
               frame.setSize(400,400);
            }
         }
         );// end anonymous inner class
  
      newFrame2.addActionListener(
         new ActionListener() // anonymous inner class
         {  
            // display new internal window
            public void actionPerformed( ActionEvent e) 
            {
               // create internal frame
              /* JInternalFrame frame = new JInternalFrame(
                  "Movable PictureFrame", true, true, true, true );
           
               

               MyJPanel2 panel = new MyJPanel2(); // create new panel
               frame.add(panel, BorderLayout.CENTER ); // add panel
               frame.setSize(400,400); 
               
               
               // frame2.pack(); // set internal frame to size of contents

              theDesktop.add( frame); // attach internal frame
               frame.setVisible( true ); // show internal frame
               theDesktop.setSize(750,550);
 */ 
            	
            	  JInternalFrame newFrame = new JInternalFrame( "Moveable Picture Frame" );
                  JPanel cBox;
                  cBox = new JPanel();

                  MyJPanel2 myJP2 = new MyJPanel2();
                  //myJP2.setBackground(Color.WHITE);
                  //newFrame.add(cBox);
                  newFrame.add(myJP2);
                  newFrame.setSize(400, 400);
                  newFrame.setVisible(true);
                  newFrame.setResizable(true);
                  newFrame.setClosable(true);
                  newFrame.setMaximizable(true);
                  newFrame.setIconifiable(true);
               
               
         
           		JPanel northpanel;
		    	JCheckBox movedrag;
		    	
		    	northpanel = new JPanel();
		    	movedrag = new JCheckBox("Move on Drag");
		    	northpanel.add(movedrag);
   		    	
   		    	movedrag.addActionListener(new ActionListener()
   		    	{
   		    		@Override
   		    		public void actionPerformed(ActionEvent e)
   		    		{
   		    		if(movedrag.isSelected())	
   		    			dragc = true;
   		    		else 
   		    			dragc = false;
   		    		}
   		    	});
   		    	

   		    	movedrag.addActionListener(this);
   		    	
   		    	Container c = newFrame.getContentPane();
   		    	c.add(northpanel,BorderLayout.NORTH);
   		    	movedrag.setSelected(true);
   		    	boolean select = movedrag.isSelected();
   		    	if(movedrag.isSelected())
   		    		dragc = true;
   		    	else {}
   		    	theDesktop.add(newFrame);
   		    	
            	}//end public void
         }// end anonymous inner class
      ); // end call to addActionListener
   } // end constructor DesktopFrame

   private class handler implements ActionListener
   {
	   public void actionPerformed (ActionEvent e)
	   {
		   
	   }
   }
   
   
protected void setcolor(Color blue) {
	// TODO Auto-generated method stub
	
}








class MyJPanel extends JPanel
{
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLUE);
		YellowCirc(g);
		BrownRect(g);
	}
	public void YellowCirc(Graphics page)
	{
	double X, Y;
	double width, height;
	width = (getWidth()*.25);
	height = (getHeight()*.25);
	int w = (int) width;
	int h = (int) height;
	X = getWidth() *.70;
	Y = getHeight() *.10;
	int x = (int) X;
	int y = (int) Y;
	page.drawOval(x, y, w, h);
	page.setColor(Color.YELLOW);
	page.fillOval(x, y, w, h);
	}
	public void BrownRect(Graphics page)
	{
		double X, Y;
		double width;
		double height;
		Color BROWN = new Color(145,70,20);	
		/*x = 0;
		y = 375;*/
		width = (getWidth()/.25);
		height = (getHeight()/.25);
		X = getWidth()*.00000001;
		Y = getHeight()*.90;
		int w = (int) width;
		int h = (int) height;
		int x = (int) X;
		int y = (int) Y;
		page.drawRect(x, y, w, h);
		page.setColor(BROWN); 
		page.fillRect(x, y, w, h);
	}
	
} // end class MyJPanel







public class MyJPanel2 extends JPanel implements MouseListener, MouseMotionListener
{
	boolean drag = false;
	 	double x, y, width, height, X, Y, X2, Y2;
	 	Color color = Color.GREEN;
	 	Graphics2D g2d;
	 	Ellipse2D circle;

	 	public MyJPanel2()
	 	{
	 		    x = 140.0;
	            y = 80.0;
	            width = 120.0;
	            height = 150.0;
	            X = x + width;
	            Y = y + height;
	            circle = new Ellipse2D.Double(x, y, width, height);

	            setFocusable(true);
	            addMouseListener(this);
	            addMouseMotionListener(this);
	            this.requestFocus();
	 	}
	    
	 	 public void paintComponent(Graphics g) {
			  super.paintComponent(g);
			  g2d = (Graphics2D) g;
			  g2d.fill(circle);
			  g2d.setColor(color);
			  g2d.fill(circle);
	 	 }

        @Override
        public void mousePressed(MouseEvent m) {
        	 double xCoor = m.getX();
             double yCoor = m.getY();

             if(xCoor > x && xCoor < X && yCoor > y && yCoor < Y)
             {
                 drag = true;
                 X2 = xCoor - x;
                 Y2 = yCoor - y;

             }
        }

        @Override
        public void mouseReleased(MouseEvent m) {
        	drag = false;
        }
        
        public void mouseExited(MouseEvent m)
        {
        
        }
        
        @Override
        public void mouseDragged(MouseEvent m) {
        	 double xCoor = m.getX();
             double yCoor = m.getY();
             if(drag && dragc)
             {
                 x = xCoor - X2;
                 y = yCoor - Y2;
                 X = x + width;
                 Y = y + height;
                 circle = new Ellipse2D.Double(x, y, width, height);
                 repaint();
             }
             else
             {
            	    x = 140.0;
    	            y = 80.0;
    	            width = 120.0;
    	            height = 150.0;
    	            X = x + width;
    	            Y = y + height;
             }
        	
        }


 
		   
	   public void GreenCirc(Graphics page)
		{
		double x;
		double y;
		double width, height;
		width = (getWidth()*.3);
		height = (getHeight()*.3);
		//x = ((getWidth()-width)/2);
		//y = ((getWidth()-height)/2);
		x = getWidth()*.37;
		y = getHeight()*.37;
		int w = (int) width;
		int h = (int) height;
		int X = (int) x;
		int Y = (int) y;
		page.drawOval(X,Y,w,h);
		page.setColor(Color.GREEN);
		page.fillOval(X,Y,w,h);
		}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}
	
	



}
class exitApp implements ActionListener
{
public void actionPerformed (ActionEvent e)
	{
		System.exit(0);
	}
}