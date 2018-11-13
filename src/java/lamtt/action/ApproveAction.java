/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamtt.action;

import Module.PushNotifictionHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import lamtt.dto.CyberGamingDTO;
import lamtt.dto.ServiceRequestDTO;
import static java.nio.charset.StandardCharsets.*;

/**
 *
 * @author royal
 */
public class ApproveAction {
    
    private int requestId;
    private final String SUCCESS = "success";
    private final String URL_APPROVE = "https://swd-backend-lamtt.herokuapp.com/serviceRequests/approve/%d";
    private final String URL_GETREQUESTBYID = "https://swd-backend-lamtt.herokuapp.com/serviceRequests/%d";
    private final String URL_GET_CYBER = "https://swd-backend-admin.herokuapp.com/cybers/%d";
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
        ServiceRequestDTO serviceRequestDTO;
        CyberGamingDTO cyberDetail;
        try{
            URL url = new URL(String.format(URL_GETREQUESTBYID, requestId));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                if ((output = br.readLine()) != null) {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    serviceRequestDTO = gson.fromJson(output, ServiceRequestDTO.class);
                    if(serviceRequestDTO != null && serviceRequestDTO.getId() != null){
                        URL urlGetDetail = new URL(String.format(URL_GET_CYBER, serviceRequestDTO.getCyberGamingId()));
                        HttpURLConnection conn2 = (HttpURLConnection) urlGetDetail.openConnection();
                        conn2.setRequestMethod("GET");
                        conn2.setRequestProperty("Accept", "application/json");
                        if (conn2.getResponseCode() != HttpURLConnection.HTTP_OK) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + conn2.getResponseCode());
                        }
                        BufferedReader br2 = new BufferedReader(new InputStreamReader((conn2.getInputStream())));
                        String output2;
                        if ((output2 = br2.readLine()) != null) {
                            Gson gson2 = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                            cyberDetail = gson2.fromJson(output2, CyberGamingDTO.class);
                            if(cyberDetail != null && serviceRequestDTO != null){
                            String stg = serviceRequestDTO.getUserId() + "-"
                                    + cyberDetail.getName() + " has been accept your request";
                            byte[] ptext = stg.getBytes(ISO_8859_1); 
                            String msg = new String(ptext, UTF_8); 
                            PushNotifictionHelper.sendPushNotification(msg);  
                        }
                        }
                    }
                }
            
        }catch(Exception ex){
            ex.printStackTrace();
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
