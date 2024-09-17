package com.epmtpq.inventario.model;

import java.time.ZonedDateTime;

import org.springframework.core.io.InputStreamResource;

public class FileResponse {
	private String filename;
    private long fileSize;
    private String contentType;
    private ZonedDateTime createdTime;
    private InputStreamResource stream;
    
    // Constructor privado para usar con el Builder
    private FileResponse(Builder builder) {
        this.filename = builder.filename;
        this.fileSize = builder.fileSize;
        this.contentType = builder.contentType;
        this.createdTime = builder.createdTime;
        this.stream = builder.stream;
    }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public ZonedDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(ZonedDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public InputStreamResource getStream() {
		return stream;
	}

	public void setStream(InputStreamResource stream) {
		this.stream = stream;
	}
    
	 // Builder para FileResponse
    public static class Builder {
        private String filename;
        private long fileSize;
        private String contentType;
        private ZonedDateTime createdTime;
        private InputStreamResource stream;

        public Builder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder fileSize(long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder createdTime(ZonedDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder stream(InputStreamResource stream) {
            this.stream = stream;
            return this;
        }

        public FileResponse build() {
            return new FileResponse(this);
        }
    }
}
