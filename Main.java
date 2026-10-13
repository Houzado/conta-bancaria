import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OpBancaria opbancaria = new OpBancaria();

        int option;

        do {
            System.out.println("\nDigite uma das opções:");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Consultar cheque especial");
            System.out.println("3 - Depositar dinheiro");
            System.out.println("4 - Sacar dinheiro");
            System.out.println("5 - Pagar um boleto");
            System.out.println("6 - Verificar se está usando cheque especial");
            System.out.println("7 - Sair");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> opbancaria.SaldoAtual();
                case 2 -> opbancaria.ConsultarChequeEspecial();
                case 3 -> opbancaria.DepositMoney(scanner);
                case 4 -> opbancaria.Sacar(scanner);
                case 5 -> opbancaria.PagarBoleto(scanner);
                case 6 -> opbancaria.VerificarChequeEspecial();
                case 7 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida.");
            }

        } while (option != 7);

        scanner.close();
    }
}