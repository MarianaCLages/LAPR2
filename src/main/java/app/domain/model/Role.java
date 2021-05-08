
package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Role {

    private String roleID;
    private String roleName;


    public Role(String roleID, String roleName) {

        this.roleID = roleID;
        this.roleName = roleName;



    }

    public String getRoleID() {
        return roleID;
    }

    @Override
    public String toString() {
        return roleName ;


    }


}
