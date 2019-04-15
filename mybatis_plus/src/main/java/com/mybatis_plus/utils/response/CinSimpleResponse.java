package com.mybatis_plus.utils.response;

/**
 *  simple impl
 * @author Kevin by kangshj
 * @version 1.0
 * @date 2019年2月19日
 */
public class CinSimpleResponse extends GeneralResponse{
	private static final long serialVersionUID = 3365667980491146389L;
	private static final String SUCCESS = "";
	private static final String SUCCESS_CODE = "0";
	private static final String FAIL_CODE = "300";

	public CinSimpleResponse() {
	}
	
	public static CinSimpleResponse getSuccessResponse(Object data) {
		CinSimpleResponse simpleResponse = new CinSimpleResponse();
		simpleResponse.setStatus(true);
		simpleResponse.setMsg(SUCCESS);
		simpleResponse.setCode(SUCCESS_CODE);
		simpleResponse.setData(data);
		return simpleResponse;
	}
	
	public static CinSimpleResponse getFailResponse(String failMsg) {
		CinSimpleResponse simpleResponse = new CinSimpleResponse();
		simpleResponse.setStatus(false);
		simpleResponse.setCode(FAIL_CODE);
		simpleResponse.setMsg(failMsg);
		simpleResponse.setData(null);
		return simpleResponse;
	}
}
