/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Severus
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
public class game {
    private static int [][] generateTable(int wx, int wy) 
    {
    int table[][] = new int[wx][wy];
    for(int i = 0; i < wx; i++)
      for(int j = 0; j < wy; j++)
        table[i][j] = 0;
    return table;
    }
    private static void printTable(int[][] table) 
    {
        for(int i = 0; i < table.length; i++) 
        {
            for(int j = 0; j < table[i].length; j++) 
            {
                if(table[i][j] == 0)System.out.print("-" + " ");
                else if(table[i][j] == 2)System.out.print("O" + " ");
                else System.out.print("X" + " ");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }
    private static void insertX(int [][] table, int x, int y)
    {
        table[y][x] = 1;
    }
    private static void insertO(int [][] table, int x, int y)
    {
        table[y][x] = 2;
    }
    private static boolean checkWin(int [][] table, int x, int y, int n, int who)
    {
        boolean[] dir = new boolean[9];
        for(int i = 0; i < 9; i++)
            dir[i] = true;
        if(y - 1 < n){dir[0] = false; dir[1] = false; dir[2] = false;}
        if(table[x].length - x < n){dir[2] = false; dir[3] = false; dir[4] = false;}
        if(table.length - y < n){dir[4] = false; dir[5] = false; dir[6] = false;}
        if(x < n){dir[6] = false; dir[7] = false; dir[0] = false;}
        if(dir[0])
        {
            int ix = x - 1, iy = y - 1;
            int how = 0;
            while(how != n)
            {
                if(table[iy][ix] == who){how++;ix--;iy--;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[1])
        {
            int iy = y - 1, how = 0;
            while(how != n)
            {
                if(table[iy][x] == who){how++;y--;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[2])
        {
            int ix = x + 1, iy = y - 1, how = 0;
            while(how != n)
            {
                if(table[iy][ix] == who){how++;ix++;iy--;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[3])
        {
            int ix = x + 1, how = 0;
            while(how != n)
            {
                if(table[y][ix] == who){how++; ix++;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[4])
        {
            int ix = x + 1, iy = y + 1, how = 0;
            while(how != n)
            {
                if(table[iy][ix] == who){how++; ix++; iy++;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[5])
        {
            int iy = y+1, how = 0;
            while(how != n)
            {
                if(table[iy][x] == who){how++; iy++;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[6])
        {
            int ix = x - 1, iy = y + 1, how = 0;
            while(how != n)
            {
                if(table[iy][ix] == who){how++;ix--;iy++;}
                else break;
            }
            if(how == n) return true;
        }
        if(dir[7])
        {
            int ix = x - 1, how = 0;
            while(how != n)
            {
                if(table[y][ix] == who){how++; ix--;}
                else break;
            }
            if(how == n) return true;
        }
        return false;
    }
    private static boolean ifCan(int [][] table, int x, int y)
    {
        if(table[y][x] == 0) return true;
        else return false;
    }
  public static void main(String[] args) throws IOException {
     BufferedReader key = new BufferedReader(new InputStreamReader(System.in)); 
    int x = 5; // szerokosc
    int y = 5; // wysokosc
    int n = 4;
    int board[][] = generateTable(x, y);
    System.out.println("Witamy w grze Kolko i Krzyzyk multiplayer");
    printTable(board);
    boolean turn = true; // 0 - tura krzyzyk, 1 - tura kolko
    boolean win = false;
    int winner = 0;
    while(true)
    {
        if(turn)
        {
            System.out.println("Tura gracza Krzyzyk");
            System.out.println("Podaj wspolrzedne, gdzie chcesz postawic Krzyzyk");
            int wx, wy;
            while(true)
            {
                String s = key.readLine();
                String sx = s.substring(0,1);
                String sy = s.substring(2,3);
                wx = new Integer(sx).intValue();
                wy = new Integer(sy).intValue();
                if(ifCan(board, wx, wy))
                {
                    insertX(board, wx, wy); break;
                }
                else
                {
                    System.out.println("Dane pole jest zajete, sprobuj jeszcze raz");
                }
            }
            turn = false;
            if(checkWin(board, wx, wy, n - 1, 1))
            {
                win = true; winner = 1;
            }
        }
        else
        {
            System.out.println("Tura gracza Kolko");
            System.out.println("Podaj wspolrzedne, gdzie chcesz postawic Kolko");
            int wx, wy;
            while(true)
            {
                String s = key.readLine();
                String sx = s.substring(0,1);
                String sy = s.substring(2,3);
                wx = new Integer(sx).intValue();
                wy = new Integer(sy).intValue();
                if(ifCan(board, wx, wy))
                {
                    insertO(board, wx, wy); break;
                }
                else
                {
                    System.out.println("Dane pole jest zajete, sprobuj jeszcze raz");
                }
            }
            turn = true;
            if(checkWin(board, wx, wy, n - 1, 2))
            {
                win = true; winner = 2;
            }
        }
        printTable(board);
        if(win)
        {
            if(winner == 1)
                System.out.println("Wygral Krzyzyk. Gratulacje");
            else
                System.out.println("Wygralo Kolko. Gratulacje");
            break;
        } 
    }
  }
}