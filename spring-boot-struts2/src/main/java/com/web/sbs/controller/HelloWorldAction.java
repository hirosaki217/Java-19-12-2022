package com.web.sbs.controller;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class HelloWorldAction extends ActionSupport {

    private static final long serialVersionUID = 7475177902802386554L;

    @Override
    public String execute() throws Exception {
	return SUCCESS;
    }

}
