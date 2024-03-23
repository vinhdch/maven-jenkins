package guru.stepDefinitions;

public class TestContext {

	// new 1 doi tuong duy nhat
	public DataContext dataContext;

	public TestContext() {
		dataContext = new DataContext();
	}

	public DataContext getDataContext() {
		return dataContext;
	}
}
