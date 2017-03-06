package com.edgedetector.pedrock.aplicativomobilesobel;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.Arrays;

/**
 * Created by Pedrock on 03/11/2016.
 */

public class SobelEdgeDetector extends  MainActivity{

        private Bitmap Gx, Gy;
        private int height;
        private int width;
        private int picsize;
        private int[] data;
        private int[] magnitude;
        private Bitmap sourceImage;
        private Bitmap edgesImage;

        public void  SobelEdgeDetector(){

        }

        public Bitmap getSourceImage() {
        return sourceImage;
    }

        public void setSourceImage(Bitmap image) {
            // Converte para RGB
            sourceImage = image.copy(Bitmap.Config.ARGB_8888, true);
        }

        public Bitmap getEdgesImage() {
            return edgesImage;
        }

        /**
         * Método usado apenas para referenciar na memória a imagem com bordas detectadas
         *
         * @param edgesImage imagem com bordas detectadas
         */

        public void setEdgesImage(Bitmap edgesImage) {
            this.edgesImage = edgesImage;
        }

        public Bitmap detect(Bitmap img)
        {
            // MAscaras.
            float[] x1 = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
            float[] y1 = {-1,-2,-1,0,0,0,1,2,1};
            Kernel MatrixA = new Kernel(3, 3, x1);
            Kernel MatrixB = new Kernel(3, 3, y1);
            // covoluindo as matrizes.
            ConvolveOp convolve1 = new ConvolveOp(MatrixA);
            ConvolveOp convolve2 = new ConvolveOp(MatrixB);

            this.Gx = convolve1.filter(img, null);
            this.Gy = convolve2.filter(img, null);


            for (int i=0; i<img.getWidth(); i++) {
                for (int j=0; j<img.getHeight(); j++) {
                    double result = G(i,j);
                    if(result < 20726564.99) {
                        img.setRGB(i,j, Color.WHITE);
                    } else {
                        img.setRGB(i,j,Color.BLACK);
                    }
                }
            }
            return img;
        }
        private double G(int x, int y)
        {
            int derp = this.Gx.getRGB(x,y);
            int herp = this.Gy.getRGB(x,y);

            double result = Math.sqrt(Math.pow(derp, 2.0) + Math.pow(herp, 2.0));
            return result;
        }
    }


