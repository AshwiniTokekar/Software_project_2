package Parking_System;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashwini
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    static Airlock A[ ] = new Airlock[3];
    static Vehicle V[] = new Vehicle[300];
    static ArrayList<String> History;
    static int v_in=0;
    static void init()
    {
        for(int i=0; i<A.length;i++)
        {
            A[i]=new Airlock();
        }
        History = new ArrayList<String>();
    }
    Airlock[] getairlocks()
    {
        return A;
    }
   public static void main(String args[])
   {
        init();
        MainScreen.Userscreen();
   }
   static void handle_entry(Vehicle v,int v_n) {
        // TODO code application logic here
        
        
        Survilence sur=new Survilence(); 
        StringBuilder sb = new StringBuilder();
        sb.append(History.size()).append(",");
        sb.append(new Date().toString()).append(":Entry,");
        if (sur.detectvehicle(v))
        {
            for (int i=0;i<A.length;i++)
            {
                if(!A[i].full())
            {
                v.set_airlock_id(i);
                System.out.println("Entry by Airlock "+i);
                if(A[i].opengate(v_n, v.vehicle_type));
                {
                    for(int j=0;j<v_n;j++)
                    {
                         A[i].entry(v);
                         System.out.println(v.get_airlock_id());
                    }
                }
                A[i].closegate();
                V[v_in]=new Vehicle(v);
                sb.append(V[v_in].toString());
                System.out.println(sb.toString());
                History.add(sb.toString());
                System.out.println(V[v_in]);
                v_in++;
                break;
            }
            }
        }
               
    }
    
   static Boolean handle_exit(int vid) {
        // TODO code application logic here
        Vehicle temp = null;
        int aid = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(History.size()).append(",");
        sb.append(new Date().toString()).append(":Exit,");
        if(v_in == 0)
        {
          System.out.println("In if");
         return false;   
        }
       try{
            for(int i=0;i<v_in;i++)
           {
            if(V[i].vehicle_id==vid)
            {
                temp=V[i];
               for(int j=i;j<v_in;j++)
               {
                   V[i]=V[i+1];
               }
               break;
            }
          }
        
        aid = temp.get_airlock_id();
        sb.append(temp.toString());
        boolean add = History.add(sb.toString());
       }catch(NullPointerException e)
       {
         JOptionPane.showMessageDialog(null,"Exit not possible");  
       }
       
        return A[aid].exit(temp);
          
    }
   
   static void print_log() throws IOException
   {
       Path file = Paths.get("log.txt");
       Path write = Files.write(file,History,Charset.forName("UTF-8"));
       
      
   }
   
   static void print_pdf()
   {
       Document pdfDoc = new Document(PageSize.A4);
       
      try {
          String output_file;
          output_file = "log.pdf";
          PdfWriter.getInstance(pdfDoc, new FileOutputStream(output_file)).setPdfVersion(PdfWriter.VERSION_1_7);
          pdfDoc.open();

          Font myfont = new Font();
          myfont.setStyle(Font.NORMAL);
          myfont.setSize(11);

          pdfDoc.add(new Paragraph("\n"));
        
          for(int i=0;i<History.size();i++) 
          {
                Paragraph para = new Paragraph( History.get(i) + "\n", myfont);
                para.setAlignment(Element.ALIGN_JUSTIFIED);
                pdfDoc.add(para);
          }
         
        }catch (FileNotFoundException | DocumentException e) {
                pdfDoc.close();
        
        } finally {
              pdfDoc.close();
       
       }
 
   }
}
