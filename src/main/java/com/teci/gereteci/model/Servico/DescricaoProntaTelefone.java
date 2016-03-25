package com.teci.gereteci.model.Servico;

public enum DescricaoProntaTelefone {
	desc1("O(s) telefone(s) está/estão mudo(s)"),
	desc2("O(s) telefone(s) não está/estão discando no(s) ramal(is) correto(s)"),
	desc3("Ramal(is) foi/foram modificado(s)"),
	desc4("Linha(s) externa(s) inoperacional(s)"),
	desc5("Linha(s) interno(s) inoperacional(s)"),
	desc6("Problema na central telefônica"),
	desc7("Problema na(s) conta da VIVO"),
	desc8("Problema na(s) conta da OI"),
	desc9("Problema no(s) modens 4G"),
	desc10("Problema no(s) modens 3G");
	
	private String descricao;
	
	DescricaoProntaTelefone(String descricao)
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
