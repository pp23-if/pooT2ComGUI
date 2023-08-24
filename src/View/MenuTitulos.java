package View;

import java.util.Scanner;

public class MenuTitulos {

    Scanner scanner = new Scanner(System.in);

    public int menuInicial() {

        StringBuilder builder = new StringBuilder("");

        builder.append("\n============ MENU INICIAL ============");
        builder.append("\n0 - Para sair do programa..........: ");
        builder.append("\n1 - Login..........................: ");
        builder.append("\n2 - Cadastrar Pessoa...............: ");
        builder.append("\n\nInforme Opcao...................: ");

        System.out.print(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
}
