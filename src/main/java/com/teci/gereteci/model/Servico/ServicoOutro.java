package com.teci.gereteci.model.Servico;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.teci.gereteci.model.Computador.Computador;
import com.teci.gereteci.model.Usuario.Usuario;

@Entity
@DiscriminatorValue("Outro")

public class ServicoOutro extends Servico {

	
	@Enumerated(EnumType.STRING)
	private DescricaoProntaOutro descricao_pronta;
	
	private boolean emprestimo_recurso;
	private String material_emprestado;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data_emprestimo;
	
		
	public void setDescricao_pronta(DescricaoProntaOutro descricao_outro)
	{
		this.descricao_pronta = descricao_outro;
	}
	public DescricaoProntaOutro getDescricao_pronta()
	{
		return this.descricao_pronta;
	}
	public boolean getEmprestimo_recurso() {
		return emprestimo_recurso;
	}
	public void setEmprestimo_recurso(boolean emprestimo_recurso) {
		this.emprestimo_recurso = emprestimo_recurso;
	}
	public String getMaterial_emprestado() {
		return material_emprestado;
	}
	public void setMaterial_emprestado(String material_emprestado) {
		this.material_emprestado = material_emprestado;
	}
	
	public Date getData_emprestimo() {
		return data_emprestimo;
	}
	public void setData_emprestimoo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}
	

	
}
