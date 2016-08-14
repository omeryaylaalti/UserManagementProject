package com.omeryaylaalti.usermanagement.service;

public interface RecaptchaService {

	boolean isResponseValid(String remoteIp, String response);

}
