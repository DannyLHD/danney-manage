package com.kj.util;

/**
 * CodeBook值
 * @author chen
 *
 */
public class CodeBookConsts {

	/**
	 * 身份权限：普通用户
	 */
	public static final Integer ROLE_AUTH_ORDINARY=0;

	/**
	 * 身份权限：单位管理员
	 */
	public static final Integer ROLE_AUTH_UNIT_ADMIN=1;
	
	/**
	 * 身份权限：子公司管理员
	 */
	public static final Integer ROLE_AUTH_CHILD_ADMIN=2;
	
	/**
	 * 身份权限：总公司管理员
	 */
	public static final Integer ROLE_AUTH_HEAD_ADMIN=3;
	
	/**
	 * 身份权限：超级管理员
	 */
	public static final Integer ROLE_AUTH_SUPER_ADMIN=4;
	/**
	 * 科技类型，通知
	 */
	public static final int TYPE_INFORM=1;
	/**
	 * 科技类型，动态
	 */
	public static final int TYPE_DYNAMIC=0;
	/**
	 * 未审核
	 */
	public static final Integer UNCHECKED=0;
	/**
	 * 审核通过
	 */
	public static final Integer CHECK_APPROVED=1;
	/**
	 * 审核未通过
	 */
	public static final Integer CHECK_UNAPPROVED=-1;
	
	/**
	 * 保存状态
	 */
	public static final Integer STATUS_SAVE=0;
	/**
	 * 提交状态
	 */
	public static final Integer STATUS_SUBMIT=1;
	
	/**
	 * 申报
	 */
	public static final Integer STAGE_APPLICATION=1;
	
	/**
	 * 立项
	 */
	public static final Integer STAGE_ESTABLISHMENT=2;
	/**
	 * 执行
	 */
	public static final Integer STAGE_EXECUTION=3;
	/**
	 * 验收
	 */
	public static final Integer STAGE_ACCEPTANCE=4;
}
