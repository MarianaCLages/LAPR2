package app.domain.model;

import java.io.Serializable;

/**
 * Class that represents an Organization Role
 */
public class Role implements Serializable {

    private final String roleID;
    private final String roleName;

    /**
     * Constructor of the Role Class
     *
     * @param roleID   Unique ID of the Organization Role
     * @param roleName Name of the Organization Role
     */
    public Role(String roleID, String roleName) {

        this.roleID = roleID;
        this.roleName = roleName;

    }

    /**
     * @return Unique ID of the Organization Role
     */
    public String getRoleID() {
        return roleID;
    }

    /**
     * @return A string with the format "roleName"
     */
    @Override
    public String toString() {
        return roleName;

    }


}
