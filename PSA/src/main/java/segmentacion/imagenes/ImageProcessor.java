package segmentacion.imagenes;

import org.opencv.core.Mat;

import segmentacion.algoritmos.Kittler;
import segmentacion.algoritmos.Umbralizacion;
import segmentacion.conf.Const;

/**
 * 
 * @author Joel
 *
 */
public class ImageProcessor {
	
	private Kittler k;
	private ImageHandler ih;
	private Umbralizacion umb;

	public ImageProcessor() {
		this.k = new Kittler();
		this.ih = new ImageHandler();
		this.umb = new Umbralizacion();
	}
	
	/**
	 * Recibe una imagen y le sobreescribe el resultado de la
	 * umbralizacion de inmediato
	 * 
	 * @param nombreImg	nombre de la imagen guardada en disco
	 */
	public void procesarImagen(String nombreImg) {	
		Mat imagen = ih.leerImagenGrises(Const.IMG_DIR + nombreImg);		
		k.setImagen(imagen);	
		k.calcularUmbral();
		umb.aplicarUmbral(k.getImagen(), k.getTao());
		ih.sobreescribirImagen(Const.IMG_DIR, nombreImg, imagen);
	}
	
}
