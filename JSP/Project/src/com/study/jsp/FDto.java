package com.study.jsp;

public class FDto {
	String file;
	String oriFile;
	
	public FDto() {
		
	}
	
	public FDto(String file, String oriFile) {
		this.file = file;
		this.oriFile = oriFile;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getOriFile() {
		return oriFile;
	}

	public void setOriFile(String oriFile) {
		this.oriFile = oriFile;
	}

	

}
