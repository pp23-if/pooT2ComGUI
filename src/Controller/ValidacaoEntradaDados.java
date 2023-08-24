package Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ValidacaoEntradaDados {

    Scanner scanner = new Scanner(System.in);

    /*Todos os metodos dessa classe serao Publicos.*/
    public String validaString(String texto) {
        while (texto.equals("") || texto.equals(" ")) {
            System.out.println("entrada incorreta!");
            texto = scanner.nextLine();

        }
        return texto;
    }

    public int validarINT(int numero) {

        while (numero <= 0) {
            System.out.println("numero invalido!");
            System.out.println("Informe outro numero Maior que 0 : ");
            numero = Integer.parseInt(scanner.nextLine());
        }
        return numero;
    }

    public double validarDoble(double numero) {

        while (numero <= 0) {
            System.out.println("numero invalido!");
            System.out.println("Informe outro numero Maior que 0 : ");
            numero = Integer.parseInt(scanner.nextLine());
        }
        return numero;
    }
    
    public LocalDate validaStringData(String dataProcedimento){
        
        DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate diaConsulta = null;
        
        while(dataProcedimento.equals("")){
            try {
                System.out.println("\nInforme a Data Do Procedimento No Seguinte Formato, "
                        + "Dia/Mes/Ano (00/00/0000)..: ");
                dataProcedimento = scanner.nextLine();
                
                diaConsulta = LocalDate.parse(dataProcedimento, fdia);
            } 
            catch (Exception e) {
                dataProcedimento = "";
                System.out.println("\nFormato invalido");   
            }   
        }
        return diaConsulta;
    }
    
    public LocalTime validaHora(String horaProcedimento){
        
        LocalTime horaProcedimentoValidado = null;
        
        while(horaProcedimento.equals("")){
            try {
                System.out.println("\nInforme a Hora Do Procedimento No Seguinte Formato, Hora:Minutos (00:00)..: ");
                horaProcedimento = scanner.nextLine();
                horaProcedimentoValidado = LocalTime.parse(horaProcedimento);
            } 
            catch (Exception e) {
                horaProcedimento = "";
                System.out.println("\nFormato invalido");   
            }   
        }
        return horaProcedimentoValidado;
    }
    
    public double validaDespesaAvulsaValor(String valorDespesa){
        
        double valorPagamento = 0;
        
        while(valorDespesa.equals("")){
            try {
                System.out.println("\nInforme O Valor Do Pagamento: ");
                valorDespesa = scanner.nextLine();
                valorPagamento = Double.parseDouble(valorDespesa);
            } 
            catch (Exception e) {
                valorDespesa = "";
                valorPagamento = 0;
            }
            
        }
        return valorPagamento;
    }
    
}
