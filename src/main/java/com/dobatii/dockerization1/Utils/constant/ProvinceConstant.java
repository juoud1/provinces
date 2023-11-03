package com.dobatii.dockerization1.Utils.constant;

/**
 * 
 * @author juoud1
 * @version 1.0
 * @date 2023-10-30
 * 
 */

public final class ProvinceConstant {

	public static final String EMPTY_STRING = "";

	public static final String DEFAULT_PATH = "/";

	public static final String HOME_PATH = "/";

	public static final String PROVINCES_PATH = "/provinces";

	public static final String PROVINCES_PROVINCE_PATH = "/provinces/{province}";

	// Security
	public static final String ENCODER_ID = "bcrypt";
	public static final String OLIBILL_API_URL_PREFIX = "/olibillapi/v1/**";
	public static final String H2_URL_PREFIX = "/h2-console/**";
	public static final String OLIBILL_SIGNUP_URL = "/olibillapi/v1/users";
	public static final String OLIBILL_TOKEN_URL = "/olibillapi/v1/auth/token";
	public static final String OLIBILL_REFRESH_URL = "/olibillapi/v1/auth/token/refresh";
	public static final String OLIBILL_PROVINCES_URL = "/olibillapi/v1/provinces/**";
	public static final String AUTHORIZATION = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String SECRET_KEY = "SECRET_KEY";
	public static final long EXPIRATION_TIME = 600_000; // 10 mins
	public static final String ROLE_CLAIM = "roles";
	public static final String AUTHORITY_PREFIX = "ROLE_";

	private ProvinceConstant() {
	}
}
