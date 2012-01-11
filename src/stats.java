/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
/**
 *
 * @author Severus
 */
public class stats 
{
    private static void createStatFile() throws IOException
    {
        File plik = new File("stats.txt");
        if(plik.exists() == true)
            plik.createNewFile();
    }
    private static void readStatFile()
    {
        
    }
    private static void writeStatFile(int X, int Y) throws IOException
    {      
        
        String cos = x + " " + y;
        BufferedWriter write = new BufferedWriter(FileWriter("stats.txt"));
        write.write(cos);
    }
}
