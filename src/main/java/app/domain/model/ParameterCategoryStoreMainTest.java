package app.domain.model;

public class ParameterCategoryStoreMainTest {
    public static void main(String[] args) {
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.CreateParameterCategory("Sirs85", "Nome12");
        store.saveParameterCategory(store.getPc());
        store.CreateParameterCategory("Sir85", "Nome182");
        store.saveParameterCategory(store.getPc());
        store.CreateParameterCategory("Sir8s85", "ome12");
        store.saveParameterCategory(store.getPc());

        System.out.println(store);
    }
}
