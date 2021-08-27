import org.eclipse.jetty.http.HttpMethod;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class FirstTest {

    @Test
    public void testPerformance() throws IOException {
        InetAddress.getByName("127.0.0.1");
        TestPlanStats stats = testPlan(
                threadGroup(10, 1000,
                        httpSampler("https://www.google.com/").method(HttpMethod.GET)),
                htmlReporter("report")).run();
        assertThat(stats.overall().elapsedTime()).isLessThan(Duration.ofSeconds(20));
    }
}