package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class PrincipalController {

	@FXML
	private Button abrirImagem1;

	@FXML
	private Button abrirImagem2;

	@FXML
	private Button salvar;

	@FXML
	private Label lblR;

	@FXML
	private Label lblG;

	@FXML
	private Label lblB;

	@FXML
	private Button histograma;

	@FXML
	private ImageView imageView1;

	@FXML
	private ImageView imageView2;

	@FXML
	private ImageView imageView3;

	@FXML
	private ImageView imgRes;

	private Image img1;
	private Image img2;
	private Image img3;

	@FXML
	public void cinzaAritmetica() {
		img3 = Pdi.cinzaMediaAritmetica(img1, 0, 0, 0);
		atualizaImagem3();
	}


	@FXML
	public void rasterImg(MouseEvent evt) {
		ImageView iw = (ImageView) evt.getTarget();
		if (iw.getImage() != null)
			verificaCor(iw.getImage(), (int) evt.getX(), (int) evt.getY());
	}

	private void verificaCor(Image img, int x, int y) {
		try {
			Color cor = img.getPixelReader().getColor(x - 1, y - 1); // Recebe o pixel que o mouse está passando
			lblR.setText("R: " + (int) (cor.getRed() * 255));
			lblG.setText("G: " + (int) (cor.getGreen() * 255));
			lblB.setText("B: " + (int) (cor.getBlue() * 255));
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@FXML
	public void abreImagem1() {
		img1 = abreImagem(imageView1, img1);
	}

	@FXML
	public void abreImagem2() {
		img2 = abreImagem(imageView2, img2);
	}

	@FXML
	public void atualizaImagem3() {
		imageView3.setImage(img3);
		imageView3.setFitWidth(img3.getWidth());
		imageView3.setFitHeight(img3.getHeight());
	}

	private Image abreImagem(ImageView imageView, Image image) {
		File f = selecionaImagem();
		if (f != null) {
			image = new Image(f.toURI().toString());
			imageView.setImage(image);
			imageView.setFitWidth(image.getWidth());
			imageView.setFitHeight(image.getHeight());
			return image;
		}
		return null;
	}

	private File selecionaImagem() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png",
				"*.PNG", "*.gif", "*.GIF", "*.bmp", "*.BMP"));
		fileChooser.setInitialDirectory(new File("C:\\Users\\danil\\Imagens"));
		File imgSelec = fileChooser.showOpenDialog(null);
		try {
			if (imgSelec != null) {
				return imgSelec;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * @FXML public void abreImagem1() { File f = selecionaImagem(); if (f != null)
	 * { Image i = new Image(f.toURI().toString()); img1.setImage(i);
	 * img1.setFitWidth(i.getWidth()); img1.setFitHeight(i.getHeight());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @FXML public void abreImagem2() { File f = selecionaImagem(); if (f != null)
	 * { Image i = new Image(f.toURI().toString()); img2.setImage(i);
	 * img2.setFitWidth(i.getWidth()); img2.setFitHeight(i.getHeight());
	 * 
	 * }
	 * 
	 * }
	 */
	/*
	 * private File selecionaImagem() {
	 * 
	 * FileChooser dialogo = new FileChooser();
	 * dialogo.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens",
	 * "*.jpg", "*.JPG", "*.png", "*.PNG", "*.gif", "*.GIF", "*.bmp", "*.BMP",
	 * "*.jpeg", "*.JPEG")); dialogo.setInitialDirectory(new
	 * File("C:\\Users\\danil\\Imagens"));
	 * 
	 * return dialogo.showOpenDialog(null); }
	 */

}
