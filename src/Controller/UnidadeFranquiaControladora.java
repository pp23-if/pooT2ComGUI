package Controller;

import Model.CalendarioSistema;
import Model.ConsultaDAO;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedicoDAO;
import Model.FranquiaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulosUnidadeFranquia;
import java.util.Scanner;

public class UnidadeFranquiaControladora {

    Scanner scanner = new Scanner(System.in);
    MenuTitulosUnidadeFranquia menuTitulosUnidadeFranquia = new MenuTitulosUnidadeFranquia();

    public UnidadeFranquiaControladora(UnidadeFranquia unidadeFranquia, UnidadeFranquiaDAO unidadeFranquiaDAO,
            MedicoDAO medicoDAO, PessoaDAO pessoaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, FranquiaDAO franquiaDAO) {

        menuOpcoesUnidadeFranquia(unidadeFranquia, unidadeFranquiaDAO, vd, medicoDAO, pessoaDAO,
                calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO, 
                consultaDAO, procedimentoDAO, franquiaDAO);
    }

    private void menuOpcoesUnidadeFranquia(UnidadeFranquia unidadeFranquia,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            MedicoDAO medicoDAO, PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, 
            FranquiaDAO franquiaDAO) {

        int opcao;

        do {
            unidadeFranquiaDAO.BuscaUnidadeFranquiaNoBancoDeDados(pessoaDAO, franquiaDAO);
            
            unidadeFranquiaDAO.atualizaUnidadeFranquiaLogadaComBancoDeDados(unidadeFranquia.getIdUnidadeFranquia(), 
                    unidadeFranquia, unidadeFranquiaDAO);
            
            pessoaDAO.BuscaPessoaNoBancoDeDados();
            
            medicoDAO.BuscaMedicoNoBancoDeDados(pessoaDAO);
            
            try {
                opcao = menuTitulosUnidadeFranquia.menuUnidadeFranquia();
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {
                    System.out.println("\n" + unidadeFranquiaDAO.buscaUnidadeDeFranquia(unidadeFranquia));
                    break;
                }
                case 2: {
                    System.out.println("\n" + unidadeFranquia.getPessoa());
                    break;
                }
                case 3: {
                    menuOpcoesAtualizaDadosUnidadeFranquia(unidadeFranquia, unidadeFranquiaDAO, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(unidadeFranquia.getFranquia());
                    break;
                }
                case 5: {
                    cadastraMedicos(medicoDAO, pessoaDAO, vd, calendarioSistema);
                    break;
                }
                case 6: {
                    System.out.println("\n");
                    medicoDAO.mostraTodosMedicosHabilitados();
                    break;
                }
                case 7: {
                    geraRelatoriosUnidadeFranquia(financeiroAdmDAO, financeiroMedicoDAO, unidadeFranquia,
                            vd, consultaDAO, procedimentoDAO, medicoDAO, calendarioSistema);
                    break;
                }

            }

        } while (opcao != 0);
    }

    private void menuOpcoesAtualizaDadosUnidadeFranquia(UnidadeFranquia unidadeFranquia,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        int opcao;

        do {
            
            try {
                opcao = menuTitulosUnidadeFranquia.menuAtualizacaoDeDadosUnidadeFranquia();
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {
                    System.out.println("\nInforme o Nova Cidade da Unidade De Franquia: ");
                    String novaCidadeUnidadeFranquia = scanner.nextLine();
                    novaCidadeUnidadeFranquia = vd.validaString(novaCidadeUnidadeFranquia);

                    if (unidadeFranquiaDAO.AtualizaCidadeUnidadeFranquiaNoBancoDeDados(novaCidadeUnidadeFranquia,
                            unidadeFranquia, calendarioSistema) == true) {
                        
                        unidadeFranquia.setCidadeUnidadeFranquia(novaCidadeUnidadeFranquia);
                        
                        System.out.println("\nCidade Da Unidade De Franquia Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar A Cidade Da Unidade De Franquia.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\nInforme o Novo Endereco da Unidade De Franquia: ");
                    String novoEnderecoUnidadeFranquia = scanner.nextLine();
                    novoEnderecoUnidadeFranquia = vd.validaString(novoEnderecoUnidadeFranquia);

                    if (unidadeFranquiaDAO.AtualizaEnderecoUnidadeFranquiaNoBancoDeDados(novoEnderecoUnidadeFranquia,
                            unidadeFranquia, calendarioSistema) == true) {
                        
                        unidadeFranquia.setEnderecoUnidadeFranquia(novoEnderecoUnidadeFranquia);
                        
                        System.out.println("\nEndereco Da Unidade De Franquia Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Endereco Da Unidade De Franquia.");
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nInforme o Novo Login Dono De Unidade De Franquia: ");
                    String novoLoginDonoUnidadeFranquia = scanner.nextLine();
                    novoLoginDonoUnidadeFranquia = vd.validaString(novoLoginDonoUnidadeFranquia);

                    if (unidadeFranquiaDAO.AtualizaLoginDonoUnidadeFranquiaNoBancoDeDados(novoLoginDonoUnidadeFranquia,
                            unidadeFranquia, calendarioSistema) == true) {
                        
                        unidadeFranquia.getPessoa().setLoginPessoa(novoLoginDonoUnidadeFranquia);
                        
                        System.out.println("\nLogin Dono Unidade De Franquia Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Login Dono Da Unidade De Franquia.");
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nInforme o Nova Senha Dono De Unidade De Franquia: ");
                    String novaSenhaDonoUnidadeFranquia = scanner.nextLine();
                    novaSenhaDonoUnidadeFranquia = vd.validaString(novaSenhaDonoUnidadeFranquia);

                    if (unidadeFranquiaDAO.AtualizaSenhaDonoUnidadeFranquiaNoBancoDeDados(novaSenhaDonoUnidadeFranquia,
                            unidadeFranquia, calendarioSistema) == true) {
                        
                        unidadeFranquia.getPessoa().setSenhaPessoa(novaSenhaDonoUnidadeFranquia);
                        
                        System.out.println("\nSenha Dono Unidade De Franquia Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar A Senha Dono Da Unidade De Franquia.");
                    }
                    break;
                }
                case 5: {
                    System.out.println("\nInforme o Novo Telefone Dono De Unidade De Franquia: ");
                    String novoTelefoneDonoUnidadeFranquia = scanner.nextLine();
                    novoTelefoneDonoUnidadeFranquia = vd.validaString(novoTelefoneDonoUnidadeFranquia);

                    if (unidadeFranquiaDAO.AtualizaTelefoneDonoUnidadeFranquiaNoBancoDeDados(novoTelefoneDonoUnidadeFranquia,
                            unidadeFranquia, calendarioSistema) == true) {
                        
                        unidadeFranquia.getPessoa().setTelefonePessoa(novoTelefoneDonoUnidadeFranquia);
                        
                        System.out.println("\nTelefone Dono Unidade De Franquia Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Telefone Dono Da Unidade De Franquia.");
                    }
                    break;

                }

            }
        } while (opcao != 0);

    }

    private void cadastraMedicos(MedicoDAO medicoDAO, PessoaDAO pessoaDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        pessoaDAO.filtraPessoasCandidatasAMedico();
        
        int idPessoa;
        try {
            System.out.println("\nInforme o Id da pessoa que Sera Medico: ");
            idPessoa = Integer.parseInt(scanner.nextLine());
            idPessoa = vd.validarINT(idPessoa);
        } catch (Exception e) {
            idPessoa = 1000;
            System.out.println("\nId da Medico invalido!!");
        }
        
        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPessoa Nao Encontrada");
        } else {

            if (medicoDAO.verificaSeMedicoExiste(pessoaEncontrada) == true) {
                System.out.println("\nPessoa ja Cadastrada Como Medico.");
            } else {

                System.out.println("\nInforme O Crm do Medico: ");
                String crm = scanner.nextLine();
                crm = vd.validaString(crm);

                if (medicoDAO.verificaCrm(crm) == true) {
                    System.out.println("\nO Crm Informado Ja Esta Sendo Usado.");
                } else {
                    System.out.println("\nInforme A Especialidade do Medico: ");
                    String medicoEspecialidade = scanner.nextLine();
                    medicoEspecialidade = vd.validaString(medicoEspecialidade);

                    System.out.println("\nInforme O Login De Medico: ");
                    String LoginMedico = scanner.nextLine();
                    LoginMedico = vd.validaString(LoginMedico);

                    if (medicoDAO.verificaSeloginEstaSendoUsado(LoginMedico) == true) {
                        System.out.println("\nLogin De Medico Ja Esta Sendo Usado!");
                    } else {
                        System.out.println("\nInforme A Senha De Medico: ");
                        String senhaMedico = scanner.nextLine();
                        senhaMedico = vd.validaString(senhaMedico);

                        Pessoa pessoaMedico = new Pessoa(pessoaEncontrada.getNomePessoa(),
                                pessoaEncontrada.getCpf(), pessoaEncontrada.getEnderecoPessoa(),
                                pessoaEncontrada.getTelefonePessoa(),
                                LoginMedico, senhaMedico, "Medico", calendarioSistema.getDataHoraSistema());

                        Medico medico = new Medico();
                        medico.setCrm(crm);
                        medico.setEspecialidade(medicoEspecialidade);
                        medico.setDataCriacao(calendarioSistema.getDataHoraSistema());

                        if (medicoDAO.insereMedicoNoBancoDeDados(pessoaMedico, medico) == true) {
                            System.out.println("\nMedico Cadastrado Com Sucesso!");
                        } else {
                            System.out.println("\nNao Foi Possivel Cadastrar o Medico.");
                        }

                    }

                }

            }

        }

    }

    private void geraRelatoriosUnidadeFranquia(FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, UnidadeFranquia unidadeFranquia, ValidacaoEntradaDados vd,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, MedicoDAO medicoDAO,
            CalendarioSistema calendarioSistema) {

        int opcao;

        do {
            
            try {
                opcao = menuTitulosUnidadeFranquia.menuRelatoriosFinanceirosUnidadeFranquia();
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {
                    relatorioGeralUnidadeFranquia(financeiroAdmDAO, financeiroMedicoDAO, unidadeFranquia,
                            consultaDAO, procedimentoDAO, medicoDAO, vd);
                    break;
                }

                case 2: {
                    relatorioMensalUnidadeFranquia(financeiroAdmDAO, financeiroMedicoDAO, unidadeFranquia,
                            consultaDAO, procedimentoDAO, medicoDAO, vd, calendarioSistema);
                    break;

                }

            }

        } while (opcao != 0);
    }

    private void relatorioGeralUnidadeFranquia(FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, UnidadeFranquia unidadeFranquia,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, MedicoDAO medicoDAO, ValidacaoEntradaDados vd) {

        int opcao;
        double valoresMedicos;
        double parteUnidadeConsulta;
        double parteUnidadeProcedimento;
        double valorBrutoConsulta;
        double valorBrutoProcedimento;

        do {

            System.out.println("\nMovimentacoes Financeiras Unidade De Franquia  - (Entrada/saida): ");
            System.out.println("\n");
            financeiroAdmDAO.geraRelatorioEntradaSaidaUnidadeFranquia(unidadeFranquia);

            System.out.println("\nMovimentacoes Financeiras Unidade De Franquia - (Pagamentos Dos Medicos): ");
            System.out.println("\n");
            medicoDAO.mostraTodosMedicos();

            int idMedico;
            try {
                System.out.println("\nInforme O ID - Medico: ");
                idMedico = Integer.parseInt(scanner.nextLine());
                idMedico = vd.validarINT(idMedico);
            } catch (Exception e) {
                idMedico = 1000;
                System.out.println("\nID - Medico invalido!!");
            }
            
            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nMedico Nao Encontrado.");
            } else {
                System.out.println("\n---- Relacao Valores Recebidos Pelo Medico - Unidade Franquia ---");
                System.out.println("\nMedico: " + medicoEncontrado);
                System.out.println("\nUnidade: " + unidadeFranquia);

                valorBrutoConsulta = consultaDAO.calculaValorConsultasPorUnidadeFranquia(medicoEncontrado, unidadeFranquia);
                System.out.println("\nValor Bruto Das Consultas: " + valorBrutoConsulta);

                valorBrutoProcedimento = procedimentoDAO.calculaValorProcedimentosPorUnidadeFranquia(medicoEncontrado, unidadeFranquia);
                System.out.println("\nValor Bruto Dos Procedimentos: " + valorBrutoProcedimento);

                parteUnidadeConsulta = consultaDAO.calculaParteDescontoConsultas(valorBrutoConsulta);
                System.out.println("\nParte Da Unidade De Franquia Sobre Consultas: " + parteUnidadeConsulta);

                parteUnidadeProcedimento = procedimentoDAO.calculaParteDescontoProcedimentos(valorBrutoProcedimento);
                System.out.println("\nParte Da Unidade De Franquia Sobre Procedimentos: " + parteUnidadeProcedimento);

                valoresMedicos = financeiroMedicoDAO.calculaValorLiquidoAReceberMedico(valorBrutoConsulta, valorBrutoProcedimento,
                        parteUnidadeConsulta, parteUnidadeProcedimento);
                System.out.println("\nValores Pagos Ao Medico - (Consultas + Procedimentos): " + valoresMedicos);
            }

            System.out.println("\n0 - Para Sair Do Relatorio: ");
            System.out.println("\n1 - Para Continuar No Relatorio: ");
            
            try {
                System.out.println("\nInforme Opcao : ");
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
        } while (opcao != 0);
    }

    private void relatorioMensalUnidadeFranquia(FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, UnidadeFranquia unidadeFranquia,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, MedicoDAO medicoDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        int opcao;
        double parteUnidadeConsulta;
        double parteUnidadeProcedimento;
        double valorBrutoConsulta;
        double valorBrutoProcedimento;
        double valoresMedicos;
        
        int numeroMes;
        try {
            System.out.println("\nInforme O Numero Do Mes Que Deseja Ver Relatorio: ");
            numeroMes = Integer.parseInt(scanner.nextLine());
            numeroMes = vd.validarINT(numeroMes);
        } catch (Exception e) {
            numeroMes = 1000;
            System.out.println("\nNumero Do Mes invalido!!");
        }
        
        do {

            System.out.println("\nMovimentacoes Financeiras Mensais Unidade De Franquia  - (Entrada/saida): ");
            System.out.println("\n");
            financeiroAdmDAO.geraRelatorioEntradaSaidaUnidadeFranquiaMes(unidadeFranquia, numeroMes);

            System.out.println("\nMovimentacoes Financeiras Mensais Unidade De Franquia - (Pagamentos Dos Medicos): ");
            System.out.println("\n");
            medicoDAO.mostraTodosMedicos();
            
            int idMedico;
            try {
                System.out.println("\nInforme O ID - Medico: ");
                idMedico = Integer.parseInt(scanner.nextLine());
                idMedico = vd.validarINT(idMedico);
            } catch (Exception e) {
                idMedico = 1000;
                System.out.println("\nID - Medico invalido!!");
            }
            
            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nMedico Nao Encontrado.");
            } else {
                System.out.println("\n---- Relacao Valores Mensais Recebidos Pelo Medico - Unidade Franquia ---");
                System.out.println("\nMedico: " + medicoEncontrado);
                System.out.println("\nUnidade: " + unidadeFranquia);

                valorBrutoConsulta = consultaDAO.calculaValorConsultasPorUnidadeFranquiaMes(medicoEncontrado,
                        unidadeFranquia, numeroMes);
                System.out.println("\nValor Bruto Das Consultas: " + valorBrutoConsulta + " " + "Mes: " + numeroMes);

                valorBrutoProcedimento = procedimentoDAO.calculaValorProcedimentosPorUnidadeFranquiaMes(medicoEncontrado,
                        unidadeFranquia, numeroMes);
                System.out.println("\nValor Bruto Dos Procedimentos: " + valorBrutoProcedimento + " " + "Mes: " + numeroMes);

                parteUnidadeConsulta = consultaDAO.calculaParteDescontoConsultas(valorBrutoConsulta);
                System.out.println("\nParte Da Unidade De Franquia Sobre Consultas: " + parteUnidadeConsulta + " " + "Mes: " + numeroMes);

                parteUnidadeProcedimento = procedimentoDAO.calculaParteDescontoProcedimentos(valorBrutoProcedimento);
                System.out.println("\nParte Da Unidade De Franquia Sobre Procedimentos: " + parteUnidadeProcedimento + " " + "Mes: " + numeroMes);

                valoresMedicos = financeiroMedicoDAO.calculaValorLiquidoAReceberMedico(valorBrutoConsulta, valorBrutoProcedimento,
                        parteUnidadeConsulta, parteUnidadeProcedimento);

                System.out.println("\nValores Recebidos - (Consultas + Procedimentos): " + valoresMedicos + " " + "Mes: " + numeroMes);
            }

            System.out.println("\n0 - Para Sair Do Relatorio: ");
            System.out.println("\n1 - Para Continuar No Relatorio: ");
            
            try {
                System.out.println("\nInforme Opcao : ");
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
           
        } while (opcao != 0);
    }
}
