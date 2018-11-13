/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamtt.action;


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
import lamtt.dto.ConfigurationDTO;
import lamtt.dto.CyberGamingDTO;
import lamtt.dto.RoomDTO;

/**
 *
 * @author royal
 */
public class CyberInfoAction {
    
    private final String SUCCESS = "success";
    private CyberGamingDTO cyberDetail;
    private final String URL_GET_CYBER = "https://swd-backend-admin.herokuapp.com/cybers/%d";
    private final String URL_GET_ROOM = "https://swd-backend-admin.herokuapp.com/rooms/getByCyberId/%d";
    private final String URL_GET_CONFIG = "https://swd-backend-admin.herokuapp.com/configurations/getByCyberId/%d";
    private List<RoomDTO> listRoom;
    private List<ConfigurationDTO> listConfig;
    
    public CyberInfoAction() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CyberGamingDTO cyberGamingDTO = (CyberGamingDTO) session.get("CYBERDETAIL");
        try {
            URL url = new URL(String.format(URL_GET_CYBER, cyberGamingDTO.getId()));
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
                cyberDetail = gson.fromJson(output, CyberGamingDTO.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            URL url = new URL(String.format(URL_GET_ROOM, cyberGamingDTO.getId()));
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
                TypeToken<List<RoomDTO>> typeToken = new TypeToken<List<RoomDTO>>(){};
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                listRoom = gson.fromJson(output, typeToken.getType());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            URL url = new URL(String.format(URL_GET_ROOM, cyberGamingDTO.getId()));
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
                TypeToken<List<ConfigurationDTO>> typeToken = new TypeToken<List<ConfigurationDTO>>(){};
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                listConfig = gson.fromJson(output, typeToken.getType());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public CyberGamingDTO getCyberDetail() {
        return cyberDetail;
    }

    public void setCyberDetail(CyberGamingDTO cyberDetail) {
        this.cyberDetail = cyberDetail;
    }

    public List<RoomDTO> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<RoomDTO> listRoom) {
        this.listRoom = listRoom;
    }

    public List<ConfigurationDTO> getListConfig() {
        return listConfig;
    }

    public void setListConfig(List<ConfigurationDTO> listConfig) {
        this.listConfig = listConfig;
    }
    
    
    
}
