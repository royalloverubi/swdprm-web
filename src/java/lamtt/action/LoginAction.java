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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import lamtt.dto.AccountDTO;
import lamtt.dto.CyberGamingDTO;

/**
 *
 * @author royal
 */
public class LoginAction {

    private String username;
    private String password;
    private String messageErr;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String URL_GET_CYBER = "https://swd-backend-admin.herokuapp.com/cybers/getByAccountId/%d";

    public LoginAction() {
    }

    public String execute() throws Exception {
        String result = FAIL;
        String urlString = "https://swd-backend-admin.herokuapp.com/authencation/checkLogin?password=" + password + "&username=" + username;
        AccountDTO dto = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;

            messageErr = "Username or password is incorrect. Try again!!!";
            while ((output = br.readLine()) != null) {
                Gson g = new Gson();
                try {
                    dto = g.fromJson(output, AccountDTO.class);
                    if (dto.getRole().endsWith("cyber")) {
                        Map session = ActionContext.getContext().getSession();
                        session.put("CYBER", dto);
                        result = SUCCESS;
                    } else {
                        messageErr = "Your account is not allowed to access this page!!!";
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (dto != null) {
            try {

                URL url = new URL(String.format(URL_GET_CYBER, dto.getId()));
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
                    CyberGamingDTO cyberGamingDTO = gson.fromJson(output, CyberGamingDTO.class);
                    if(cyberGamingDTO != null) {
                        Map session = ActionContext.getContext().getSession();
                        session.put("CYBERDETAIL", cyberGamingDTO);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessageErr() {
        return messageErr;
    }

    public void setMessageErr(String messageErr) {
        this.messageErr = messageErr;
    }

}
