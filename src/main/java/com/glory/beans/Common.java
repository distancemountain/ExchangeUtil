package com.glory.beans;

import com.glory.common.BaseForm;

/**
 * @Author : glory
 * @since  : 2020-11-18
 */
public class Common extends BaseForm {

	private String accessKey;
	private String secretkey;
	private String accountId;
	private String requestId;
	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public String getAccountId() {
		return accountId;
	}
	
	/**
	 * 可不传
	 * @param accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
}
