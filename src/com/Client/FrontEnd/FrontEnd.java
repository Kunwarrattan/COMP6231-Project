package com.Client.FrontEnd;

import java.io.File;
import java.net.SocketException;

import org.apache.commons.io.FileUtils;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import com.assignment1.config.Configuration;
import com.assignment1.utils.CommunicationManager;


public class FrontEnd {
	
	CommunicationManager mgr = null;
	
	public FrontEnd() throws SocketException{
		mgr = new CommunicationManager();
	}
	
	public static void main(String[] args) {
		
		try{

		  ORB orb = ORB.init(args, null); 
		  
		  POA rootPoa = POAHelper.narrow(orb .resolve_initial_references("RootPOA"));
		  
		  FrontEndUDP server1 = new FrontEndUDP(); 
		  
		  byte[] id1 = rootPoa.activate_object(server1); 
		  
		  org.omg.CORBA.Object obj = rootPoa.id_to_reference(id1); 
		  
		  String str1 = orb.object_to_string(obj); 
		  
		  FileUtils.writeStringToFile(new File(".//" + Configuration.LIBRARY1 + "IOR.txt"), str1);
		  
		  rootPoa.the_POAManager().activate(); 
		  orb.run();
		 
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
}