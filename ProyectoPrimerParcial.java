package proyectoprimerparcial;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Peter Josue Rodriguez Reyes.
 */
class ProyectoPrinerParcial {
    public static Random Rand = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static List<Producto> listaProductos = new ArrayList<>();
    public static List<Producto> listaProductosMostrar = new ArrayList<>();
    public static List<Producto> listaProductosElegidos = new ArrayList<>();
    public static List<Integer> cantidadProductosElegidos = new ArrayList<>();
    public static List<Vip> listaClientesVIP = new ArrayList<>();
    public static List<Fresh> listaClientesFresh = new ArrayList<>();
    public static List<Receta> listaRecetasCargadas = new ArrayList<>();
    public static List<Receta> listaRecetasDesayuno = new ArrayList<>();
    public static List<Receta> listaRecetasAlmuerzo = new ArrayList<>();
    public static List<Receta> listaRecetasMerienda = new ArrayList<>();
    public static List<Receta> ListaMenuAleatorio = new ArrayList<>();
    public static List<String> diasSemana = Arrays.asList("Lunes","Martes","Miercoles","Jueves","Viernes");

    public static void main(String[] args) {
        File archivo = new File("ingredientes.csv");
        List<String> lista = new ArrayList<>();
        if (archivo.exists()) {
            try {
                lista = Files.readAllLines(archivo.toPath(), Charset.defaultCharset());
                lista.remove(0);
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

            menu();
        }
    }

    public static void menu() {
        String opcion = "";
        while (!opcion.equals("7")) {
            System.out.println("NutriFit");
            System.out.println("1.-Registrar Nuevo Usuario");
            System.out.println("2.-Crear Receta");
            System.out.println("3.-Generar Menu");
            System.out.println("4.-Enviar menu semanal");
            System.out.println("5.-Cargar receta");
            System.out.println("6.-Consultar Suscripciones activas");
            System.out.println("7.-Salir");

            System.out.print("Opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    CrearNuevoUsuario();
                    break;
                case "2":
                    CrearReceta();
                    break;
                case "3":
                    GenerarMenu();
                    break;
                case "4":
                    EnviarMenuSemanal();
                    break;
                case "5":
                    CargarReceta();
                    break;
                case "6":
                    ConsultarSuscripcionesActivas();
                    break;
            }
        }
    }

    public static void CrearNuevoUsuario() {
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
        System.out.println("Cual suscripcion desea elegir: ");
        System.out.println("1.-VIP $120/mensual\n" + "2.-Fresh $70/mensual");
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
                listaClientesVIP.add(new Vip(cedula, nombres, apellidos, telefono, correo, direccion, c1, peso, estatura, horasEjercicio, tr));
                System.out.println("Su usuario ha sido registrado con una Suscripcion VIP");
                break;

            case "2":
                Date fechaAcF = new Date();
                Calendar c2 = Calendar.getInstance();
                c2.setTime(fechaAcF);
                listaClientesFresh.add(new Fresh(cedula, nombres, apellidos, telefono, correo, direccion, c2));
                break;
        }
    }

    public static void ConsultarSuscripcionesActivas() {
        System.out.println("UsuariosVip: ");
        for (Vip cliente : listaClientesVIP) {
            if (cliente.validarSuscripcion()) {
                System.out.println("El " + cliente.toString() + " Tiene aún su suscripción válida");
            } else {
                System.out.println("El " + cliente.toString() + " Ya no tiene su suscripción válida");
            }
        }
        System.out.println("UsuariosFresh: ");
        for (Fresh cliente : listaClientesFresh) {
            if (cliente.validarSuscripcion()) {
                System.out.println("El " + cliente.toString() + " Tiene aún su suscripción válida");
            } else {
                System.out.println("El " + cliente.toString() + " Ya no tiene su suscripción válida");
            }
        }
    }

    public static void CrearReceta() {
        System.out.println("La receta será para: ");
        System.out.println("1.-Desayuno");
        System.out.println("2.-Almuerzo");
        System.out.println("3.-Merienda");
        System.out.print("Elija: ");
        String tipo = sc.nextLine();
        System.out.print("¿Cómo se llamará la receta?: ");
        String nombre = sc.nextLine();
        for (int i = 0; i <= listaProductos.size(); i++) {
            int salida = 0;
            if (i == 0) {
                i += 1;
            }
            listaProductosMostrar.add(listaProductos.get(i - 1));
            while (listaProductosMostrar.size() == 10 && salida == 0) {
                int a = 0;
                System.out.println("¿Qué producto desea agregar a la receta?");
                for (a = 0; a <= listaProductosMostrar.size(); a++) {
                    if (a == 0) {
                        a += 1;
                    }
                    System.out.println(a + " " + listaProductosMostrar.get(a - 1));
                }
                System.out.println("11.-Siguiente Página");
                System.out.println("12.-¿Ya no desea elegir más?");
                System.out.print("Opcion: ");
                String opcionPro = sc.nextLine();
                if (opcionPro.equals("11")) {
                    listaProductosMostrar.clear();
                } else if (opcionPro.equals("12")) {
                    salida += 1;
                } else {
                    listaProductosElegidos.add(listaProductosMostrar.get(Integer.parseInt(opcionPro) - 1));
                    System.out.println("¿Qué cantidad de " + listaProductosMostrar.get(Integer.parseInt(opcionPro) - 1) + " desea agregar en gramos");
                    System.out.print("Gramos: ");
                    String cantidad = sc.nextLine();
                    cantidadProductosElegidos.add(Integer.parseInt(cantidad));
                    System.out.println("El producto " + listaProductosMostrar.get(Integer.parseInt(opcionPro) - 1) + " ha sido agregado");
                }
            }
        }
        System.out.println("¿Cuál es la preparación?");
        String preparacion = sc.nextLine();
        listaProductosMostrar.clear();
        Receta x = new Receta(listaProductosElegidos, cantidadProductosElegidos, preparacion, nombre);
        switch (tipo) {
            case "1":
                listaRecetasDesayuno.add(x);
                break;
            case "2":
                listaRecetasAlmuerzo.add(x);
                break;
            case "3":
                listaRecetasMerienda.add(x);
                break;
        }
        System.out.println("La informacion Nutricional de la receta: ");
        x.informacionNutricional();
        x.crearArchivo();
    }
    
    public static void GenerarMenu() {
        List<List> recetas = new ArrayList<>();
        List<Receta> almuerzo = new ArrayList<>();
        List<Receta> merienda = new ArrayList<>();
        List<Receta> desayuno = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        
        for(int i = 15; i>0 ; i--){ //3 recetas por dias (5 dias por semana)
            for (int a = 0; a<=10; a++){
                int valorRandom = Rand.nextInt(listaProductos.size());
                productos.add(listaProductos.get(valorRandom));
                
                if(productos.size()==10){
                    if(desayuno.size()<=5){
                        desayuno.add(new Receta(productos));
                        productos.clear();
                    }else if(almuerzo.size()<=5){
                        almuerzo.add(new Receta(productos));
                        productos.clear();
                    }else if(merienda.size()<=5){
                        merienda.add(new Receta(productos));
                        productos.clear();
                    }
                }
            }
        }
        recetas.add(desayuno);
        recetas.add(almuerzo);
        recetas.add(merienda);
        System.out.println("El menu es: ");
        for(int a = 0; diasSemana.size()>= a;a++){
            if(a==0){
                a+=1;
            }
            int valorRandom2 = Rand.nextInt(desayuno.size());
            if(valorRandom2>=2){
                valorRandom2-=1;
            }else if(valorRandom2==0){
                valorRandom2+=1;
            }
            System.out.println("-------"+diasSemana.get(a-1)+"-------");
            System.out.println("-->Desayuno: ");
            Receta receta = desayuno.get(valorRandom2-1);
            List<Producto> listPro = receta.getListaProductos();
            System.out.println("La receta contiene: ");
            for(Producto prox : listPro){
                System.out.println(prox.getNombreProducto());
            }
            System.out.println(desayuno.get(valorRandom2-1));
            
            System.out.println("-->Almuerzo: ");
            Receta receta2 = almuerzo.get(valorRandom2-1);
            List<Producto> listPro2 = receta2.getListaProductos();
            System.out.println("La receta contiene: ");
            for(Producto prox : listPro2){
                System.out.println(prox.getNombreProducto());
            }
            
            System.out.println("-->Merienda: ");
            Receta receta3 = merienda.get(valorRandom2-1);
            List<Producto> listPro3 = receta3.getListaProductos();
            System.out.println("La receta contiene: ");
            for(Producto prox : listPro3){
                System.out.println(prox.getNombreProducto());
            }
        }
    }

    public static void EnviarMenuSemanal() {
        System.out.println("¿Con que suscripcion desea enviar el menu?");
        System.out.println("1.-Vip\n"+"2.-Fresh");
        List<Vip> listaClientesVIPEle = new ArrayList<>();
        List<Fresh> listaClientesFreshEl = new ArrayList<>();
        String categoria = sc.nextLine();
        if (categoria.equals("1")){
            for(Vip cliente : listaClientesVIP){
                listaClientesVIPEle.add(cliente);
                if(listaClientesVIPEle.size()==1){
                    System.out.println("¿Desea enviarle a este usuario?");
                    for(int i = 0; i<= listaClientesVIPEle.size(); i++){
                        if(i==0){
                            i+=1;
                        }
                        System.out.println((i)+".-"+listaClientesVIPEle.get(i-1));
                        System.out.println("2.-Siguiente Usuario?");
                        String option = sc.nextLine();
                        if(option.equals("2")){
                            listaClientesVIPEle.clear();
                        }else if(option.equals("1")){
                            String correo = listaClientesVIPEle.get(i-1).getCorreoElectronico();
                            System.out.println("Se ha enviado el menu al correo: "+correo);
                        }
                    }
                }
            }
        }else if (categoria.equals("2")){
            for(Fresh clientex : listaClientesFresh){
                listaClientesFreshEl.add(clientex);
                if(listaClientesFreshEl.size()==1){
                    System.out.println("¿Desea enviarle a este usuario?");
                    for(int i = 0; i<= listaClientesFreshEl.size(); i++){
                        if(i==0){
                            i+=1;
                        }
                        System.out.println((i)+".-"+listaClientesFreshEl.get(i-1));
                        System.out.println("2.-Siguiente Usuario?");
                        String option = sc.nextLine();
                        if(option.equals("2")){
                            listaClientesFreshEl.clear();
                        }else if(option.equals("1")){
                            String correo = listaClientesFreshEl.get(i-1).getCorreoElectronico();
                            System.out.println("Se ha enviado el menu al correo: "+correo);
                        }
                    }
                }
            }
        }
    }

    public static void CargarReceta() {
        System.out.println("Escriba el nombre del archivo sin la extensión(solo .txt): ");
        String nombreArch = sc.nextLine();
        File archivo = new File(nombreArch+".txt");
        List<String> lista = new ArrayList<>();
        List<Producto> productoR = new ArrayList<>();
        List<Integer> cantidadP = new ArrayList<>();
        if (archivo.exists()) {
            try {
                lista = Files.readAllLines(archivo.toPath(), Charset.defaultCharset());
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (lista.isEmpty()) {
                return;
            }
            String[] al = lista.get(0).split(",");
            String nombreReceta = al[0];
            String preparacionReceta = al[1];
            lista.remove(0);
            for (String linea : lista) {
                String[] res = linea.split(",");
                for(Producto produ : listaProductos){
                    if(produ.getNombreProducto().equals(res[0])){
                        productoR.add(produ);
                        cantidadP.add(Integer.parseInt(res[1]));
                    }
                }
            }
            listaRecetasCargadas.add(new Receta (productoR, cantidadP, preparacionReceta, nombreReceta));
            System.out.println("La receta ha sido cargada correctamente.");
        }
    }
    
    

}
