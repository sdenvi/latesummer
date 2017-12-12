package com.latesummer.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共常量
 * 
 * @author suzhenwei
 * @date
 * 
 */
public class DDIC {

	/**
	 * 默认密码
	 */
	public static final String DEFAULT_PASSWORD = "670B14728AD9902AECBA32E22FA4F6BD";

	/**
	 * 用户角色
	 */
	public static final String USER_ROLE_ID_ADMIN = "admin";
	public static final String USER_ROLE_ID_ORG = "org";
	public static final String USER_ROLE_ID_PERSONAL = "personal";
	public static final String USER_ROLE_ID_USER = "user";

	/**
	 * 附件分类
	 */
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_LOGO = "logo";
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_ADVERT = "advert";
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_DETAIL = "detail";
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_PRODUCT = "Product";
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_USER = "User";
	/**
	 * 是否
	 */
	public static final String YESNO_YES = "yes";
	public static final String YESNO_NO = "no";
	public static final String YESNO_YES_TEXT = "是";
	public static final String YESNO_NO_TEXT = "否";
	public static Map<String, String> MAP_YESNO = new HashMap<String, String>();
	static {
		MAP_YESNO.put(YESNO_YES, YESNO_YES_TEXT);
		MAP_YESNO.put(YESNO_NO, YESNO_NO_TEXT);
	}
	/**
	 * 处理状态: 待处理、处理中、已处理、交易完毕、交易失败
	 */
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_PAY = "os_waitpay";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_SEND = "os_waitsend";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_RECEIVE = "os_waitreceive";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_COMMENT = "os_waitcom";
	public static final String DDIC_CODE_ORDER_STATUS_END = "os_end";

	public static final String DDIC_CODE_ORDER_STATUS_WAIT_PAY_TEXT = "待付款";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_SEND_TEXT = "待发货";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_RECEIVE_TEXT = "待收货";
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_COMMENT_TEXT = "待评价";
	public static final String DDIC_CODE_ORDER_STATUS_END_TEXT = "交易结束";

	public static Map<String, String> MAP_ORDER_STATUS = new HashMap<String, String>();
	static {
		MAP_ORDER_STATUS.put(DDIC_CODE_ORDER_STATUS_WAIT_PAY, DDIC_CODE_ORDER_STATUS_WAIT_PAY_TEXT);
		MAP_ORDER_STATUS.put(DDIC_CODE_ORDER_STATUS_WAIT_SEND, DDIC_CODE_ORDER_STATUS_WAIT_SEND_TEXT);
		MAP_ORDER_STATUS.put(DDIC_CODE_ORDER_STATUS_END, DDIC_CODE_ORDER_STATUS_END_TEXT);
		MAP_ORDER_STATUS.put(DDIC_CODE_ORDER_STATUS_WAIT_RECEIVE, DDIC_CODE_ORDER_STATUS_WAIT_RECEIVE_TEXT);
		MAP_ORDER_STATUS.put(DDIC_CODE_ORDER_STATUS_WAIT_COMMENT, DDIC_CODE_ORDER_STATUS_WAIT_COMMENT_TEXT);
	}

	/**
	 * 审核状态：待审核、审核通过、审核不通过
	 */
	public static final String DDIC_ITEM_CHECK_STATUS_WAIT = "cs_wait";
	public static final String DDIC_ITEM_CHECK_STATUS_SUCCESS = "cs_success";
	public static final String DDIC_ITEM_CHECK_STATUS_FAIL = "cs_fail";
	public static final String DDIC_ITEM_CHECK_STATUS_WAIT_TEXT = "待审核";
	public static final String DDIC_ITEM_CHECK_STATUS_SUCCESS_TEXT = "审核通过";
	public static final String DDIC_ITEM_CHECK_STATUS_FAIL_TEXT = "审核未通过";
	public static Map<String, String> MAP_CHECK_STATUS = new HashMap<String, String>();
	static {
		MAP_CHECK_STATUS.put(DDIC_ITEM_CHECK_STATUS_WAIT, DDIC_ITEM_CHECK_STATUS_WAIT_TEXT);
		MAP_CHECK_STATUS.put(DDIC_ITEM_CHECK_STATUS_SUCCESS, DDIC_ITEM_CHECK_STATUS_SUCCESS_TEXT);
		MAP_CHECK_STATUS.put(DDIC_ITEM_CHECK_STATUS_FAIL, DDIC_ITEM_CHECK_STATUS_FAIL_TEXT);
	}

	/**
	 * 读取状态 0 未读取 1 已读取
	 */
	public static final String DDIC_ITEM_READ_STATUS_YES = "1";
	public static final String DDIC_ITEM_READ_STATUS_NO = "0";
	public static final String DDIC_ITEM_READ_STATUS_YES_TEXT = "已读取";
	public static final String DDIC_ITEM_READ_STATUS_NO_TEXT = "未读取";

	public static Map<String, String> MAP_READ_STATUS = new HashMap<String, String>();
	static {
		MAP_READ_STATUS.put(DDIC_ITEM_READ_STATUS_YES, DDIC_ITEM_READ_STATUS_YES_TEXT);
		MAP_READ_STATUS.put(DDIC_ITEM_READ_STATUS_NO, DDIC_ITEM_READ_STATUS_NO_TEXT);
	}

	/**
	 * 招聘/培训状态 0 未读取 1 已读取
	 */
	public static final String RECUIT_STATUS_START = "start";
	public static final String RECUIT_STATUS_END = "end";
	public static final String RECUIT_STATUS_NOTSTART = "notstart";
	public static final String RECUIT_STATUS_START_TEXT = "进行中";
	public static final String RECUIT_STATUS_END_TEXT = "结束";
	public static final String RECUIT_STATUS_NOTSTART_TEXT = "未开始";
	public static Map<String, String> MAP_RECUIT_STATUS = new HashMap<String, String>();
	static {
		MAP_RECUIT_STATUS.put(RECUIT_STATUS_START, RECUIT_STATUS_START_TEXT);
		MAP_RECUIT_STATUS.put(RECUIT_STATUS_END, RECUIT_STATUS_END_TEXT);
		MAP_RECUIT_STATUS.put(RECUIT_STATUS_NOTSTART, RECUIT_STATUS_NOTSTART_TEXT);
	}
}
