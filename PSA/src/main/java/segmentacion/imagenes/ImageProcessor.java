package segmentacion.imagenes;

import org.opencv.core.Mat;

import segmentacion.Histograma;
import segmentacion.algoritmos.Etiquetado;
import segmentacion.algoritmos.Kittler;
import segmentacion.algoritmos.Umbralizacion;
import segmentacion.conf.Const;

/**
 * 
 * @author Joel
 *
 */
public class ImageProcessor {
	
	// Manejo de imágenes
	private ImageHandler ih;
	
	// Algoritmo de Kittler
	private Kittler k;
	
	// Algoritmo de umbralización OpenCV
	private Umbralizacion umb;
	
	// Algoritmo de etiquetado basado en OpenCV
	private Etiquetado etq;

	/**
	 * Constructor
	 */
	public ImageProcessor() {
		this.k = new Kittler();
		this.ih = new ImageHandler();
		this.umb = new Umbralizacion();
		this.etq = new Etiquetado();
	}
	
	/**
	 * Recibe una imagen y le sobreescribe el resultado de la
	 * umbralizacion de inmediato
	 * 
	 * @param nombreImg	nombre de la imagen guardada en disco
	 */
	public void procesarImagen(String nombreImg) {	
		Mat imagen = ih.leerImagenGrises(Const.IMG_DIR + nombreImg);
		Histograma hist = new Histograma(imagen, Const.LIMITE);
		k.setHistograma(hist);		
		k.calcularUmbral();
		umb.aplicarUmbral(imagen, k.getTao());
		imagen = etq.etiquetarCelulas(imagen);
		ih.sobreescribirImagen(Const.IMG_DIR, nombreImg, imagen);
	}
	
}
