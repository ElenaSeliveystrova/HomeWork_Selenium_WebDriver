package greenCity.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class RunnerExtension implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean testResult = context.getExecutionException().isPresent();
        log.error(context.getExecutionException() + "Exception is present");
        TestRunner.isTestSuccessful = !testResult;
    }
}
