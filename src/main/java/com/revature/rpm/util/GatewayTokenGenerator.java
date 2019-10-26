package com.revature.rpm.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;

/**
 * A utility class which is used to generate gateway tokens which is used by
 * downstream resource servers to validate that requests were sent through the
 * API Gateway.
 *
 */
public class GatewayTokenGenerator {

	@Value("${rpm.gateway.header:X-RPM-Gateway}")
	private String header;

	@Value("${rpm.gateway.salt:local-deploy}")
	private String salt;

	@Value("${rpm.gateway.secret:local-deploy}")
	private String secret;
	
	public String getHeader() {
		return header;
	}

	/**
	 * Generates a gateway token, which is represented as a SHA-512 hash value of
	 * the secret and salt values provided during configuration.
	 * 
	 * @return a hash value representing the gateway token
	 */
	public String generateGatewayToken() {

		String hash = null;

		try {

			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(secret.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			hash = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash;

	}

}
