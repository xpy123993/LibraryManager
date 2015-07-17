package common;

import gui.GUIWindow;
import network.MainServer;

/**
 * Created by ≈Ù”Ó on 2015/7/17 0017.
 */
public class Main {

    public static void main(String[] args){

        if (args.length == 1 && args[0].equals("-s"))
            MainServer.Show();
        else if (args.length == 1 && args[0].equals("-c"))
            GUIWindow.Show();
        else
            System.out.println("Usage:\n-s\t:Run as library server\n-c\t:Run as library client");

    }

}
