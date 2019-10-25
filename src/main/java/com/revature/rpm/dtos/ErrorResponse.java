package com.revature.rpm.dtos;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Basic error response data transfer object used to convey
 * internal system errors to clients.
 *
 */
public class ErrorResponse {

	/**
	 * HTTP status which correlates to the error.
	 */
	private int status;
	
	/**
	 * A concise description of the error.
	 */
	private String message;
	
	/**
	 * The time at which the error occurred.
	 */
	private LocalDate timestamp;
	
	public ErrorResponse() {
		super();
	}
	
	public ErrorResponse(int status, LocalDate timestamp) {
		super();
		this.status = status;
		this.timestamp = timestamp;
	}
	
	public ErrorResponse(int status, String message, LocalDate timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, status, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ErrorResponse))
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		return Objects.equals(message, other.message) && status == other.status
				&& Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "ErrorResponse [status=" + status + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

}
