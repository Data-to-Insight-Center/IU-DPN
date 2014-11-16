package org.seadva.registry.database.model.obj.vaRegistry;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;
import org.seadva.registry.database.enums.subtype.vaRegistry.BaseEntitySubclassType;
import org.seadva.registry.database.model.obj.vaRegistry.BaseEntity;
import org.seadva.registry.database.model.obj.vaRegistry.iface.IEvent;


/** 
 * Object mapping for hibernate-handled table: event.
 * @author autogenerated
 */

@Entity
@PrimaryKeyJoinColumn(name = "event_id")
@Table(name = "event", catalog = "va_registry")
public class Event extends BaseEntity implements  IEvent {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559002637L;

	

	/** Field mapping. */
	private String eventDetail;
	/** Field mapping. */
	private EventType eventType;
 

	/**
 	 * Return an enum of the type of this subclass. This is useful to be able to use switch/case in your code.
 	 *
 	 * @return BaseEntitySubclassType enum.
 	 */
 	@Transient
 	@Override
 	public BaseEntitySubclassType getBaseEntitySubclassType() {
		return BaseEntitySubclassType.EVENT;
 	}  

 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Override
	@Transient
	public Class<?> getClassType() {
		return Event.class;
	}
 

    /**
     * Return the value associated with the column: eventDetail.
	 * @return A String object (this.eventDetail)
	 */
	@Basic( optional = false )
	@Column( name = "event_detail", nullable = false, length = 256  )
	public String getEventDetail() {
		return this.eventDetail;
		
	}
	

  
    /**  
     * Set the value related to the column: eventDetail.
	 * @param eventDetail the eventDetail value you wish to set
	 */
	public void setEventDetail(final String eventDetail) {
		this.eventDetail = eventDetail;
	}

    /**
     * Return the value associated with the column: eventType.
	 * @return A EventType object (this.eventType)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = false )
	@JoinColumn(name = "event_type_id", nullable = false )
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
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Event clone() throws CloneNotSupportedException {
		
        final Event copy = (Event)super.clone();

		copy.setEventDetail(this.getEventDetail());
		copy.setEventType(this.getEventType());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("eventDetail: " + this.getEventDetail() + ", ");
		if (this.getClass().getSuperclass() != this.getClass()) {
			sb.append("(super.toString=" + super.toString() + ")");
		}
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

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final Event that; 
		try {
			that = (Event) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		if (this.getClass().getSuperclass() != this.getClass()) {
			result = super.equals(that);
		}
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getEventDetail() == null) && (that.getEventDetail() == null)) || (getEventDetail() != null && getEventDetail().equals(that.getEventDetail())));
		result = result && (((getEventType() == null) && (that.getEventType() == null)) || (getEventType() != null && getEventType().getId().equals(that.getEventType().getId())));	
		return result;
	}
	
	

	
}
