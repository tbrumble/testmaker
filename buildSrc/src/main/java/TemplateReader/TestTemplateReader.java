package TemplateReader;


import Model.TestTemplate;
//import lombok.experimental.Accessors;

//@Accessors(chain = true)
public class TestTemplateReader {
    public TestTemplate getTestTemplate(String restPath,
                                        String switcherName,
                                        String testName,
                                        String testPackageName) {
        TestTemplate testTemplate = new TestTemplate();
        testTemplate.setTestName(testName);
        testTemplate.setSwitchName(switcherName);
        testTemplate.setTestPackageName(testPackageName);
        testTemplate.setRestPath(restPath);
        return testTemplate;
    }
}
