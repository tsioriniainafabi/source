/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s6.atelier.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import s6.atelier.utilitaire.Util;

/**
 *
 * @author Admin
 */
public class GenerateurDao {

    //liste  ( String[] ) des lignes different dy type List 
    public List<String[]> getattribut(String file) {
        List<String> priv = Util.getprivate(file);
        String[][] spliter = new String[priv.size()][3];
        List<String[]> attr = new ArrayList<String[]>();
        for (int i = 0; i < priv.size(); i++) {
            spliter[i] = (priv.get(i)).trim().split(" ");
            if (spliter[i][1].startsWith("List")) {
                // ne rien faire pour les types List
            } else {
                attr.add(spliter[i]);
            }
        }
        return attr;
    }



    public void writeheaderonFile(String file, String nameofpackage, String nameofclass, String connectiondb) throws Exception {
        String[] donnees = new String[4];
        donnees[0] = "package " + nameofpackage + ";";
        donnees[1] = "\n";
        donnees[2] = "public class " + nameofclass + "{";
        donnees[3] = connectiondb;
        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public void writeinsertonFile(String fichedoc, String file, String nameofclass, String connectgetconn) throws Exception {
        List<String[]> attr = this.getattribut(fichedoc);
        int taille = attr.size();

        String[] donnees = new String[11];
        donnees[0] = "public void save" + nameofclass + "(" + nameofclass + " " + nameofclass.toLowerCase() + ")throws Exception{";
        donnees[1] = "Connection con=null;";
        donnees[2] = "PreparedStatement prepare=null;";
        donnees[3] = "try{";
        donnees[4] = "String req=\"insert into " + nameofclass.toUpperCase() + " (";
        for (int i = 1; i < taille - 1; i++) {
            donnees[4] = donnees[4].concat(attr.get(i)[2].replace(";", " ") + ",");
        }
        donnees[4] = donnees[4].concat(attr.get(taille - 1)[2] + ") values(");
        for (int i = 1; i < taille; i++) {
            donnees[4] = donnees[4].concat("?, ");
        }
        donnees[4] = donnees[4].concat(")\";");
        donnees[5] = "con=" + connectgetconn + ".getConnPostgre();";
        donnees[6] = "prepare=con.prepareStatement(req);";
        donnees[7] = "prepare.executeUpdate();";
        donnees[8] = "";
        for (int i = 1; i < taille; i++) {
            if (attr.get(i)[1].equals("String")) {
                donnees[8] += "prepare.set" + attr.get(i)[1] + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("int")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("int", "Int") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("boolean")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("boolean", "Boolean") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("Date")) {
                donnees[8] += "prepare.set" + attr.get(i)[1] + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("double")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("double", "Double") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                donnees[8] += "prepare.setInt" + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "().getId" + attr.get(i)[1].toLowerCase() + "());\n";
            }
        }
        donnees[9] = "}catch(Exception e){\n e.getMessage();\n }";
        donnees[10] = " finally{\n if (prepare != null) {\n prepare.close(); \n } \n if (con != null) {\n con.close(); \n } \n } \n } ";

        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            }
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }

    public void writedeleteonFile(String fichedoc, String file, String nameofclass, String connectgetconn) throws Exception {
        List<String[]> attr = this.getattribut(fichedoc);
        int taille = attr.size();

        String[] donnees = new String[11];
        donnees[0] = "public void delete" + nameofclass + "(" + nameofclass + " " + nameofclass.toLowerCase() + ")throws Exception{";
        donnees[1] = "Connection con=null;";
        donnees[2] = "PreparedStatement prepare=null;";
        donnees[3] = "try{";
        donnees[4] = "String req=\"delete from " + nameofclass.toLowerCase() + " where " + attr.get(0)[2].replace(";", "") + "=? \";";
        donnees[5] = "con=" + connectgetconn + ".getConnPostgre();";
        donnees[6] = "prepare=con.prepareStatement(req);";
        donnees[7] = "prepare.executeUpdate();";
        donnees[8] = "prepare.setInt(1 , " + nameofclass.toLowerCase() + ".get" + (attr.get(0)[2].replaceFirst(Character.toString(attr.get(0)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(0)[2].charAt(0))))).replace(";", "") + "() );";
        donnees[9] = "}catch(Exception e){\n e.getMessage();\n }";
        donnees[10] = " finally{\n if (prepare != null) {\n prepare.close(); \n } \n if (con != null) {\n con.close(); \n } \n } \n } ";

        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            }
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }

    public void writeupdateonFile(String fichedoc, String file, String nameofclass, String connectgetconn) throws Exception {
        List<String[]> attr = this.getattribut(fichedoc);
        int taille = attr.size();

        String[] donnees = new String[11];
        donnees[0] = "public void update" + nameofclass + "(" + nameofclass + " " + nameofclass.toLowerCase() + ")throws Exception{";
        donnees[1] = "Connection con=null;";
        donnees[2] = "PreparedStatement prepare=null;";
        donnees[3] = "try{";
        donnees[4] = "String req=\"update from " + nameofclass.toLowerCase() + " set ";
        for (int i = 1; i < taille; i++) {
            donnees[4] = donnees[4].concat(attr.get(i)[2].replace(";", "") + "=? ,");
        }
        donnees[4] = donnees[4].concat("where " + attr.get(0)[2].replace(";", "") + "=? \";");
        donnees[5] = "con=" + connectgetconn + ".getConnPostgre();";
        donnees[6] = "prepare=con.prepareStatement(req);";
        donnees[7] = "prepare.executeUpdate();";
        donnees[8] = "";
        for (int i = 1; i < taille; i++) {
            if (attr.get(i)[1].equals("String")) {
                donnees[8] += "prepare.set" + attr.get(i)[1] + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("int")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("int", "Int") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("boolean")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("boolean", "Boolean") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("Date")) {
                donnees[8] += "prepare.set" + attr.get(i)[1] + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("double")) {
                donnees[8] += "prepare.set" + attr.get(i)[1].replace("double", "Double") + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "());\n";
            }
            if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                donnees[8] += "prepare.setInt" + "(" + (i) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(i)[2].replaceFirst(Character.toString(attr.get(i)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[2].charAt(0))))).replace(";", "") + "().getId" + attr.get(i)[1].toLowerCase() + "());\n";
            }
        }
        donnees[8] = donnees[8].concat("prepare.setInt" + "(" + (taille) + " , " + nameofclass.toLowerCase() + ".get" + (attr.get(0)[2].replaceFirst(Character.toString(attr.get(0)[2].charAt(0)), Character.toString(Character.toUpperCase(attr.get(0)[2].charAt(0))))).replace(";", "") + "());\n");
        donnees[9] = "}catch(Exception e){\n e.getMessage();\n }";
        donnees[10] = " finally{\n if (prepare != null) {\n prepare.close(); \n } \n if (con != null) {\n con.close(); \n } \n } \n }";

        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }
                }
                fichier.close();
            }
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
    
    public void writefindallonFile(String fichedoc, String file, String nameofclass, String connectgetconn) throws Exception {
        List<String[]> attr = this.getattribut(fichedoc);
        int taille = attr.size();

        String[] donnees = new String[11];
        donnees[0] = "public List<"+nameofclass+"> findall" + nameofclass + "()throws Exception{";
        donnees[1] = "List<"+nameofclass+"> resultat=null; \n Connection con=null;";
        donnees[2] = "PreparedStatement prepare=null; \n ResultSet res=null;";
        donnees[3] = "try{";
        donnees[4] = "String req=\"select * from "+ nameofclass.toLowerCase() +" \";";
        donnees[5] = "con=" + connectgetconn + ".getConnPostgre();";
        donnees[6] = "prepare=con.prepareStatement(req);";
        donnees[7] = "res=prepare.executeQuery();";
        donnees[8] = "while(res.next()){ \n resultat.add(new "+ nameofclass +"(";
        for (int i = 0; i < taille; i++) {
            if(i<taille-1){
                if (attr.get(i)[1].equals("String")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("int")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("boolean")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("Date")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("double")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                    donnees[8] += "res.getInt"+ "("+ (i+1) +")" + ",";
                }
            }else{
                if (attr.get(i)[1].equals("String")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("int")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("boolean")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("Date")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("double")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                    donnees[8] += "res.getInt"+ "("+ (i+1) +")";
                }
            }
        }
        donnees[8] = donnees[8].concat(")); \n }");
        donnees[9] = "}catch(Exception e){\n e.getMessage();\n }";
        donnees[10] = "finally{\n if (res != null)  res.close(); \n if (prepare != null) {\n prepare.close(); \n } \n if (con != null) {\n con.close(); \n } \n } \n return resultat; \n }";

        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }
                }
                fichier.close();
            }
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
    
    public void writefindbyidonFile(String fichedoc, String file, String nameofclass, String connectgetconn) throws Exception {
        List<String[]> attr = this.getattribut(fichedoc);
        int taille = attr.size();

        String[] donnees = new String[11];
        donnees[0] = "public "+nameofclass+" findbyid" + nameofclass + "(int id)throws Exception{";
        donnees[1] = ""+nameofclass+" resultat=null; \n Connection con=null;";
        donnees[2] = "PreparedStatement prepare=null; \n ResultSet res=null;";
        donnees[3] = "try{";
        donnees[4] = "String req=\"select * from "+ nameofclass.toLowerCase() +" where "+ attr.get(0)[2].replace(";", "") +" = ?\";";
        donnees[5] = "con=" + connectgetconn + ".getConnPostgre();";
        donnees[6] = "prepare=con.prepareStatement(req); \n prepare.setInt(1, id);";
        donnees[7] = "res=prepare.executeQuery();";
        donnees[8] = "while(res.next()){ \n resultat=(new "+ nameofclass +"(";
        for (int i = 0; i < taille; i++) {
            if(i<taille-1){
                if (attr.get(i)[1].equals("String")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("int")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("boolean")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("Date")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("double")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")" + ",";
                }
                if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                    donnees[8] += "res.getInt"+ "("+ (i+1) +")" + ",";
                }
            }else{
                if (attr.get(i)[1].equals("String")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("int")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("boolean")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("Date")) {
                    donnees[8] += "res.get" + attr.get(i)[1] + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("double")) {
                    donnees[8] += "res.get" + attr.get(i)[1].replaceFirst(Character.toString(attr.get(i)[1].charAt(0)), Character.toString(Character.toUpperCase(attr.get(i)[1].charAt(0)))) + "("+ (i+1) +")";
                }
                if (attr.get(i)[1].equals("Date") == false && attr.get(i)[1].equals("boolean") == false && attr.get(i)[1].equals("int") == false && attr.get(i)[1].equals("String") == false && attr.get(i)[1].equals("double") == false) {
                    donnees[8] += "res.getInt"+ "("+ (i+1) +")";
                }
            }
        }
        donnees[8] = donnees[8].concat(")); \n }");
        donnees[9] = "}catch(Exception e){\n e.getMessage();\n }";
        donnees[10] = "finally{\n if (res != null)  res.close(); \n if (prepare != null) {\n prepare.close(); \n } \n if (con != null) {\n con.close(); \n } \n } \n return resultat; \n } \n }";

        try {
            File f = new File(file);
            if (f.exists() == true) {
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }

                }
                fichier.close();
            } else {
                f = Util.createFile(file);
                FileWriter fichier = new FileWriter(file, true);

                int i = 0;
                while (i < donnees.length) {
                    if (donnees[i] != null) {
                        fichier.write(String.valueOf(donnees[i]));
                        fichier.write("\n");
                        i++;
                    } else {
                        break;
                    }
                }
                fichier.close();
            }
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
    
    public void generateurdao(String repertoiremodele, String repertoiredao, String nompackage, String connectiondb, String connectgetconn) throws Exception {
        List<String> allfile;
        allfile = Util.getallfileinfolder(repertoiremodele);

        for (int i = 0; i < allfile.size(); i++) {
            String nameofclass = Util.getnameofclass(repertoiremodele + "/" + allfile.get(i));
            File newfile = Util.createFile(repertoiredao + nameofclass + "Dao.java");
            this.writeheaderonFile(repertoiredao + nameofclass + "Dao.java", nompackage, nameofclass + "Dao", connectiondb);
            this.writeinsertonFile(repertoiremodele + "/" + allfile.get(i), repertoiredao + nameofclass + "Dao.java", nameofclass, connectgetconn);
            this.writedeleteonFile(repertoiremodele + "/" + allfile.get(i), repertoiredao + nameofclass + "Dao.java", nameofclass, connectgetconn);
            this.writeupdateonFile(repertoiremodele + "/" + allfile.get(i), repertoiredao + nameofclass + "Dao.java", nameofclass, connectgetconn);
            this.writefindallonFile(repertoiremodele + "/" + allfile.get(i), repertoiredao + nameofclass + "Dao.java", nameofclass, connectgetconn);
            this.writefindbyidonFile(repertoiremodele + "/" + allfile.get(i), repertoiredao + nameofclass + "Dao.java", nameofclass, connectgetconn);
        }
    }

    public static void main(String[] args) {
        GenerateurDao gen = new GenerateurDao();

        String repertoiremodele = "C:/Users/Admin/Documents/NetBeansProjects/atelier/src/java/s6/atelier/modele";
        String repertoiredao = "C:/Users/Admin/Documents/NetBeansProjects/atelier/src/java/s6/atelier/dao/";
        String nompackage = "s6.atelier.dao";
        String connectiondb = "UtilDb utildb=new UtilDb();";
        String connectgetconn = "utildb";

        try {
            gen.generateurdao(repertoiremodele, repertoiredao, nompackage, connectiondb, connectgetconn);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
