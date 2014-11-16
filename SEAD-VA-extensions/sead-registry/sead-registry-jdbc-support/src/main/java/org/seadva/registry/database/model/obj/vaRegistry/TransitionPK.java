package org.seadva.registry.database.model.obj.vaRegistry;


import org.seadva.registry.database.model.obj.vaRegistry.iface.ITransitionPK;


/** 
 * Object mapping for hibernate-handled table: transition.
 * @author autogenerated
 */


public class TransitionPK implements ITransitionPK {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559002617L;

	

	/** Field mapping. */

	private EventType eventType;

	/** Field mapping. */

	private State nextState;

	/** Field mapping. */

	private State startState;

 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/

	public Class<?> getClassType() {
		return TransitionPK.class;
	}
 

    /**
     * Return the value associated with the column: eventType.
	 * @return A EventType object (this.eventType)
	 */
	public EventType getEventType() {
		return this.eventType;
		
	}
	

  
    /**  
     * Set the value related to the column: eventType.
	 * @param eventType the eventType value you wish to set
	 */
	public void setEventType(final EventType eventType) {
		this.eventType = eventType;
	}

    /**
     * Return the value associated with the column: nextState.
	 * @return A State object (this.nextState)
	 */
	public State getNextState() {
		return this.nextState;
		
	}
	

  
    /**  
     * Set the value related to the column: nextState.
	 * @param nextState the nextState value you wish to set
	 */
	public void setNextState(final State nextState) {
		this.nextState = nextState;
	}

    /**
     * Return the value associated with the column: startState.
	 * @return A State object (this.startState)
	 */
	public State getStartState() {
		return this.startState;
		
	}
	

  
    /**  
     * Set the value related to the column: startState.
	 * @param startState the startState value you wish to set
	 */
	public void setStartState(final State startState) {
		this.startState = startState;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public TransitionPK clone() throws CloneNotSupportedException {
		
        final TransitionPK copy = (TransitionPK)super.clone();

		return copy;
	}
	


	/** Provides toString implementation.
	 * @see Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see Object#equals(Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		if (aThat == null)  {
			 return false;
		}
		
		final TransitionPK that; 
		try {
			that = (TransitionPK) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((getEventType() == null) && (that.getEventType() == null)) || (getEventType() != null && getEventType().getId().equals(that.getEventType().getId())));	
		result = result && (((getNextState() == null) && (that.getNextState() == null)) || (getNextState() != null && getNextState().getId().equals(that.getNextState().getId())));	
		result = result && (((getStartState() == null) && (that.getStartState() == null)) || (getStartState() != null && getStartState().getId().equals(that.getStartState().getId())));	
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
	int hash = 0;
		hash = hash + getEventType().hashCode();
		hash = hash + getNextState().hashCode();
		hash = hash + getStartState().hashCode();
	return hash;
	}
	

	
}