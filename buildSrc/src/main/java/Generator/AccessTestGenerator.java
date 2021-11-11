package Generator;

import Model.TestTemplate;
import freemarker.template.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class AccessTestGenerator {
    private TestTemplate testTemplate;
    private static final String TEST_URL = "templates/RestAccessTestTemplate.txt";

    public AccessTestGenerator(TestTemplate testTemplate) {
        this.testTemplate = testTemplate;
    }

    public GenerateResult generateTest() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endedDateTime = LocalDateTime.now();

        GenerateResult generateResult = new GenerateResult();
        Configuration cfg = new Configuration();
        try {
            Writer bufferedWriter = new BufferedWriter(
                    new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(testTemplate.getTestName()+ ".java"), "utf-8")));

            Template template = cfg.getTemplate(TEST_URL);
            Map<String, String> data = new HashMap<>();
            data.put("switchName", testTemplate.getSwitchName());
            data.put("restPath", testTemplate.getRestPath());
            data.put("testName", testTemplate.getTestName());

            template.process(data, bufferedWriter);


            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            generateResult.setResult(false);
        }
        finally {
            generateResult.setResult(true);
        }

        generateResult.setTime(ChronoUnit.SECONDS.between(startTime, endedDateTime));
        return generateResult;
    }
}