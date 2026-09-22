package com.mycompany.minpro.pbo2.satwaygdilindungi;

import Controller.SatwaCRUD;
import View.Menu;
import java.util.Scanner;

public class MinproPbo2Satwaygdilindungi {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        SatwaCRUD crud = new SatwaCRUD();

        Menu.jalankan(input, crud);

        input.close();
    }
}