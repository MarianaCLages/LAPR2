package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterCategoryStore;
import auth.UserSession;

public class ParameterCategoryController {

    private Company company;
    private ParameterCategoryStore store;

    public ParameterCategoryController() {
        this(App.getInstance().getCompany());
      //  App app = new App();
      //  UserSession session = App.getCurrentUserSession();
      //  if (!session.isLoggedInWithRole("ADMINISTRATOR")) {
      //      throw new IllegalStateException("Not Authorized");
      //  }
    }

    public ParameterCategoryController(Company company) {
        this.company = company;

    }


    public boolean createParameterCategory(String code, String name) {
        store = company.getParameterCategoryList();
        if (store.CreateParameterCategory(code,name)){
            return true;
        }else {
            return false;
        }


    }

    public ParameterCategory getpc(){
        return store.getPc();
    }

    public boolean saveParameterCategory() {
        return this.store.saveParameterCategory();
    }
}
