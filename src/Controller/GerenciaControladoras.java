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

    public boolean cadastrarPessoa(String nomePessoa, String cpf, String enderecoPessoa,
            String telefonePessoa, String loginPessoa, String senhaPessoa) {

            boolean adicionado;
    
            Pessoa pessoa = new Pessoa();

            pessoa.setNomePessoa(nomePessoa);
            pessoa.setCpf(cpf);
            pessoa.setEnderecoPessoa(enderecoPessoa);
            pessoa.setTelefonePessoa(telefonePessoa);
            pessoa.setLoginPessoa(loginPessoa);
            pessoa.setSenhaPessoa(senhaPessoa);
            pessoa.setTipoUsuario("Paciente");
            pessoa.setHabilitado(true);
            pessoa.setDataCriacao(calendarioSistema.getDataHoraSistema());

            adicionado = pessoaDAO.inserePessoaNoBancoDeDados(pessoa);

        return adicionado == true;
    }

    
    public boolean verificaSePessoaExiste(String loginPessoa, String Cpf)
    {
       boolean existePessoa = pessoaDAO.verificaSePessoaExiste(loginPessoa, Cpf);
       
        return existePessoa == true;
    }
    
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
