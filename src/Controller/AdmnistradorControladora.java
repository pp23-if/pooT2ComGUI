package Controller;

import Model.Admnistrador;
import Model.AdmnistradorDAO;
import Model.CalendarioSistema;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.FinanceiroAdm;
import Model.FinanceiroAdmDAO;
import Model.FinanceiroMedico;
import Model.FinanceiroMedicoDAO;
import Model.FranquiaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.Procedimento;
import Model.ProcedimentoDAO;
import Model.UnidadeFranquia;
import Model.UnidadeFranquiaDAO;
import View.MenuTitulosAdmistrador;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdmnistradorControladora {

    MenuTitulosAdmistrador telaAdmistrador = new MenuTitulosAdmistrador();

    Scanner scanner = new Scanner(System.in);

    public AdmnistradorControladora(PessoaDAO pessoaDAO, AdmnistradorDAO admnistradorDAO,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ConsultaDAO consultaDAO, ValidacaoEntradaDados vd,
            Admnistrador admnistrador, MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO,
            CalendarioSistema calendarioSistema, FinanceiroAdmDAO financeiroAdmDAO,
            FinanceiroMedicoDAO financeiroMedicoDAO, FranquiaDAO franquiaDAO) {

        menuOpcoesAdmnistrador(pessoaDAO, admnistradorDAO,
                unidadeFranquiaDAO, consultaDAO, vd, admnistrador, medicoDAO, procedimentoDAO,
                calendarioSistema, financeiroAdmDAO, financeiroMedicoDAO, franquiaDAO);

    }

    private void menuOpcoesAdmnistrador(PessoaDAO pessoaDAO,
            AdmnistradorDAO admnistradorDAO, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ConsultaDAO consultaDAO, ValidacaoEntradaDados vd, Admnistrador admnistrador,
            MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO, CalendarioSistema calendarioSistema,
            FinanceiroAdmDAO financeiroAdmDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            FranquiaDAO franquiaDAO) {

        int opcao;

        do {

            consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);
            procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);
            financeiroAdmDAO.buscaFinanceiroADMNoBancoDeDados(unidadeFranquiaDAO);
            financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);
            
            try {
                opcao = telaAdmistrador.menuAdmnistrador();
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }

            switch (opcao) {
                case 1: {
                    System.out.println("\n" + admnistradorDAO.
                            buscaAdmnistradorAtravesPessoaVinculada(admnistrador.getPessoa()));

                    break;
                }
                case 2: {
                    menuOpcoesConsulta(consultaDAO, admnistrador,
                            unidadeFranquiaDAO, vd, pessoaDAO, medicoDAO, calendarioSistema);
                    break;
                }
                case 3: {
                    menuOpcoesProcedimento(consultaDAO, admnistrador, unidadeFranquiaDAO,
                            vd, pessoaDAO, medicoDAO, procedimentoDAO, calendarioSistema);
                    break;
                }
                case 4: {
                    menuOpcoesFinanceiro(financeiroAdmDAO, calendarioSistema,
                            consultaDAO, procedimentoDAO, admnistrador, unidadeFranquiaDAO, vd,
                            financeiroMedicoDAO, medicoDAO, pessoaDAO, franquiaDAO);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void menuOpcoesConsulta(ConsultaDAO consultaDAO,
            Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            PessoaDAO pessoaDAO, MedicoDAO medicoDAO, CalendarioSistema calendarioSistema) {

        int opcao;

        do {

            consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);
            
            try {
                opcao = telaAdmistrador.menuConsultas();
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {
                    marcarConsulta(admnistrador, unidadeFranquiaDAO, vd,
                            pessoaDAO, medicoDAO, consultaDAO, calendarioSistema);

                    break;
                }
                case 2: {
                    System.out.println("\n");
                    consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 3: {
                    cancelarConsulta(consultaDAO, admnistrador, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    remarcarConsulta(admnistrador, unidadeFranquiaDAO, vd,
                            pessoaDAO, medicoDAO, consultaDAO, calendarioSistema);
                    break;
                }
            }
        } while (opcao != 0);
    }

    private void marcarConsulta(Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd,
            PessoaDAO pessoaDAO, MedicoDAO medicoDAO, ConsultaDAO consultaDAO,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());
        
        int idUnidadeFranquia;
        try {
            System.out.println("\nInforme o ID da Unidade da Franquia que deseja realizar a consulta: ");
            idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
            idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);
        } 
        catch (Exception e) {
            idUnidadeFranquia = 1000;
            System.out.println("\nID da Unidade da Franquia invalido!! ");
        }
        
        UnidadeFranquia unidadeEncontrada = unidadeFranquiaDAO.
                buscaUnidadeFranquiaPorId(idUnidadeFranquia);

        if (unidadeEncontrada == null) {
            System.out.println("\nUnidade de franquia nao encontrada.");
        } else {

            System.out.println("\n");
            pessoaDAO.filtraPacientes();
            
            int idPessoaConsulta;
            try {
                System.out.println("\nInforme o ID Da pessoa que deseja marcar para consulta: ");
                idPessoaConsulta = Integer.parseInt(scanner.nextLine());
                idPessoaConsulta = vd.validarINT(idPessoaConsulta);
            } 
            catch (Exception e) {
                idPessoaConsulta = 1000;
                System.out.println("\nID Da pessoa invalido!!");
            }
  
            System.out.println("\n");
            Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoaConsulta);

            if (pessoaEncontrada == null) {
                System.out.println("\nPessoa nao encontrada");
            } else {

                System.out.println("\n");
                medicoDAO.mostraTodosMedicosHabilitados();
                
                int idMedico;
                try {
                    System.out.println("\nInforme o ID Do Medico que deseja se consultar: ");
                    idMedico = Integer.parseInt(scanner.nextLine());
                    idMedico = vd.validarINT(idMedico);
                } 
                catch (Exception e) {
                    idMedico = 1000;
                    System.out.println("\nID Do Medico invalido!!");
                }
                
                Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

                if (medicoEncontrado == null) {
                    System.out.println("\nmedico nao encontrado");
                } else {
                    if (medicoDAO.vericaSeMedicoEPacienteSaoIguais(pessoaEncontrada,
                            medicoEncontrado) == true) {

                        System.out.println("\nMedico e Paciente sao as Mesmas Pessoas..");
                    } else {
                        double valorConsulta = medicoDAO.verificaValorConsulta(medicoEncontrado);
                        
                        DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        
                        String dia = "";
                        LocalDate diaConsulta;
                        try {
                            System.out.println("\nInforme a Data Da Consulta No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                            dia = scanner.nextLine();
                            diaConsulta = LocalDate.parse(dia, fdia);
                        } 
                        catch (Exception e) {
                            dia = "";
                            diaConsulta = vd.validaStringData(dia);
                        }
                        
                        String Hora = "";
                        LocalTime horaConsulta;
                        try {
                            System.out.println("\nInforme a Hora Da Consulta No Seguinte Formato, Hora:Minutos (00:00)..: ");
                            Hora = scanner.nextLine();
                            horaConsulta = LocalTime.parse(Hora);
                        }
                        catch (Exception e) {
                            Hora = "";
                            horaConsulta = vd.validaHora(Hora);
                        }
                       

                        if (consultaDAO.verificaDataConsulta(calendarioSistema, diaConsulta) == true) {
                            System.out.println("\nData Informada Invalida.");
                        } else {
                            if (consultaDAO.verificaDisponibilidadeDiaEHoraConsultaMedico(diaConsulta,
                                    horaConsulta, medicoEncontrado) == true) {

                                System.out.println("\nDia e Hora Informados Indisponiveis.");
                            } else {
                                Consulta novaConsulta = new Consulta(diaConsulta, horaConsulta,
                                        medicoEncontrado, pessoaEncontrada, unidadeEncontrada,
                                        valorConsulta, "Agendada", calendarioSistema.getDataHoraSistema());

                                if (consultaDAO.insereConsultaNoBancoDeDados(novaConsulta) == true) {
                                    System.out.println("\nConsulta marcada com sucesso.");
                                } else {
                                    System.out.println("\nNao foi possivel marcar Consulta");
                                }

                            }
                        }

                    }

                }
            }

        }

    }

    private void cancelarConsulta(ConsultaDAO consultaDAO,
            Admnistrador admnistrador, ValidacaoEntradaDados vd, CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());
        
        int idConsulta;
        try {
            System.out.println("\nInforme o ID da Consulta que deseja cancelar: ");
            idConsulta = Integer.parseInt(scanner.nextLine());
            idConsulta = vd.validarINT(idConsulta);
        } 
        catch (Exception e) {
            idConsulta = 1000;
            System.out.println("\nID da Consulta invalido!!");
        }
        
        Consulta consultaEncontra = consultaDAO.buscaConsultaPorId(idConsulta);

        if (consultaEncontra == null) {
            System.out.println("\nConsulta nao Encontrada");
        } else {
            if (consultaDAO.cancelaConsultaNoBancoDeDados(consultaEncontra, calendarioSistema) == true) {
                System.out.println("\nConsulta cancelada com sucesso.");

            } else {
                System.out.println("\nNao foi Possivel cancelar Consulta..");
            }

        }
    }

    private void remarcarConsulta(Admnistrador admnistrador, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, PessoaDAO pessoaDAO, MedicoDAO medicoDAO, ConsultaDAO consultaDAO,
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        consultaDAO.buscaConsultaPorFranquia(admnistrador.getFranquia());
        
        int idConsulta;
        try {
            System.out.println("\nInforme o ID da consulta que desaja remarcar: ");
            idConsulta = Integer.parseInt(scanner.nextLine());
            idConsulta = vd.validarINT(idConsulta);
        } 
        catch (Exception e) {
            idConsulta = 1000;
            System.out.println("\nID da consulta invalido!!");
        }
        
        Consulta consultaEncontrada = consultaDAO.buscaConsultaPorId(idConsulta);

        if (consultaEncontrada == null) {
            System.out.println("\nConsulta Nao Encontrada");
        } else {
            
            DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            String dia = "";
            LocalDate diaConsulta;
            try {
                System.out.println("\nInforme a Nova Data Da Consulta No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                dia = scanner.nextLine();
                diaConsulta = LocalDate.parse(dia, fdia);
            } 
            catch (Exception e) {
                dia = "";
                diaConsulta = vd.validaStringData(dia);
            }
            
            String Hora = "";
            LocalTime horaConsulta;
            try {
                System.out.println("\nInforme a Nova Hora Da Consulta No Seguinte Formato, Hora:Minutos (00:00)..: ");
                Hora = scanner.nextLine();
                horaConsulta = LocalTime.parse(Hora);
            } 
            catch (Exception e) {
                Hora = "";
                horaConsulta = vd.validaHora(Hora);
            }
            
            if (consultaDAO.verificaDataConsulta(calendarioSistema, diaConsulta) == true) {

                System.out.println("\nData Informada Invalida.");

            } else {

                if (consultaDAO.verificaDisponibilidadeDiaEHoraConsultaMedico(diaConsulta, horaConsulta,
                        consultaEncontrada.getMedico()) == true) {
                    System.out.println("\nDia e Hora Informados Indisponiveis.");
                } else {

                    if (consultaDAO.remarcaConsultaNoBancoDeDados(diaConsulta, horaConsulta,
                            consultaEncontrada, calendarioSistema) == true) {

                        System.out.println("\nConsulta Remarcada Com Sucesso.");

                    } else {

                        System.out.println("\nNao Foi Possivel Remarcar a Consulta.");
                    }
                }
            }
        }
    }

    private void menuOpcoesProcedimento(ConsultaDAO consultaDAO, Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ValidacaoEntradaDados vd, PessoaDAO pessoaDAO,
            MedicoDAO medicoDAO, ProcedimentoDAO procedimentoDAO, CalendarioSistema calendarioSistema) {

        int opcao;

        do {

            procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);
            
            try {
                opcao = telaAdmistrador.menuProcedimentos();
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {

                    marcarProcedimento(pessoaDAO, medicoDAO, admnistrador, unidadeFranquiaDAO,
                            procedimentoDAO, vd, calendarioSistema, consultaDAO);
                    break;
                }
                case 2: {
                    System.out.println("\n");
                    procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 3: {
                    cancelarProcedimento(procedimentoDAO, admnistrador, vd, calendarioSistema);
                    break;
                }
                case 4: {
                    remarcarProcedimento(procedimentoDAO, admnistrador, vd, calendarioSistema);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void marcarProcedimento(PessoaDAO pessoaDAO, MedicoDAO medicoDAO, Admnistrador admnistrador,
            UnidadeFranquiaDAO unidadeFranquiaDAO, ProcedimentoDAO procedimentoDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO) {

        System.out.println("\n");
        pessoaDAO.filtraPacientes();
        
        int idPessoa;
        try {
            System.out.println("\nInforme o ID - paciente que Ira Passar pelo Procedimento: ");
            idPessoa = Integer.parseInt(scanner.nextLine());
            idPessoa = vd.validarINT(idPessoa);
        } 
        catch (Exception e) {
            idPessoa = 1000;
            System.out.println("\nID - paciente invalido!!");
        }
        

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaPorId(idPessoa);

        if (pessoaEncontrada == null) {
            System.out.println("\nPaciente Nao Encontrado.");
        } else {
            System.out.println("\n");
            medicoDAO.mostraTodosMedicosHabilitados();
            
            int idMedico;
            try {
                System.out.println("\nInforme o ID - Medico que Ira Fazer O Procedimento: ");
                idMedico = Integer.parseInt(scanner.nextLine());
                idMedico = vd.validarINT(idMedico);
            } 
            catch (Exception e) {
                idMedico = 1000;
                System.out.println("\nID - Medico invalido!!");
            }
            
            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nMedico Nao Encontrado.");
            } else {
                System.out.println("\n");
                unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());
                
                int idUnidadeFranquia;
                try {
                    System.out.println("\nInforme o ID - UnidadeFranquia Onde Ocorrera O Procedimento: ");
                    idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
                    idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);
                } 
                catch (Exception e) {
                    idUnidadeFranquia = 1000;
                    System.out.println("\nID - UnidadeFranquia invalido!!");
                }
                
                UnidadeFranquia unidadeFranquiaEncontrada
                        = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

                if (unidadeFranquiaEncontrada == null) {
                    System.out.println("\nUnidade De Franquia Nao Encontrada.");
                } else {

                    DateTimeFormatter fdia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    
                    System.out.println("\nQual procedimento Sera Feito: ");
                    String nomeProcedimento = scanner.nextLine();
                    nomeProcedimento = vd.validaString(nomeProcedimento);
                    
                    String dia = "";
                    LocalDate diaProcediemnto;
                    try {
                        System.out.println("\nInforme a Data Do Procedimento No Seguinte Formato, Dia/Mes/Ano (00/00/0000)..: ");
                        dia = scanner.nextLine();
                        diaProcediemnto = LocalDate.parse(dia, fdia);
                    } 
                    catch (Exception e) {
                        dia = "";
                        diaProcediemnto = vd.validaStringData(dia);
                    }
                    
                    String Hora = "";
                    LocalTime horaProcedimento;
                    try {
                        System.out.println("\nInforme a Hora Do Procedimento No Seguinte Formato, Hora:Minutos (00:00)..: ");
                        Hora = scanner.nextLine();
                        horaProcedimento = LocalTime.parse(Hora);
                    } 
                    catch (Exception e) {
                        Hora = "";
                        horaProcedimento = vd.validaHora(Hora);
                    }
                    
                    if (procedimentoDAO.verificaDataProcedimento(calendarioSistema, diaProcediemnto) == true) {
                        System.out.println("\nData Informada Invalida.");
                    } else {
                        if (procedimentoDAO.verificaDisponibilidadeDataEHoraProcedimentoMedico(diaProcediemnto, horaProcedimento,
                                medicoEncontrado) == true) {

                            System.out.println("\nDia e Hora Informados Indisponiveis.");

                        } else {

                            Consulta consulta = new Consulta(diaProcediemnto, horaProcedimento, medicoEncontrado,
                                    pessoaEncontrada, unidadeFranquiaEncontrada, 0, "Realizada",
                                    calendarioSistema.getDataHoraSistema());

                            if (consultaDAO.insereConsultaNoBancoDeDados(consulta) == true) {
                                consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO, unidadeFranquiaDAO);

                                Consulta consultaRecemInserida = consultaDAO.buscaConsultaPorIndice();

                                Procedimento procedimento = new Procedimento();
                                procedimento.setNomeProcedimento(nomeProcedimento);
                                procedimento.setDiaProcedimento(diaProcediemnto);
                                procedimento.setHoraProcedimento(horaProcedimento);
                                procedimento.setEstadoProcedimento("Agendado");
                                procedimento.setValorProcedimento(1500);
                                procedimento.setLaudo("");
                                procedimento.setDataCriacao(calendarioSistema.getDataHoraSistema());

                                if (procedimentoDAO.insereProcedimentoNoBancoDeDados(consultaRecemInserida, procedimento) == true) {

                                    System.out.println("\nProcedimento Marcado Com Sucesso!");

                                } else {

                                    System.out.println("\nNao Foi Possivel Marcar O Procediemnto.");
                                }

                            } else {
                                System.out.println("\nErro Ao Marcar O Procedimento.");
                            }
                        }
                    }
                }
            }
        }
    }

    private void cancelarProcedimento(ProcedimentoDAO procedimentoDAO, 
            Admnistrador admnistrador, ValidacaoEntradaDados vd, 
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());
        
        int idProcedimento;
        try {
            System.out.println("\nInforme o ID - Procedimento Que Deseja Cancelar: ");
            idProcedimento = Integer.parseInt(scanner.nextLine());
            idProcedimento = vd.validarINT(idProcedimento);
        } 
        catch (Exception e) {
            idProcedimento = 1000;
            System.out.println("\nID - Procedimento invalido!!");
        }
        

        Procedimento procedimentoEncontrado = 
                procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

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

    private void remarcarProcedimento(ProcedimentoDAO procedimentoDAO, 
            Admnistrador admnistrador, ValidacaoEntradaDados vd, 
            CalendarioSistema calendarioSistema) {

        System.out.println("\n");
        procedimentoDAO.buscaProcedimentoPorFranquia(admnistrador.getFranquia());
        
        int idProcedimento;
        try {
            System.out.println("\nInforme o ID - Procedimento Que Deseja Remarcar: ");
            idProcedimento = Integer.parseInt(scanner.nextLine());
            idProcedimento = vd.validarINT(idProcedimento);
        } 
        catch (Exception e) {
            idProcedimento = 1000;
            System.out.println("\nID - Procedimento invalido!!");
        }
        
        Procedimento procedimentoEncontrado = 
                procedimentoDAO.buscaProcedimentoPorId(idProcedimento);

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
            } 
            catch (Exception e) {
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
                        procedimentoEncontrado.getConsulta().getMedico()) == true) {

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

    private void menuOpcoesFinanceiro(FinanceiroAdmDAO financeiroAdmDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador, 
            UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, FinanceiroMedicoDAO financeiroMedicoDAO,
            MedicoDAO medicoDAO, PessoaDAO pessoaDAO, FranquiaDAO franquiaDAO) {

        int opcao;

        do {

            financeiroAdmDAO.buscaFinanceiroADMNoBancoDeDados(unidadeFranquiaDAO);
            financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);

            System.out.println("\nData e Hora do Sistema: " + calendarioSistema.getDataHoraSistema().format(DateTimeFormatter.
                    ofPattern("dd/MM/yyyy HH:mm:ss")));
            
            try {
                opcao = telaAdmistrador.menuFinanceiroAdm();
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            int dias = 0;
            switch (opcao) {
                case 1: {

                    dias++;
                    
                    LocalDateTime diaAntesDePassar = calendarioSistema.getDataHoraSistema();
                    if (calendarioSistema.passaDias(dias) == true) {
                        
                        if(calendarioSistema.
                                passaDiasnoBancoDeDados(calendarioSistema.
                                        getDataHoraSistema()) == true){
                            
                            calendarioSistema.buscaDataHoraSistemaNoBancoDeDados();
                            
                            consultaDAO.BuscaConsultaNoBancoDeDados(pessoaDAO, medicoDAO,
                                    unidadeFranquiaDAO);
                            
                            procedimentoDAO.BuscaProcedimentosNoBancoDeDados(consultaDAO);

                            cancelaConsultasNaoAtendidasNoDia(consultaDAO, calendarioSistema);
                            cancelaProcedimentosNaoAtendidosNoDia(procedimentoDAO, calendarioSistema);
                            
                            System.out.println("\nDia encerrado com sucesso!!");
                        }else{
                            calendarioSistema.setDataHoraSistema(diaAntesDePassar);
                            System.out.println("\nErro ao Passar Dia!!");
                        }
                        
                    } else {
                        System.out.println("\nNao foi possivel Encerrar o dia");
                    }

                    break;
                }
                case 2: {
                    pagamentosAdm(financeiroAdmDAO, calendarioSistema, consultaDAO,
                            procedimentoDAO, admnistrador, unidadeFranquiaDAO, vd, financeiroMedicoDAO, medicoDAO, franquiaDAO);
                    break;
                }
                case 3: {
                    System.out.println("\n");
                    financeiroAdmDAO.buscaMovimentacoesFinanceirasPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    financeiroMedicoDAO.buscaPagamentosMedicosPorFranquia(admnistrador.getFranquia());
                    break;
                }
                case 5: {
                    if(financeiroAdmDAO.geraRelatorioGeralFinanceiroAdmEmPdf() == true){
                        System.out.println("\nRelatorio gerado com sucesso!!");
                    }else{
                        System.out.println("\nNao foi possivel gerar relatorio.");
                    }
                }
            }
        } while (opcao != 0);
    }

    private void cancelaConsultasNaoAtendidasNoDia(ConsultaDAO consultaDAO,
            CalendarioSistema calendarioSistema) {

        if (consultaDAO.cancelaConsultasNaoRealizadasNoDia(calendarioSistema) == true) {
            System.out.println("\nTodas Consultas Nao realizadas No Dia Anterior Foram Canceladas.");
        }
    }

    private void cancelaProcedimentosNaoAtendidosNoDia(ProcedimentoDAO procedimentoDAO,
            CalendarioSistema calendarioSistema) {
        if (procedimentoDAO.cancelaProcedimentosNaoRealizadosNoDia(calendarioSistema) == true) {
            System.out.println("\nTodos Procedimentos Nao Realizados No Dia Anterior Foram Cancelados.");
        }
    }

    private boolean verificaSeEhPrimeiroDiaDoMes(CalendarioSistema calendarioSistema) {

        int diaSistema = calendarioSistema.getDiaDoSistema().getDayOfMonth();

        if (diaSistema == 01) {
            return true;
        }

        return false;
    }

    private void pagamentosAdm(FinanceiroAdmDAO financeiroAdmDAO,
            CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Admnistrador admnistrador, UnidadeFranquiaDAO unidadeFranquiaDAO,
            ValidacaoEntradaDados vd, FinanceiroMedicoDAO financeiroMedicoDAO, MedicoDAO medicoDAO, FranquiaDAO franquiaDAO) {

        int opcao;

        do {
            
            try {
                opcao = telaAdmistrador.menuPagamentosAdm();
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            switch (opcao) {
                case 1: {

                    if (verificaSeEhPrimeiroDiaDoMes(calendarioSistema) == true) {
                        if (pagaAdmnistradora(calendarioSistema, financeiroAdmDAO, unidadeFranquiaDAO,
                                admnistrador, vd) == true) {

                            if (CalculaValoresMedicos(calendarioSistema, consultaDAO, procedimentoDAO,
                                    financeiroMedicoDAO, medicoDAO, vd, admnistrador, franquiaDAO) == true) {

                                pagarMedicos(admnistrador, financeiroMedicoDAO, calendarioSistema, vd, medicoDAO, franquiaDAO);
                            }
                        }

                    } else {
                        System.out.println("\nEsse Tipo De Pagamento So Libera No Dia 01 De Cada Mes!");
                    }
                    break;
                }
                case 2: {
                    pagaDespesasAvulsas(calendarioSistema, unidadeFranquiaDAO, admnistrador, vd, financeiroAdmDAO);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private boolean pagaAdmnistradora(CalendarioSistema calendarioSistema, 
            FinanceiroAdmDAO financeiroAdmDAO, 
            UnidadeFranquiaDAO unidadeFranquiaDAO, Admnistrador admnistrador, 
            ValidacaoEntradaDados vd) {

        double rendaBruta;
        double parteAdministradora;
        double ganhoLiquido;

        boolean pago;
        int opcao;

        boolean saiu = false;

        System.out.println("\n============ Dia de Pagamento!!! =============");

        do {

            System.out.println("\n");
            unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());
            
            int idUnidadeFranquia;
            try {
                System.out.println("\nInforme o ID - UnidadeFranquia da Qual deseja fazer o Pagamento: ");
                idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
                idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);
            } 
            catch (Exception e) {
                idUnidadeFranquia = 1000;
                System.out.println("\nID - UnidadeFranquia invalida!!");
            }
            
            financeiroAdmDAO.buscaFinanceiroADMNoBancoDeDados(unidadeFranquiaDAO);
            UnidadeFranquia unidadeSelecionada = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

            if (unidadeSelecionada == null) {
                System.out.println("\nUnidade de Franquia nao Encontrada!");
            } else {

                pago = financeiroAdmDAO.verificaPagamentoUnidade(calendarioSistema, unidadeSelecionada);

                if (pago == true) {
                    System.out.println("\n------ A Unidade Informada Ja Fez O Pagamento Esse Mes. -----");
                } else {
                    rendaBruta = financeiroAdmDAO.calculaRendaBruta(calendarioSistema, unidadeSelecionada);
                    parteAdministradora = financeiroAdmDAO.calculaParteValorAdmnistradora(rendaBruta,
                            unidadeSelecionada, calendarioSistema);
                    ganhoLiquido = financeiroAdmDAO.calculaRendaLiquida(rendaBruta, parteAdministradora);

                    System.out.println("\nUnidade: " + unidadeSelecionada);
                    System.out.println("\n*******Ganho Bruto: ");
                    System.out.println("R$: " + rendaBruta);

                    System.out.println("\n*******Parte Da Admnistradora: ");
                    System.out.println("R$: " + parteAdministradora);

                    System.out.println("\n******Ganho Liquido: ");
                    System.out.println("R$: " + ganhoLiquido);
                }
            }

            System.out.println("\n0 - Para Sair Do Modulo De Pagamentos Franquia: ");
            System.out.println("\n1 - Para Continuar Realizando Pagamentos Franquia: ");
            
            try {
                System.out.println("\nInforme Opcao : ");
                opcao = Integer.parseInt(scanner.nextLine());
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            if (opcao == 0) {
                saiu = true;
            }

        } while (saiu == false);

        return saiu == true;
    }

    private void pagaDespesasAvulsas(CalendarioSistema calendarioSistema, 
            UnidadeFranquiaDAO unidadeFranquiaDAO, Admnistrador admnistrador, 
            ValidacaoEntradaDados vd, FinanceiroAdmDAO financeiroAdmDAO) {

        System.out.println("\n");
        unidadeFranquiaDAO.buscaUnidadeFranquiaAtravesDaFranquiaVinculada(admnistrador.getFranquia());
        
        int idUnidadeFranquia;
        try {
            System.out.println("\nInforme o ID - UnidadeFranquia da Qual deseja fazer o Pagamento: ");
            idUnidadeFranquia = Integer.parseInt(scanner.nextLine());
            idUnidadeFranquia = vd.validarINT(idUnidadeFranquia);
        } 
        catch (Exception e) {
            idUnidadeFranquia = 1000;
            System.out.println("\nID - UnidadeFranquia invalido!!");
        }
        
        UnidadeFranquia unidadeSelecionada = unidadeFranquiaDAO.buscaUnidadeFranquiaPorId(idUnidadeFranquia);

        if (unidadeSelecionada == null) {
            System.out.println("\nUnidade de Franquia nao Encontrada!");
        } else {
            System.out.println("\nInforme O Descritivo Do Movimento: ");
            String descritivoMovimento = scanner.nextLine();
            descritivoMovimento = vd.validaString(descritivoMovimento);
            
            double valorPagamento;
            String valorDespesa = "";
            try {
                System.out.println("\nInforme O Valor Do Pagamento: ");
                valorDespesa = scanner.nextLine();
                valorPagamento = Double.parseDouble(valorDespesa);
                valorPagamento = vd.validarDoble(valorPagamento);
            } 
            catch (Exception e) {
                valorDespesa = "";
                valorPagamento = vd.validaDespesaAvulsaValor(valorDespesa);
            }
            
            FinanceiroAdm financeiroAdm = new FinanceiroAdm("Saida", valorPagamento, unidadeSelecionada,
                    descritivoMovimento, calendarioSistema.getDataHoraSistema());

            if (financeiroAdmDAO.inserePagamentoAvulsoEPagamentoFranquiaNoBancoDeDados(financeiroAdm) == true) {
                System.out.println("\nPagamento Realizado Com Sucesso!");
            } else {
                System.out.println("\nNao Foi Possivel Realizar O Pagamento.");
            }
        }
    }

    private boolean CalculaValoresMedicos(CalendarioSistema calendarioSistema, ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, FinanceiroMedicoDAO financeiroMedicoDAO,
            MedicoDAO medicoDAO, ValidacaoEntradaDados vd, Admnistrador admnistrador, FranquiaDAO franquiaDAO) {

        double valorConsultas;
        double valorProcedimentos;
        double parteDescontadaConsulta;
        double parteDescontadaProcedimento;
        double valorLiquidomedico;

        boolean saiu = false;
        int opcao;

        System.out.println("\n=========== Gerar Valores Dos Medicos! =============");

        do {

            System.out.println("\n");
            medicoDAO.mostraTodosMedicos();
            
            int idMedico;
            try {
                System.out.println("\nInforme o ID - Medico Que deseja Gerar O Calculo: ");
                idMedico = Integer.parseInt(scanner.nextLine());
                idMedico = vd.validarINT(idMedico);
            } catch (Exception e) {
                idMedico = 1000;
                System.out.println("\nID - Medico invalido!!");
            }
            
            Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

            if (medicoEncontrado == null) {
                System.out.println("\nO Medico Informado Nao Foi Encontrado!!!");
            } else {

                financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);

                if (financeiroMedicoDAO.verificaCalculosValoresMedico(medicoEncontrado, calendarioSistema,
                        admnistrador.getFranquia()) == true) {
                    System.out.println("\n--------- Os Calculos Do Medico Informado Ja Foram Feitos esse mes. -----");
                } else {
                    System.out.println("\n" + medicoEncontrado);

                    valorConsultas = consultaDAO.calculaValorConsultasDoMes(medicoEncontrado, calendarioSistema,
                            admnistrador.getFranquia());
                    System.out.println("\nValor Bruto Das Consultas: " + valorConsultas);

                    valorProcedimentos = procedimentoDAO.calculaValorProcedimentosDoMes(medicoEncontrado,
                            calendarioSistema, admnistrador.getFranquia());
                    System.out.println("\nValor Bruto Dos Procedimentos: " + valorProcedimentos);

                    parteDescontadaConsulta = consultaDAO.calculaParteDescontoConsultas(valorConsultas);
                    System.out.println("\nA Parte Descontada Sobre As Consultas E: " + parteDescontadaConsulta);

                    parteDescontadaProcedimento = procedimentoDAO.calculaParteDescontoProcedimentos(valorProcedimentos);
                    System.out.println("\nA Parte Descontada Sobre Os Procedimentos E: " + parteDescontadaProcedimento);

                    valorLiquidomedico = financeiroMedicoDAO.calculaValorLiquidoAReceberMedico(valorConsultas, valorProcedimentos,
                            parteDescontadaConsulta, parteDescontadaProcedimento);
                    System.out.println("\nO Valor Liquido A Ser Pago Ao Medico E: " + valorLiquidomedico);

                    FinanceiroMedico financeiroMedico = new FinanceiroMedico(valorLiquidomedico, medicoEncontrado,
                            "Agendado", admnistrador.getFranquia(), calendarioSistema.getDataHoraSistema());

                    if (financeiroMedicoDAO.insereFinanceiroMedicoNoBancoDeDados(financeiroMedico) == true) {
                        System.out.println("\nCalculos Gerados Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Gerar Os Calculos.");
                    }

                }
            }

            System.out.println("\n0 - Para Sair Do Modulo De Geracao De Calculos Financeiros De Medicos: ");
            System.out.println("\n1 - Para Continuar Realizando Geracao De Calculos Financeiros De Medicos: ");
            
            try {
                System.out.println("\nInforme Opcao : ");
                opcao = Integer.parseInt(scanner.nextLine());
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
            if (opcao == 0) {
                saiu = true;
            }

        } while (saiu == false);

        return saiu == true;
    }

    private void pagarMedicos(Admnistrador admnistrador, FinanceiroMedicoDAO financeiroMedicoDAO,
            CalendarioSistema calendarioSistema, ValidacaoEntradaDados vd, MedicoDAO medicoDAO, FranquiaDAO franquiaDAO) {

        int opcao;

        System.out.println("\n=========== Pagar Os Medicos! =============");

        do {

            financeiroMedicoDAO.buscaFinanceiroMedicoNoBancoDeDados(medicoDAO, franquiaDAO);

            System.out.println("\n");
            if (financeiroMedicoDAO.buscaPagamentosMedicosPorFranquiaEhMes(admnistrador.getFranquia(),
                    calendarioSistema) == false) {
                System.out.println("\n------------ Todos Os Medicos Ja Foram pagos Esse Mes! --------");

            } else {
                
                int idFinanceiroMedico;
                try {
                    System.out.println("\nInforme o ID - Financeiro Medico Que Deseja Pagar: ");
                    idFinanceiroMedico = Integer.parseInt(scanner.nextLine());
                    idFinanceiroMedico = vd.validarINT(idFinanceiroMedico);
                } 
                catch (Exception e) {
                    idFinanceiroMedico = 1000;
                    System.out.println("\nID - Financeiro Medico invalido!!");
                }
                
                FinanceiroMedico financeiroMedicoEncontrado
                        = financeiroMedicoDAO.buscaPagamentosMedicosPorID(idFinanceiroMedico);

                if (financeiroMedicoEncontrado == null) {
                    System.out.println("\nFinanceiro Medico Nao Encontrado!");
                } else {
                    if (financeiroMedicoDAO.realizaPagamentoFinanceroMedicoNoBancoDeDados(financeiroMedicoEncontrado,
                            calendarioSistema) == true) {
                        System.out.println("\nPagamento Realizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Realizar O Pagamento.");
                    }
                }
            }

            System.out.println("\n0 - Para Sair Do Modulo De Pagamento De Medicos: ");
            System.out.println("\n1 - Para Continuar Realizando Pagamento De Medicos: ");
            
            try {
                System.out.println("\nInforme Opcao : ");
                opcao = Integer.parseInt(scanner.nextLine());
            } 
            catch (Exception e) {
                opcao = 20;
                System.out.println("\nOpcao invalida!!");
            }
            
        } while (opcao != 0);

    }

}
