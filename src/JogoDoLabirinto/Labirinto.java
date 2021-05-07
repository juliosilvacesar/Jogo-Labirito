package JogoDoLabirinto;

public class Labirinto {
	private static final char PAREDE_VERTICAL = '|';
	private static final char PAREDE_HORIZONTAL = '-';
	private static final char CAMINHO = '.';
	private static final char SEM_SAIDA = 'x';
	private static final char INICIO = 'I';
	private static final char DESTINO = 'D';
	private static final char VAZIO = ' ';
	private static final char TAMANHO = 10;
	private static final char PAREDE_INTERNA = '@';
	private static final double PROBABILIDADE = 0.7; // temos 8 constantes da linha 4 a 11 - para definir os atributos
														// visuais do tabuleiro. Caso seja necessario basta alterar!
	private static char[][] tabuleiro;// declaração de uma matriz de caractes de nome tabuleiro - onde será alocados
										// os elementos tabulueiro como paredes verticais e horizontais e os espaços em
										// branco
	private static int linhaInicio;
	private static int colunaInicio;
	private static int linhaDestino;
	private static int colunaDestino;

	public static boolean posicaoVazia(int linha, int coluna) {
		boolean vazio = false;
		if (linha >= 0 && coluna >= 0 && linha < TAMANHO && coluna < TAMANHO) {
			vazio = (tabuleiro[linha][coluna] == VAZIO);
		}
		return vazio;
	}

	private static boolean tentarCaminho(int proxLinha, int proxColuna) {
		boolean achou = false;
		if (tabuleiro[proxLinha][proxColuna] == DESTINO) {
			achou = true;
		} else if (posicaoVazia(proxLinha, proxColuna)) {
			// marcar
			tabuleiro[proxLinha][proxColuna] = CAMINHO;
			imprimir();
			achou = procurarCaminho(proxLinha, proxColuna);
			if (!achou) {
				tabuleiro[proxLinha][proxColuna] = SEM_SAIDA;
				imprimir();
			}
		}
		return achou;
	}

	public static boolean procurarCaminho(int linhaAtual, int colunaAtual) {
		int proxLinha;
		int proxColuna;
		boolean achou = false;
		// tenta subir
		proxLinha = linhaAtual - 1;
		proxColuna = colunaAtual;
		achou = tentarCaminho(proxLinha, proxColuna);
		// tenta descer
		if (!achou) {
			proxLinha = linhaAtual + 1;
			proxColuna = colunaAtual;
			achou = tentarCaminho(proxLinha, proxColuna);
		}
		// tenta à esquerda
		if (!achou) {
			proxLinha = linhaAtual;
			proxColuna = colunaAtual - 1;
			achou = tentarCaminho(proxLinha, proxColuna);
		}
		// tenta à direita
		if (!achou) {
			proxLinha = linhaAtual;
			proxColuna = colunaAtual + 1;
			achou = tentarCaminho(proxLinha, proxColuna);
		}
		return achou;
	}

	public static int gerarNumero(int minimo, int maximo) {// função que gera um número inteiro entre um dado intervalo
		int valor = (int) Math.round(Math.random() * (maximo - minimo));
		return minimo + valor;
	}

	public static void inicializarMatriz() {// procedimento de inicialização
		int i, j;
		for (i = 0; i < TAMANHO; i++) {// codigo que preenchera a matriz com as paredes verticais e horizontais
			tabuleiro[i][0] = PAREDE_VERTICAL;
			tabuleiro[i][TAMANHO - 1] = PAREDE_VERTICAL;
			tabuleiro[0][i] = PAREDE_HORIZONTAL;
			tabuleiro[TAMANHO - 1][i] = PAREDE_HORIZONTAL;
		}

		for (i = 1; i < TAMANHO - 1; i++) {// codigo que preenche internamento o tabuleiro com o caractere vazio.
			for (j = 1; j < TAMANHO - 1; j++) {
				if (Math.random() > PROBABILIDADE) {// comando condicional com a função Math.random() para preencher os
													// espaços com @ e vazio ' '.
					tabuleiro[i][j] = PAREDE_INTERNA;
				} else {
					tabuleiro[i][j] = VAZIO;
				}
			}
		}
		linhaInicio = gerarNumero(1, TAMANHO / 2 - 1);// invocamos a função gerar numero.
		colunaInicio = gerarNumero(1, TAMANHO / 2 - 1);// teremos uma posição gerada aleatoriamente dentro do tabuleiro.
		tabuleiro[linhaInicio][colunaInicio] = INICIO;

		int linhaDestino = gerarNumero(TAMANHO / 2, TAMANHO - 2);
		int colunaDestino = gerarNumero(TAMANHO / 2, TAMANHO - 2);
		tabuleiro[linhaDestino][colunaDestino] = DESTINO;
	}

	public static void imprimir() {/* procedimento de impressão tem a função de pecorrer todos os elementos da
 matriz e os imprimir no console.*/
		try {//uma exercessão para o caso do imprimir.
			Thread.sleep(300);//um delay para melhor visualização
			for (int i = 0; i < TAMANHO; i++) {
				for (int j = 0; j < TAMANHO; j++) {
					System.out.print(tabuleiro[i][j]);
				}
				System.out.println();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String Arg[]) {// criamos uma matriz 10*10 e invocamos a função inicializarMatriz(); e
											// imprimir(); logo sera impresso a matriz com suas bordas verticais e
											// horizontais e seus espaços vazios.
		tabuleiro = new char[TAMANHO][TAMANHO];// como o tamanho é 10 basta por [][] a constante tamanho caso necessite
												// altere na constante.
		inicializarMatriz();// procedimento
		imprimir();// procedimento
		System.out.println("\n- Procurando solução -\n");
		boolean achou = procurarCaminho(linhaInicio, colunaInicio);
		if (achou) {
			System.out.println("ACHOU O CAMINHO!");
		} else {
			System.out.println("Não tem caminho!");
		}

	}
}
