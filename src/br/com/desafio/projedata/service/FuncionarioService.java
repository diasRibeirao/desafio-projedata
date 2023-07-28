package br.com.desafio.projedata.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.desafio.projedata.dao.FuncionarioDAO;
import br.com.desafio.projedata.modelo.Funcionario;

public class FuncionarioService {
	FuncionarioDAO dao = new FuncionarioDAO();

	public void inserir(Funcionario funcionario) {
		this.dao.inserir(funcionario);
	}

	public void removerPeloNome(String nome) {
		this.dao.removerPeloNome(nome);
	}

	public List<Funcionario> buscarTodos() {
		return this.dao.buscarTodos();
	}

	public void ajustarSalario(BigDecimal percAjuste) {
		List<Funcionario> funcionarios = this.buscarTodos();
		funcionarios.forEach(funcionario -> funcionario.ajustarSalario(percAjuste));
	}
}
