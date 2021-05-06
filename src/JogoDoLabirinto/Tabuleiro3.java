package JogoDoLabirinto;

public class Tabuleiro3 {
	private static final char PAREDE_VERTICAL = '|';
	private static final char PAREDE_HORIZONTAL = '-';
	private static final char VAZIO = ' ';
	private static final char TAMANHO = 10;
	private static final char PAREDE_INTERNA = '@';
	private static final double PROBABILIDADE = 0.7; // temos 6 constantes da linha 4 a 9 - para definir os atributos
														// visuais do tabuleiro. Caso seja necessario basta alterar!
	private static char[][] tabuleiro;// declaração de uma matriz de caractes de nome tabuleiro - onde será alocados
										// os elementos tabulueiro como paredes verticais e horizontais e os espaços em
										// branco

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
				if (i == j) {
					tabuleiro[i][j] = PAREDE_INTERNA;
				} else {
					tabuleiro[i][j] = VAZIO;
				}
			}
		}
	}

	public static void imprimir() {// procedimento de impressão tem a função de pecorrer todos os elementos da
									// matriz e os imprimir no console.
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				System.out.print(tabuleiro[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String Arg[]) {// criamos uma matriz 10*10 e invocamos a função inicializarMatriz(); e
											// imprimir(); logo sera impresso a matriz com suas bordas verticais e
											// horizontais e seus espaços vazios.
		tabuleiro = new char[TAMANHO][TAMANHO];// como o tamanho é 10 basta por [][] a constante tamanho caso necessite
												// altere na constante.
		inicializarMatriz();
		imprimir();
	}
}
