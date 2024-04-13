package accuweatherWithWireMock;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public abstract class AccuweatherWireMockWithRestAssuredAbstractTest {
    private static WireMockServer wireMockServer = new WireMockServer();
    private static final int port = 8080;
    private static String baseUrl;
    private static final Logger logger = LoggerFactory.getLogger(AccuweatherWireMockWithRestAssuredAbstractTest.class);

    @BeforeAll
    static void startServer() {
        baseUrl = "http://localhost:" + port;
        wireMockServer.start();
        configureFor("localhost", port);
        logger.info("Start WireMockServer on port {}", port);
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
        logger.info("Stop WireMockServer");
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
