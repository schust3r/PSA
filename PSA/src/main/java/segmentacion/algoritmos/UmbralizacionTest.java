package segmentacion.algoritmos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Mat;

import segmentacion.imagenes.ImageHandler;

public class UmbralizacionTest {

	@Test
	public void testAplicarUmbral() {

		/**
		 * Cargar la imagen en escala de grises a umbralizar
		 */
		ImageHandler ih = new ImageHandler();
		Mat img = ih.leerImagenGrises("C:/Users/Joel/Dropbox/I Semestre 2017/Aseguramiento de la Calidad del Software/Proyectos/1 POC/Entregable PoC/Pruebas/ojo_original.png");
		
		/**
		 * Leer imagen umbralizada de referencia (t = 150)
		 */
		Mat imgRef = ih.leerImagenGrises("C:/Users/Joel/Dropbox/I Semestre 2017/Aseguramiento de la Calidad del Software/Proyectos/1 POC/Entregable PoC/Pruebas/ojo_umbral_150.png");
		
		Umbralizacion umb = new Umbralizacion();
		umb.aplicarUmbral(img, 150 - 1); // Empieza desde índice cero
				
		int data1[][] = new int[img.rows()][img.cols()];
		int data2[][] = new int[imgRef.rows()][imgRef.cols()];
		
		// Cargar información importante de píxeles en arreglo
		for (int x = 0; x < img.rows(); x++) {
			for (int y = 0; y < img.cols(); y++) {
				data1[x][y] = (int)img.get(x, y)[0];
				data2[x][y] = (int)imgRef.get(x, y)[0];
			}
		}
		
		// Verificar si el resultado es el mismo
		assertEquals("Imágenes no dieron mismo resultado", data1, data2);
		
	}

}
