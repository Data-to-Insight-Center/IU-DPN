package org.seadva.registry.database.model.obj.vaRegistry;

import com.google.gson.annotations.Expose;
import org.seadva.registry.database.model.obj.vaRegistry.iface.IAggregation;


/** 
 * Object mapping for hibernate-handled table: aggregation.
 * @author autogenerated
 */

public class Aggregation implements IAggregation {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559002653L;

	

	/** Field mapping. */
    @Expose
	private AggregationPK id;

	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Aggregation() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Aggregation(AggregationPK id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	public Class<?> getClassType() {
		return Aggregation.class;
	}
 

    /**
     * Return the value associated with the column: id.
	 * @return A AggregationPK object (this.id)
	 */
	public AggregationPK getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final AggregationPK id) {
		this.id = id;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Aggregation clone() throws CloneNotSupportedException {
		
        final Aggregation copy = (Aggregation)super.clone();

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
	
		final Aggregation that = (Aggregation) aThat;
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