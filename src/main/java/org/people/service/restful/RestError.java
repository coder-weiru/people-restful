package org.people.service.restful;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.http.HttpStatus;

import com.google.common.base.Objects;

public class RestError implements Serializable {

	private static final long serialVersionUID = 5033629021810013302L;

	private final HttpStatus httpStatus;
	private final String message;

	@JsonCreator
	public RestError(@JsonProperty("status") HttpStatus status,
			@JsonProperty("message") String message) {
		this.httpStatus = status;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		String statusMessage = new StringBuilder()
				.append(getHttpStatus().value()).append(" (")
				.append(getHttpStatus()).append(")").toString();

		return Objects.toStringHelper(this).add("status", statusMessage)
				.add("message", getMessage()).omitNullValues().toString();
	}
}
