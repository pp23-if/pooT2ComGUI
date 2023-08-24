package View;

import java.util.Scanner;

public class MenuTitulosUnidadeFranquia {

    Scanner scanner = new Scanner(System.in);

    public int menuUnidadeFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============= MENU UNIDADE DE FRANQUIA ==============");
        builder.append("\n0 - Para Sair.....................................: ");
        builder.append("\n1 - Ver Cadastro Unidade De Franquia..............: ");
        builder.append("\n2 - Ver Cadastro Dono Unidade De Franquia.........: ");
        builder.append("\n3 - Atualizar Dados Unidade De Franquia E Dono....: ");
        builder.append("\n4 - Exibir Todas As Unidades De Franquia..........: ");
        builder.append("\n5 - Cadastrar Medico..............................: ");
        builder.append("\n6 - Exibir Todos Os Medicos.......................: ");
        builder.append("\n7 - Relatorios Unidade De Franquia................: ");
        builder.append("\n\nInforme Opcao...................................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuAtualizacaoDeDadosUnidadeFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU ATUALIZACAO DE DADOS UNIDADES FRANQUIA =====");
        builder.append("\n0 - Para Sair..............................................: ");
        builder.append("\n1 - Atualizar Cidade da Unidade De Franquia................: ");
        builder.append("\n2 - Atualizar Endereco Da Unidade De Franquia..............: ");
        builder.append("\n3 - Atualizar Login Dono Unidade Franquia..................: ");
        builder.append("\n4 - Atualizar Senha Dono Unidade Franquia..................: ");
        builder.append("\n5 - Atualizar Telefone Dono Unidade De Franquia............: ");
        builder.append("\n\nInforme Opcao............................................: ");
        
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
     public int menuRelatoriosFinanceirosUnidadeFranquia() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU RELATORIOS FIANCEIROS UNIDADE FRANQUIA ===========");
        builder.append("\n0 - Para Sair..............................................: ");
        builder.append("\n1 - Ver Relatorio Geral Unidade Franquia...................: ");
        builder.append("\n2 - Ver Relatorio Mensal Unidade Franquia..................: ");
        builder.append("\n\nInforme Opcao...........................................: ");
        
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
}
