package View;

import java.util.Scanner;

public class MenuTitulosPaciente {

    Scanner scanner = new Scanner(System.in);

    public int menuPaciente() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU PACIENTE ==========");
        builder.append("\n0 - Para Sair..........................:");
        builder.append("\n1 - Ver Meu Cadastro...................:");
        builder.append("\n2 - Atualizar Meus Dados...............: ");
        builder.append("\n3 - Ver Minhas Consultas...............: ");
        builder.append("\n4 - Ver Meus Procedimentos.............: ");
        builder.append("\n5 - Gerar Relatorio Medico.............: ");
        builder.append("\n\nInforme Opcao........................: ");
        
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    public int menuAlteraDadosPaciente() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU ATUALIZACAO DE DADOS PACIENTE ======");
        builder.append("\n0 - Para sair......................................: ");
        builder.append("\n1 - Atualizar Nome.................................: ");
        builder.append("\n2 - Atualizar Cpf..................................: ");
        builder.append("\n3 - Atualizar Endereco.............................: ");
        builder.append("\n4 - Atualizar Telefone.............................: ");
        builder.append("\n5 - Atualizar Login................................: ");
        builder.append("\n6 - Atualizar Senha................................: ");
        builder.append("\n\nInforme Opcao....................................: ");
        
        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

}
