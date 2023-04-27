/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectIniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Felipe
 */
public class Principal {
    public static void main (String[] args) {
        ArrayList<Funcionario> listaDeFuncionarios = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador");
        Funcionario joao = new Funcionario("João", LocalDate.of(1990, 05, 12), new BigDecimal(2284.38), "Operador");
        Funcionario caio = new Funcionario("Caio", LocalDate.of(1961, 05, 02), new BigDecimal(9836.14), "Coordenador");
        Funcionario miguel = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor");
        Funcionario alice = new Funcionario("Alice", LocalDate.of(1995, 01, 05), new BigDecimal(2234.68), "Recepcionista");
        Funcionario heitor = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador");
        Funcionario arthur = new Funcionario("Arthur", LocalDate.of(1993, 03, 31), new BigDecimal(4071.84), "Contador");
        Funcionario laura = new Funcionario("Laura", LocalDate.of(1994, 07, 8), new BigDecimal(3017.45), "Gerente");
        Funcionario heloisa = new Funcionario("Heloísa", LocalDate.of(2003, 05, 24), new BigDecimal(1606.85), "Eletricista");
        Funcionario helena = new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal(2799.93), "Gerente");
       
        // 3.1 – Inserir todos os funcionário
        listaDeFuncionarios.add(maria);
        listaDeFuncionarios.add(joao);
        listaDeFuncionarios.add(caio);
        listaDeFuncionarios.add(miguel);
        listaDeFuncionarios.add(alice);
        listaDeFuncionarios.add(heitor);
        listaDeFuncionarios.add(arthur);
        listaDeFuncionarios.add(laura);
        listaDeFuncionarios.add(heloisa);
        listaDeFuncionarios.add(helena);
        
        //3.2 – Remover o funcionário “João” da lista.
        listaDeFuncionarios.remove(joao);
        
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        
        //3.3 – Imprimir todos os funcionários com todas suas informações.
        for (Funcionario funcionario: listaDeFuncionarios) {
            System.out.print("Nome: " + funcionario.getNome());
            System.out.print(" Data de nascimento: " + fmt.format(funcionario.getDataNascimento()));
            System.out.print(" Salário: " +  nf.format(funcionario.getSalario()));
            System.out.println(" Função: " + funcionario.getFuncao());
            System.out.println();
        }
        
        //3.4 – Os funcionários receberam 10% de aumento de salário
        for (Funcionario funcionario: listaDeFuncionarios) {
            BigDecimal salarioAtual = funcionario.getSalario();
            BigDecimal salarioNovo = salarioAtual.multiply(new BigDecimal(1.1));
            funcionario.setSalario(salarioNovo);
        }
        
        //3.5 - Agrupar os funcionários por função em um MAP.
        Map<String, List<Funcionario>> funcionariosPorFuncao = listaDeFuncionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        //3.6 – Imprimir os funcionários, agrupados por função.
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Funcionários da função " + funcao + ":");
            List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao.get(funcao);
            for (Funcionario funcionario : funcionariosDaFuncao) {
                System.out.print("- " + funcionario.getNome());
                System.out.print(" Data de nascimento: " + fmt.format(funcionario.getDataNascimento()));
                System.out.println(" Salário: " +  nf.format(funcionario.getSalario()));
            }
            System.out.println();
        }

        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        for (Funcionario funcionario: listaDeFuncionarios) {
            LocalDate dataNascimento = funcionario.getDataNascimento();
            int mesNascimento = dataNascimento.getMonthValue();
            if (mesNascimento == 10 || mesNascimento == 12) {
                System.out.println(funcionario.getNome() + " faz aniversário em " + mesNascimento);
            }
        }
        System.out.println();

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maisVelho = listaDeFuncionarios.get(0);
        LocalDate hoje = LocalDate.now();
        for (Funcionario funcionario: listaDeFuncionarios) {
            LocalDate dataNascimento = funcionario.getDataNascimento();
            Period periodo = Period.between(dataNascimento, hoje);
            int idade = periodo.getYears();
            if (idade > Period.between(maisVelho.getDataNascimento(), hoje).getYears()) {
                maisVelho = funcionario;
            }
        }
        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + " com " + 
            Period.between(maisVelho.getDataNascimento(), hoje).getYears() + " anos");
        System.out.println();

        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        listaDeFuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        for (Funcionario funcionario : listaDeFuncionarios) {
            System.out.println(funcionario.getNome());
        }
        System.out.println();

        //3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : listaDeFuncionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total dos salários: " + nf.format(totalSalarios));
        System.out.println();
        
        //3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal(1212.00);
        for (Funcionario funcionario : listaDeFuncionarios) {
            BigDecimal salarioMinimoGanho = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println(funcionario.getNome() + " - Salários mínimos: " + salarioMinimoGanho);
        }
    }
}
