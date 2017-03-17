/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s6.atelier.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import s6.atelier.modele.Atelier;
import s6.atelier.modele.CategMatiere;

/**
 *
 * @author Admin
 */
public class GeneriqueDao {
    public void insert(BaseModele basemodele)throws Exception{
        Class classe=basemodele.getClass();
        Connection c=null;
        PreparedStatement pst=null;
        try{
            String req=setReq(classe);  
            System.out.println(req);
            c=UtilDb.getConnPostgre();
            pst=c.prepareStatement(req);
            setPreparedStatement(pst,basemodele);
            pst.executeUpdate();
            c.commit();
        }catch(Exception e){
            throw e;
        }finally{
            if(pst != null )pst.close();
            if(c != null) c.close();
        }
    }
    
    public void update(BaseModele basemodele)throws Exception{
        Class classe=basemodele.getClass();
        Connection c=null;
        PreparedStatement pst=null;
        try{
            String req=setReqUpdate(classe);  
            System.out.println(req);
            c=UtilDb.getConnPostgre();
            pst=c.prepareStatement(req);
            int indice=setPreparedStatement(pst,basemodele);
            Method getAttr=classe.getMethod("getId");
            pst.setInt(indice+1, (int)getAttr.invoke(basemodele));
            pst.executeUpdate();
            c.commit();
        }catch(Exception e){
            throw e;
        }finally{
            if(pst != null )pst.close();
            if(c != null) c.close();
        }
    }
    
    public void delete(BaseModele basemodele) throws Exception{
        Class classe=basemodele.getClass();
        Connection c=null;
        PreparedStatement pst=null;
        try{
            String req=setReqDelete(classe);  
            System.out.println(req);
            c=UtilDb.getConnPostgre();
            pst=c.prepareStatement(req);
            Method getAttr=classe.getMethod("getId");
            pst.setInt(1, (int)getAttr.invoke(basemodele));
            pst.executeUpdate();
            c.commit();
        }catch(Exception e){
            throw e;
        }finally{
            if(pst != null )pst.close();
            if(c != null) c.close();
        }
    }
    
    public List<BaseModele> getAll(BaseModele basemodele)throws Exception{
        Class classe=basemodele.getClass();
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet res=null;
        
                    
        Field[] attributs=classe.getDeclaredFields();
        try{
            
            String req="SELECT * FROM \""+classe.getSimpleName()+"\" ;";
            conn=UtilDb.getConnPostgre();
            pst = conn.prepareStatement(req);
            res = pst.executeQuery();
            Class[] arguments=new Class[attributs.length+1];
            List<BaseModele> ret =new ArrayList();
            
            arguments[0]=int.class;
            for(int j=0; j<attributs.length; j++){
                arguments[j+1]=attributs[j].getType();
            }
            
            int i=0;
            
            while (res.next()){
                System.out.println("***** debut ****");
                int id=res.getInt("id_"+classe.getSimpleName().toLowerCase());
                System.out.println("i == "+i);
                    ret.add((BaseModele) classe.getConstructor(arguments).newInstance(getResultOnResultset(res,basemodele)));
                    i++;
                    System.out.println("***** fin ****");
            }
            return ret;
        }catch(Exception e){
            throw e;
        }finally{
            if(res != null)res.close();
            if(pst != null)pst.close();
            if(conn != null)conn.close();
        }
    }
    
    public Object[] getResultOnResultset(ResultSet res, BaseModele basemodele) throws Exception{
        System.out.println("............");
        Class classe=basemodele.getClass();
        Field[] attributs=classe.getDeclaredFields();
        Object[] ret=new Object[attributs.length+1];
        Method getInt=res.getClass().getMethod("getInt", String.class);
        ret[0]=getInt.invoke(res, "id_"+classe.getSimpleName().toLowerCase());
        System.out.println(ret[0]);
        for(int i=0; i< attributs.length; i++){
            Method get=res.getClass().getMethod("get"+toUpperCaseFirstElement(attributs[i].getType().getSimpleName()), String.class);
            ret[i+1]=get.invoke(res, attributs[i].getName());
            System.out.print(ret[i+1]+" ");
        }
        System.out.println();
        return ret;
    }
    
    
    public int setPreparedStatement(PreparedStatement pst,BaseModele basemodele) throws Exception{
        int j=0;
        try{
            Class classePst=pst.getClass();
            Class[] arguments=new Class[2];
            arguments[0]=int.class;
            Class classe=basemodele.getClass();
            Field[] attributs=classe.getDeclaredFields();
            
            while( j<attributs.length){
                String nomSetType="set"+toUpperCaseFirstElement(attributs[j].getType().getSimpleName());
                String nomgetAttribut="get"+toUpperCaseFirstElement(attributs[j].getName());
                Method getAttr=classe.getMethod(nomgetAttribut);
                arguments[1]=attributs[j].getType();
                Method setType=classePst.getMethod(nomSetType, arguments);
                Object[] arg=new Object[2];
                arg[0]=j+1;
                arg[1]=getAttr.invoke(basemodele);
                setType.invoke(pst, arg);
                j++;
            }
        return j;
        }catch(Exception e){
            throw e;
        }
    }
    
    public static int testIfPrimitive(Object c) throws Exception{
        if(c.getClass().isPrimitive()!=true){
            Method getint=c.getClass().getMethod("getId");
            return (int)getint.invoke(c);
        }
        return 0;
    }
    
    public String toUpperCaseFirstElement(String mot){
        String upper=mot.substring(0,1).toUpperCase();
        return upper+mot.substring(1) ;
    }
    
    public String setReqUpdate(Class classe){
        Field[] attributs=classe.getDeclaredFields();
        String ret="UPDATE \""+classe.getSimpleName()+"\" SET ";
        for(int i=0; i<attributs.length; i++){
            ret=ret+attributs[i].getName()+" = ?";
            if(i != attributs.length-1){
                ret=ret+",";
            }
        }
        ret=ret+" WHERE id_"+classe.getSimpleName().toLowerCase()+" = ? ;";
        return ret;
    }  
    
    public String setReqDelete(Class classe){
        String ret="DELETE FROM \""+classe.getSimpleName()+"\" WHERE id_"+classe.getSimpleName().toLowerCase()+" = ? ;";
        return ret;
    }
    
    public String setReq(Class classe){
        Field[] attributs=classe.getDeclaredFields();
        String ret="INSERT INTO \""+classe.getSimpleName();
        ret=ret+"\" VALUES (NEXTVAL('";
        ret=ret+getSeq(classe)+"')";
        for(int i=0; i<attributs.length; i++){
            ret=ret+",?";
        }
        ret=ret+")";
        return ret;
    }
    
    public String getSeq(Class classe){
        String nomClasse=classe.getSimpleName().toLowerCase();
        String id="_id"+nomClasse.toLowerCase()+"_seq";
        return nomClasse+id;
    }
    
    public static void main(String[] args) {
        CategMatiere a=new CategMatiere(1,"testCategmatiere");
        GeneriqueDao g=new GeneriqueDao();
        try {
            
            List<BaseModele> list=g.getAll(a);
            
            for(int i=0;i<list.size();i++){
                System.out.println(((CategMatiere)list.get(i)).getNomcateg()+" == designayion");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(GeneriqueDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
