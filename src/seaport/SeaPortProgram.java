package seaport;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * File: SeaPortProgram.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This is the main class that launch the GUI for SeaPort program 
 */
public class SeaPortProgram extends JFrame{
	
	static final long serialVersionUID = 1L;

	World world = new World();
	
	JTextArea jta = new JTextArea();
	JComboBox <String> jcb, jcbSort;
	JTextField jtf;
	String token;
	Scanner scanLine;
	String line;
	/**
	 *No-arg constructor
	 */
	public SeaPortProgram(){
	        setTitle ("SeaPort Project");
	        setSize (600, 300);
	        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        setVisible (true);
	        jta.setFont (new java.awt.Font ("Monospaced", 0, 12)); 
	        JScrollPane jsp = new JScrollPane(jta);
	        add (jsp, BorderLayout.CENTER);
	        JLabel jlbl = new JLabel ("Search Target");
	        jtf = new JTextField (10);
	        //combo box
	        jcb = new JComboBox <String> ();
	        jcb.addItem ("Name");
	        jcb.addItem ("Index");
	        jcb.addItem ("Skills");
	       
	        //Buttons
	        JButton jbr = new JButton ("Read file");
	        JButton jbs = new JButton ("Search");
	        JButton jbd = new JButton ("Display Text");
	        
	        //adding for project 2
	        jcbSort = new JComboBox <String> ();
	        JButton sort = new JButton ("Sort");
	      
	       
	       // jcbSort.addItem("SeaPort by Name");
	        jcbSort.addItem("Person by Name");
	        jcbSort.addItem("Ship by Name");
	        jcbSort.addItem("Ship by Draft");
	        jcbSort.addItem("Ship by Width");
	        jcbSort.addItem("Ship by Length");
	        jcbSort.addItem("Ship by Weight");
	        
	        JPanel mainPane = new JPanel(new BorderLayout());
	        JPanel jp = new JPanel ();
	        JPanel jp1 = new JPanel();
	        jp.add (jbr);
	        jp.add(jbd);
	        jp.add (jbd);
	        jp.add (jlbl);
	        jp.add (jtf);
	        jp.add (jcb);
	        jp.add (jbs);
	        jp1.add(jcbSort);
	        jp1.add(sort);
	        mainPane.add(jp, BorderLayout.NORTH);
	        mainPane.add(jp1, BorderLayout.SOUTH);
	        add (mainPane, BorderLayout.PAGE_START);
	       // add(jp1, BorderLayout.LINE_END);
	        validate();
	        
	        
	    jbr.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                	JFileChooser jfc = new JFileChooser ("."); 
            		int returnVal = jfc.showOpenDialog(null);
            		
            	       if(returnVal == JFileChooser.APPROVE_OPTION) {
            	         // System.out.println("You chose to open this file: " +
            	         //  jfc.getSelectedFile().getName());
            	         // open and read file:
            	          try {
            	             Scanner sfin = new Scanner (jfc.getSelectedFile());
                             readFile (sfin);
            	          } 
         	             catch (FileNotFoundException ex) {
         	                JOptionPane.showMessageDialog(null, "File not found.");
         	             }
                } // end required method
            }
	    }// end local definition of inner class
        ); // the anonymous inner class
        
        jbd.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    display ();
                } // end required method
            } // end local definition of inner class
        ); // the anonymous inner class
        
        jbs.addActionListener ( new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    search ((String)(jcb.getSelectedItem()), jtf.getText());
                } // end required method
            } // end local definition of inner class
        ); // the anonymous inner class
        
        sort.addActionListener ( new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                sort ((String)(jcbSort.getSelectedItem()));
            } // end required method
        } // end local definition of inner class
    );
	        
        
	}//end constructor
	
	/**
	 * method that handles Read button
	 */
	public void readFile (Scanner sfin) {
		 // NOT the file name!!!!
	             while (sfin.hasNext()) {
	            	 
	            	  line = sfin.nextLine();
	            	 if(line.startsWith("//") || line.isEmpty()){
	            		 continue;
	            	 }
	            	
	            	 world.process(line);	
	            	 
	             }
	             sfin.close();
	            
	         
	          
	       } 
    
    /**
     * this method handles display button
     */
    public void display () {
      
         jta.setText ("" + world.ports.values().toString());
    	}
    
    /**
     * This method handles Search button
     * @param type is a type of search user want to perform
     * @param target is entered string in textfield
     */
    public void search (String type, String target) {
    	world.doSearch(type,target);
    	if (world.searchList.isEmpty()){
    	jta.setText( type + ": " + target + " Does not exist");
    	}else{
    	jta.setText("Search Result for: " + type + ": " + target + "\n\n"+ world.searchList.toString());  
    	
    }
    }
    /**
     * handles sorting button
     * @param sortType is a type of sort user choose from JCombobox
     */
    public void sort(String sortType){
    	String s = sortType.substring(sortType.lastIndexOf(" ")+ 1);
    	    	if (s  == "Name"){
          world.doSort(sortType);
          jta.setText( world.sortedMap.values().toString());
          return;
    	}
    	else{
    		 world.doSort(sortType);
    	      jta.setText( world.ports.values().toString());
    	}

    	
    }
    
	/**
	 * main method that launch the SeaPort program
	 * @param args
	 */
	public static void main (String args []) {
		     new SeaPortProgram ();
		   
		  } 
	 
}
