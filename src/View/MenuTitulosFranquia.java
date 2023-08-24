
package View;

import java.util.Scanner;


public class MenuTitulosFranquia {
    
    Scanner scanner = new Scanner(System.in);
    
     public int menuFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU DONO DE FRANQUIA ==========");
        builder.append("\n0 - Para Sair.............................:");
        builder.append("\n1 - Ver Cadastro da Franquia..............: ");
        builder.append("\n2 - Ver Cadastro Dono De Franquia.........: ");
        builder.append("\n3 - Cadastrar Nova Franquia...............: ");
        builder.append("\n4 - Designar Admnistrador da Franquia.....: ");
        builder.append("\n5 - Atualizar Dados da Franquia E Dono....: ");
        builder.append("\n6 - Exclusoes/Reverter Exclusoes..........: ");
        builder.append("\n7 - Exibir Todas As Franquias.............: ");
        builder.append("\n8 - Cadastrar Medico......................: ");
        builder.append("\n9 - Exibir Todos Os Medicos...............: ");
        builder.append("\n10 - Cadastar Unidade De Franquia.........: ");
        builder.append("\n11 - Exibir Unidades Franquia.............: ");
        builder.append("\n12 - Relatorios Franquia..................: ");
        builder.append("\n13 - Exibir Todos Pacientes...............: ");
        builder.append("\n\nInforme Opcao...........................: ");
        
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
     
     public int menuAtualizacaoDeDadosFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU ATUALIZACAO DE DADOS FRANQUIA ==========");
        builder.append("\n0 - Para Sair..........................................:");
        builder.append("\n1 - Atualizar Nome Da Franquia.........................: ");
        builder.append("\n2 - Atualizar Cidade da Franquia.......................: ");
        builder.append("\n3 - Atualizar Endereco Da Franquia.....................: ");
        builder.append("\n4 - Atualizar Login Dono De Franquia...................: ");
        builder.append("\n5 - Atualizar Senha Dono De Franquia...................: ");
        builder.append("\n6 - Atualizar Telefone Dono De Franquia................: ");
        builder.append("\n\nInforme Opcao........................................: ");
       
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
     
     public int menuRelatoriosFinanceirosFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU RELATORIOS FIANCEIROS FRANQUIA ==========");
        builder.append("\n0 - Para Sair..........................................:");
        builder.append("\n1 - Ver Relatorio Geral Franquia.......................: ");
        builder.append("\n2 - Ver Relatorio Mensal Franquia......................: ");
       
        builder.append("\n\nInforme Opcao........................................: ");
       
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
     
      public int menuExclusoesDonoFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU EXCLUSOES DONO DE FRANQUIA ==========");
        builder.append("\n0 - Para Sair................................: ");
        builder.append("\n1 - Excluir Paciente.........................: ");
        builder.append("\n2 - Reverter Exclusao Paciente...............: ");
        builder.append("\n3 - Excluir Medico...........................: ");
        builder.append("\n4 - Reverter Exclusao Medico.................: ");
        builder.append("\n\nInforme Opcao..............................: ");
       
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
     
}
