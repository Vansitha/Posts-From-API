package curtin.edu.assignment1.data;

import android.app.Activity;

import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

// TODO: REFERENCE SAMPLE CODE SearchTask.java
public class GetData implements Callable<String> {

    private String searchKey = null;
    private String baseUrl;
    private RemoteUtilities remoteUtilities;
    private Activity uiActivity;

    public GetData(Activity uiActivity) {
        baseUrl = "https://jsonplaceholder.typicode.com/";
        remoteUtilities = RemoteUtilities.getInstance(uiActivity);
        this.uiActivity = uiActivity;

    }
    @Override
    public String call() throws Exception {
        String response = null;
        String apiEndPoint = generateSearchUrl();
        HttpURLConnection connection = remoteUtilities.openConnection(apiEndPoint);
        if (connection != null) {
            if (remoteUtilities.isConnectionOkay(connection)) {
                response = remoteUtilities.getResponseString(connection);
                connection.disconnect();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {}
            }
        }
        return response;
    }

    private String generateSearchUrl() {
        String url = null;
        if (searchKey != null) {
            url = baseUrl + searchKey;
        }
        return url;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
