package seaport;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * File: Job.java
 * Date: March 31, 2019
 * @author Pooja Patel
 * Purpose: This class represents the Job. this class extends Thing.java
 */
public class Job extends Thing{
	
	double duration;
	/**
	 * list of skills
	 */
	ArrayList<String> requirements  = new ArrayList<>();
	/**
	 * Constructor to create an object of class
	 * @param sc is Scanner that reads input file
	 */
	public Job(Scanner sc){
		super(sc);
		if(sc.hasNextDouble()) duration = sc.nextDouble();
		while(sc.hasNext()) {
			String s = sc.next();
			requirements.add(s);
		}
	}
	
	@Override
	public String toString(){
		String st = "Jobs: " + super.toString() + " " + duration + requirements.toString() ;
		return st;
		
	}
	
}
