package com.yc.crbook.bean;

public class Result {

	private int code; //0 失败 1 成功
	
	private String msg; //返回的消息
	
	private Object data; //返回的数据
	
	/**
	 * javabean规范
	 * 必须要为每一个bean定义无参的构造函数
	 */
	public Result() {
		super();
	}

	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	

	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
