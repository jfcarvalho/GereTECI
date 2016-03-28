package com.teci.gereteci.model.Servico;

public enum DescricaoProntaEmail {
	desc1("Usuário esqueceu a senha do email"),
	desc2("Usuário trocou informações da conta"),
	desc3("Mudança do plano de email");
	
	private String descricao;
	
	DescricaoProntaEmail(String descricao)
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
