package com.activiti.demo.commons;

/**
 * Sequence flow type
 */
public enum SequenceflowTypeEnum {

	/**
	 * INCOMING
	 */
	FLOW_IN("INCOMING"),
	/**
	 * OUTGOING
	 */
	FLOW_OUT("OUTGOING");

	/**
	 * enum value
	 */
	private String value;

	/**
	 * get enum value
	 * 
	 * @return enum value
	 */
	public String getValue() {
		return this.value;
	}

	private SequenceflowTypeEnum(String value) {
		this.value = value;
	}

}
