import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Period;
import java.util.Comparator;
import java.math.RoundingMode;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        System.out.println("-----------------------------------------");
        System.out.println("3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.");
        System.out.println("-----------------------------------------");
        funcionarios.add(new Funcionario(
            "Maria",
            LocalDate.of(2000, 10, 18),
            new BigDecimal("2009.44"),
            "Operador"
        ));

        funcionarios.add(new Funcionario(
            "João",
            LocalDate.of(1990, 5, 12),
            new BigDecimal("2284.38"),
            "Operador"
        ));

        funcionarios.add(new Funcionario(
            "Caio",
            LocalDate.of(1961, 5, 2),
            new BigDecimal("9836.14"),
            "Coordenador"
        ));

        funcionarios.add(new Funcionario(
            "Miguel",
            LocalDate.of(1988, 10, 14),
            new BigDecimal("19119.88"),
            "Diretor"
        ));

        funcionarios.add(new Funcionario(
            "Alice",
            LocalDate.of(1995, 1, 5),
            new BigDecimal("2234.68"),
            "Recepcionista"
        ));

        funcionarios.add(new Funcionario(
            "Heitor",
            LocalDate.of(1999, 11, 19),
            new BigDecimal("1582.72"),
            "Operador"
        ));

        funcionarios.add(new Funcionario(
            "Arthur",
            LocalDate.of(1993, 3, 31),
            new BigDecimal("4071.84"),
            "Contador"
        ));

        funcionarios.add(new Funcionario(
            "Laura",
            LocalDate.of(1994, 7, 8),
            new BigDecimal("3017.45"),
            "Gerente"
        ));

        funcionarios.add(new Funcionario(
            "Heloísa",
            LocalDate.of(2003, 5, 24),
            new BigDecimal("1606.85"),
            "Eletricista"
        ));

        funcionarios.add(new Funcionario(
            "Helena",
            LocalDate.of(1996, 9, 2),
            new BigDecimal("2799.93"),
            "Gerente"
        ));
        
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        // 3.2 Remover o funcionário “João” da lista.
        System.out.println("-----------------------------------------");
        System.out.println("3.2 Remover o funcionário “João” da lista.");
        System.out.println("-----------------------------------------");
        funcionarios.removeIf(
                funcionario -> funcionario.getNome().equals("João")
            );

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }   


        /*3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.*/
        System.out.println("-----------------------------------------");
        System.out.println("Imprimir todos os funcionários com todas suas informações");
        System.out.println("-----------------------------------------");

        DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormatSymbols symbols =
            new DecimalFormatSymbols(new Locale("pt", "BR"));

        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat moneyFormatter =
            new DecimalFormat("#,##0.00", symbols);

        for (Funcionario funcionario : funcionarios) {

            System.out.println(
                "Nome: " + funcionario.getNome()
                + " | Data de nascimento: "
                + funcionario.getDataNascimento().format(dateFormatter)
                + " | Salário: "
                + moneyFormatter.format(funcionario.getSalario())
                + " | Função: "
                + funcionario.getFuncao()
                );
         }


        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        System.out.println("-----------------------------------------");
        System.out.println("3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.");
        System.out.println("-----------------------------------------");

        BigDecimal percentualAumento = new BigDecimal("0.10");

        for (Funcionario funcionario : funcionarios) {

            BigDecimal valorAumento = funcionario
                .getSalario()
                .multiply(percentualAumento);

            BigDecimal novoSalario = funcionario
                .getSalario()
                .add(valorAumento);

            funcionario.setSalario(novoSalario);
        }

            for (Funcionario funcionario : funcionarios) {

            System.out.println(
                "Nome: " + funcionario.getNome()
                + " | Data de nascimento: "
                + funcionario.getDataNascimento().format(dateFormatter)
                + " | Salário: "
                + moneyFormatter.format(funcionario.getSalario())
                + " | Função: "
                + funcionario.getFuncao()
                );
         }


        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        System.out.println("-----------------------------------------");
        System.out.println("3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.");
        System.out.println("-----------------------------------------");

        Map<String, List<Funcionario>> funcionariosPorFuncao =
        funcionarios.stream()
        .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("--- DONE --- ");

        System.out.println("-----------------------------------------");
        System.out.println("3.6 – Imprimir os funcionários, agrupados por função.");
        System.out.println("-----------------------------------------");

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {

        System.out.println("Função: " + entry.getKey());

        for (Funcionario funcionario : entry.getValue()) {
            System.out.println(
                "Nome: " + funcionario.getNome()
                + " | Salário: "
                + moneyFormatter.format(funcionario.getSalario())
            );
        }

        System.out.println();


        System.out.println("-----------------------------------------");
        System.out.println("3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.");
        System.out.println("-----------------------------------------");

        for (Funcionario funcionario : funcionarios) {

        int mes = funcionario.getDataNascimento().getMonthValue();

        if (mes == 10 || mes == 12) {
            System.out.println(
                funcionario.getNome()
                + " - "
                + funcionario.getDataNascimento().format(dateFormatter)
            );
         }
        }


        System.out.println("-----------------------------------------");
        System.out.println("3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.");
        System.out.println("-----------------------------------------");

        Funcionario maisVelho = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento()
                    .isBefore(maisVelho.getDataNascimento())) {

                maisVelho = funcionario;
            }
        }

        int idade = Period.between(
            maisVelho.getDataNascimento(),
            LocalDate.now()
        ).getYears();

        System.out.println(
            "Nome: " + maisVelho.getNome() +
            " | Idade: " + idade
        );


        System.out.println("-----------------------------------------");
        System.out.println("3.10 – Imprimir a lista de funcionários por ordem alfabética.");
        System.out.println("-----------------------------------------");

        funcionarios.sort(
            Comparator.comparing(Funcionario::getNome)
        );

        System.out.println("Funcionários em ordem alfabética:");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }


        System.out.println("-----------------------------------------");
        System.out.println("3.11 – Imprimir o total dos salários dos funcionários");
        System.out.println("-----------------------------------------");

        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
         
        System.out.println(
            "Total dos salários: R$ "
            + moneyFormatter.format(totalSalarios)
        );


        System.out.println("-----------------------------------------");
        System.out.println("3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.");
        System.out.println("-----------------------------------------");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {

        BigDecimal quantidadeSalariosMinimos = funcionario
        .getSalario()
        .divide(salarioMinimo, 2, RoundingMode.HALF_UP);

        System.out.println(
            funcionario.getNome()
            + " ganha "
            + quantidadeSalariosMinimos
                + " salários mínimos"
            );
        }

    }

    }
}