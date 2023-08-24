package Controller;

import Model.Admnistrador;
import Model.AdmnistradorDAO;
import Model.CalendarioSistema;
import Model.ConsultaDAO;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedicoDAO;
import Model.Franquia;
import Model.FranquiaDAO;
import Model.InfoConsultaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import java.util.Scanner;

public class GerenciaControladoras {

    
    /*Instanciando a Classe de Validacao de dads.*/
    ValidacaoEntradaDados vd = new ValidacaoEntradaDados();

    /*Instanciando o Calendario Do Sistema*/
    CalendarioSistema calendarioSistema = new CalendarioSistema();

    /*Instanciando os DAO.*/
    PessoaDAO pessoaDAO = new PessoaDAO(calendarioSistema);
    MedicoDAO medicoDAO = new MedicoDAO(pessoaDAO, calendarioSistema);
    FranquiaDAO franquiaDAO = new FranquiaDAO(pessoaDAO, calendarioSistema);
    UnidadeFranquiaDAO unidadeFranquiaDAO = new UnidadeFranquiaDAO(pessoaDAO, 
            franquiaDAO, calendarioSistema);
    ConsultaDAO consultaDAO = new ConsultaDAO();
    AdmnistradorDAO admnistradorDAO = new AdmnistradorDAO(pessoaDAO, 
            franquiaDAO, calendarioSistema);
    InfoConsultaDAO infoConsultaDAO = new InfoConsultaDAO();
    ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(pessoaDAO,
            medicoDAO, unidadeFranquiaDAO,
            calendarioSistema, consultaDAO);
    FinanceiroAdmDAO financeiroAdmDAO = new FinanceiroAdmDAO();
    FinanceiroMedicoDAO financeiroMedicoDAO = new FinanceiroMedicoDAO();

    public GerenciaControladoras() {
        
            fazBuscaGeralNoBancoDeDados(pessoaDAO, medicoDAO, franquiaDAO,
                    unidadeFranquiaDAO, consultaDAO, procedimentoDAO,
                    infoConsultaDAO, financeiroAdmDAO, 
                    financeiroMedicoDAO, admnistradorDAO, calendarioSistema);
            
    }

    /*private void cadastrarPessoa() {

        System.out.println("\nInforme o Nome da Pessoa: ");
        String nomePessoa = scanner.nextLine();
        nomePessoa = vd.validaString(nomePessoa);

        System.out.println("\nInforme o Cpf da Pessoa: ");
        String cpf = scanner.nextLine();
        cpf = vd.validaString(cpf);

        System.out.println("\nInforme o Endereco da Pessoa: ");
        String enderecoPessoa = scanner.nextLine();
        enderecoPessoa = vd.validaString(enderecoPessoa);

        System.out.println("\nInforme o login da Pessoa: ");
        String loginPessoa = scanner.nextLine();
        loginPessoa = vd.validaString(loginPessoa);

        System.out.println("\nInforme a Senha da Pessoa: ");
        String senhaPessoa = scanner.nextLine();
        senhaPessoa = vd.validaString(senhaPessoa);

        System.out.println("\nInforme o Telefone da Pessoa: ");
        String telefonePessoa = scanner.nextLine();
        telefonePessoa = vd.validaString(telefonePessoa);

        String tipoUsuario = "Paciente";

        boolean existePessoa;
        boolean adicionado;

        existePessoa = pessoaDAO.verificaSePessoaExiste(loginPessoa, cpf);

        if (existePessoa == true) {
            System.out.println("\nPessoa ja Cadastrada");

        } else {

            Pessoa pessoa = new Pessoa();

            pessoa.setNomePessoa(nomePessoa);
            pessoa.setCpf(cpf);
            pessoa.setEnderecoPessoa(enderecoPessoa);
            pessoa.setTelefonePessoa(telefonePessoa);
            pessoa.setLoginPessoa(loginPessoa);
            pessoa.setSenhaPessoa(senhaPessoa);
            pessoa.setTipoUsuario(tipoUsuario);
            pessoa.setHabilitado(true);
            pessoa.setDataCriacao(calendarioSistema.getDataHoraSistema());

            adicionado = pessoaDAO.inserePessoaNoBancoDeDados(pessoa);

            if (adicionado == true) {
                System.out.println("\nPessoa Cadastrada Com Sucesso!!!");
            } else {
                System.out.println("\nNao Foi Possivel Cadastrar a Pessoa.");
            }
        }
    }*/

    
   
    
    public Pessoa fazLogin(String login, String senha) {
        
        Pessoa pessoaLogada = pessoaDAO.buscaPessoaQuerendoLogar(login, senha);
        
        return pessoaLogada;
    }

  

    public void fazBuscaGeralNoBancoDeDados(PessoaDAO pessoaDAO, MedicoDAO medicoDAO,
            FranquiaDAO franquiaDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO,
            InfoConsultaDAO infoConsultaDAO, FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, 
            AdmnistradorDAO admnistradorDAO, CalendarioSistema calendarioSistema) {

        pessoaDAO.BuscaPessoaNoBancoDeDados();
        medicoDAO.BuscaMedicoNoBancoDeDados(pessoaDAO);
        franquiaDAO.BuscaFranquiaNoBancoDeDados(pessoaDAO);
        unidadeFranquiaDAO.BuscaUnidadeFranquiaNoBancoDeDados(pessoaDAO, franquiaDAO);
        admnistradorDAO.BuscaAdmnistradorNoBancoDeDados(pessoaDAO, franquiaDAO);
        consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);
        infoConsultaDAO.BuscaInfoConsultaNoBancoDeDados(consultaDAO);
        procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);
        financeiroAdmDAO.buscaFinanceiroADMNoBancoDeDados(unidadeFranquiaDAO);
        financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);
        calendarioSistema.buscaDataHoraSistemaNoBancoDeDados();
    }

  
}
