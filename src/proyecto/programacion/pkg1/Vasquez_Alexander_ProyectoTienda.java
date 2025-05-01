/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.programacion.pkg1;

/**
 *
 * @author alexv
 */

import java.util.Scanner;
        
/**
 * Proyecto Programacion 1 - Caja Registradora
 * @author alexv
 */
public class Vasquez_Alexander_ProyectoTienda {
    public static void main(String[] args) {
        // estas son definiciones de constantes y variables
        Scanner lea = new Scanner(System.in);
        
        // precio de compra y venta de los producto
        // Azucar
        String descripcionProducto1 = "Azucar";
        double precioVentaProducto1 = 30;
        double precioCompraProducto1 = 25;
        
        // Avena
        String descripcionProducto2 = "Avena";
        double precioVentaProducto2 = 25;
        double precioCompraProducto2 = 20;
        
        // Trigo
        String descripcionProducto3 = "Trigo";
        double precioVentaProducto3 = 32;
        double precioCompraProducto3 = 30;
        
        // Maiz
        String descripcionProducto4 = "Maiz";
        double precioVentaProducto4 = 20;
        double precioCompraProducto4 = 18;
        
        // estas son las variables para el estado del sistema
        double efectivoEnCaja = 0;
        boolean cajaAbierta = false;
        boolean sistemaCerrado = false;
        
        // Contadores para reportes
        int numeroVentas = 0;
        int numeroCompras = 0;
        double volumenTotalVentas = 0;
        double volumenTotalCompras = 0;
        double mayorGananciaVenta = 0;
        double mayorGastoCompra = 0;
        
        // Contadores para el producto estrella     
        double cantidadVendidaAzucar = 0;
        double cantidadVendidaAvena = 0;
        double cantidadVendidaTrigo = 0;
        double cantidadVendidaMaiz = 0;
        
        // Valores para constantes
        double minSubtotalDesc5 = 1000;
        double minSubtotalDesc10 = 5000;
        double descuento5 = 0.05;
        double descuento10 = 0.1;
        double impuesto = 0.07;
        double maxPorcentajeDeposito = 0.6;
        
        // Inicio del sistema
        System.out.println("=== SISTEMA DE CAJA REGISTRADORA ===");
        System.out.println("LA TIENDA COMIENZA EL DIA CON CAJA A LPS. 0.00");
        
        int opcion = 0;
        do {
            // Menú principal
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Abrir Caja");
            System.out.println("2. Ventas");
            System.out.println("3. Compras");
            System.out.println("4. Reportes");
            System.out.println("5. Cierre de Caja");
            System.out.println("6. Salir del Sistema");
            System.out.print("Seleccione una opción: ");
            
            // Leer opción del usuario
            try {
                opcion = Integer.parseInt(lea.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0; // Opción inválida
            }
            
            // Procesar opción
            switch (opcion) {
                case 1: // Abrir Caja
                    if (sistemaCerrado) {
                        // Si el sistema estaba cerrado, reiniciamos los contadores
                        numeroVentas = 0;
                        numeroCompras = 0;
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
                        System.out.println("Caja abierta nuevamente para un nuevo día.");
                    } else {
                        // Si es la primera apertura o una adición de efectivo
                        System.out.print("Ingrese la cantidad de efectivo a agregar a la caja: Lps. ");
                        try {
                            double cantidad = Double.parseDouble(lea.nextLine());
                            if (cantidad >= 0) {
                                efectivoEnCaja += cantidad;
                                cajaAbierta = true;
                                System.out.println("Caja abierta con Lps. " + efectivoEnCaja);
                            } else {
                                System.out.println("No se puede agregar una cantidad negativa.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Cantidad inválida. La caja no se ha modificado.");
                        }
                    }
                    break;
                    
                case 2: // Ventas
                    if (cajaAbierta && !sistemaCerrado) {
                        System.out.print("Ingrese el tipo de cliente (A, B o C): ");
                        String tipoClienteStr = lea.nextLine().toUpperCase();
                        char tipoCliente = ' ';
                        
                        if (tipoClienteStr.length() > 0) {
                            tipoCliente = tipoClienteStr.charAt(0);
                        }
                        
                        if (tipoCliente != 'A' && tipoCliente != 'B' && tipoCliente != 'C') {
                            System.out.println("Tipo de cliente inválido.");
                        } else {
                            boolean continuarComprando = true;
                            double subtotalVenta = 0;
                            double gananciaVenta = 0;
                            
                            System.out.println("\n=== DETALLE DE COMPRA ===");
                            System.out.println("Kg\tProducto\tPrecio Unit.");
                            
                            while (continuarComprando) {
                                System.out.print("Ingrese el código del producto (1-4): ");
                                int codigoProducto;
                                try {
                                    codigoProducto = Integer.parseInt(lea.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Código inválido.");
                                    continue;
                                }
                                
                                if (codigoProducto < 1 || codigoProducto > 4) {
                                    System.out.println("Código de producto inválido.");
                                    continue;
                                }
                                
                                // Verificar si el cliente puede comprar el producto
                                boolean puedeComprar = false;
                                
                                if (tipoCliente == 'A') {
                                    puedeComprar = true; // Puede comprar cualquier producto
                                } else if (tipoCliente == 'B') {
                                    puedeComprar = (codigoProducto == 1 || codigoProducto == 2 || codigoProducto == 3);
                                } else if (tipoCliente == 'C') {
                                    puedeComprar = (codigoProducto == 4);
                                }
                                
                                if (!puedeComprar) {
                                    System.out.println("NO PUEDE COMPRAR DICHO PRODUCTO.");
                                } else {
                                    // Obtener información del producto según su código
                                    String descripcionProducto = "";
                                    double precioVenta = 0.0;
                                    double precioCompra = 0.0;
                                    
                                    if (codigoProducto == 1) {
                                        descripcionProducto = descripcionProducto1;
                                        precioVenta = precioVentaProducto1;
                                        precioCompra = precioCompraProducto1;
                                    } else if (codigoProducto == 2) {
                                        descripcionProducto = descripcionProducto2;
                                        precioVenta = precioVentaProducto2;
                                        precioCompra = precioCompraProducto2;
                                    } else if (codigoProducto == 3) {
                                        descripcionProducto = descripcionProducto3;
                                        precioVenta = precioVentaProducto3;
                                        precioCompra = precioCompraProducto3;
                                    } else if (codigoProducto == 4) {
                                        descripcionProducto = descripcionProducto4;
                                        precioVenta = precioVentaProducto4;
                                        precioCompra = precioCompraProducto4;
                                    }
                                    
                                    System.out.println("Producto: " + descripcionProducto + " - Precio: Lps. " + precioVenta + "/kg");
                                    
                                    System.out.print("Ingrese cantidad en kilogramos a comprar: ");
                                    double kilos;
                                    try {
                                        kilos = Double.parseDouble(lea.nextLine());
                                        if (kilos <= 0) {
                                            System.out.println("La cantidad debe ser positiva.");
                                            continue;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Cantidad inválida.");
                                        continue;
                                    }
                                    
                                    double subtotalProducto = kilos * precioVenta;
                                    double gananciaProducto = kilos * (precioVenta - precioCompra);
                                    
                                    subtotalVenta += subtotalProducto;
                                    gananciaVenta += gananciaProducto;
                                    
                                    // Actualizar contadores de productos vendidos
                                    if (codigoProducto == 1) {
                                        cantidadVendidaAzucar += kilos;
                                    } else if (codigoProducto == 2) {
                                        cantidadVendidaAvena += kilos;
                                    } else if (codigoProducto == 3) {
                                        cantidadVendidaTrigo += kilos;
                                    } else if (codigoProducto == 4) {
                                        cantidadVendidaMaiz += kilos;
                                    }
                                    
                                    System.out.printf("%.2f\t%s\t\tLps. %.2f\n", kilos, descripcionProducto, precioVenta);
                                }
                                
                                System.out.print("¿Desea comprar otro producto? (S/N): ");
                                String respuesta = lea.nextLine();
                                continuarComprando = respuesta.toUpperCase().startsWith("S");
                            }
                            
                            if (subtotalVenta > 0) {
                                // Calcular descuento
                                double descuento = 0;
                                if (subtotalVenta >= minSubtotalDesc10) {
                                    descuento = subtotalVenta * descuento10;
                                    System.out.println("Descuento aplicado: 10%");
                                } else if (subtotalVenta >= minSubtotalDesc5) {
                                    descuento = subtotalVenta * descuento5;
                                    System.out.println("Descuento aplicado: 5%");
                                } else {
                                    System.out.println("No aplica descuento");
                                }
                                
                                // Calcular impuesto y total
                                double impuestoCalculado = subtotalVenta * impuesto;
                                double totalVenta = subtotalVenta - descuento + impuestoCalculado;
                                
                                // Mostrar factura
                                System.out.println("\n=== FACTURA ===");
                                System.out.printf("Subtotal:\t\tLps. %.2f\n", subtotalVenta);
                                System.out.printf("Descuento:\t\tLps. %.2f\n", descuento);
                                System.out.printf("Impuesto (7%%):\tLps. %.2f\n", impuestoCalculado);
                                System.out.printf("Total a pagar:\tLps. %.2f\n", totalVenta);
                                
                                // Actualizar efectivo en caja y contadores
                                efectivoEnCaja += totalVenta;
                                numeroVentas++;
                                volumenTotalVentas += totalVenta;
                                
                                // Verificar si es la venta con mayor ganancia
                                if (gananciaVenta > mayorGananciaVenta) {
                                    mayorGananciaVenta = gananciaVenta;
                                }
                            }
                        }
                    } else {
                        System.out.println("La caja está cerrada. Primero debe abrir la caja.");
                    }
                    break;
                    
                case 3: // Compras
                    if (cajaAbierta && !sistemaCerrado) {
                        System.out.print("Ingrese el tipo de proveedor (A, B o C): ");
                        String tipoProveedorStr = lea.nextLine().toUpperCase();
                        char tipoProveedor = ' ';
                        
                        if (tipoProveedorStr.length() > 0) {
                            tipoProveedor = tipoProveedorStr.charAt(0);
                        }
                        
                        if (tipoProveedor != 'A' && tipoProveedor != 'B' && tipoProveedor != 'C') {
                            System.out.println("Tipo de proveedor inválido.");
                        } else {
                            System.out.print("Ingrese el código del producto a comprar (1-4): ");
                            int codigoProducto;
                            try {
                                codigoProducto = Integer.parseInt(lea.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Código inválido.");
                                break;
                            }
                            
                            if (codigoProducto < 1 || codigoProducto > 4) {
                                System.out.println("Código de producto inválido.");
                                break;
                            }
                            
                            // Verificar si el proveedor puede suplir el producto
                            boolean puedeSuplir = false;
                            
                            if (tipoProveedor == 'A') {
                                puedeSuplir = (codigoProducto == 1 || codigoProducto == 4);
                            } else if (tipoProveedor == 'B') {
                                puedeSuplir = (codigoProducto == 2 || codigoProducto == 3);
                            } else if (tipoProveedor == 'C') {
                                puedeSuplir = (codigoProducto == 2);
                            }
                            
                            if (!puedeSuplir) {
                                System.out.println("Proveedor no vende dicho Producto");
                            } else {
                                // Obtener información del producto según su código
                                String descripcionProducto = "";
                                double precioCompra = 0;
                                
                                if (codigoProducto == 1) {
                                    descripcionProducto = descripcionProducto1;
                                    precioCompra = precioCompraProducto1;
                                } else if (codigoProducto == 2) {
                                    descripcionProducto = descripcionProducto2;
                                    precioCompra = precioCompraProducto2;
                                } else if (codigoProducto == 3) {
                                    descripcionProducto = descripcionProducto3;
                                    precioCompra = precioCompraProducto3;
                                } else if (codigoProducto == 4) {
                                    descripcionProducto = descripcionProducto4;
                                    precioCompra = precioCompraProducto4;
                                }
                                
                                System.out.println("Producto: " + descripcionProducto + " - Precio compra: Lps. " + precioCompra + "/kg");
                                
                                System.out.print("Ingrese cantidad en kilogramos a comprar: ");
                                double kilos;
                                try {
                                    kilos = Double.parseDouble(lea.nextLine());
                                    if (kilos <= 0) {
                                        System.out.println("La cantidad debe ser positiva.");
                                        break;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Cantidad inválida.");
                                    break;
                                }
                                
                                double totalCompra = kilos * precioCompra;
                                
                                // Verificar si hay suficiente efectivo en caja
                                if (totalCompra > efectivoEnCaja) {
                                    System.out.println("No se Puede Pagar Compra");
                                } else {
                                    // Actualizar efectivo en caja y contadores
                                    efectivoEnCaja -= totalCompra;
                                    numeroCompras++;
                                    volumenTotalCompras += totalCompra;
                                    
                                    // Verificar si es la compra con mayor gasto
                                    if (totalCompra > mayorGastoCompra) {
                                        mayorGastoCompra = totalCompra;
                                    }
                                    
                                    System.out.printf("Compra realizada: %.2f kg de %s por Lps. %.2f\n", 
                                                     kilos, descripcionProducto, totalCompra);
                                    System.out.printf("Efectivo restante en caja: Lps. %.2f\n", efectivoEnCaja);
                                }
                            }
                        }
                    } else {
                        System.out.println("La caja está cerrada. Primero debe abrir la caja.");
                    }
                    break;
                    
                case 4: // Reportes
                    System.out.println("\n=== REPORTES ===");
                    System.out.printf("a. Cantidad Actual en Caja: Lps. %.2f\n", efectivoEnCaja);
                    System.out.printf("b. Número de Compras: %d, Número de Ventas: %d\n", numeroCompras, numeroVentas);
                    
                    double margenGanancia = volumenTotalVentas - volumenTotalCompras;
                    System.out.printf("c. Volumen total de Compras: Lps. %.2f, Volumen total de Ventas: Lps. %.2f\n", 
                                     volumenTotalCompras, volumenTotalVentas);
                    System.out.printf("   Margen de ganancia: Lps. %.2f\n", margenGanancia);
                    
                    double promedioCompra = (numeroCompras > 0) ? volumenTotalCompras / numeroCompras : 0;
                    double promedioVenta = (numeroVentas > 0) ? volumenTotalVentas / numeroVentas : 0;
                    System.out.printf("d. Valor Medio de Compra: Lps. %.2f, Valor Medio de Venta: Lps. %.2f\n", 
                                     promedioCompra, promedioVenta);
                    
                    System.out.printf("e. Venta con mayor ganancia: Lps. %.2f, Compra con más gasto: Lps. %.2f\n", 
                                     mayorGananciaVenta, mayorGastoCompra);
                    
                    // Determinar el producto estrella
                    String productoEstrella = "";
                    double mayorCantidad = 0;
                    
                    if (cantidadVendidaAzucar > mayorCantidad) {
                        mayorCantidad = cantidadVendidaAzucar;
                        productoEstrella = descripcionProducto1;
                    }
                    
                    if (cantidadVendidaAvena > mayorCantidad) {
                        mayorCantidad = cantidadVendidaAvena;
                        productoEstrella = descripcionProducto2;
                    }
                    
                    if (cantidadVendidaTrigo > mayorCantidad) {
                        mayorCantidad = cantidadVendidaTrigo;
                        productoEstrella = descripcionProducto3;
                    }
                    
                    if (cantidadVendidaMaiz > mayorCantidad) {
                        mayorCantidad = cantidadVendidaMaiz;
                        productoEstrella = descripcionProducto4;
                    }
                    
                    if (mayorCantidad > 0) {
                        System.out.printf("f. Producto Estrella: %s (%.2f kg)\n", productoEstrella, mayorCantidad);
                    } else {
                        System.out.println("f. Aún no hay ventas de productos.");
                    }
                    break;
                    
                case 5: // Cierre de Caja
                    if (cajaAbierta) {
                        System.out.println("\n=== CIERRE DE CAJA ===");
                        System.out.printf("Total de ganancia en caja: Lps. %.2f\n", efectivoEnCaja);
                        
                        double maximoDeposito = efectivoEnCaja * maxPorcentajeDeposito;
                        System.out.printf("Cantidad máxima a depositar en el banco (60%%): Lps. %.2f\n", maximoDeposito);
                        
                        double cantidadDeposito = -1;
                        boolean depositoValido = false;
                        
                        while (!depositoValido) {
                            System.out.print("Ingrese la cantidad a depositar en el banco: Lps. ");
                            try {
                                cantidadDeposito = Double.parseDouble(lea.nextLine());
                                
                                if (cantidadDeposito < 0) {
                                    System.out.println("La cantidad no puede ser negativa. Intente de nuevo.");
                                } else if (cantidadDeposito > maximoDeposito) {
                                    System.out.println("La cantidad excede el máximo permitido para depositar. Intente de nuevo.");
                                } else {
                                    depositoValido = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Cantidad inválida. Intente de nuevo.");
                            }
                        }
                        
                        // Procesar el depósito válido
                        efectivoEnCaja -= cantidadDeposito;
                        sistemaCerrado = true;
                        
                        System.out.printf("Cantidad depositada: Lps. %.2f\n", cantidadDeposito);
                        System.out.printf("Efectivo restante en caja para el siguiente día: Lps. %.2f\n", efectivoEnCaja);
                        System.out.println("Caja cerrada. Para realizar nuevas operaciones debe abrir la caja nuevamente.");
                        
                    } else {
                        System.out.println("La caja no está abierta. Primero debe abrir la caja.");
                    }
                    break;
                    
                case 6: // Salir del Sistema
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
        
        lea.close();
        System.out.println("¡Gracias por usar el sistema de caja registradora!");
    }
    hika
}   