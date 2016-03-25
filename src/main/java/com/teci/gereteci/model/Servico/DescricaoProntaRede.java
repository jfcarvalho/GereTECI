package com.teci.gereteci.model.Servico;

public enum DescricaoProntaRede {
	desc1("Servidor com pouca memória na pasta raiz de diretório"),
	desc2("Servidor com problema no Active Directory"),
	desc3("Servidor com problema na impressão"),
	desc4("Servidor com problema no gerenciamento de usuários");
	
	private String descricao;
	
	DescricaoProntaRede(String descricao)
	{
		this.descricao= descricao;
	}
	
	public String getDescricao()
	{
		return descricao;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao= descricao;
	}
}
