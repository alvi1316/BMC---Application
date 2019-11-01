package bmc.application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;



public class Database {
    public String[] fileList(){
        File f = new File("DataBase");
        String[] fileList = f.list();
        return fileList;
    }
    public resultComp search(String keyword){
        resultComp rc = new resultComp();
        String result = "Roll\t\t\t\tCount\n";
        int present=0,start=0,finish=0,temp=0;
        List<SortComp> s = new ArrayList<>();
        String[] list = fileList();
        for(String str : list){
            try {
                Scanner scr = new Scanner(new File("DataBase/"+str));
                while(scr.hasNextLine()){
                    String line = scr.nextLine();
                    
                    if(line.contains(keyword)){
                        if(line.contains("REG")||line.contains("IRR")){
                            String linetest = line.substring(line.contains("REG") ? line.indexOf("REG"):line.indexOf("IRR"), line.length()-1);
                            if(linetest.contains(keyword)){
                                present++;
                                temp=Integer.parseInt(line.substring(1,7));
                                if(start==0){
                                    
                                    start=Integer.parseInt(line.substring(1,7));;
                                    
                                    finish=Integer.parseInt(line.substring(1,7));
                                    
                                }
                                if(finish+1==temp){
                                    finish=Integer.parseInt(line.substring(1,7));
                                }
                                if(finish+1!=temp){
                                    if(finish==temp){
                                        continue;
                                    }
                                    if(start==finish){
                                        
                                        SortComp sc = new SortComp(start,0,1);
                                        s.add(sc);
                                        
                                    }else{
                                        
                                        SortComp sc = new SortComp(start,finish,finish-start+1);
                                        s.add(sc);
                                        
                                    }
                                    start=temp;
                                    finish=temp;
                                }
                                
                            }
                        }
                    }
                    
                }
                if(start==finish){
                    SortComp sc = new SortComp(start,0,1);
                    s.add(sc);
                    
                }else{
                    SortComp sc = new SortComp(start,finish,finish-start+1);
                    s.add(sc);
                }
                s = sort(s);
            } catch (FileNotFoundException ex) {
                System.out.println("File Can't be found");
            }
        }
        for(int i=0;i<s.size();i++){
            if(s.get(i).getMid()==0){
                result=result+s.get(i).getFirst()+"\t\t\t"+1+"\n";
            }else{
                result=result+s.get(i).getFirst()+"-"+s.get(i).getMid()+"\t"+s.get(i).getLast()+"\n";
            }
        }
        rc.setAttend(Integer.toString(present));
        rc.setResultText(result);
        return rc;
    }
    public resultComp spSearch(String keyword){
        resultComp rc = new resultComp();
        String linetestresult = new String();
        String result = "Roll\t\t\t\tCount\n";
        int sppresent=0,present=0,start=0,finish=0,temp=0;
        List<SortComp> s = new ArrayList<>();
        List<SortComp> ns = new ArrayList<>();
        String[] list = fileList();
        for(String str : list){
            try {
                Scanner scr = new Scanner(new File("DataBase/"+str));
                while(scr.hasNextLine()){
                    String line = scr.nextLine();
                    if(line.contains("SP.")){
                        if(line.contains("IRR")){
                            String linetest = line.substring(line.indexOf("IRR"), line.length()-1);
                            if(linetest.contains("SP.")&&linetest.contains(keyword)){
                                sppresent++;                       
                                temp=Integer.parseInt(line.substring(1,7));
                                linetestresult =linetestresult +temp+"\t"+linetest+"\n";
                                if(start==0){
                                    start=Integer.parseInt(line.substring(1,7));;
                                    finish=Integer.parseInt(line.substring(1,7));;
                                }
                                if(finish+1==temp){
                                    finish=Integer.parseInt(line.substring(1,7));;
                                }
                                if(finish+1!=temp){
                                    if(finish==temp){
                                        continue;
                                    }
                                    if(start==finish){
                                        SortComp sc = new SortComp(start,0,1);
                                        s.add(sc);
                                    }else{
                                        SortComp sc = new SortComp(start,finish,finish-start+1);
                                        s.add(sc);
                                    }
                                    start=temp;
                                    finish=temp;
                                }
                            }
                        }
                         
                    }
                }
                if(start==finish){
                    SortComp sc = new SortComp(start,0,1);
                    s.add(sc);
                }else{
                    SortComp sc = new SortComp(start,finish,finish-start+1);
                    s.add(sc);
                }
                s=sort(s);
                
                
            } catch (FileNotFoundException ex) {
                System.out.println("File Can't be found");
            }
            for(int i=0;i<s.size();i++){
                if(s.get(i).getMid()==0){
                    result=result+s.get(i).getFirst()+"\t\t\t"+1+"\n";
                }else{
                    result=result+s.get(i).getFirst()+"-"+s.get(i).getMid()+"\t"+s.get(i).getLast()+"\n";
                }
            }
            start=0;temp=0;finish=0;
            result=result+"---------------------------------\nSpecial = \t\t\t"+sppresent+"\n\n\nRoll\t\t\t\tCount\n";
            try {
                Scanner scr = new Scanner(new File("DataBase/"+str));
                while(scr.hasNextLine()){
                    String line = scr.nextLine();
                    
                    if(line.contains("REG")||line.contains("IRR")){
                        String linetest = line.substring(line.contains("REG") ? line.indexOf("REG"):line.indexOf("IRR"), line.length()-1);
                        
                        if((!linetest.contains("SP."))&&!(linetest.contains("REG.NO."))){
                            present++;
                            temp=Integer.parseInt(line.substring(1,7));
                            if(start==0){

                                start=Integer.parseInt(line.substring(1,7));;
                                finish=Integer.parseInt(line.substring(1,7));

                            }
                            if(finish+1==temp){
                                finish=Integer.parseInt(line.substring(1,7));
                            }
                            if(finish+1!=temp){
                                if(finish==temp){
                                    continue;
                                }
                                if(start==finish){

                                    SortComp sc = new SortComp(start,0,1);
                                    ns.add(sc);

                                }else{

                                    SortComp sc = new SortComp(start,finish,finish-start+1);
                                    ns.add(sc);

                                }
                                start=temp;
                                finish=temp;
                            }
                        }
                    }
                    
                }
                if(start==finish){
                    SortComp sc = new SortComp(start,0,1);
                    ns.add(sc);
                    
                }else{
                    SortComp sc = new SortComp(start,finish,finish-start+1);
                    ns.add(sc);
                }
                ns = sort(ns);
            } catch (FileNotFoundException ex) {
                System.out.println("File Can't be found");
            }
            for(int i=0;i<ns.size();i++){
                if(ns.get(i).getMid()==0){
                    result=result+ns.get(i).getFirst()+"\t\t\t"+1+"\n";
                }else{
                    result=result+ns.get(i).getFirst()+"-"+ns.get(i).getMid()+"\t"+ns.get(i).getLast()+"\n";
                }
            }
            result=result+"---------------------------------\nNot Special = \t\t"+present+"\n\n\n"+linetestresult;
        }
        rc.setAttend(Integer.toString(present));
        rc.setResultText(result);
        return rc;
    }
    public List<SortComp> sort(List<SortComp> s){
        
        for(int i=0;i<s.size();i++){
            for(int j=1;j<s.size()-i;j++){
                if(s.get(j-1).getFirst()>s.get(j).getFirst()){
                    SortComp temp = s.get(j-1);
                    s.set(j-1, s.get(j));
                    s.set(j,temp);
                }
            }
        }
        return s;
    }
    public void save(String subCode,String text,String total){
        try {
            BufferedWriter writer = null;
            
            writer = new BufferedWriter(new FileWriter("Save/"+subCode+".txt"));
            
            writer.write("Subject Code = " + subCode +"\t"+"Total student of this subject = "+total+"\n");
            writer.write(System.lineSeparator());
            text = text.replace("\n", System.lineSeparator());
            writer.write(text);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Text Saved");
            alert.setHeaderText("");
            alert.setContentText("The text has been successfully saved!");
            alert.showAndWait();
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
}
