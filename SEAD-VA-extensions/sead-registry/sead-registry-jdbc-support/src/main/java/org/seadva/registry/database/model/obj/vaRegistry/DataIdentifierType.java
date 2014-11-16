package org.seadva.registry.database.model.obj.vaRegistry;

import com.google.gson.annotations.Expose;
import org.seadva.registry.database.model.obj.vaRegistry.iface.IDataIdentifierType;

import java.util.HashSet;
import java.util.Set;


/** 
 * Object mapping for hibernate-handled table: data_identifier_type.
 * @author autogenerated
 */

public class DataIdentifierType implements IDataIdentifierType {


	/** hashCode temporary storage. */
	private volatile String hashCode;
	

	/** Field mapping. */
	private Set<DataIdentifier> dataIdentifiers = new HashSet<DataIdentifier>();

	/** Field mapping. */
    @Expose
	private String dataIdentifierTypeName;
	/** Field mapping. */
    @Expose
	private String id;
	/** Field mapping. */
    @Expose
	private String schemaUri;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public DataIdentifierType() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public DataIdentifierType(String id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param dataIdentifierTypeName String object;
	 * @param id String object;
	 * @param schemaUri String object;
	 */
	public DataIdentifierType(String dataIdentifierTypeName, String id, String schemaUri) {

		this.dataIdentifierTypeName = dataIdentifierTypeName;
		this.id = id;
		this.schemaUri = schemaUri;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/

	public Class<?> getClassType() {
		return DataIdentifierType.class;
	}
 

    /**
     * Return the value associated with the column: dataIdentifier.
	 * @return A Set&lt;DataIdentifier&gt; object (this.dataIdentifier)
	 */

	public Set<DataIdentifier> getDataIdentifiers() {
		return this.dataIdentifiers;
		
	}
	
	/**
	 * Adds a bi-directional link of type DataIdentifier to the dataIdentifiers set.
	 * @param dataIdentifier item to add
	 */
	public void addDataIdentifier(DataIdentifier dataIdentifier) {
		dataIdentifier.getId().setDataIdentifierType(this);
		this.dataIdentifiers.add(dataIdentifier);
	}

  
    /**  
     * Set the value related to the column: dataIdentifier.
	 * @param dataIdentifier the dataIdentifier value you wish to set
	 */
	public void setDataIdentifiers(final Set<DataIdentifier> dataIdentifier) {
		this.dataIdentifiers = dataIdentifier;
	}

    /**
     * Return the value associated with the column: dataIdentifierTypeName.
	 * @return A String object (this.dataIdentifierTypeName)
	 */

	public String getDataIdentifierTypeName() {
		return this.dataIdentifierTypeName;
		
	}
	

  
    /**  
     * Set the value related to the column: dataIdentifierTypeName.
	 * @param dataIdentifierTypeName the dataIdentifierTypeName value you wish to set
	 */
	public void setDataIdentifierTypeName(final String dataIdentifierTypeName) {
		this.dataIdentifierTypeName = dataIdentifierTypeName;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A String object (this.id)
	 */

	public String getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final String id) {

		this.id = id;
	}

    /**
     * Return the value associated with the column: schemaUri.
	 * @return A String object (this.schemaUri)
	 */

	public String getSchemaUri() {
		return this.schemaUri;
		
	}
	

  
    /**  
     * Set the value related to the column: schemaUri.
	 * @param schemaUri the schemaUri value you wish to set
	 */
	public void setSchemaUri(final String schemaUri) {
		this.schemaUri = schemaUri;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public DataIdentifierType clone() throws CloneNotSupportedException {
		
        final DataIdentifierType copy = (DataIdentifierType)super.clone();

		if (this.getDataIdentifiers() != null) {
			copy.getDataIdentifiers().addAll(this.getDataIdentifiers());
		}
		copy.setDataIdentifierTypeName(this.getDataIdentifierTypeName());
		copy.setId(this.getId());
		copy.setSchemaUri(this.getSchemaUri());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("dataIdentifierTypeName: " + this.getDataIdentifierTypeName() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("schemaUri: " + this.getSchemaUri());
		return sb.toString();		
	}


}