package com.mx.itam.adsi.codeanalyser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *Este es el Test
 * @author Alberto Acosta
 */
public class CodeReader {

        private final static Logger LOG= Logger.getLogger(CodeReader.class);
        private static CommentAnalysis obj = new CommentAnalysis();
        
        @BeforeClass
        public static void beforeClass() {
            System.out.println("Before Class");
        }

        @Before
        public void before() {
            System.out.println("Before Test Case");
        }
        
        @Test
        public static void isCorrect(){
            
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
        
        private static boolean calc (File fileName, int res) {
            int calcu=obj.analyzeFile(fileName);
            return calcu==res;
        }

        @After
        public void after() {
            System.out.println("After Test Case");
        }

        @AfterClass
        public static void afterClass() {
            System.out.println("After Class");
        }

    
}
