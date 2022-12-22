package com.web.sbs.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.web.sbs.model.User;
import com.web.sbs.repository.UserRepository;
import com.web.sbs.utitls.PageUtils;
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

    private int page;
    private int size = 5;

    private int totalPages = 0;

    private String name, email, groups, password;
    private boolean active;

    private User user;


    private List<User> users;

    private Map<String, Object> userSession;
    public List<User> getUsers(){
        return users;
    }


    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }



    public String list(){
//        if(userSession.get(USER) == null){
//            return "login";
//        }
            int totalRecord = userRepository.findAll().size();
            PageUtils pageUtils = new PageUtils(page, size, totalRecord);
            setTotalPages(pageUtils.getTotalPages());
            this.users = userRepository.findUsers(pageUtils.getNext(), size);
        System.out.println(users);
        System.out.println(userSession.get(USER));
        return SUCCESS;
    }
    public String insert(){
        User user = new User(email, name, groups, active, password);
        user.setCreatedAt(new Date());
        System.out.println("INSERT "+ user);
        try {
            userRepository.addUser(user);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }

    public String update(){
        User user = new User(email, name, groups, active, password);
//        user.setCreatedAt(new Date());
        try {
            userRepository.updateUser(user);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }
    public String search() throws Exception{
        User user = new User(email, name, groups, active);
        System.out.println(user);
        try {
            userRepository.findUserByOption(user);

        }catch (Exception e){
            System.out.println(e);
        }
        int totalRecord = userRepository.findUserByOption(user).size();

        PageUtils pageUtils = new PageUtils(page, size, totalRecord);
        setTotalPages(pageUtils.getTotalPages());
        this.users = userRepository.findUserByOptions(user, pageUtils.getNext(), size);
        System.out.println("DÂSSSASDADSASD" + totalRecord);

        System.out.println(users);


        System.out.println(userSession.get(USER));
        return SUCCESS;
    }
    public String get(){

        try {
            this.user =  userRepository.getUser(email);
            System.out.println("USER "+ user);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }

    public String delete(){
        try {
            userRepository.deleteUser(email);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }

    public String toogleLockUser(){
        try {
            userRepository.toogleLockUser(active, email);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        InetAddress IP =InetAddress.getLocalHost();

        if(user.getEmail().length() > 0){

            User userDB = userRepository.getUserByEmail(user.getEmail());
            userDB.setLastLogin(new Date());
            userDB.setIpLastLogin(IP.getHostAddress());

            try {
                if(userDB != null){

                    boolean matchPassword = user.getPassword().equals( userDB.getPassword());
                    if(matchPassword){

                        userSession.put(USER, user.getEmail());
                        userRepository.updateUser(userDB);

                        if(isRemember()){
                            System.out.println("REMMEMBER ");
//                        save to session
                        }
                        System.out.println(userDB);
                        return SUCCESS;

                    }

                }
            }catch (Exception e){
                System.out.println(e);
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
                addFieldError("user.email", "Email không được để trống");
            }

            if (user.getPassword().length() == 0) {
                addFieldError("user.password", "Password không được để trống");
            }
        }catch (Exception e){}

    }

    @Override
    public boolean acceptableParameterName(String parameterName) {
        return !parameterName.contains("session") && !parameterName.contains("request");
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public void setIsActive(boolean active) {
        this.active = active;
    }
    public boolean getIsActive() {
        return active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
