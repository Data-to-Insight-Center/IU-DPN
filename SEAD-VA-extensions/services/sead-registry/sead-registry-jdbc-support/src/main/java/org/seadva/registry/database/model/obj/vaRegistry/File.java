package org.seadva.registry.database.model.obj.vaRegistry;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.google.gson.annotations.Expose;
import org.hibernate.proxy.HibernateProxy;
import org.seadva.registry.database.enums.subtype.vaRegistry.BaseEntitySubclassType;
import org.seadva.registry.database.model.obj.vaRegistry.BaseEntity;
import org.seadva.registry.database.model.obj.vaRegistry.Format;
import org.seadva.registry.database.model.obj.vaRegistry.iface.IFile;


/** 
 * Object mapping for hibernate-handled table: file.
 * @author autogenerated
 */

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@PrimaryKeyJoinColumn(name = "entity_id")
@Table(name = "file", catalog = "va_registry")
public class File extends BaseEntity implements  IFile {

	/** Serial Version UID. */
	private static final long serialVersionUID = -559002635L;

	

	/** Field mapping. */
    @Expose
	private String fileName;
	/** Field mapping. */
    @Expose
	private Set<Format> formats = new HashSet<Format>();

	/** Field mapping. */
    @Expose
	private Integer isObsolete;
	/** Field mapping. */
    @Expose
	private Long sizeBytes;
	/** Field mapping. */
    @Expose
	private String versionNum;

    public File(){}
    public File(BaseEntity entity){
        this.setId(entity.getId());
        this.setEntityName(entity.getEntityName());
        this.setEntityCreatedTime(entity.getEntityCreatedTime());
        this.setEntityLastUpdatedTime(entity.getEntityLastUpdatedTime());
        this.setProperties(entity.getProperties());
    }
	/**
 	 * Return an enum of the type of this subclass. This is useful to be able to use switch/case in your code.
 	 *
 	 * @return BaseEntitySubclassType enum.
 	 */
 	@Transient
 	@Override
 	public BaseEntitySubclassType getBaseEntitySubclassType() {
		return BaseEntitySubclassType.FILE;
 	}  

 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Override
	@Transient
	public Class<?> getClassType() {
		return File.class;
	}
 

    /**
     * Return the value associated with the column: fileName.
	 * @return A String object (this.fileName)
	 */
	@Basic( optional = false )
	@Column( name = "file_name", nullable = false, length = 127  )
	public String getFileName() {
		return this.fileName;
		
	}
	

  
    /**  
     * Set the value related to the column: fileName.
	 * @param fileName the fileName value you wish to set
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

    /**
     * Return the value associated with the column: format.
	 * @return A Set&lt;Format&gt; object (this.format)
	 */
 	@OneToMany( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "entity"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	public Set<Format> getFormats() {
		return this.formats;
		
	}
	
	/**
	 * Adds a bi-directional link of type Format to the formats set.
	 * @param format item to add
	 */
	public void addFormat(Format format) {
		format.setEntity(this);
		this.formats.add(format);
	}

  
    /**  
     * Set the value related to the column: format.
	 * @param format the format value you wish to set
	 */
	public void setFormats(final Set<Format> format) {
		this.formats = format;
	}

    /**
     * Return the value associated with the column: isObsolete.
	 * @return A Integer object (this.isObsolete)
	 */
	@Basic( optional = true )
	@Column( name = "is_obsolete"  )
	public Integer getIsObsolete() {
		return this.isObsolete;
		
	}
	

  
    /**  
     * Set the value related to the column: isObsolete.
	 * @param isObsolete the isObsolete value you wish to set
	 */
	public void setIsObsolete(final Integer isObsolete) {
		this.isObsolete = isObsolete;
	}

    /**
     * Return the value associated with the column: sizeBytes.
	 * @return A Long object (this.sizeBytes)
	 */
	@Basic( optional = true )
	@Column( name = "size_bytes"  )
	public Long getSizeBytes() {
		return this.sizeBytes;
		
	}
	

  
    /**  
     * Set the value related to the column: sizeBytes.
	 * @param sizeBytes the sizeBytes value you wish to set
	 */
	public void setSizeBytes(final Long sizeBytes) {
		this.sizeBytes = sizeBytes;
	}

    /**
     * Return the value associated with the column: versionNum.
	 * @return A String object (this.versionNum)
	 */
	@Basic( optional = false )
	@Column( name = "version_num", nullable = false, length = 127  )
	public String getVersionNum() {
		return this.versionNum;
		
	}
	

  
    /**  
     * Set the value related to the column: versionNum.
	 * @param versionNum the versionNum value you wish to set
	 */
	public void setVersionNum(final String versionNum) {
		this.versionNum = versionNum;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public File clone() throws CloneNotSupportedException {
		
        final File copy = (File)super.clone();

		copy.setFileName(this.getFileName());
		if (this.getFormats() != null) {
			copy.getFormats().addAll(this.getFormats());
		}
		copy.setIsObsolete(this.getIsObsolete());
		copy.setSizeBytes(this.getSizeBytes());
		copy.setVersionNum(this.getVersionNum());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("fileName: " + this.getFileName() + ", ");
		sb.append("isObsolete: " + this.getIsObsolete() + ", ");
		sb.append("sizeBytes: " + this.getSizeBytes() + ", ");
		sb.append("versionNum: " + this.getVersionNum());
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
		
		final File that; 
		try {
			that = (File) proxyThat;
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
		result = result && (((getFileName() == null) && (that.getFileName() == null)) || (getFileName() != null && getFileName().equals(that.getFileName())));
		result = result && (((getIsObsolete() == null) && (that.getIsObsolete() == null)) || (getIsObsolete() != null && getIsObsolete().equals(that.getIsObsolete())));
		result = result && (((getSizeBytes() == null) && (that.getSizeBytes() == null)) || (getSizeBytes() != null && getSizeBytes().equals(that.getSizeBytes())));
		result = result && (((getVersionNum() == null) && (that.getVersionNum() == null)) || (getVersionNum() != null && getVersionNum().equals(that.getVersionNum())));
		return result;
	}
	
	

	
}
