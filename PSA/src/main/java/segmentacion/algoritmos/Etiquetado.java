package segmentacion.algoritmos;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
 
/**
 * Haciendo uso de la librería opencv se hace una matris de la imagen en binario, 
 * se obtienen los contornos y se dibuja cada uno
 *
 * @author Reggie Barker
 * 
 */
 
public class Etiquetado {
     
    /**
     * Aplicar etiquetado de células a color
     *
     * @param imagen                Imagen en binario
     * @param imagenParaEtiquetar   Imagen con formato a color
     * @return                      Imagen con las células etiquetadas con colores
     */  
    public Mat etiquetarCelulas(Mat imagen) {
        Mat imagenParaEtiquetar = new Mat();
        Imgproc.cvtColor(imagen, imagenParaEtiquetar, Imgproc.COLOR_GRAY2RGB);
        ArrayList<MatOfPoint> contornos = new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(imagen, contornos, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
       
        for (MatOfPoint matOfPoint : contornos) { // Recorre los puntos del contorno y los dibuja
            ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
            c.add(matOfPoint);
            Scalar color = new Scalar(ThreadLocalRandom.current().nextInt(70, 255),
                    ThreadLocalRandom.current().nextInt(70, 255),ThreadLocalRandom.current().nextInt(70, 255));
            Imgproc.drawContours(imagenParaEtiquetar, c,-1,color,-1);  
        }   
        return imagenParaEtiquetar;       
    }
 
}