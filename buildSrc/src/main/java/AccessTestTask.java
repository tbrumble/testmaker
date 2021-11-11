import Generator.AccessTestGenerator;
import Generator.GenerateResult;
import Model.TestTemplate;
import TemplateReader.TestTemplateReader;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;
import org.gradle.api.DefaultTask;

public class AccessTestTask extends DefaultTask {
    @Option(option = "restPath", description = "Путь к методу реста")
    private String restPath;
    @Option(option = "switcherName", description = "Рубильник")
    private String switcherName;
    @Option(option = "testName", description = "Имя теста")
    private String testName;
    @Option(option = "testPackageName", description = "Пакет с тестами")
    private String testPackageName;


    @TaskAction
    void runAccessTestGenerator() {
        getLogger().debug("Start to generate testName = {}, restPath = {}", testName, restPath);

        getLogger().debug("Generate test template");
        TestTemplate testTemplate = new TestTemplateReader().getTestTemplate(restPath, switcherName, testName, testPackageName);

        if ((testPackageName.isEmpty()) || (switcherName.isEmpty()) || (restPath.isEmpty()) || (testName.isEmpty())) {
            getLogger().debug("Test data is empty");
        }

        getLogger().debug("Data: {} {} {} {}", testTemplate.getTestName(), testTemplate.getTestPackageName(),
                testTemplate.getSwitchName(), testTemplate.getRestPath());

        getLogger().debug("Generate test from template");
        GenerateResult generateResult = new AccessTestGenerator(testTemplate)
                .generateTest();

        getLogger().debug("Process of test generation is being ended result {}", generateResult);
        getLogger().debug("Process of test generation is ended testName = {}, restPath = {}", testName, restPath);
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    public void setSwitcherName(String switcherName) {
        this.switcherName = switcherName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestPackageName(String testPackageName) {
        this.testPackageName = testPackageName;
    }
}
