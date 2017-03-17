/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s6.atelier.utilitaire;

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import s6.atelier.dao.UtilDb;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Util {
    
    public static void main(String[] args) {
        try {
            lecture();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // liste de tous les noms de fichier dans une repertoire
    public static List<String> getallfileinfolder(String repertoire) throws Exception {
        List<String> allfiles = new ArrayList<>();
        File folder = new File(repertoire);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().endsWith(".java")) {
                    allfiles.add(listOfFiles[i].getName());
                } else {
                    throw new Exception("erreur sur la lecture du nom de fichier");
                }
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        return allfiles;
    }
    
    //lecture du fichier en donnant le chemin et le nom de fichier
    public static List<String> readfile(String file) {
        List<String> rowString = new ArrayList<String>();
        try {
            
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            String ligne = br.readLine();
            while (ligne != null) {
                rowString.add(ligne);
                ligne = br.readLine();
            }
            br.close();
            reader.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
        return rowString;
    }
    
    // get nom de la classe en donnant le chemin et le nom de fichier
    public static String getnameofclass(String file) {
        List<String> contenu = readfile(file);
        String res = null;
        for (int i = 0; i < contenu.size(); i++) {
            if (contenu.get(i).trim().startsWith("public class")) {
                res = contenu.get(i).replace('{', ' ');
            }
        }
        String[] spliter = new String[3];
        spliter = res.split(" ");

        String resultat = spliter[2];

        return resultat;
    }
    
    // liste des lignes dans un fichier commencant par private en donnant le chemain et le nom de fichier
    public static List<String> getprivate(String file) {
        List<String> contenu = Util.readfile(file);

        List<String> resultat = new ArrayList<String>();
        for (int i = 0; i < contenu.size(); i++) {
            if (contenu.get(i).trim().startsWith("private")) {
                resultat.add(contenu.get(i));
            }
        }

        return resultat;
    }
    
    // creation de fichier en donnant de chemin + nom
    public static File createFile(String nom) throws Exception{
        File file = new File(nom);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erreur de remplacement du fichier");
                throw e;
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Erreur de creation du fichier");
                throw e;
            }
        }
        return file;
    }
    
    
    
    
    public static void lecture()throws IOException{
        Path file =FileSystems.getDefault().getPath("C:/Users/Admin/Documents/NetBeansProjects/atelier/src/java/s6/atelier/dao","UtilDb.java");
        
        BufferedReader bfreader=null;
        FileReader filereader=new FileReader("C:/Users/Admin/Documents/NetBeansProjects/atelier/src/java/s6/atelier/modele/Atelier.java");
        bfreader=new BufferedReader(filereader);
//        String ret=new String(Files.readAllLines(file));
//        System.out.println(bfreader.readLine());
        File f=null;
        f=new File("C:/Users/Admin/Desktop/test");
        f.mkdir();
        f.createNewFile();
        
        while(true){
            String line=bfreader.readLine();
            if(line==null){
                break;
            }
            String [] listeLigne=line.split(" ");
            int i=0;
                if(listeLigne != null){
                    
                    for(String ligne:listeLigne){
                        if("private".compareTo(ligne.trim())==0){
                            System.out.println(ligne);
                            i++;
                            System.out.println(listeLigne[i]);
                        }
                    }
                }
            
        }
//        while(true){
//            String line=bfreader.readLine();
//            String [] listeLigne=line.split(" ");
//            for(int i=0; i< listeLigne.length; i++){
//                System.out.println(listeLigne[i].trim()+""+i);
//                
//                if(listeLigne[4]=="private"){
//                    System.out.println("111");
//                }else{
//                    System.out.println("000");
//                }
//                System.out.println("_________");
//            }
//            if(line==null){
//                break;
//            }
//        }
    }
    
    public static void reinitDB() throws Exception {
        String aSQLScriptFilePath = "D:/c/s6/atelier/Ccreation.sql";
        String aSQLScriptFilePathInsert = "D:/c/s6/atelier/Cinsert.sql";

        Connection con = null;

        try {
            con = UtilDb.getConnPostgre();

            
            ScriptRunner sr = new ScriptRunner(con, false, false);

            
            Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
            Reader reader_ins = new BufferedReader(new FileReader(aSQLScriptFilePathInsert));

            
            sr.runScript(reader);
            sr.runScript(reader_ins);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (con != null)con.close();
        }
    }
}
