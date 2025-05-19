/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.programacion.pkg1;

import java.util.Scanner;

/**
 *
 * @author alexv
 */
public class Vasquez_Alexander_ProyectoTienda {

    public static void main(String[] args) {
        Scanner lea = new Scanner(System.in);

        // precio de compra y venta de los producto
        // azucar
        String descripcionAzucar = "Azucar";
        double precioVentaAzucar = 30;
        double precioCompraAzucar = 25;

        // avena
        String descripcionAvena = "Avena ";
        double precioVentaAvena = 25;
        double precioCompraAvena = 20;

        // trigo
        String descripcionTrigo = "Trigo ";
        double precioVentaTrigo = 32;
        double precioCompraTrigo = 30;

        // maiz
        String descripcionMaiz = "Maiz  ";
        double precioVentaMaiz = 20;
        double precioCompraMaiz = 18;

        // las variables para el estado del sistema
        double efectivoEnCaja = 0;
        boolean cajaAbierta = false;
        boolean sistemaCerrado = false;

        // contadores para reportes
        int numeroVentas = 0;
        int numeroCompras = 0;
        double volumenTotalVentas = 0;
        double volumenTotalCompras = 0;
        double mayorGananciaVenta = 0;
        double mayorGastoCompra = 0;

        // contadores para el producto estrella     
        double cantidadVendidaAzucar = 0;
        double cantidadVendidaAvena = 0;
        double cantidadVendidaTrigo = 0;
        double cantidadVendidaMaiz = 0;

        int codigoProducto = 0;
        double subtotalVenta = 0;
        double kilos = 0;

        // Variable para acumular los detalles de la venta en la factura de ventas
        String detallesVenta = "";

        boolean primeraVez = true; //esta variable es para controlar si es la primera vez que se abre el programa

        // inicio del sistema
        System.out.println("====================================");
        System.out.println("****SISTEMA DE CAJA REGISTRADORA****");
        System.out.println("====================================");
        System.out.println("\nLa tienda comienza el dia con Lps. 0.00");

        int opcion = 0;
        while (true) {
            System.out.println("\n\n==========================");
            System.out.println(" =---=MENU PRINCIPAL=---="); //menu principal
            System.out.println("==========================");
            System.out.println("\n1) Abrir Caja");
            System.out.println("2) Ventas");
            System.out.println("3) Compras");
            System.out.println("4) Reportes");
            System.out.println("5) Cierre de Caja");
            System.out.println("6) Salir del Sistema");
            System.out.println("\n==========================");
            System.out.print("Ingrese una opcion (1-6): ");// opcion del usuario
            opcion = lea.nextInt();
            lea.nextLine();   // limpia buffer

            if (opcion == 1) { // abrir caja
                if (sistemaCerrado) { // si el sistema estaba cerrado, reiniciamos los contadores
                    numeroVentas = 0;
                    numeroCompras = 0;                    // reiniciar contadores
                    volumenTotalVentas = 0;
                    volumenTotalCompras = 0;
                    mayorGananciaVenta = 0;
                    mayorGastoCompra = 0;
                    cantidadVendidaAzucar = 0;
                    cantidadVendidaAvena = 0;
                    cantidadVendidaTrigo = 0;
                    cantidadVendidaMaiz = 0;
                    sistemaCerrado = false;
                    cajaAbierta = true;
                    System.out.println("\nCaja abierta con Lps. " + efectivoEnCaja);
                } else if (primeraVez || !sistemaCerrado) {
                    double cantidad = -1;
                    while (true) {
                        System.out.print("\nIngrese la cantidad de efectivo (en numeros) a agregar a la caja: Lps. ");
                        cantidad = lea.nextDouble();
                        lea.nextLine(); // limpia buffer
                        if (cantidad >= 0) {
                            efectivoEnCaja += cantidad;
                            cajaAbierta = true; // abrimos la caja
                            System.out.println("\nCaja abierta con Lps. " + efectivoEnCaja);
                            primeraVez = false; // Ya no es la primera vez
                            break;
                        } else { // evitamos numeros negativos
                            System.out.println("No ingresar Numeros Negativos Porfavor");
                        }
                    }
                }
            } else if (opcion == 2) { // ventas
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }
                if (cajaAbierta && !sistemaCerrado) {
                    boolean continuarComprando = true;
                    char tipoCliente = ' ';
                    subtotalVenta = 0; // Reiniciar datos
                    detallesVenta = "";

                    while (continuarComprando) {
                        if (tipoCliente == ' ') { // preguntamos
                            System.out.print("Ingrese el tipo de cliente (A, B o C): ");
                            tipoCliente = lea.next().toUpperCase().charAt(0); // uso charAt por si pone mas de un caracter
                        }

                        if (tipoCliente != 'A' && tipoCliente != 'B' && tipoCliente != 'C') {
                            System.out.println("Tipo de cliente no valido. Debe elegir entre A, B o C.");
                            tipoCliente = ' ';
                            continue;
                        }

                        boolean puedeComprar = false;
                        String descripcionProducto = "";

                        while (true) {
                            System.out.println("====================================");
                            System.out.println("      ------Productos------");
                            System.out.println("====================================");
                            System.out.println("\n1) Azucar\n2) Avena\n3) Trigo\n4) Maiz");
                            System.out.println("\n====================================");
                            System.out.print("\nIngrese el codigo del producto (1-4): ");
                            codigoProducto = lea.nextInt();
                            lea.nextLine(); // Limpiar el buffer

                            if (codigoProducto < 1 || codigoProducto > 4) {
                                System.out.println("Codigo de producto invalido. Debe ser entre 1 y 4.");
                            } else {
                                break;
                            }
                        }

                        if (tipoCliente == 'A') { // verificamos si el cliente puede comprar el producto
                            puedeComprar = true;
                        } else if (tipoCliente == 'B') {
                            puedeComprar = (codigoProducto == 1 || codigoProducto == 2 || codigoProducto == 3);
                        } else if (tipoCliente == 'C') {
                            puedeComprar = (codigoProducto == 4);
                        }

                        if (!puedeComprar) {
                            System.out.println("Este cliente no puede comprar este producto");
                        } else {
                            double precioVenta = 0.0;

                            if (codigoProducto == 1) {
                                descripcionProducto = descripcionAzucar;
                                precioVenta = precioVentaAzucar;
                            } else if (codigoProducto == 2) {
                                descripcionProducto = descripcionAvena;
                                precioVenta = precioVentaAvena;
                            } else if (codigoProducto == 3) {
                                descripcionProducto = descripcionTrigo;
                                precioVenta = precioVentaTrigo;
                            } else if (codigoProducto == 4) {
                                descripcionProducto = descripcionMaiz;
                                precioVenta = precioVentaMaiz;
                            }

                            while (true) {
                                System.out.print("Ingrese la cantidad en kilogramos (en numeros) de " + descripcionProducto + " (" + precioVenta + " Lps. por kg) a comprar: "); //preguntar kilos
                                kilos = lea.nextDouble();
                                lea.nextLine();
                                if (kilos <= 0) {
                                    System.out.println("La cantidad debe ser positiva.");
                                    continue;
                                }
                                break;
                            }

                            double precioUnitario = precioVenta;
                            double subtotalProducto = kilos * precioUnitario;
                            subtotalVenta += subtotalProducto;

                            System.out.println(String.format("La Venta de %.2f kilos de %s por Lps. %.2f se ha agregado.", kilos, descripcionProducto, subtotalProducto));

                            // actualizar contadores para la opcion del reporte
                            if (codigoProducto == 1) {
                                cantidadVendidaAzucar += kilos;
                            } else if (codigoProducto == 2) {
                                cantidadVendidaAvena += kilos;
                            } else if (codigoProducto == 3) {
                                cantidadVendidaTrigo += kilos;
                            } else if (codigoProducto == 4) {
                                cantidadVendidaMaiz += kilos;
                            }

                            // detallesVenta se usa para la facturacion, es la parte de factura que son los productos comprados
                            detallesVenta += String.format("%s             %.0f kg x Lps. %.2f \n", descripcionProducto, kilos, precioVenta);
                        }

                        System.out.print("Desea comprar otro producto? (Si/No): "); // preguntamos si desea comprar más productos
                        String respuesta = lea.nextLine().toLowerCase();

                        while (true) {
                            if (respuesta.equals("si")) {
                                break;
                            } else if (respuesta.equals("no")) {
                                continuarComprando = false;
                                tipoCliente = ' ';
                                break;
                            } else {
                                System.out.println("Invalido. Intente de nuevo.");
                                System.out.print("Desea comprar otro producto? (Si/No): ");
                                respuesta = lea.nextLine().toLowerCase();
                            }
                        }
                    }

                    if (!continuarComprando) {
                        tipoCliente = ' ';
                    }

                    if (subtotalVenta > 0) {
                        // calcular descuentos
                        double descuento = 0;
                        if (subtotalVenta >= 5000) {
                            descuento = subtotalVenta * 0.1;
                        } else if (subtotalVenta >= 1000) {
                            descuento = subtotalVenta * 0.05;
                        }

                        double impuesto = subtotalVenta * 0.07;
                        double totalPagar = subtotalVenta - descuento + impuesto;

                        // actualizar efectivo en caja y contadores para reportes
                        efectivoEnCaja += totalPagar;
                        numeroVentas++;
                        volumenTotalVentas += totalPagar;
                        double costoTotal = 0;
                        double gananciaVenta = 0;

                        // calcular el costo total de los productos 
                        if (codigoProducto == 1) {
                            costoTotal = kilos * precioCompraAzucar;
                        } else if (codigoProducto == 2) {
                            costoTotal = kilos * precioCompraAvena;
                        } else if (codigoProducto == 3) {
                            costoTotal = kilos * precioCompraTrigo;
                        } else if (codigoProducto == 4) {
                            costoTotal = kilos * precioCompraMaiz;
                        }

                        gananciaVenta = totalPagar - costoTotal;

                        if (gananciaVenta > mayorGananciaVenta) {
                            mayorGananciaVenta = gananciaVenta;
                        }

                        // FACTURA
                        System.out.println("\n\n=====================================");
                        System.out.println("              FACTURA");
                        System.out.println("=====================================");
                        System.out.print("\n");
                        System.out.println("\nPRODUCTO       CANTIDAD        PRECIO");
                        System.out.print("-------------------------------------");
                        System.out.println("\n");
                        System.out.println(detallesVenta);
                        System.out.print("-------------------------------------");
                        System.out.println(String.format("\nSubtotal Total:            Lps.%.2f", subtotalVenta)); //imprimir resultados
                        System.out.println(String.format("\nDescuento:                 Lps.%.2f", descuento));
                        System.out.println(String.format("\nImpuesto:                  Lps.%.2f", impuesto));
                        System.out.println("\n=====================================");
                        System.out.println(String.format("\nTotal a Pagar:             Lps.%.2f", totalPagar));
                    } else {
                        System.out.println("No se realizaron ventas.");
                    }
                }
            } else if (opcion == 3) { // compras
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }
                if (cajaAbierta && !sistemaCerrado) {
                    System.out.print("Ingrese el tipo de proveedor (A, B o C): ");
                    char tipoProveedor = lea.next().toUpperCase().charAt(0);

                    while (tipoProveedor != 'A' && tipoProveedor != 'B' && tipoProveedor != 'C') {
                        System.out.println("Tipo de proveedor invalido. Debe elegir entre A, B o C.");
                        System.out.print("Ingrese el tipo de proveedor (A, B o C): ");
                        tipoProveedor = lea.next().toUpperCase().charAt(0);
                    }

                    while (true) {
                        System.out.println("=====================================");
                        System.out.println("        ------Productos------");
                        System.out.println("=====================================");
                        System.out.println("\n1) Azucar\n2) Avena\n3) Trigo\n4) Maiz");
                        System.out.println("\n=====================================");
                        System.out.print("\nIngrese el codigo del producto a comprar (1-4): ");
                        codigoProducto = lea.nextInt();
                        lea.nextLine();

                        if (codigoProducto < 1 || codigoProducto > 4) {
                            System.out.println("Codigo de producto invalido. Debe ser entre 1 y 4.");
                        } else {
                            break;
                        }
                    }

                    boolean puedeSuplir = false;

                    if (tipoProveedor == 'A') {
                        puedeSuplir = (codigoProducto == 1 || codigoProducto == 4);
                    } else if (tipoProveedor == 'B') {
                        puedeSuplir = (codigoProducto == 2 || codigoProducto == 3);
                    } else if (tipoProveedor == 'C') {
                        puedeSuplir = (codigoProducto == 2);
                    }

                    if (!puedeSuplir) {
                        System.out.println("El proveedor no vende este producto");
                    } else {
                        String descripcionProducto = "";
                        double precioCompra = 0;

                        if (codigoProducto == 1) {
                            descripcionProducto = descripcionAzucar;
                            precioCompra = precioCompraAzucar;
                        } else if (codigoProducto == 2) {
                            descripcionProducto = descripcionAvena;
                            System.out.println("\nPrecios disponibles para la Avena:");
                            System.out.println("1) Lps. 20 por kg");
                            System.out.println("2) Lps. 22 por kg");
                            System.out.print("Seleccione el precio (1 o 2): ");
                            int opcionPrecio = lea.nextInt();
                            lea.nextLine();

                            while (opcionPrecio != 1 && opcionPrecio != 2) {
                                System.out.println("Opcion invalida. Debe ser 1 o 2.");
                                System.out.print("Seleccione el precio (1 o 2): ");
                                opcionPrecio = lea.nextInt();
                                lea.nextLine();
                            }
                            if (opcionPrecio == 1) {
                                precioCompra = 20;
                            } else {
                                precioCompra = 22;
                            }
                        } else if (codigoProducto == 3) {
                            descripcionProducto = descripcionTrigo;
                            precioCompra = precioCompraTrigo;
                        } else if (codigoProducto == 4) {
                            descripcionProducto = descripcionMaiz;
                            precioCompra = precioCompraMaiz;
                        }

                        while (true) {
                            System.out.print("Ingrese la cantidad en kilogramos (en numeros) de " + descripcionProducto + " (" + precioCompra + " Lps. por kg) a comprar: "); //preguntar kilo
                            kilos = lea.nextDouble();
                            lea.nextLine();
                            if (kilos <= 0) {
                                System.out.println("La cantidad debe ser positiva.");
                                continue;
                            }
                            break;
                        }

                        double totalCompra = kilos * precioCompra;

                        // verificar si hay suficiente efectivo en caja
                        if (totalCompra > efectivoEnCaja) {
                            System.out.println("No se Puede Pagar Compra");
                        } else {
                            // actualizar efectivo en caja y contadores
                            efectivoEnCaja -= totalCompra;
                            numeroCompras++;
                            volumenTotalCompras += totalCompra;

                            // verificar si es la compra con mayor gasto
                            if (totalCompra > mayorGastoCompra) {
                                mayorGastoCompra = totalCompra;
                            }
                            System.out.println("\n\n=======================================================");
                            System.out.println(String.format("Compra realizada de %.2f kg de %s por Lps. %.2f", kilos, descripcionProducto, totalCompra));
                        }
                    }
                }
            } else if (opcion == 4) {
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }

                // Realizar calculos antes de imprimir
                double margenGanancia = volumenTotalVentas - volumenTotalCompras;
                double promedioCompra;

                if (numeroCompras > 0) {
                    promedioCompra = volumenTotalCompras / numeroCompras;
                } else {
                    promedioCompra = 0;
                }

                double promedioVenta;

                if (numeroVentas > 0) {
                    promedioVenta = volumenTotalVentas / numeroVentas;
                } else {
                    promedioVenta = 0;
                }

                String productoEstrella = "";
                double mayorCantidad = 0;

                if (cantidadVendidaAzucar > mayorCantidad) {
                    mayorCantidad = cantidadVendidaAzucar;
                    productoEstrella = descripcionAzucar;
                }

                if (cantidadVendidaAvena > mayorCantidad) {
                    mayorCantidad = cantidadVendidaAvena;
                    productoEstrella = descripcionAvena;
                }

                if (cantidadVendidaTrigo > mayorCantidad) {
                    mayorCantidad = cantidadVendidaTrigo;
                    productoEstrella = descripcionTrigo;
                }

                if (cantidadVendidaMaiz > mayorCantidad) {
                    mayorCantidad = cantidadVendidaMaiz;
                    productoEstrella = descripcionMaiz;
                }

                // imprimir resultados
                System.out.println("\n============ REPORTES =============");
                System.out.println(String.format("\na. Cantidad Actual en Caja: Lps. %.2f", efectivoEnCaja));
                System.out.println(String.format("\nb. Numero de Compras: %d, Numero de Ventas: %d", numeroCompras, numeroVentas));
                System.out.println(String.format("\nc. Volumen total de Compras: Lps. %.2f, Volumen total de Ventas: Lps. %.2f", volumenTotalCompras, volumenTotalVentas));
                System.out.println(String.format("   Margen de ganancia: Lps. %.2f", margenGanancia));
                System.out.println(String.format("\nd. Valor Medio de Compra: Lps. %.2f, Valor Medio de Venta: Lps. %.2f", promedioCompra, promedioVenta));
                System.out.println(String.format("\ne. Venta con mayor ganancia: Lps. %.2f, Compra con mas gasto: Lps. %.2f", mayorGananciaVenta, mayorGastoCompra));

                if (mayorCantidad > 0) {
                    System.out.println(String.format("\nf. Producto Estrella: %s (%.2f kg)", productoEstrella, mayorCantidad));
                } else {
                    System.out.println("\nf. Aun no se ha vendido ningun producto.");
                }
            } else if (opcion == 5) { // cierre de caja
                if (!cajaAbierta) {
                    System.out.println("Debe abrir la caja primero.");
                    continue;
                }
                if (cajaAbierta) {
                    System.out.println("\n=== CIERRE DE CAJA ===");
                    System.out.println(String.format("Total de ganancia en caja: Lps. %.2f", efectivoEnCaja));

                    double maximoDeposito = efectivoEnCaja * 0.6;
                    System.out.println(String.format("Cantidad maxima a depositar en el banco (solo puede retirar el 60%%): Lps. %.2f", maximoDeposito));

                    double cantidadDeposito = 0;

                    while (true) {
                        System.out.print("Ingrese la cantidad a depositar (en numeros) en el banco: Lps. ");
                        cantidadDeposito = lea.nextDouble();

                        if (cantidadDeposito < 0) {
                            System.out.println("La cantidad no puede ser negativa.");
                        } else if (cantidadDeposito > maximoDeposito) {
                            System.out.println("La cantidad excede el maximo permitido para depositar.");
                        } else {
                            break;
                        }
                    }

                    efectivoEnCaja -= cantidadDeposito;
                    sistemaCerrado = true;
                    cajaAbierta = false;

                    System.out.println(String.format("Cantidad depositada: Lps. %.2f", cantidadDeposito));
                    System.out.println(String.format("Efectivo restante en caja para el siguiente dia: Lps. %.2f", efectivoEnCaja));
                    System.out.println("Caja cerrada.");
                }
            } else if (opcion == 6) { // salir del sistema
                break;
            } else {
                System.out.println("Opcion invalida. Debe ser un numero entre 1 y 6");
            }
        }
        System.out.println("fin del sistema");
    }
}
