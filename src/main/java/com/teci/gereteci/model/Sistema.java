package com.teci.gereteci.model;

public enum Sistema {
	vXp("Windows XP"),
	vServer("Windows Server"),
	vVista("Windows Vista"),
	v7("Windows 7"),
	v8("Windows 8"),
	v10("Windows 10");

	private String sistema;
	
	Sistema(String sistema)
	{
		this.sistema = sistema;
	}
	
	public String getSistema()
	{
		return sistema;
	}
	
	public void setSistema(String sistema)
	{
		this.sistema = sistema;
	}
	
}
