/*
package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Role {

    private String roleID;
    private String roleName;


    public Role(String roleID, String roleName) {
        checkroleIDRules(roleID);
        checkroleNameRules(roleName);

        this.roleID = roleID;
        this.roleName = roleName;

        Role cepo = new Role();

    }


    private void checkroleIDRules(String roleID) {
        if (StringUtils.isBlank(roleID))
            throw new IllegalArgumentException("Name cannot be blank.");


    }
    private void checkroleNameRules(String roleName) {
        if (StringUtils.isBlank(roleName))
            throw new IllegalArgumentException("Name cannot be blank.");

        roleName = roleName.toLowerCase();
        char[] charArray = roleName.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (!(c >= 'a' && c <= 'z')) {
                throw new IllegalArgumentException("Name only accepts letters");
            }
        }
    }
    @Override
    public String toString() {
        return "Role:" +
                "ID=" + roleID +
                ", name=" + roleName ;


    }


}
*/