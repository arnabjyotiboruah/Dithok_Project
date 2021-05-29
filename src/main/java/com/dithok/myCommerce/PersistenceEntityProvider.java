package com.dithok.myCommerce;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceEntityProvider {

	  public static final boolean DEBUG = true;
	  private static final PersistenceEntityProvider singleton = new PersistenceEntityProvider();	  
	  protected EntityManagerFactory emf;	 
	  
	  public static PersistenceEntityProvider getInstance() {	    
	    return singleton;
	  }
	  
	  private PersistenceEntityProvider() 
	  {	 
		  
	  }
	 
	  public EntityManagerFactory getEntityManagerFactory() {
	    
	    if (emf == null)
	      createEntityManagerFactory();
	    return emf;
	  }
	  
	  public void closeEntityManagerFactory() {
	    
	    if (emf != null) {
	      emf.close();
	      emf = null;
	      if (DEBUG)
	        System.out.println("n*** Persistence finished at " + new java.util.Date());
	    }
	  }
	  
	  protected void createEntityManagerFactory() {
		  
	    this.emf = Persistence.createEntityManagerFactory("persistence");
	    if (DEBUG)
	      System.out.println("n*** Persistence started at " + new java.util.Date());
	  }

}
