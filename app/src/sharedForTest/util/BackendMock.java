package util;

import com.dotabuilds.AppParameters;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Lei Chen on 2017/12/22.
 */

public class BackendMock {

    private MockWebServer server;

    public BackendMock(){
        server = new MockWebServer();
    }

    public MockWebServer getServer(){
        return server;
    }

    public BackendMock mockBaseUrl(){
        AppParameters.baseUrl = server.url("/api/dotabuilds/").toString();
        return this;
    }

    public BackendMock mockResponseOfGetRecentMatchesById(){
        MockResponse response = new MockResponse()
                .setBody(MatchFactory.serializeTestMatches());
        server.enqueue(response);

        return this;
    }

    public BackendMock startServer(){
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void shutdownServer(){
        try {
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
