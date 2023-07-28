package br.com.desafio.projedata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.desafio.projedata.modelo.Funcionario;
import br.com.desafio.projedata.service.FuncionarioService;

public class Principal {
	FuncionarioService service = new FuncionarioService();
	
	public static void main(String[] args) {
		new Principal().executar();		
	}
	
	public void executar() {
		// 3.1 - Inserir todos os funcionários
		this.service.inserir(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		this.service.inserir(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		this.service.inserir(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		this.service.inserir(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		this.service.inserir(new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"));
		this.service.inserir(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		this.service.inserir(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		this.service.inserir(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		this.service.inserir(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		this.service.inserir(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		// 3.2 - Remover o funcionário "João"
		this.service.removerPeloNome("João");

		imprimir();		
	}

	private void imprimir() {
		List<Funcionario> funcionarios = service.buscarTodos();
		
		// 3.3 - Imprimir todos os funcionários
        System.out.println("Todos os funcionários:");
        funcionarios.forEach(System.out::println);
		
        // 3.4 - Aumentar salário em 10%
        this.service.ajustarSalario(new BigDecimal("1.10"));
        
        funcionarios = service.buscarTodos();
        
        // 3.5 - Agrupar os funcionários por função em um MAP        
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios
				.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));
		
		// 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(funcionario -> System.out.println("  " + funcionario));
        });
        		
		// 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        funcionarios
        		.stream()
                .filter(funcionario -> Arrays.asList("10", "12")
                		.contains(monthFormatter.format(funcionario.getDataNascimento())))
                .forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        funcionarios = service.buscarTodos();
        Funcionario funcionarioMaisVelho = Collections.min(funcionarios, Comparator.comparingInt(f -> f.getDataNascimento().getYear()));
        System.out.println("\nFuncionário mais velho:");
        System.out.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + funcionarioMaisVelho.getIdade());

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
        
        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em relação ao salário mínimo:");
        funcionarios.forEach(funcionario -> {
            int salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN).intValue();
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        });
		
	}

}
