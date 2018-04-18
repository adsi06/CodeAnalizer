package com.mx.itam.adsi.codeanalyser;

import org.apache.log4j.Logger;
import java.io.*;

/**
 *Este es el main
 * @author Alberto Acosta
 */
public class CommentAnalysis {

    int codeLines = 0, commentLines = 0, totalLines = 0;
    boolean commentStarted = false;

    private final static Logger LOG = Logger.getLogger(CommentAnalysis.class);
    
    public static void main(String[] args) {
        CommentAnalysis c= new CommentAnalysis();
        //LOG.info(c.analyzeFile(fileName));
    }
    
    public int analyzeFile(File fileName) {
        BufferedReader br = null;
        String sCurrentLine = null;
        boolean sameLine = false;

        try {
            br = new BufferedReader(new FileReader(fileName));

            LOG.debug("Leyendo el archivo");
            while ((sCurrentLine = br.readLine()) != null) {
                sCurrentLine = sCurrentLine.trim();
                sameLine = false;
                while(sCurrentLine != null && sCurrentLine.length() > 0) {
                    LOG.debug("");
                    sCurrentLine = analyzeLine(sCurrentLine, sameLine);
                    sameLine = true;
                }
            }

            totalLines = codeLines + commentLines;
            System.out.println("Total number of Lines are : " + totalLines);
            System.out.println("Number of comments: " + commentLines);
            System.out.println("Number of code lines: " + codeLines);
        }
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (br != null) {
                    LOG.debug("Archivo terminado");
                    br.close();
                }
            } catch (IOException ex) {
                LOG.error("Error: "+ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }
        return commentLines;
    }

    private String analyzeLine(String sCurrentLine, boolean sameLine) {        
        
        if(commentStarted && sCurrentLine.contains("*/")) {
            if (!sameLine){
                commentLines++;
                LOG.debug("Sigue comentario");
            }
            commentStarted = false;
            if (!sCurrentLine.endsWith("*/")){
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
                LOG.debug("Termina comentario largo");
            }
            else
                sCurrentLine = null;
        }
        else if(sCurrentLine.startsWith("//")) {
            if (!sameLine) {
                commentLines++;
                LOG.debug("Comentario simple");
            }
            sCurrentLine = null;
        }
        else if(sCurrentLine.contains("/*")) {            
            commentStarted = true;
            LOG.debug("Empieza comentario largo");
            if (!sCurrentLine.startsWith("/*")){
                if (!sameLine)
                    codeLines++;
                sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("/*")).trim();                
            }
            else {
                if (!sameLine)
                    commentLines++;
                if (sCurrentLine.contains("*/")) {
                    commentStarted = false;
                    if (!sCurrentLine.endsWith("*/")){
                        sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf("*/")+2).trim();
                        LOG.debug("Termina comentario largo");
                    }
                    else
                        sCurrentLine = null;
                }
                else
                    sCurrentLine = null;
            }
        }        
        else if(commentStarted) {
            if (!sameLine){
                commentLines++;
                LOG.debug("Continúa comentario largo");
            }
            sCurrentLine = null;
        }
        else {
            commentStarted = false;
            if (!sameLine)
                codeLines++;
            if (sCurrentLine.contains(";")) {
                if (!sCurrentLine.endsWith(";")) {
                    sCurrentLine = sCurrentLine.substring(sCurrentLine.indexOf(";")+1).trim();
                }
                else
                    sCurrentLine = null;
            }
            else
                sCurrentLine = null;
        }

        return sCurrentLine;
    }
}
