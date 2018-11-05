/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamtt.action;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author royal
 */
public class ApproveAction {
    
    private int requestId;
    private final String SUCCESS = "success";
    private final String URL_APPROVE = "https://swd-backend-lamtt.herokuapp.com/serviceRequest/approve/%d";
    public ApproveAction() {
    }
    
    public String execute() throws Exception {
        try {
            URL url = new URL(String.format(URL_APPROVE, requestId));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
    
    
    
}
