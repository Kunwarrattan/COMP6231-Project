package com.Client.FrontEnd;

import java.io.File;



import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.assignment1.config.Configuration;
import com.assignment1.utils.Sequencer;

public class FrontEnd {
	
		
	
	public static void main(String[] args) {
		
		try{

		  ORB orb = ORB.init(args, null); 
		  
		  POA rootPoa = POAHelper.narrow(orb .resolve_initial_references("RootPOA"));
		  
		  FrontEndUDP server1 = new FrontEndUDP(); 
		  Sequencer seq = new Sequencer();
		  
		  byte[] id1 = rootPoa.activate_object(server1); 
		  
		  org.omg.CORBA.Object obj = rootPoa.id_to_reference(id1); 
		  
		  String str1 = orb.object_to_string(obj); 
		  
		  FileUtils.writeStringToFile(new File(".//" + Configuration.FRONT_END_IOR_FILE + "IOR.txt"), str1);
		  
		  System.out.println("Front End Server is running and listening for the clients.......");
		  
		  rootPoa.the_POAManager().activate(); 
		  
		  orb.run();
		  
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
