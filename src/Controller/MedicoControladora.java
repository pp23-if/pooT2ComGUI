package Controller;

import Model.CalendarioSistema;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedicoDAO;
import Model.InfoConsulta;
import Model.InfoConsultaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.Procedimento;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulosMedico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MedicoControladora {

    Scanner scanner = new Scanner(System.in);
    MenuTitulosMedico telaMedico = new MenuTitulosMedico();

    public MedicoControladora(Medico medico, MedicoDAO medicoDAO, ValidacaoEntradaDados vd,
            ConsultaDAO consultaDAO, InfoConsultaDAO infoConsultaDAO,
            ProcedimentoDAO procedimentoDAO, PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO) {

        menuOpcoesMedico(medico, medicoDAO, vd, consultaDAO, infoConsultaDAO, procedimentoDAO,
                pessoaDAO, calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO, unidadeFranquiaDAO);

    }

    private void menuOpcoesMedico(Medico medico, MedicoDAO medicoDAO,
            ValidacaoEntradaDados vd, ConsultaDAO consultaDAO, InfoConsultaDAO infoConsultaDAO,
            ProcedimentoDAO procedimentoDAO, PessoaDAO pessoaDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO, UnidadeFranquiaDAO unidadeFranquiaDAO) {

        int opcao;

        do {

            try {
                opcao = telaMedico.menuMedico();
            } catch (NumberFormatException e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }

            pessoaDAO.BuscaPessoaNoBancoDeDados();

            medicoDAO.BuscaMedicoNoBancoDeDados(pessoaDAO);

            medicoDAO.atualizaMedicoLogadaComBancoDeDados(medico.getCrm(), medico, medicoDAO);

            switch (opcao) {
                case 1: {
                    System.out.println("\n" + medicoDAO.buscaMedico(medico));
                    break;
                }
                case 2: {
                    menuOpcoesAtualizarDadosMedico(medico, medicoDAO, vd, calendarioSistema);
                    break;
                }
                case 3: {
                    menuOpcoesConsultaMedico(medico, consultaDAO, infoConsultaDAO, vd,
                            calendarioSistema, financeiroAdmDAO, medicoDAO, pessoaDAO, unidadeFranquiaDAO);
                    break;
                }
                case 4: {
                    menuOpcoesProcedimentosMedico(consultaDAO, procedimentoDAO, medico, vd,
                            calendarioSistema, financeiroAdmDAO);
                    break;
                }
                case 5: {
                    gerarRelatorioDeConsultasEProcedimentosDeUmDadoPaciente(consultaDAO, procedimentoDAO,
                            pessoaDAO, medico, vd);
                    break;
                }
                case 6: {
                    geraRelatorioFinanceiroMedico(medico, financeiroMedicoDAO, vd);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void menuOpcoesAtualizarDadosMedico(Medico medico, MedicoDAO medicoDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {
        int opcao;

        do {

            try {
                opcao = telaMedico.menuAlteraDadosMedico();
            } catch (NumberFormatException e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }

            switch (opcao) {
                case 1: {

                    System.out.println("\nInforme o Novo login De Medico: ");
                    String novologinMedico = scanner.nextLine();
                    novologinMedico = vd.validaString(novologinMedico);

                    if (medicoDAO.AtualizaLoginMedicoNoBancoDeDados(novologinMedico, medico, calendarioSistema) == true) {

                        medico.getPessoa().setLoginPessoa(novologinMedico);

                        System.out.println("\nO Login De Medico Foi Atualizado Com Sucesso!");

                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Login de Medico.");
                    }
                    break;
                }
                case 2: {

                    System.out.println("\nInforme a Nova Senha De Medico: ");
                    String novaSenhaMedico = scanner.nextLine();
                    novaSenhaMedico = vd.validaString(novaSenhaMedico);

                    if (medicoDAO.AtualizaSenhaMedicoNoBancoDeDados(novaSenhaMedico, medico, calendarioSistema) == true) {

                        medico.getPessoa().setSenhaPessoa(novaSenhaMedico);

                        System.out.println("\nA Senha De Medico Foi Atualizada Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar a Senha de Medico.");
                    }
                    break;
                }

                case 3: {
                    System.out.println("\nInforme o Novo Telefone De Medico: ");
                    String novoTelefoneMedico = scanner.nextLine();
                    novoTelefoneMedico = vd.validaString(novoTelefoneMedico);

                    if (medicoDAO.AtualizaTelefoneMedicoNoBancoDeDados(novoTelefoneMedico, medico, calendarioSistema) == true) {

                        medico.getPessoa().setTelefonePessoa(novoTelefoneMedico);

                        System.out.println("\nO Telefone De Medico Foi Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar o Telefone de Medico.");
                    }
                    break;
                }
            }

        } while (opcao != 0);

    }

    private void menuOpcoesConsultaMedico(Medico medico, ConsultaDAO consultaDAO,
            InfoConsultaDAO infoConsultaDAO, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, MedicoDAO medicoDAO, PessoaDAO pessoaDAO, UnidadeFranquiaDAO unidadeFranquiaDAO) {

        int opcao;

        do {
            try {
                opcao = telaMedico.menuGerenciamentoConsultas();
            } catch (NumberFormatException e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }

            consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);
            infoConsultaDAO.BuscaInfoConsultaNoBancoDeDados(consultaDAO);

            switch (opcao) {
                case 1: {

                    System.out.println("\n");
                    if (consultaDAO.buscaConsultasDoDia(calendarioSistema, medico) == true) {

                        if (consultaDAO.recebeConsultaParaSerAtendida(medico, calendarioSistema, financeiroAdmDAO) == true) {
                            System.out.println("\nConsulta atendida com sucesso.");
                        } else {
                            System.out.println("\nNao Foi Possivel Atender a Consulta.");
                        }
                    } else {
                        System.out.println("\nNao existe mais consultas marcadas.");
                    }

                    break;
                }
                case 2: {
                    System.out.println("\n");
                    consultaDAO.buscaConsultaPorMedico(medico);
                    break;
                }
                case 3: {
                    System.out.println("\n");
                    infoConsultaDAO.buscaInfoConsultasPorMedico(medico);
                    break;
                }
                case 4: {
                    atualizaInfoConsulta(medico, infoConsultaDAO, vd, calendarioSistema);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void atualizaInfoConsulta(Medico medico, InfoConsultaDAO infoConsultaDAO,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        infoConsultaDAO.buscaInfoConsultasPorMedico(medico);

        int idInfoConsulta;
        try {
            System.out.println("\nInforme o ID - InfoConsulta Que Deseja Atualizar: ");
            idInfoConsulta = Integer.parseInt(scanner.nextLine());
            idInfoConsulta = vd.validarINT(idInfoConsulta);
        } catch (NumberFormatException e) {
            idInfoConsulta = 200;
            System.out.println("\nID - InfoConsulta invalida!!");
        }

        InfoConsulta infoConsulta = infoConsultaDAO.buscaInfoConsultaPorId(idInfoConsulta);

        if (infoConsulta == null) {
            System.out.println("\nInfo Consulta Nao Encontrada.");
        } else {
            System.out.println("\nInforme A Descricao Da Info Consulta: ");
            String descricao = scanner.nextLine();

            if (infoConsultaDAO.atualizaDescricaoInfoConsultaNoBancoDeDados(infoConsulta, descricao, calendarioSistema) == true) {
                System.out.println("\nDescricao Da Info Consulta Atualizada Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Atualizar A Descricao Da Info Consulta.");
            }
        }
    }

    private void menuOpcoesProcedimentosMedico(ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Medico medico, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO) {

        int opcao;

        do {
            try {
                opcao = telaMedico.menuGerenciaProcedimentos();
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }

            procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);

            switch (opcao) {
                case 1: {
                    marcarProcedimentoComoMedico(consultaDAO, procedimentoDAO, medico, vd, calendarioSistema);
                    break;
                }
                case 2: {
                    realizarProcedimento(procedimentoDAO, medico, vd, calendarioSistema,
                            financeiroAdmDAO, consultaDAO);
                    break;
                }
                case 3: {
                    System.out.println("\n");
                    procedimentoDAO.buscaProcedimentoPorMedico(medico);
                    break;
                }
                case 4: {
                    cancelarProcedimentoComoMedico(procedimentoDAO, medico, vd, calendarioSistema);
                    break;
                }
                case 5: {
                    remarcarProcedimentoComoMedico(procedimentoDAO, medico, vd, calendarioSistema);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void marcarProcedimentoComoMedico(ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Medico medico, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        consultaDAO.buscaConsultaPorMedico(medico);

        int idConsulta;
        try {
            System.out.println("\nInforme O ID - Consulta Que deseja Usar: ");
            idConsulta = Integer.parseInt(scanner.nextLine());
            idConsulta = vd.validarINT(idConsulta);
        } catch (NumberFormatException e) {
            idConsulta = 200;
            System.out.println("\nID - Consulta invalido!!");
        }

        Consulta consultaEncontrada = consultaDAO.buscaConsultaRealizadaPorId(idConsulta);

        if (consultaEncontrada == null) {
            System.out.println("\nConsulta Nao Encontrada Ou Ainda Nao Realizada.");
        } else {
            DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("\nQual procedimento Sera Feito: ");
            String nomeProcedimento = scanner.nextLine();
            nomeProcedimento = vd.validaString(nomeProcedimento);

            String dia = "";
            LocalDate diaProcedimento;
            try {
                System.out.println("\nInforme a Data Do Procedimento No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                dia = scanner.nextLine();
                diaProcedimento = LocalDate.parse(dia, fdia);
            } catch (Exception e) {
                dia = "";
                diaProcedimento = vd.validaStringData(dia);
            }

            String Hora = "";
            LocalTime horaProcedimento;
            try {
                System.out.println("\nInforme a Hora Do Procedimento No Seguinte Formato, Hora:Minutos (00:00)..: ");
                Hora = scanner.nextLine();
                horaProcedimento = LocalTime.parse(Hora);
            } catch (Exception e) {
                Hora = "";
                horaProcedimento = vd.validaHora(Hora);
            }

            if (procedimentoDAO.verificaDataProcedimento(calendarioSistema, diaProcedimento) == true) {

                System.out.println("\nData Informada Invalida.");

            } else {

                if (procedimentoDAO.verificaDisponibilidadeDataEHoraProcedimentoMedico(diaProcedimento,
                        horaProcedimento, medico) == true) {

                    System.out.println("\nDia e hora Informados, Indisponiveis.");

                } else {

                    Procedimento procedimento = new Procedimento();
                    procedimento.setNomeProcedimento(nomeProcedimento);
                    procedimento.setDiaProcedimento(diaProcedimento);
                    procedimento.setHoraProcedimento(horaProcedimento);
                    procedimento.setHoraProcedimento(horaProcedimento);
                    procedimento.setEstadoProcedimento("Agendado");
                    procedimento.setValorProcedimento(1500);
                    procedimento.setLaudo("");
                    procedimento.setDataCriacao(calendarioSistema.getDataHoraSistema());

                    if (procedimentoDAO.insereProcedimentoNoBancoDeDados(consultaEncontrada, procedimento) == true) {

                        System.out.println("\nProcedimento Marcado Com Sucesso!");

                    } else {
                        System.out.println("\nNao Foi Possivel Marcar o Procedimento.");
                    }
                }
            }

        }

    }

    private void cancelarProcedimentoComoMedico(ProcedimentoDAO procedimentoDAO, Medico medico,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorMedico(medico);

        int idProcedimento;
        try {
            System.out.println("\nInforme O ID - Procedimento Que Deseja Cancelar: ");
            idProcedimento = Integer.parseInt(scanner.nextLine());
            idProcedimento = vd.validarINT(idProcedimento);
        } catch (Exception e) {
            idProcedimento = 200;
            System.out.println("\nID - Procedimento invalido!! ");
        }

        Procedimento procedimentoEncontrado = procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

        if (procedimentoEncontrado == null) {
            System.out.println("\nProcedimento Nao Encontrado.");
        } else {
            if (procedimentoDAO.cancelaProcedimentoNoBancoDeDados(procedimentoEncontrado, calendarioSistema) == true) {
                System.out.println("\nProcedimento Cancelado Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Cancelar O Procedimento.");
            }
        }

    }

    private void remarcarProcedimentoComoMedico(ProcedimentoDAO procedimentoDAO, Medico medico,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorMedico(medico);

        int idProcedimento;
        try {
            System.out.println("\nInforme O ID - Procedimento Que Deseja Remarcar: ");
            idProcedimento = Integer.parseInt(scanner.nextLine());
            idProcedimento = vd.validarINT(idProcedimento);
        } catch (Exception e) {
            idProcedimento = 200;
            System.out.println("\nID - Procedimento invalido!!");
        }

        Procedimento procedimentoEncontrado = procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

        if (procedimentoEncontrado == null) {
            System.out.println("\nProcedimento Nao Encontrado.");
        } else {
            DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String dia = "";
            LocalDate diaProcedimento;
            try {
                System.out.println("\nInforme a Nova Data Do Procedimento No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                dia = scanner.nextLine();
                diaProcedimento = LocalDate.parse(dia, fdia);
            } catch (Exception e) {
                dia = "";
                diaProcedimento = vd.validaStringData(dia);
            }

            String Hora = "";
            LocalTime horaProcedimento;
            try {
                System.out.println("\nInforme a Nova Hora Do Procedimento No Seguinte Formato, Hora:Minutos (00:00)..: ");
                Hora = scanner.nextLine();
                horaProcedimento = LocalTime.parse(Hora);
            } 
            catch (Exception e) {
                Hora = "";
                horaProcedimento = vd.validaHora(Hora);
            }

            if (procedimentoDAO.verificaDataProcedimento(calendarioSistema, diaProcedimento) == true) {

                System.out.println("\nData Informada Invalida.");

            } else {
                if (procedimentoDAO.verificaDisponibilidadeDataEHoraProcedimentoMedico(diaProcedimento, horaProcedimento,
                        medico) == true) {

                    System.out.println("\nDia e hora Informados, Indisponiveis.");

                } else {
                    if (procedimentoDAO.remarcaProcedimentoNoBancoDeDados(diaProcedimento, horaProcedimento,
                            procedimentoEncontrado, calendarioSistema) == true) {

                        System.out.println("\nProcedimento Remarcado Com Sucesso!");

                    } else {

                        System.out.println("\nNao Foi Possivel Remarcar O Procedimento.");
                    }
                }
            }

        }
    }

    private void realizarProcedimento(ProcedimentoDAO procedimentoDAO, Medico medico,
            ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, ConsultaDAO consultaDAO) {

        procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);
        System.out.println("\n");

        Procedimento procedimentoEncontrado = procedimentoDAO.buscaProcedimentoNaoRealizado(medico, calendarioSistema);

        if (procedimentoEncontrado == null) {
            System.out.println("\nNao Existem Procedimentos Agendados.");
        } else {
            System.out.println("\n" + procedimentoEncontrado);
            
            System.out.println("\nInforme O Laudo Do Procedimento: ");
            String laudoProcedimento = scanner.nextLine();
            laudoProcedimento = vd.validaString(laudoProcedimento);

            if (procedimentoDAO.realizaProcedimentoNoBancoDeDados(procedimentoEncontrado, calendarioSistema, laudoProcedimento) == true) {
                System.out.println("\nProcedimento Realizado Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Realizar O Procedimento.");
            }

        }

    }

    private void gerarRelatorioDeConsultasEProcedimentosDeUmDadoPaciente(ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, PessoaDAO pessoaDAO, Medico medico, ValidacaoEntradaDados vd) {

        System.out.println("\n");
        pessoaDAO.filtraPacientes();
        
        int idPessoa;
        try {
            System.out.println("\nInforme o ID - Pessoa: ");
            idPessoa = Integer.parseInt(scanner.nextLine());
            idPessoa = vd.validarINT(idPessoa);
        } catch (Exception e) {
            idPessoa = 200;
            System.out.println("\nID - Pessoa invalido!!");
        }
        

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPaciente Nao Encontrado.");
        } else {
            System.out.println("\n******* - Paciente: " + pessoaEncontrada.getNomePessoa());
            System.out.println("\n ....... Consultas ........:");
            System.out.println("\n");
            consultaDAO.buscaConsultasQueTemMedicoSolicitanteEPacienteEscolhido(pessoaEncontrada, medico);

            System.out.println("\n ....... Procedimentos ........:");
            System.out.println("\n");
            procedimentoDAO.buscaProcedimentosQueTemMedicoSolicitanteEPacienteEscolhido(pessoaEncontrada, medico);

        }

    }

    private void geraRelatorioFinanceiroMedico(Medico medico, FinanceiroMedicoDAO financeiroMedicoDAO,
            ValidacaoEntradaDados vd) {
        
        int opcao;
        do {
            
            try {
                opcao = telaMedico.menuRelatoriosFinanceirosMedico();
            } catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            

            switch (opcao) {
                case 1: {
                    relatorioMontanteGeralMedico(medico, financeiroMedicoDAO);
                    break;
                }

                case 2: {
                    relatorioMontanteMensalMedico(medico, financeiroMedicoDAO, vd);
                    break;

                }

            }

        } while (opcao != 0);
    }

    private void relatorioMontanteGeralMedico(Medico medico, FinanceiroMedicoDAO financeiroMedicoDAO) {
        System.out.println("\nMedico: " + medico);
        System.out.println("\nValores Recebidos - (Consultas + Procedimentos): ");
        System.out.println("\n");
        financeiroMedicoDAO.buscaPagamentosMedicosPorMedico(medico);
    }

    private void relatorioMontanteMensalMedico(Medico medico, FinanceiroMedicoDAO financeiroMedicoDAO,
            ValidacaoEntradaDados vd) {
        
        int numeroMes;
        try {
            System.out.println("\nInforme O Numero Do Mes Que Deseja Ver Relatorio: ");
            numeroMes = Integer.parseInt(scanner.nextLine());
            numeroMes = vd.validarINT(numeroMes);
        } catch (Exception e) {
            numeroMes = 200;
            System.out.println("\nNumero Do Mes invalido");
        }
        
        System.out.println("\nMedico: " + medico);
        System.out.println("\nValores Recebidos - (Consultas + Procedimentos): ");
        System.out.println("\n");
        financeiroMedicoDAO.buscaPagamentosMedicosPorMedicoMes(medico, numeroMes);
    }
}
