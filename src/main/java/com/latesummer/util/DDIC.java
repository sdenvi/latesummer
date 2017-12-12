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

	// 默认页码
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	// 默认密码
	public static final String DEFAULT_PASSWORD = "670B14728AD9902AECBA32E22FA4F6BD";

	public static final String SHOW_PRODUCT_VIEW_URL = "http://yaojixie.com/mach/pages/mobile/m_home.jsp";

	/**
	 * 用户角色
	 */
	public static final String USER_ROLE_ID_ADMIN = "admin";// 系统管理员
	public static final String USER_ROLE_ID_ORG = "org";// 企业用户（厂家卖方）
	public static final String USER_ROLE_ID_PERSONAL = "personal";// 个人用户（厂家卖方）
	public static final String USER_ROLE_ID_USER = "user";// 买家（购买方）

	// 用户类别 org 商家（企业）、personal 客户（个人）
	public static final String DDIC_USER_TYPE_ORG = USER_ROLE_ID_ORG;
	public static final String DDIC_USER_TYPE_PERSONAL = USER_ROLE_ID_PERSONAL;
	public static final String DDIC_USER_TYPE_ORG_TEXT = "企业用户";
	public static final String DDIC_USER_TYPE_PERSONAL_TEXT = "个人用户";

	public static Map<String, String> MAP_USER_TYPE = new HashMap<String, String>();
	static {
		MAP_USER_TYPE.put(DDIC_USER_TYPE_ORG, DDIC_USER_TYPE_ORG_TEXT);
		MAP_USER_TYPE.put(DDIC_USER_TYPE_PERSONAL, DDIC_USER_TYPE_PERSONAL_TEXT);
	}

	// 根目录
	public static final String DDIC_ITEM_ROOT_ID = "root_id";

	public static final String BLANK_IMG_PATH = "/mach/styles/mobile/blank_img.png";
	public static final String DEFAULT_HEADER_IMG_PATH = "/mach/styles/mobile/header_admin.jpg";

	/**
	 * 附件分类
	 */
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_LOGO = "logo";// logo
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_ADVERT = "advert";// 广告
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_DETAIL = "detail";// 产品详细
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_PRODUCT = "Product";// 产品
	public static final String DDIC_ITEM_ATTACHEMENT_CATEGOY_USER = "User";// 用户
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
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_PAY = "os_waitpay";// 待支付
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_SEND = "os_waitsend";// 待发货
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_RECEIVE = "os_waitreceive";// 待收货
	public static final String DDIC_CODE_ORDER_STATUS_WAIT_COMMENT = "os_waitcom";// 待评价
	public static final String DDIC_CODE_ORDER_STATUS_END = "os_end";// 交易结束

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

	// 评论类别：评论、回复
	public static final String DDIC_ITEM_COMMENT_TYPE_COM = "ct_comment";
	public static final String DDIC_ITEM_COMMENT_TYPE_REPLY = "ct_reply";
	public static final String DDIC_ITEM_COMMENT_TYPE_COM_TEXT = "评论";
	public static final String DDIC_ITEM_COMMENT_TYPE_REPLY_TEXT = "回复";
	public static Map<String, String> MAP_COMMENT_TYPE = new HashMap<String, String>();
	static {
		MAP_COMMENT_TYPE.put(DDIC_ITEM_COMMENT_TYPE_COM, DDIC_ITEM_COMMENT_TYPE_COM_TEXT);
		MAP_COMMENT_TYPE.put(DDIC_ITEM_COMMENT_TYPE_REPLY, DDIC_ITEM_COMMENT_TYPE_REPLY_TEXT);
	}

	// 收藏类别：商家、产品
	public static final String DDIC_ITEM_FAVORITE_TYPE_SUPPLIER = "ft_supplier";
	public static final String DDIC_ITEM_FAVORITE_TYPE_PRODUCT = "ft_product";
	public static final String DDIC_ITEM_FAVORITE_TYPE_SUPPLIER_TEXT = "店铺";
	public static final String DDIC_ITEM_FAVORITE_TYPE_PRODUCT_TEXT = "产品";
	public static Map<String, String> MAP_FAVORITE_TYPE = new HashMap<String, String>();
	static {
		MAP_FAVORITE_TYPE.put(DDIC_ITEM_FAVORITE_TYPE_SUPPLIER, DDIC_ITEM_FAVORITE_TYPE_SUPPLIER_TEXT);
		MAP_FAVORITE_TYPE.put(DDIC_ITEM_FAVORITE_TYPE_PRODUCT, DDIC_ITEM_FAVORITE_TYPE_PRODUCT_TEXT);
	}

	// 支付状态：已支付、未支付
	public static final String DDIC_ITEM_PAY_STATUS_YES = "1";
	public static final String DDIC_ITEM_PAY_STATUS_NO = "0";
	public static final String DDIC_ITEM_PAY_STATUS_YES_TEXT = "已支付";
	public static final String DDIC_ITEM_PAY_STATUS_NO_TEXT = "未支付";
	public static Map<String, String> MAP_PAY_STATUS = new HashMap<String, String>();
	static {
		MAP_PAY_STATUS.put(DDIC_ITEM_PAY_STATUS_YES, DDIC_ITEM_PAY_STATUS_YES_TEXT);
		MAP_PAY_STATUS.put(DDIC_ITEM_PAY_STATUS_NO, DDIC_ITEM_PAY_STATUS_NO_TEXT);
	}

	// 审核状态：待审核、审核通过、审核不通过
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

	// 评价结果
	public static final String DDIC_ITEM_COMMENT_RESULT_GOOD = "good";
	public static final String DDIC_ITEM_COMMENT_RESULT_MEDIUM = "medium";
	public static final String DDIC_ITEM_COMMENT_RESULT_BAD = "bad";
	public static final String DDIC_ITEM_COMMENT_RESULT_GOOD_TEXT = "好评";
	public static final String DDIC_ITEM_COMMENT_RESULT_MEDIUM_TEXT = "中评";
	public static final String DDIC_ITEM_COMMENT_RESULT_BAD_TEXT = "差评";
	public static Map<String, String> MAP_COMMENT_RESULT = new HashMap<String, String>();
	static {
		MAP_COMMENT_RESULT.put(DDIC_ITEM_COMMENT_RESULT_GOOD, DDIC_ITEM_COMMENT_RESULT_GOOD_TEXT);
		MAP_COMMENT_RESULT.put(DDIC_ITEM_COMMENT_RESULT_MEDIUM, DDIC_ITEM_COMMENT_RESULT_MEDIUM_TEXT);
		MAP_COMMENT_RESULT.put(DDIC_ITEM_COMMENT_RESULT_BAD, DDIC_ITEM_COMMENT_RESULT_BAD_TEXT);
	}

	// 读取状态 0 未读取 1 已读取
	public static final String DDIC_ITEM_READ_STATUS_YES = "1";
	public static final String DDIC_ITEM_READ_STATUS_NO = "0";
	public static final String DDIC_ITEM_READ_STATUS_YES_TEXT = "已读取";
	public static final String DDIC_ITEM_READ_STATUS_NO_TEXT = "未读取";

	public static Map<String, String> MAP_READ_STATUS = new HashMap<String, String>();
	static {
		MAP_READ_STATUS.put(DDIC_ITEM_READ_STATUS_YES, DDIC_ITEM_READ_STATUS_YES_TEXT);
		MAP_READ_STATUS.put(DDIC_ITEM_READ_STATUS_NO, DDIC_ITEM_READ_STATUS_NO_TEXT);
	}

	// 招聘/培训状态 0 未读取 1 已读取
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

	// 交易状态 ：income收入、expense 支出
	public static final String DDIC_FUND_STATUS_INCOM = "income";
	public static final String DDIC_FUND_STATUS_EXPENSE = "expense";
	public static final String DDIC_FUND_STATUS_INCOM_TEXT = "收入";
	public static final String DDIC_FUND_STATUS_EXPENSE_TEXT = "支出";

	public static Map<String, String> MAP_FUND_STATUS = new HashMap<String, String>();
	static {
		MAP_FUND_STATUS.put(DDIC_FUND_STATUS_INCOM, DDIC_FUND_STATUS_INCOM_TEXT);
		MAP_FUND_STATUS.put(DDIC_FUND_STATUS_EXPENSE, DDIC_FUND_STATUS_EXPENSE_TEXT);
	}

	// 产品类别：1：新产品 2：二手 产品3：租赁产品
	public static final String DDIC_PRODUCT_TYPE_1 = "1";
	public static final String DDIC_PRODUCT_TYPE_2 = "2";
	public static final String DDIC_PRODUCT_TYPE_3 = "3";
	public static final String DDIC_PRODUCT_TYPE_4 = "4";
	public static final String DDIC_PRODUCT_TYPE_1_TEX = "新机销售";
	public static final String DDIC_PRODUCT_TYPE_2_TEX = "二手产品";
	public static final String DDIC_PRODUCT_TYPE_3_TEX = "租赁服务";
	public static final String DDIC_PRODUCT_TYPE_4_TEX = "配件市场";

	public static Map<String, String> MAP_PRODUCT_TYPE = new HashMap<String, String>();
	static {
		MAP_PRODUCT_TYPE.put(DDIC_PRODUCT_TYPE_1, DDIC_PRODUCT_TYPE_1_TEX);
		MAP_PRODUCT_TYPE.put(DDIC_PRODUCT_TYPE_2, DDIC_PRODUCT_TYPE_2_TEX);
		MAP_PRODUCT_TYPE.put(DDIC_PRODUCT_TYPE_3, DDIC_PRODUCT_TYPE_3_TEX);
	}
	// 产品筛选：1：新产品 2：二手 产品3：租赁产品
	public static final String DDIC_CATE_LEVEL_1 = "1";
	public static final String DDIC_CATE_LEVEL_2 = "2";
	public static final String DDIC_CATE_LEVEL_3 = "3";
	public static final String DDIC_CATE_LEVEL_4 = "4";
	public static final String DDIC_CATE_LEVEL_1_TEXT = "机械分类";
	public static final String DDIC_CATE_LEVEL_2_TEXT = "机械种类";
	public static final String DDIC_CATE_LEVEL_3_TEXT = "机械型号";
	public static final String DDIC_CATE_LEVEL_4_TEXT = "厂家品牌";

	public static Map<String, String> MAP_CATE_LEVEL = new HashMap<String, String>();
	static {
		MAP_CATE_LEVEL.put(DDIC_CATE_LEVEL_1, DDIC_CATE_LEVEL_1_TEXT);
		MAP_CATE_LEVEL.put(DDIC_CATE_LEVEL_2, DDIC_CATE_LEVEL_2_TEXT);
		MAP_CATE_LEVEL.put(DDIC_CATE_LEVEL_3, DDIC_CATE_LEVEL_3_TEXT);
		MAP_CATE_LEVEL.put(DDIC_CATE_LEVEL_4, DDIC_CATE_LEVEL_4_TEXT);
	}

	// 店铺默认级别
	public static final String DDIC_STORE_LEVEL_1 = "1";
}
