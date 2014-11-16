package org.seadva.registry.database.model.obj.vaRegistry.iface;
import javax.persistence.Basic;
import org.seadva.registry.database.model.obj.vaRegistry.BaseEntity;
import org.seadva.registry.database.model.obj.vaRegistry.MetadataType;


/** 
 * Object interface mapping for hibernate-handled table: metadata_reference.
 * @author autogenerated
 */

public interface IMetadataReference {



    /**
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
	Long getId();
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	void setId(final Long id);

    /**
     * Return the value associated with the column: metadata.
	 * @return A MetadataType object (this.metadata)
	 */
	MetadataType getMetadata();
	

  
    /**  
     * Set the value related to the column: metadata.
	 * @param metadata the metadata value you wish to set
	 */
	void setMetadata(final MetadataType metadata);

    /**
     * Return the value associated with the column: objectEntity.
	 * @return A BaseEntity object (this.objectEntity)
	 */
	BaseEntity getObjectEntity();
	

  
    /**  
     * Set the value related to the column: objectEntity.
	 * @param objectEntity the objectEntity value you wish to set
	 */
	void setObjectEntity(final BaseEntity objectEntity);

    /**
     * Return the value associated with the column: subjectEntity.
	 * @return A BaseEntity object (this.subjectEntity)
	 */
	BaseEntity getSubjectEntity();
	

  
    /**  
     * Set the value related to the column: subjectEntity.
	 * @param subjectEntity the subjectEntity value you wish to set
	 */
	void setSubjectEntity(final BaseEntity subjectEntity);

	// end of interface
}