package com.web.sbs.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.web.sbs.model.User;
import com.web.sbs.repository.UserRepository;
import com.web.sbs.utitls.PageUtils;
import com.web.sbs.utitls.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserAction extends ActionSupport  implements SessionAware, ParameterNameAware, ServletResponseAware, ServletRequestAware {
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
    private String nameCurrentUser;

    private List<User> users;

    private Map<String, Object> userSession;
    public List<User> getUsers(){
        return users;
    }
//    getter setter ...

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

    public List<User> getListUsers(){
        return userRepository.findAll();
    }



    public User getUser(){
        return user;
    }

    public void setUser(User user){

        this.user = user;

    }
//FUNCTION
// For access to the raw servlet request / response, eg for cookies
    protected HttpServletResponse servletResponse;
    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }


// list user
    public String list(){
        if(getRememberToken() == null && userSession.get(REMEMBER_TOKEN) == null)
            return "login";

        int totalRecord = userRepository.findAll().size();
        PageUtils pageUtils = new PageUtils(page, size, totalRecord);
        setTotalPages(pageUtils.getTotalPages());
        this.users = userRepository.findUsers(pageUtils.getNext(), size);
        System.out.println(users);
        System.out.println(userSession.get(USER));
        return SUCCESS;
    }

//    insert user
    public String insert(){
        User user = new User(email, name, groups, active, password);
        user.setCreatedAt(new Date());
        user.setDelete(false);
        System.out.println("INSERT "+ user);
        try {
            userRepository.addUser(user);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }

//    update user
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
//    search
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



        System.out.println(userSession.get(USER));
        return SUCCESS;
    }

//    get user
    public String get(){

        try {
            this.user =  userRepository.getUser(email);
            System.out.println("USER "+ user);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }
// delete usser
    public String delete(){
        try {
            userRepository.deleteUser(email);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }
// change status user
    public String toogleLockUser(){
        try {
            userRepository.toogleLockUser(active, email);
        }catch (Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }


    public String checkLogin(){
        if(getRememberToken() != null)
            return SUCCESS;
        return "login";

    }
    public String login() throws Exception{
        InetAddress IP =InetAddress.getLocalHost();
        String token = UUID.randomUUID().toString();
//        check if exists token cookie  or session on browser
        if(user==null){
            if(getRememberToken()== null && userSession.get(REMEMBER_TOKEN) == null)
                return SUCCESS;
            return "manager";
        }

// check on form

        if(user.getEmail().length() > 0){
            User userDB = userRepository.getUserByEmail(user.getEmail());

            try {
                if(userDB != null){
                    userDB.setLastLogin(new Date());
                    userDB.setIpLastLogin(IP.getHostAddress());
                    userDB.setRememberToken(token);

                    boolean matchPassword = user.getPassword().equals( userDB.getPassword());
                    if(matchPassword){
                        userRepository.updateLastLogin(userDB);
                        userSession.put(REMEMBER_TOKEN, token);
                        userSession.put(USER, userDB.getName());
                        if(isRemember()){
//                        save to session
                            System.out.println(userDB);
                            Cookie cookie = new Cookie(REMEMBER_TOKEN, token);
                            String name = StringUtils.removeAccent(userDB.getName()).replace(" ", "_");
                            Cookie cookieName = new Cookie(USER, name);
                            servletResponse.addCookie(cookie);
                            servletResponse.addCookie(cookieName);
                        }

                        return "manager";

                    }

                }
            }catch (Exception e){
                System.out.println(e);
            }

        }

        return INPUT;

    }
//  load page manage
    @Override
    public String execute() throws Exception {
        System.out.println("execute");
        InetAddress IP =InetAddress.getLocalHost();
        String token = UUID.randomUUID().toString();
        String tokenRequest = getRememberToken();


        if(tokenRequest != null){

            User userDB = userRepository.findUserByToken(tokenRequest);
            if(userDB != null){
                return SUCCESS;
            }
        }
        return "login";
    }

    public String getRememberToken(){
        if(servletRequest.getCookies() != null)
            for(Cookie c : servletRequest.getCookies()){
                if(c.getName().equals(REMEMBER_TOKEN))
                    return c.getValue();
            }
        return null;
    }
    public String getNameCurrentUser(){
        for(Cookie c : servletRequest.getCookies()){
            if(c.getName().equals(USER))
                return c.getValue();
        }
        return null;
    }







    public void validate(){

        try {
            User userDB = userRepository.getUserByEmail(user.getEmail());
//            boolean matchPassword = user.getPassword().equals( userDB.getPassword());
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(user.getEmail());
            if (user.getEmail().length() == 0 || user.getEmail() == null) {
                addFieldError("a", "Email không được để trống");
            }else{

                if(!matcher.find()){
                    addFieldError("d", "Email không đúng định dạng");
                }
            }

            if (user.getPassword().length() == 0) {
                addFieldError("b", "Password không được để trống");

            }


            if(userDB == null){
                addFieldError("c", "Tài khoản hoặc mật khẩu không chính xác");
            }



        }catch (Exception e){
        }

    }

    @Override
    public boolean acceptableParameterName(String parameterName) {
        return !parameterName.contains("session") && !parameterName.contains("request");
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
    }



}
