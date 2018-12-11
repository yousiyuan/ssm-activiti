package com.activiti.flow.variables;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 自定义流程变量
 */
public class Employee implements Serializable {

	/**
	 * 版本号
	 */
	@JSONField(serialize = false, deserialize = false)
	private static final long serialVersionUID = -2595494512739876876L;

	/**
	 * 员工编号
	 */
	private String empCode;
	/**
	 * 员工姓名
	 */
	private String empName;
	/**
	 * 性别（1：男；0：女）
	 */
	private Integer gender;
	/**
	 * 岗位
	 */
	private String position;
	/**
	 * 入职日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date entryDate;
	/**
	 * 离职日期
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date quitDate;
	/**
	 * 离职原因
	 */
	private String reason;

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames);
	}
}
