package app.controller;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.ParameterStore;

public class ParameterController {

    private Company company;
    private Parameter pc;
    private ParameterStore store;

    public ParameterController() {

        this(App.getInstance().getCompany());
    }

    public ParameterController(Company company) {
        this.company = company;
        this.pc = null;

    }


    public boolean createParameter(String code, String name, String description, ParameterCategory cat) {
        store = company.getParameterList();
        if (store.CreateParameter(code, name, description, cat)){
            return true;
        }else {
            return false;
        }
    }

    public boolean saveParameterCategory() {
        return this.store.saveParameter(pc);
    }
}
