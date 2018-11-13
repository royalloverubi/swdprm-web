/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamtt.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author royal
 */
public class LogoutAction {
    
    private final String SUCCESS = "success";
    
    public LogoutAction() {
    }
    
    public String execute() throws Exception {
        
        Map session = ActionContext.getContext().getSession();
        session.remove("CYBER");
        session.remove("CYBERDETAIL");
        return SUCCESS;
    }
    
}
