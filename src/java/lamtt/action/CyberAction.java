/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamtt.action;

import com.example.demo.service.dto.ServiceRequestDetailDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import lamtt.dto.AccountDTO;

/**
 *
 * @author royal
 */
public class CyberAction {

    private List<ServiceRequestDetailDTO> listRequestNeedToApprove;
    private List<ServiceRequestDetailDTO> listRequestNeedToDone;
    private final String URL_GET_APPROVE = "https://swd-backend-lamtt.herokuapp.com/serviceRequest/getListNeedToAproveByAccountId/%d";
    private final String URL_GET_DONE = "https://swd-backend-lamtt.herokuapp.com/serviceRequest/getListNeedToDoneByAccountId/%d";
    private final String SUCCESS = "success";

    public CyberAction() {
    }

    public String execute() throws Exception {
        String result = SUCCESS;
        Map session = ActionContext.getContext().getSession();
        AccountDTO accountDTO = (AccountDTO) session.get("CYBER");
        try {
            URL url = new URL(String.format(URL_GET_APPROVE, accountDTO.getId()));
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
                System.out.println(output);
                TypeToken<List<ServiceRequestDetailDTO>> typeToken = new TypeToken<List<ServiceRequestDetailDTO>>() {
                };
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                listRequestNeedToApprove = gson.fromJson(output, typeToken.getType());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            URL url = new URL(String.format(URL_GET_DONE, accountDTO.getId()));
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
                System.out.println(output);
                TypeToken<List<ServiceRequestDetailDTO>> typeToken = new TypeToken<List<ServiceRequestDetailDTO>>() {
                };
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                listRequestNeedToDone = gson.fromJson(output, typeToken.getType());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public List<ServiceRequestDetailDTO> getListRequestNeedToApprove() {
        return listRequestNeedToApprove;
    }

    public void setListRequestNeedToApprove(List<ServiceRequestDetailDTO> listRequestNeedToApprove) {
        this.listRequestNeedToApprove = listRequestNeedToApprove;
    }

    public List<ServiceRequestDetailDTO> getListRequestNeedToDone() {
        return listRequestNeedToDone;
    }

    public void setListRequestNeedToDone(List<ServiceRequestDetailDTO> listRequestNeedToDone) {
        this.listRequestNeedToDone = listRequestNeedToDone;
    }
    
    

}
