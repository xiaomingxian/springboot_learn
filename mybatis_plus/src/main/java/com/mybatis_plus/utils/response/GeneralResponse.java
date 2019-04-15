package com.mybatis_plus.utils.response;

import java.io.Serializable;

/**
 * 通用RESTFULL response实体类
 * @author Kevin by kangshj
 * @version 1.0
 * @date 2019年2月15日
 */
public abstract class GeneralResponse implements Serializable{
	private static final long serialVersionUID = -5284549910598699863L;
	
	private boolean status;
	private String  code;
	private String msg;
	private Object data;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	protected GeneralResponse() {
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GeneralResponse [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
