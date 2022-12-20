package com.web.sbs.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.web.sbs.model.User;
import com.web.sbs.repository.UserRepository;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class UserAction extends ActionSupport  implements SessionAware, ParameterNameAware {
    @Autowired
    UserRepository userRepository;
    public static final String REMEMBER_TOKEN ="remember_token";
    public static final String USER ="USER";
    private boolean remember;


    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    private User user;


    private User[] users;

    private Map<String, Object> userSession;
    public User[] getUsers(){
        return users;
    }

    public String list(){


            users = userRepository.findAll().toArray(new User[0]);

        System.out.println(userSession.get(REMEMBER_TOKEN));
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {

        InetAddress IP =InetAddress.getLocalHost();
        if(user.getEmail().length() > 0){
            User userDB = userRepository.getUserByEmail(user.getEmail());
            userDB.setLastLogin(new Date());
            userDB.setIpLastLogin(IP.getHostAddress());
            if(userDB != null){

                boolean matchPassword = user.getPassword().equals( userDB.getPassword());
                if(matchPassword){
                    userSession.put(REMEMBER_TOKEN, user.getEmail());
                    userRepository.updateUser(userDB);

                    if(isRemember()){
//                        save to session
                    }
                    return SUCCESS;

                }

            }

        }

        return INPUT;

    }

//    public boolean isRemember(){
//        return  remember;
//    }

//    public void setRemember(boolean remember){
//        this.remember = remember;
//    }



    public List<User> getListUsers(){
        return userRepository.findAll();
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;

    }



    public void validate(){
        try {
            if (user.getEmail().length() == 0 || user.getEmail() == null) {
                addFieldError("emailHelp", "Email không được để trống");
            }

            if (user.getPassword().length() == 0) {
                addFieldError("passwordHelp", "Password không được để trống");
            }
        }catch (Exception e){}

    }

    @Override
    public boolean acceptableParameterName(String parameterName) {
        if(parameterName.contains("session") || parameterName.contains("request"))
            return false;
        return true;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }


}
