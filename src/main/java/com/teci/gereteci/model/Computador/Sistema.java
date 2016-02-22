package com.teci.gereteci.model.Computador;

public enum Sistema {
	v7("Windows 7"),
	vXp("Windows XP"),
	vVista("Windows Vista"),
	vServer("Windows Server"),
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
