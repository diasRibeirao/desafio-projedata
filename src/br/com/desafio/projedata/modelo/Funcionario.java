package br.com.desafio.projedata.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
	private BigDecimal salario;
	private String funcao;

	public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getFuncao() {
		return funcao;
	}
	
	public void ajustarSalario(BigDecimal percAjuste) {
		this.salario = this.salario.multiply(percAjuste);
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("Nome: %s, Data de Nascimento: %s, Salário: R$ %.2f, Função: %s", 
				getNome(),
				dateFormatter.format(getDataNascimento()), 
				getSalario(), 
				getFuncao());
	}

}
