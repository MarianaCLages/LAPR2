package app.domain.model;

import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;

    private ParameterCategoryStore parameterCategoryList;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * creates a new empty list of Parameter Categories objects
     * @return empty list of Parameter Categories objects
     */

    public ParameterCategoryStore getParameterCategoryList() {
        return this.parameterCategoryList = new ParameterCategoryStore();
    }

/*    public static ParameterCategory createParameterCategory(String code, String name) {
        ParameterCategoryStore list = new ParameterCategoryStore();
        return ParameterCategoryStore.CreateParameterCategory(code, name);
    }

    public boolean validateParameterCategory(ParameterCategory pc) {
        if (pc == null)
            return false;
        return (!this.parameterCategoryList.contains(pc));
    }

    public boolean saveParameterCategory(ParameterCategory pc) {
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoryList.add(pc);
    }

 */
}
