package br.com.desafio.projedata.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.desafio.projedata.modelo.Funcionario;

public class FuncionarioDAO {
	private List<Funcionario> funcionarios = new ArrayList<>();

	public void inserir(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
	}

	public void removerPeloNome(String nome) {
		this.funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
	}

	public List<Funcionario> buscarTodos() {
		return this.funcionarios;
	}

}
