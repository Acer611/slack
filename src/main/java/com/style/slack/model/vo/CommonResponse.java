package com.style.slack.model.vo;

import com.style.slack.common.constant.ErrorConstant;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CommonResponse implements Serializable {
	private static final long serialVersionUID = 2029838219398373649L;
	/**
	 * 错误返回码，正常时返回为0
	 */
	@ApiModelProperty(value="错误返回码，正常时返回为0")
	private Integer retCode = ErrorConstant.SUCCESS;
	/**
	 * 错误消息
	 */
	@ApiModelProperty(value="错误消息")
	private String retMsg = "success";

	public CommonResponse() {
	}

	public CommonResponse(Integer retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public Integer getRetCode() {
		return retCode;
	}
	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public static CommonResponse createEmptyParameterResponse(String retMsg){
		class EmptyParameterResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.EMPTY_PARAMETER;
			}
		}

		CommonResponse response = new EmptyParameterResponse();
		response.setRetMsg(retMsg);
		return response;
	}

	public static CommonResponse createInvalidParameterResponse(String retMsg){
		class InvalidParameterResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.INVALID_PARAMETER;
			}
		}

		CommonResponse response = new InvalidParameterResponse();
		response.setRetMsg(retMsg);
		return response;
	}

	public static CommonResponse createBadCredentialsResponse(){
		class BadCredentialsResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.BAD_CREDENTIALS;
			}

			@Override
			public String getRetMsg() {
				return "用户名或密码不正确！";
			}
		}

		CommonResponse response = new BadCredentialsResponse();
		return response;
	}
	public static CommonResponse createUserRepeatResponse(){
		class UserRepeatResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.USER_REPEAT;
			}

			@Override
			public String getRetMsg() {
				return "用户名重复！";
			}
		}

		return new UserRepeatResponse();
	}

	public static CommonResponse createNotFoundResponse(String retMsg){
		class NotFoundResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_FOUND;
			}
		}

		CommonResponse response = new NotFoundResponse();
		response.setRetMsg(retMsg);
		return response;
	}

	public static CommonResponse createNotJOINResponse() {
		class NotJOINResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_JOIN;
			}

			@Override
			public String getRetMsg() {
				return "当前用户还没有注册，请注册后登陆。";
			}
		}

		CommonResponse response = new NotJOINResponse();
		return response;
	}

	public static CommonResponse createNotSendMassageResponse() {
		class NotSendMassageResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.SMS_SEND_FAIL;
			}

			@Override
			public String getRetMsg() {
				return "短信发送失败,请重新获取！";
			}
		}

		CommonResponse response = new NotSendMassageResponse();
		return response;
	}
	public static CommonResponse createNotSendMailResponse() {
		class NotSendMailResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.MAIL_SEND_FAIL;
			}

			@Override
			public String getRetMsg() {
				return "邮件发送失败！";
			}
		}

		CommonResponse response = new NotSendMailResponse();
		return response;
	}

	public static CommonResponse createNotChinesePhoneNumberResponse() {
		class NotSendMassageResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_CHINESE_PHONE_NUMBER;
			}

			@Override
			public String getRetMsg() {
				return "该手机号不是大陆地区的，暂不支持发送验证码短信！";
			}
		}

		CommonResponse response = new NotSendMassageResponse();
		return response;
	}

	public static CommonResponse createNotHasUserResponse() {
		class NotHasUserResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_USER;
			}

			@Override
			public String getRetMsg() {
				return "未能成功创建小组，原因是该小组的组长尚未加入，不能成为小组组长！";
			}
		}

		CommonResponse response = new NotHasUserResponse();
		return response;
	}

	public static CommonResponse createNotSamePassResponse() {
		class NotSamePassResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_INVALID_PASSWORD;
			}

			@Override
			public String getRetMsg() {
				return "旧密码不正确，不能修改密码！";
			}
		}

		CommonResponse response = new NotSamePassResponse();
		return response;
	}

	public static CommonResponse createBadSmsVerificationCodeResponse() {
		class BadSmsVerificationCodeResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.BAD_SMS_VERIFICATION_CODE;
			}

			@Override
			public String getRetMsg() {
				return "手机验证码不正确或已过期";
			}
		}

		return new BadSmsVerificationCodeResponse();
	}

	public static CommonResponse createBadInvitationCodeResponse() {
		class BadInvitationCodeResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.BAD_INVITATION_CODE;
			}

			@Override
			public String getRetMsg() {
				return "邀请码不正确或不存在";
			}
		}

		return new BadInvitationCodeResponse();
	}

	public static CommonResponse createBadVerificationCodeResponse() {
		class BadVerificationCodeResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.BAD_VERIFICATION_CODE;
			}

			@Override
			public String getRetMsg() {
				return "验证码不正确";
			}
		}

		return new BadVerificationCodeResponse();
	}

	public static CommonResponse createNotAssignedResponse() {
		class NotAssignedResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.NOT_ASSIGNED;
			}

			@Override
			public String getRetMsg() {
				return "团队中还有成人游客未分到房间，不能保存房间方案。务必把成人游客安排到房间中！";
			}
		}

		return new NotAssignedResponse();
	}

	public static CommonResponse createExistPhoneResponse() {
		class ExistPhoneResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.EXIST_PHONENUMBER;
			}

			@Override
			public String getRetMsg() {
				return "手机号码已经被使用";
			}
		}

		return new ExistPhoneResponse();
	}

	public static CommonResponse createNotAllowed(String retMsg) {
		class NotAllowedResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.METHOD_NOT_ALLOWED;
			}

		}

		CommonResponse response = new NotAllowedResponse();
		response.setRetMsg(retMsg);
		return response;
	}

	public static CommonResponse createUnauthorizedUser(String retMsg) {
		class UnauthorizedUserResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.UNAUTHORIZED_USER;
			}

		}

		CommonResponse response = new UnauthorizedUserResponse();
		response.setRetMsg(retMsg);
		return response;
	}

	public static CommonResponse createUserNotLoginResponse(){
		class UserNotLoginResponse extends CommonResponse {

			@Override
			public Integer getRetCode() {
				return ErrorConstant.USER_NOT_LOGIN;
			}

			@Override
			public String getRetMsg() {
				return "用户未登录或登录信息过期";
			}
		}

		return new UserNotLoginResponse();
	}


	public static CommonResponse createTimGroupErrorResponse(Integer retCode){
		return new CommonResponse(retCode,"创建腾迅云群组失败");
	}

	@Override
	public String toString() {
		return "CommonRespone {retCode=" + retCode + ", retMsg=" + retMsg + "}";
	}

}
