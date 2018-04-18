package com.mx.itam.adsi.codeanalyser;

import java.io.File;
import org.apache.log4j.Logger;

/**
 *Este es el Test
 * @author Alberto Acosta
 */
public class CodeReader {

    public static void main(String[] args) {

        private final static Logger LOG= Logger.getLogger(CodeReader.class);
        private CommentAnalysis obj = new CommentAnalysis();
        
        @Test
        public void isCorrect(){
            
            LOG.info("Ejecutando la prueba");
            
            File fileName = new File("Archivo01.java");
            assertTrue("Cálculo del archivo",calc(fileName,0));

            fileName = new File("Archivo02.java");
            assertTrue("Cálculo del archivo",calc(fileName,2));

            fileName = new File("Archivo03.java");
            assertTrue("Cálculo del archivo",calc(fileName,7));

            fileName = new File("Archivo04.java");
            assertTrue("Cálculo del archivo",calc(fileName,8));

            fileName = new File("Archivo05.java");
            assertTrue("Cálculo del archivo",calc(fileName,6));
        }
        
        private boolean calc(File fileName, int res){
            int calcu=obj.obj.analyzeFile(fileName);
            return calcu==res;
        }
    }
    
}
