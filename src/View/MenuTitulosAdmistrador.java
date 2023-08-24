
package View;

import java.util.Scanner;

public class MenuTitulosAdmistrador {
    Scanner scanner = new Scanner(System.in);

    public int menuAdmnistrador() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU ADMNISTRADOR ==========");
        builder.append("\n0 - Para sair do programa.............: ");
        builder.append("\n1 - Ver Cadastro Admnistrador.........: ");
        builder.append("\n2 - Consultas.........................: ");
        builder.append("\n3 - Procedimentos.....................: ");
        builder.append("\n4 - Financeiro........................: ");
        builder.append("\n\nInforme Opcao.......................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuConsultas() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU CONSULTAS ===========");
        builder.append("\n0 - Para sair do programa...........: ");
        builder.append("\n1 - Marcar Consulta.................: ");
        builder.append("\n2 - Ver Consultas...................: ");
        builder.append("\n3 - Cancelar Consulta...............: ");
        builder.append("\n4 - Remarcar Consulta...............: ");
        builder.append("\n\nInforme Opcao.....................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
      public int menuProcedimentos() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU PROCEDIMENTOS ==========");
        builder.append("\n0 - Para sair do programa..............: ");
        builder.append("\n1 - Marcar Procedimento................: ");
        builder.append("\n2 - Ver Procedimentos..................: ");
        builder.append("\n3 - Cancelar Procedimento..............: ");
        builder.append("\n4 - Remarcar Procedimento..............: ");
        builder.append("\n\nInforme Opcao........................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
      
      public int menuFinanceiroAdm() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU FINANCEIRO =============");
        builder.append("\n0 - Para sair do programa..............: ");
        builder.append("\n1 - Encerrar Dia.......................: ");
        builder.append("\n2 - Fazer Pagamentos...................: ");
        builder.append("\n3 - Ver Movimentos Financeiros.........: ");
        builder.append("\n4 - Ver Pagamentos Dos Medicos.........: ");
        builder.append("\n5 - Gerar Relatorio Geral Pdf..........: ");
        builder.append("\n\nInforme Opcao........................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
      
      
       public int menuPagamentosAdm() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU PAGAMENTOS =============");
        builder.append("\n0 - Para sair do programa...................: ");
        builder.append("\n1 - Pagar Franquia E Medicos................: ");
        builder.append("\n2 - Pagar Despesas Avulsas..................: ");
        builder.append("\n\nInforme Opcao.............................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
}
