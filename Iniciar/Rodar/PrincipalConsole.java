package Rodar;

import java.util.InputMismatchException;
import java.util.Scanner;

import execoes.RoomsAllocationException;
import fachada.Facade;

/**
 * Classe para a execucao via linha de comando
 * 
 * @author Igor
 *
 */
public class PrincipalConsole {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);

		Facade fachada = new Facade();

		boolean roda = true;
		System.out.println(" 	Programa iniciado.");
		do {
			System.out.println("\nQue metodo deseja usar\n");
			System.out.println(
					"1 - Adicionar Sala\n2 - Buscar Atributo de Sala \n3 - Adicionar Evento \n4 - Buscar Atributo de Evento \n5 - Alocar Evento \n6 - Localizar Evento \n7 - Desalocar Evento \n8 - Cancelar Evento \n9 - Remover Sala \n0 - Sair ");

			String opmenu = ler.nextLine();

			switch (opmenu) {
			case "1":

				System.out.print("Para adicionar uma sala voce precisa de um \nidentificador para sua sala ID:");
				String id = ler.nextLine();

				System.out.print("escolha um numero para a Capacidade: ");
				int capacidade = 0;
				try {
					capacidade = ler.nextInt();
				} catch (InputMismatchException | NumberFormatException e) {
					System.out.println("A capacidade deve ser um numero");
					break;
				}
				System.out.print(
						"Qual o tipo de Finalidade: \n1-Laboratorio \n2-Sala de Aula  \n3-Escritorio \n4-Sala de Conferencia");
				int op = 9;
				try {
					op=ler.nextInt();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				String finalidade = "";
				String tipo = "";
				
				boolean continua = true;

				switch (op) {
				case 1:
					finalidade = "Laboratorio";

					System.out.print("Tipo: 1-Quimica 2-Biologia 3-Fisica 4-Computacao");
					int op1 = 0;
					try {
						op1 = ler.nextInt();
					} catch (Exception e) {
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					switch (op1) {
					case 1:
						tipo = "Quimica";

						break;
					case 2:
						tipo = "Biologia";
						break;
					case 3:
						tipo = "Fisica";
						break;
					case 4:
						tipo = "Computacao";
						break;

					default:
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					break;
				case 2:
					finalidade = "Sala de Aula";
					System.out.print("Tipo: 1-Normal 2-Inteligente");
					int op2 = 0;
					try {
						op2 = ler.nextInt();
					} catch (Exception e) {
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}
					switch (op2) {
					case 1:
						tipo = "Normal";

						break;
					case 2:
						tipo = "Inteligente";
						break;

					default:
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					break;
				case 3:
					finalidade = "Escritorio";
					System.out.print("Tipo: 1-Computação");
					int op3 = 0;
					try {
						op3 = ler.nextInt();
					} catch (Exception e) {
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}
					switch (op3) {
					case 1:
						tipo = "Computação";

						break;

					default:
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					break;
				case 4:
					finalidade = "Sala de Conferencia";
					System.out.print("Tipo: 1-Vidio Conferencia 2-Normal");
					int op4 = 0;
					try {
						op4 = ler.nextInt();
					} catch (Exception e) {
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					switch (op4) {
					case 1:
						tipo = "Vidio Conferencia";

						break;
					case 2:
						tipo = "Normal";
						break;

					default:
						System.out.println("Tipo Invalido");
						continua=false;
						break;
					}

					break;
					

				default:
					System.out.println("Finalidade Invalida");
					continua=false;
					break;
				}
				if(continua==false){
					break;
				}

				System.out.print("Apelido: ");

				String apelido = ler.next();

				int op5=9;
				try {
					System.out.print("Aberta: 1-Verdadeiro 2-falso");
					op5 = ler.nextInt();
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				boolean aberto = false;

				switch (op5) {
				case 1:
					aberto = true;

					break;
				case 2:
					aberto = false;
					break;

				default:
					aberto = false;
					break;
				}

				if (apelido == null || apelido.equalsIgnoreCase("")) {
					apelido = "";
					try {
						fachada.adicionarSala(id, capacidade, finalidade, tipo, apelido, aberto);
						System.out.println("Concluido");
						break;
					} catch (RoomsAllocationException e) {
						System.out.println(e.getMessage());
					}

				} else {

					try {
						fachada.adicionarSala(id, capacidade, finalidade, tipo, apelido, aberto);
						System.out.println("Concluido");
						break;
					} catch (RoomsAllocationException e) {
						System.out.println(e.getMessage());
					}

				}

				break;
			case "2":
				System.out.println("Passe o id da Sala");
				String idSala = ler.nextLine();

				System.out.println("Passe o atributo da Sala");
				String atributo = ler.nextLine();

				try {
					System.out.println(atributo + ":" + fachada.getAtributoSala(idSala, atributo));
					break;
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "3":
				System.out.print("Para adicionar um evento voce precisa de um \nID:");
				String idEvento = ler.nextLine();

				System.out.print("Nome: ");
				String nome = ler.nextLine();

				System.out.print("Inicio: ");
				String inicio = ler.nextLine();

				System.out.print("Fim: ");
				String fim = ler.nextLine();

				System.out.print("Area: ");
				String area = ler.nextLine();

				System.out.print("Contato: ");
				String contato = ler.nextLine();

				System.out.print("Repeticoes: se nao apenas de ENTER");
				int repeticoes = 0;

				try {
					repeticoes = ler.nextInt();
				} catch (InputMismatchException | NumberFormatException e) {
					System.out.println(e.getMessage());
					break;

				}
				if (repeticoes > 0) {
					try {
						fachada.adicionarEvento(idEvento, nome, inicio, fim, area, contato);
						break;
					} catch (RoomsAllocationException e) {
						System.out.println(e.getMessage());
					}
				} else {
					try {
						fachada.adicionarEvento(idEvento, nome, inicio, fim, area, contato, repeticoes);
						break;
					} catch (RoomsAllocationException e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "4":
				System.out.println("Passe o id do evento");
				String idEventoBusca = ler.nextLine();

				System.out.println("Passe o atributo da Evento");
				String atributoEventoBusca = ler.nextLine();

				try {
					System.out.println(
							atributoEventoBusca + ":" + fachada.getAtributoEvento(idEventoBusca, atributoEventoBusca));
					break;
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "5":
				System.out.println("Passe o id da Sala");
				String idSalaAlocar = ler.nextLine();

				System.out.println("Passe o id do Evento");
				String idEventoAlocar = ler.nextLine();

				try {
					fachada.alocarEvento(idEventoAlocar, idSalaAlocar);
					System.out.println("Concluido.");
					break;
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "6":
				System.out.print("Localizar -- atributo: 1-Id 2-Nome 3-area 4-Horario 5-Contato ");
				
				int opc = 0;
				try {
					opc = ler.nextInt();
					
				} catch (Exception e) {
					System.out.println("Digite um numero");
					break;
				}
				
				String atibuto = "";

				switch (opc) {
				case 1:
					atibuto = "Id";

					break;
				case 2:
					atibuto = "Nome";
					break;
				case 3:
					atibuto = "area";

					break;
				case 4:
					atibuto = "Horario";
					System.out.println("O horario deve segurir essa formatação\nex: dd/MM/aaaa hh:mm");

					break;
				case 5:
					atibuto = "Contato";

					break;
				default:
					System.out.println("Opcao invalida");
					opmenu="6";
					break;
				}
				System.out.println("digite o valor buscado.");
				String valor = ler.next();
				
				try {
					fachada.localizarEvento(atibuto, valor);
				} catch (RoomsAllocationException e1) {
					System.out.println(e1.getMessage());
				}
				

				break;
			case "7":
				System.out.println("Passe o id da Evento a ser Desalocado");
				String idEventoDesalocado = ler.nextLine();

				try {
					fachada.desalocarEvento(idEventoDesalocado);
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "8":
				System.out.println("Passe o id do Evento para ser Cancelado");
				String idEventoCancelado = ler.nextLine();

				try {
					fachada.cancelarEvento(idEventoCancelado);
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "9":
				System.out.println("Passe o id da Sala para remover");
				String idSalaRemovida = ler.nextLine();

				try {
					fachada.removerSala(idSalaRemovida);
				} catch (RoomsAllocationException e) {
					System.out.println(e.getMessage());
				}

				break;
			case "0":
				System.out.println("\nFinalizando...\n");
				roda = false;
				break;

			default:
				System.out.println("\nEscolha uma das opcoes\n");
				break;
			}
		} while (roda);
		ler.close();
	}

}
