package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Pdi {

	public static Image cinzaMediaAritmetica(Image imagem, int pcR, int pcG, int pcB) { // Recebe uma imagem e os
																						// percentuais
		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader(); // le cada pixel da imagem e pega informações das cores atuais
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter(); // cria a imagem nova

			for (int i = 0; i < w; i++) { // percorre todas linhas e colunas
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j); // usa o 'pixel reader' para pegar a cor do pixel atual
					double media = (corA.getRed() + corA.getGreen() + corA.getBlue()) / 3; // pega os canais R G B do
																							// pixel e divide por 3

					if (pcR != 0 || pcG != 0 || pcB != 0) // se algum percentual for diferente de 0, significa que a
															// média é ponderada
						media = (corA.getRed() * pcR + corA.getGreen() * pcG + corA.getBlue() * pcB) / 100; // faz o
																											// calculo
																											// da media
																											// ponderada
					Color corN = new Color(media, media, media, corA.getOpacity()); // cria a nova cor no pixel,
																					// passando os valores calculados e
																					// a opacidade da imagem anterior
					pw.setColor(i, j, corN); // usa o 'pixel writer' para escrever a nova cor no pixel atual
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Image limiarizacao(Image imagem, double limiar) { // recebe a imagem e a limiar
		try {
			int w = (int) imagem.getWidth(); // pega a largura
			int h = (int) imagem.getHeight(); // pega a altura

			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j); // pega a cor atual
					Color corN; // cor nova

					if (corA.getRed() >= limiar) // se o canal atual for maior que o limiar
						corN = new Color(1, 1, 1, corA.getOpacity()); // o rgb da imagem fica tudo branco
					else // senão
						corN = new Color(0, 0, 0, corA.getOpacity()); // fica preto

					pw.setColor(i, j, corN);
				}
			}
			return wi;

		} catch (Exception e) {
			return null;
		}

	}

	public static Image negativa(Image imagem) {
		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();
			System.out.println(w);
			System.out.println(h);

			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j);
					Color corN = new Color(
							1 - corA.getRed(), // diminui 1 resultando na negativa
							1 - corA.getGreen(), 
							1 - corA.getBlue(), corA.getOpacity());
					pw.setColor(i, j, corN);

				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
