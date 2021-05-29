package com.dithok.myCommerce.exception;

import org.springframework.lang.Nullable;

public enum DbErrorStatus {

	/**
	 * {@code 1000 Execution Failed}.
	 */

	EXECUTION_FAILED(1000, "Execution Failed"),

	/**
	 * {@code 1001 Invalid Data}.
	 */
	INVALID_DATA(1001, "Invalid Data"),
	/**
	 * {@code 1002 Data Not Found}.
	 */
	DATA_NOT_FOUND(1002, "Data Not Found"),
	/**
	 * {@code 1003 Null Value}.
	 */
	NULL_VALUE(1003, "Null Value"),
	/**
	 * {@code 1004 Multiple Data}.
	 */
	MULT_DATA(1004, "Multiple Data"),
	/**
	 * {@code 1005 Duplicate Data}.
	 */
	DUPLICATE_DATA(1005, "Duplicate Data"),
	/**
	 * {@code 1015 Duplicate Email}.
	 */
	DUPLICATE_DATA_EMAIL(1015, "Duplicate Email"),
	/**
	 * {@code 1025 Duplicate Phone Number}.
	 */
	DUPLICATE_DATA_PHONE(1025, "Duplicate Phone Number"),
	
	DUPLICATE_DATA_PRODUCTNAME(1035, "Duplicate Product Name"),
	DUPLICATE_DATA_OPTIONVALUE(1045, "Duplicate Option Name"),
	DUPLICATE_DATA_PRODUCTCATEGORY(1055, "Duplicate Product Category Name");
	;

	private final int value;
	private final String reasonPhrase;

	/**
	 * @return the value
	 */
	public int value() {
		return value;
	}

	/**
	 * @return the reasonPhrase
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * @param value
	 * @param reasonPhrase
	 */
	private DbErrorStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return this.value + " " + name();
	}

	/**
	 * Return the enum constant of this type with the specified numeric value.
	 * 
	 * @param statusCode the numeric value of the enum to be returned
	 * @return the enum constant with the specified numeric value
	 * @throws IllegalArgumentException if this enum has no constant for the
	 *                                  specified numeric value
	 */
	public static DbErrorStatus valueOf(int statusCode) {
		DbErrorStatus status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}

	/**
	 * Resolve the given status code to an {@code HttpStatus}, if possible.
	 * 
	 * @param statusCode the Data Base status code (potentially non-standard)
	 * @return the corresponding {@code DbErrorStatus}, or {@code null} if not found
	 */
	@Nullable
	public static DbErrorStatus resolve(int statusCode) {
		for (DbErrorStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}

}
