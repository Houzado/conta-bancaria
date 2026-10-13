
import java.util.Scanner;

public class OpBancaria {

    private float saldo;
    private float chequeEspecialUsado;
    private float limiteCheque;
    private float taxaPendente;
    private float deposito;
    private boolean limiteDefinido;

    public OpBancaria() {
    this.saldo = 0f;
    this.chequeEspecialUsado = 0f;
    this.limiteCheque = 0f;
    this.taxaPendente = 0f;
    this.deposito = 0f;
    this.limiteDefinido = false;
    }

    public void DepositMoney(Scanner scanner) {

        do {
            System.out.println("Deposite um valor positivo: ");
            this.deposito = scanner.nextFloat();
        } while (deposito <= 0);

        if (!limiteDefinido) {
            if (deposito <= 500) {
                limiteCheque = 50.0f;
            } else {
                limiteCheque = deposito * 0.5f;
            }

            limiteDefinido = true;

            System.out.println(
                    "Limite do cheque especial definido: R$ "
                    + limiteCheque);
        }

        saldo += deposito;

        chequeEspecialUsado = Math.max(0f, -saldo);

        if (taxaPendente > 0 && saldo > 0) {
            float taxaPaga = Math.min(saldo, taxaPendente);
            saldo -= taxaPaga;
            taxaPendente -= taxaPaga;

            chequeEspecialUsado = Math.max(0f, -saldo);
        }

        System.out.println("Depósito realizado com sucesso.");
    }

    public void SaldoAtual() {
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Limite do cheque especial: R$ "
                + limiteCheque);
        System.out.println("Cheque especial usado: R$ "
                + chequeEspecialUsado);
        System.out.println("Taxa pendente: R$ " + taxaPendente);
    }

    public void ConsultarChequeEspecial() {
        System.out.println("Limite do cheque especial: R$ "
                + limiteCheque);
        System.out.println("Valor utilizado: R$ "
                + chequeEspecialUsado);
        System.out.println("Valor disponível: R$ "
                + Math.max(0f, limiteCheque - chequeEspecialUsado));
    }

    public void Sacar(Scanner scanner) {

        float disponivel = saldo + limiteCheque;

        System.out.println("Digite o valor de saque: ");
        float saque = scanner.nextFloat();

        if (saque <= 0) {
            System.out.println("Digite um valor positivo.");
        } else if (saque > disponivel) {
            System.out.println(
                    "Valor de saque excede o limite disponível.");
        } else {
            float valorUtilizado = saque - Math.max(saldo, 0f);

            if (valorUtilizado > 0) {
                taxaPendente += valorUtilizado * 0.20f;
            }

            saldo -= saque;
            chequeEspecialUsado = Math.max(0f, -saldo);

            System.out.println(
                    "Saque de R$ " + saque + " realizado com sucesso.");
        }
    }

    public void PagarBoleto(Scanner scanner) {

        float disponivel = saldo + limiteCheque;

        System.out.println(
                "Digite o valor do boleto que você quer pagar: ");
        float valorBoleto = scanner.nextFloat();

        if (valorBoleto <= 0) {
            System.out.println("Digite um valor positivo.");
        } else if (valorBoleto > disponivel) {
            System.out.println(
                    "O boleto excede o limite disponível.");
        } else {
            float valorUtilizado =
                    valorBoleto - Math.max(saldo, 0f);

            if (valorUtilizado > 0) {
                taxaPendente += valorUtilizado * 0.20f;
            }

            saldo -= valorBoleto;
            chequeEspecialUsado = Math.max(0f, -saldo);

            System.out.println(
                    "Boleto de R$ " + valorBoleto
                    + " pago com sucesso.");
        }
    }

    public void VerificarChequeEspecial() {
        if (chequeEspecialUsado > 0) {
            System.out.println(
                    "A conta está usando cheque especial.");
            System.out.println(
                    "Valor utilizado: R$ " + chequeEspecialUsado);
        } else {
            System.out.println(
                    "A conta não está usando cheque especial.");
        }
    }
}
