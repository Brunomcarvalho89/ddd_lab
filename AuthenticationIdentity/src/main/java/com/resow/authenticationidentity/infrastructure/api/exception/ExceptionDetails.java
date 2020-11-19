package com.resow.authenticationidentity.infrastructure.api.exception;

/**
 *
 * @author home
 */
public class ExceptionDetails {

    private String details;
    private int status;
    private String detail;
    private long timestamp;

    private ExceptionDetails(String details, int status, String detail, long timestamp) {
        this.details = details;
        this.status = status;
        this.detail = detail;
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ExceptionDetailsBuilder builder() {
        return new ExceptionDetailsBuilder();
    }

    public static final class ExceptionDetailsBuilder {

        private String title;
        private int status;
        private String details;
        private long timestamp;
        private String developerMessage;

        private ExceptionDetailsBuilder() {
        }

        public ExceptionDetailsBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ExceptionDetailsBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ExceptionDetailsBuilder withDetails(String details) {
            this.details = details;
            return this;
        }

        public ExceptionDetailsBuilder withTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExceptionDetails build() {
            return new ExceptionDetails(title, status, details, timestamp);
        }

    }
}
