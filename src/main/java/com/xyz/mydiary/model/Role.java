package com.xyz.mydiary.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="m_role")
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // @formatter:off
    @ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
    @JoinTable(name = "map_roles_privileges",joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "priv_id", referencedColumnName = "id") })
    private Set<Privilege> privileges;
    // @formatter:on
	@Column(name="status",nullable=false, unique=true)
	private String status;
	
	@Column(name="super_role",nullable=false, unique=true)
	private Boolean superRole;
	
	public Boolean getSuperRole() {
		return superRole;
	}

	public void setSuperRole(Boolean superRole) {
		this.superRole = superRole;
	}

	public String getStatus() { 
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public Role() {
        super();
    }

    public Role(final String nameToSet) {
        super();
        name = nameToSet;
    }

    public Role(final String nameToSet, final Set<Privilege> privilegesToSet) {
        super();
        name = nameToSet;
        privileges = privilegesToSet;
    }

    // API

    public Long getId() {
        return id;
    }

    public void setId(final Long idToSet) {
        id = idToSet;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(final Set<Privilege> privilegesToSet) {
        privileges = privilegesToSet;
    }

    //

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Role other = (Role) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

  /*  @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).toString();
    }
*/
}


 

