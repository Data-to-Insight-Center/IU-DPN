package org.seadva.registry.database.model.obj.vaRegistry.iface;
import java.util.Set;
import javax.persistence.Basic;
import org.seadva.registry.database.model.obj.vaRegistry.MetadataReference;
import org.seadva.registry.database.model.obj.vaRegistry.Property;


/** 
 * Object interface mapping for hibernate-handled table: metadata_type.
 * @author autogenerated
 */

public interface IMetadataType {



    /**
     * Return the value associated with the column: id.
	 * @return A String object (this.id)
	 */
	String getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final String id);

    /**
     * Return the value associated with the column: metadataReference.
	 * @return A Set&lt;MetadataReference&gt; object (this.metadataReference)
	 */
	Set<MetadataReference> getMetadataReferences();
	
	/**
	 * Adds a bi-directional link of type MetadataReference to the metadataReferences set.
	 * @param metadataReference item to add
	 */
	void addMetadataReference(MetadataReference metadataReference);

  
    /**  
     * Set the value related to the column: metadataReference.
	 * @param metadataReference the metadataReference value you wish to set
	 */
	void setMetadataReferences(final Set<MetadataReference> metadataReference);

    /**
     * Return the value associated with the column: metadataElement.
	 * @return A String object (this.metadataElement)
	 */
	String getMetadataElement();
	

  
    /**  
     * Set the value related to the column: metadataElement.
	 * @param metadataElement the metadataElement value you wish to set
	 */
	void setMetadataElement(final String metadataElement);

    /**
     * Return the value associated with the column: metadataSchema.
	 * @return A String object (this.metadataSchema)
	 */
	String getMetadataSchema();
	

  
    /**  
     * Set the value related to the column: metadataSchema.
	 * @param metadataSchema the metadataSchema value you wish to set
	 */
	void setMetadataSchema(final String metadataSchema);

    /**
     * Return the value associated with the column: property.
	 * @return A Set&lt;Property&gt; object (this.property)
	 */
	Set<Property> getProperties();
	
	/**
	 * Adds a bi-directional link of type Property to the properties set.
	 * @param property item to add
	 */
	void addProperty(Property property);

  
    /**  
     * Set the value related to the column: property.
	 * @param property the property value you wish to set
	 */
	void setProperties(final Set<Property> property);

	// end of interface
}