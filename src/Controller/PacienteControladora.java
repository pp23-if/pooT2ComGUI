package Controller;

import Model.CalendarioSistema;
import Model.ConsultaDAO;
import Model.Medico;
import Model.MedicoDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.ProcedimentoDAO;
import View.MenuTitulosPaciente;
import java.util.Scanner;

public class PacienteControladora {

    Scanner scanner = new Scanner(System.in);

    MenuTitulosPaciente telaPaciente = new MenuTitulosPaciente();

    public PacienteControladora(Pessoa pessoa, PessoaDAO pessoaDAO,
            ValidacaoEntradaDados vd, ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO,
            CalendarioSistema calendarioSistema, MedicoDAO medicoDAO) {

        menuOpcoesPaciente(pessoa, pessoaDAO, vd, consultaDAO, procedimentoDAO, calendarioSistema, medicoDAO);
    }

    private void menuOpcoesPaciente(Pessoa pessoa, PessoaDAO pessoaDAO, ValidacaoEntradaDados vd,
            ConsultaDAO consultaDAO, ProcedimentoDAO procedimentoDAO, CalendarioSistema calendarioSistema,
            MedicoDAO medicoDAO) {

        int opcao;

        do {

            pessoaDAO.BuscaPessoaNoBancoDeDados();

            pessoaDAO.atualizaPessoaLogadaComBancoDeDados(pessoa.getCpf(), pessoa);

            try {
                opcao = telaPaciente.menuPaciente();
            } catch (Exception e) {
                System.out.println("\nOpcao invalida!! ");
                opcao = 20;
            }

            switch (opcao) {
                case 1: {
                    System.out.println("\n" + pessoaDAO.buscaPessoaCadastrada(pessoa.getLoginPessoa(),
                            pessoa.getSenhaPessoa()));
                    break;
                }
                case 2: {
                    menuOpcoesAtualizarDadosPaciente(pessoa, pessoaDAO, vd, calendarioSistema);
                    break;
                }
                case 3: {
                    System.out.println("\n");
                    consultaDAO.buscaConsultaAtravesDaPessoaVinculada(pessoa);
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    procedimentoDAO.buscaProcedimentoPorPaciente(pessoa);
                    break;
                }
                case 5: {
                    gerarRelatorioDeConsultasEProcedimentosDeUmDadoPaciente(consultaDAO, procedimentoDAO,
                            pessoa, vd, medicoDAO);
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void menuOpcoesAtualizarDadosPaciente(Pessoa pessoa, PessoaDAO pessoaDAO, ValidacaoEntradaDados vd,
            CalendarioSistema calendarioSistema) {

        int opcao;

        do {

            pessoaDAO.BuscaPessoaNoBancoDeDados();
            
            try {
                opcao = telaPaciente.menuAlteraDadosPaciente();
            } catch (Exception e) {
                System.out.println("\n opcao invalida!!");
                opcao = 20;
            }
            

            switch (opcao) {
                case 1: {
                    System.out.println("\nInforme o Novo Nome: ");
                    String novoNomePessoa = scanner.nextLine();
                    novoNomePessoa = vd.validaString(novoNomePessoa);

                    if (pessoaDAO.AtualizaNomePessoaNoBancoDeDados(novoNomePessoa, pessoa, calendarioSistema) == true) {
                        System.out.println("\nO Nome Foi Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNome Foi possivel Atualizar o Nome.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("\nInforme o Novo Cpf: ");
                    String novoCpf = scanner.nextLine();
                    novoCpf = vd.validaString(novoCpf);

                    if (pessoaDAO.AtualizaCpfPessoaNoBancoDeDados(novoCpf, pessoa, calendarioSistema) == true) {

//                        pessoaDAO.BuscaPessoaNoBancoDeDados();
//
//                        pessoaDAO.atualizaPessoaLogadaComBancoDeDados(novoCpf, pessoa);
                        pessoa.setCpf(novoCpf);

                        System.out.println("\nO Cpf Foi Atualizado Com Sucesso!");

                    } else {
                        System.out.println("\nNome Foi possivel Atualizar o Cpf.");
                    }

                    break;
                }

                case 3: {
                    System.out.println("\nInforme o Novo Endereco: ");
                    String novoEndereco = scanner.nextLine();
                    novoEndereco = vd.validaString(novoEndereco);

                    if (pessoaDAO.AtualizaEnderecoPessoaNoBancoDeDados(novoEndereco, pessoa, calendarioSistema) == true) {
                        System.out.println("\nO Endereco Foi Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Endereco");
                    }
                    break;
                }

                case 4: {
                    System.out.println("\nInforme o Novo Telefone: ");
                    String novoTelefone = scanner.nextLine();
                    novoTelefone = vd.validaString(novoTelefone);

                    if (pessoaDAO.AtualizaTelefonePessoaNoBancoDeDados(novoTelefone, pessoa, calendarioSistema) == true) {
                        System.out.println("\nO Telefone Foi Atualizado Com Sucesso!");
                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Telefone");
                    }
                    break;
                }

                case 5: {
                    System.out.println("\nInforme o Novo Login: ");
                    String novoLogin = scanner.nextLine();
                    novoLogin = vd.validaString(novoLogin);

                    if (pessoaDAO.AtualizaloginPessoaNoBancoDeDados(novoLogin, pessoa, calendarioSistema) == true) {

                        pessoa.setLoginPessoa(novoLogin);

                        System.out.println("\nO Login Foi Atualizado Com Sucesso!");

                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar O Login");
                    }

                    break;
                }

                case 6: {
                    System.out.println("\nInforme a Nova Senha: ");
                    String novaSenha = scanner.nextLine();
                    novaSenha = vd.validaString(novaSenha);

                    if (pessoaDAO.AtualizaSenhaPessoaNoBancoDeDados(novaSenha, pessoa, calendarioSistema) == true) {

                        pessoa.setSenhaPessoa(novaSenha);
                        System.out.println("\nA Senha Foi Atualizada Com Sucesso!");

                    } else {
                        System.out.println("\nNao Foi Possivel Atualizar A Senha");
                    }
                    break;
                }
            }

        } while (opcao != 0);
    }

    private void gerarRelatorioDeConsultasEProcedimentosDeUmDadoPaciente(ConsultaDAO consultaDAO,
            ProcedimentoDAO procedimentoDAO, Pessoa pessoa, ValidacaoEntradaDados vd, MedicoDAO medicoDAO) {

        System.out.println("\n");
        medicoDAO.mostraTodosMedicosHabilitados();
        
        int idMedico;
        try {
            System.out.println("\nInforme o ID - Medico: ");
            idMedico = Integer.parseInt(scanner.nextLine());
            idMedico = vd.validarINT(idMedico);
        } catch (NumberFormatException e) {
            idMedico = 100;
            System.out.println("\nID - Medico - invalido");
        }
        
        Medico medicoEncontrado = medicoDAO.buscaMedicoPorId(idMedico);

        if (medicoEncontrado == null) {
            System.out.println("\nMedico Nao Encontrado.");
        } else {
            System.out.println("\n******* - Paciente: " + pessoa.getNomePessoa());
            System.out.println("\n ....... Consultas ........:");
            System.out.println("\n");
            consultaDAO.buscaConsultasQueTemMedicoSolicitanteEPacienteEscolhido(pessoa, medicoEncontrado);

            System.out.println("\n ....... Procedimentos ........:");
            System.out.println("\n");
            procedimentoDAO.buscaProcedimentosQueTemMedicoSolicitanteEPacienteEscolhido(pessoa, medicoEncontrado);

        }

    }

}
