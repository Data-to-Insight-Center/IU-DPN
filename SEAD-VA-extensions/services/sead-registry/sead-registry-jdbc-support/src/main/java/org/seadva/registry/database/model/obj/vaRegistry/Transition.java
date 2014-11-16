package org.seadva.registry.database.model.obj.vaRegistry;


import org.seadva.registry.database.model.obj.vaRegistry.iface.ITransition;


/** 
 * Object mapping for hibernate-handled table: transition.
 * @author autogenerated
 */


public class Transition implements  ITransition {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559002619L;

	

	/** Field mapping. */
	private TransitionPK id;

	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Transition() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Transition(TransitionPK id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	public Class<?> getClassType() {
		return Transition.class;
	}
 

    /**
     * Return the value associated with the column: id.
	 * @return A TransitionPK object (this.id)
	 */
	public TransitionPK getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final TransitionPK id) {
		this.id = id;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Transition clone() throws CloneNotSupportedException {
		
        final Transition copy = (Transition)super.clone();

		copy.setId(this.getId());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("id: " + this.getId());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see Object#equals(Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		if ( this == aThat ) {
			 return true;
		}

		if ((aThat == null) || ( !(aThat.getClass().equals(this.getClass())))) {
			 return false;
		}
	
		final Transition that = (Transition) aThat;
		return this.getId().equals(that.getId());
	}
	
	/** Calculate the hashcode.
	 * @see Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	

	
}
