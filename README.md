package proyectoprimerparcial;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

class ProyectoPrinerParcial {

    public static Scanner sc = new Scanner(System.in);
    public static List<Producto> listaProductos = new ArrayList<>();
    public static List<Vip> listaClientesVIP = new ArrayList<>();
    public static List<Fresh> listaClientesFresh = new ArrayList<>();
    public static void main(String[] args) {
        File archivo = new File("ingredientes.csv");
        List<String> lista = new ArrayList<>();
        if (archivo.exists()) {
            try {
                lista = Files.readAllLines(archivo.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (lista.isEmpty()) {
                return;
            }
            for (String linea : lista) {
                String[] res = linea.split(",");
                listaProductos.add(new Producto(res[0], res[1], res[2], res[3], res[4], res[5]));
            }
            //for(Producto pro: listaProductos)
            //    System.out.println(pro);
            menu();
        }
    }

    public static void menu() {
        String opcion = "";
        while (!opcion.equals("6")) {
            System.out.println("NutriFit");
            System.out.println("1.-Registrar Nuevo Usuario");
            System.out.println("2.-Crear Receta");
            System.out.println("3.-Enviar menu semanal");
            System.out.println("4.-Cargar receta");
            System.out.println("5.-Consultar Suscripciones activas");
            System.out.println("6.-Salir");

            System.out.print("Opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    CrearNuevoUsuario();
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                    break;
                case "5":
                    ConsultarSuscripcionesActivas();
                    break;
            }
        }
    }
    
    public static void CrearNuevoUsuario(){
        System.out.print("Ingrese su numero de cedula: ");
        String cedula = sc.nextLine();
        System.out.print("Ingrese sus dos primeros nombres: ");
        String nombres = sc.nextLine();
        System.out.print("Ingrese sus dos apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Ingrese su numero de telefono: ");
        String telefono = sc.nextLine();
        System.out.print("Ingrese su correo electrónico: ");
        String correo = sc.nextLine();
        System.out.print("Ingrese su dirección: ");
        String direccion = sc.nextLine();
        //listaClientesVIP.add(new Cliente (cedula, nombres, apellidos, telefono, correo, direccion));
        System.out.println("Cual suscripcion desea elegir: ");
        System.out.println("1.-VIP $120/mensual\n"+"2.-Fresh $70/mensual");
        System.out.print("Opcion: ");
        String opcionSuscripcion = sc.nextLine();
        
        switch (opcionSuscripcion) {
            case "1":
                System.out.print("Ingrese su peso en kilogramos (kg): ");
                double peso = sc.nextDouble();
                System.out.print("Ingrese su estatura en metros (m): ");
                double estatura = sc.nextDouble();
                System.out.print("Ingrese las hora de ejercicio que realiza por semana: ");
                double horasEjercicio = sc.nextDouble();
                System.out.print("Ingrese su profesión/trabajo: ");
                String tr = sc.next();
                Date fechaAc = new Date();
                Calendar c1 = Calendar.getInstance();
                c1.setTime(fechaAc);
                //Calendar c = new GregorianCalendar();
                listaClientesVIP.add(new Vip (cedula, nombres, apellidos, telefono, correo, direccion, c1, peso, estatura, horasEjercicio, tr));
                System.out.println("Su usuario ha sido registrado con una Suscripcion VIP");
                break;

            case "2":
                Date fechaAcF = new Date();
                Calendar c2 = Calendar.getInstance();
                c2.setTime(fechaAcF);
                listaClientesFresh.add(new Fresh (cedula, nombres, apellidos, telefono, correo, direccion,c2));
                break;
        }
    }
    
    public static void ConsultarSuscripcionesActivas(){
        for(Vip cliente : listaClientesVIP){
            if(cliente.validarSuscripcion()){
                System.out.println("El "+cliente.toString()+" Tiene aun su suscripción valida");
            }else{
                System.out.println("El "+cliente.toString()+" Ya no tiene su suscripción valida");
            }
        }
    }
        
}
