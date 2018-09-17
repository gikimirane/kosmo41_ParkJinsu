package com.study.jsp;

import java.sql.Timestamp;

public class BDto {
	
	int bId;
	String bName;
	String bTitle;
	String bContent;
	Timestamp bDate;
	int bHit;
	int bGroup;
	int bStep;
	int bIndent;
	String bCheck;
	String fileName;
	
	int num;
	String roomList;
	String bj;
	int count;
	String ox;
	
	String waitRoom;
	
	public String getWaitRoom() {
		return waitRoom;
	}
	public void setWaitRoom(String waitRoom) {
		this.waitRoom = waitRoom;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOx() {
		return ox;
	}
	public void setOx(String ox) {
		this.ox = ox;
	}
	public String getRoomList() {
		return roomList;
	}
	public void setRoomList(String roomList) {
		this.roomList = roomList;
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

	String sysFile;
	
	

	private String id;
	private String pw;
	private String name;
	private String eMail;
	private Timestamp rDate;
	private String address;
	
	
	
	public BDto() {
		
	}
	
	public BDto(String waitRoom) {
		this.waitRoom = waitRoom;
	}
	
	public BDto(int num, String roomList, String bj, int count, String ox) {
		this.num = num;
		this.roomList = roomList;
		this.bj = bj;
		this.count = count;
		this.ox = ox;
	}

	public BDto(String fileName, String sysFile) {
		this.fileName = fileName;
		this.sysFile = sysFile;
	}

	public BDto(int bId, String bName, String bTitle, String bContent, 
			Timestamp bDate, int bHit, int bGroup, int bStep, 
			int bIndent,String bCheck,String fileName, String sysFile)
	{
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bCheck = bCheck;
		this.fileName = fileName;
		this.sysFile = sysFile;
	}
	
	
	public String getbCheck() {
		return bCheck;
	}

	public void setbCheck(String bCheck) {
		this.bCheck = bCheck;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Timestamp getbDate() {
		return bDate;
	}
	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Timestamp getrDate() {
		return rDate;
	}

	public void setrDate(Timestamp rDate) {
		this.rDate = rDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
