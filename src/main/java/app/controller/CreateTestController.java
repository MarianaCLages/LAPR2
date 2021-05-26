package app.controller;

import app.domain.mappers.CategoryListMapper;
import app.domain.mappers.ParameterListMapper;
import app.domain.mappers.TestTypeListMapper;
import app.domain.mappers.dto.CategoryListDTO;
import app.domain.mappers.dto.ParameterDTO;
import app.domain.mappers.dto.TestTypeDTO;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.stores.*;

import java.util.List;

public class CreateTestController {
    private Company company;
    private TestStore store;
    private TestTypeStore ttList;
    private TestType testType;
    private ParameterCategoryStore categoriesList;
    private ParameterCategoryStore caList;
    private ParameterStore paList;



    public CreateTestController() {
        this(App.getInstance().getCompany());
    }

    public CreateTestController(Company company) {
        this.company = company;
        store = company.getTestList();
    }

    public void getLists() {

        this.caList = new ParameterCategoryStore();
        this.paList = new ParameterStore();

    }

    public boolean existClient(String clientTin) {
        ClientStore cList = company.clientList();
        return cList.exists(clientTin);
    }

    public void createTest(String testNhsNumber, String clientCc) {
        store.createTest(testNhsNumber, clientCc, this.testType, this.caList, this.paList);
    }

    public boolean saveTest() {
        return store.saveTest();
    }

    public List<TestTypeDTO> getTestTypeList() {
        this.ttList = company.testTypeList();
        TestTypeListMapper typeMapper = new TestTypeListMapper();
        return typeMapper.toDTO(ttList);
    }

    public List<CategoryListDTO> getCategories(String testTypeCode) {
        this.testType = ttList.getByID(testTypeCode);
        this.categoriesList = testType.getCatStore();
        CategoryListMapper catMapper = new CategoryListMapper();
        return catMapper.toDTO(categoriesList);
    }

    public List<ParameterDTO> getParameters(String categoryCode) {
        ParameterStore paStore = company.parameterList();

        ParameterCategory category = this.categoriesList.getByCode(categoryCode);
        this.caList.add(category);
        List<Parameter> plist = paStore.getParameterList(categoryCode);
        ParameterListMapper pMapper = new ParameterListMapper();
        return pMapper.toDTO(plist);
    }

    public void addParameter(String parameterCode) {
        ParameterStore paStore = company.parameterList();
        Parameter pa = paStore.getParameter(parameterCode);
        this.paList.add(pa);
    }

    public String getTest(){
        return store.getTest();
    }




}
