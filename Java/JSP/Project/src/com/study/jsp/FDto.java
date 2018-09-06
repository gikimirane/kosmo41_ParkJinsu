package com.study.jsp;

public class FDto {
	String fileName;
	String sysFile;
	
	public FDto() {
		
	}
	public FDto(String fileName, String sysFile) {
		this.fileName = fileName;
		this.sysFile = sysFile;
	}


	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getSysFile() {
		return sysFile;
	}



	public void setSysFile(String sysFile) {
		this.sysFile = sysFile;
	}

	

}
