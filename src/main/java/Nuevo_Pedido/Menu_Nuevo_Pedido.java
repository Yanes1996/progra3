/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Nuevo_Pedido;
import VariablesGlobales.Listado_de_Productos;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;




/**
 *
 * @author IT
 */
public class Menu_Nuevo_Pedido extends javax.swing.JFrame {
    



    /**
     * Creates new form Menu_Nuevo_Pedido
     */
    public Menu_Nuevo_Pedido() {
        initComponents();
        this.setVisible(true);
        agregaritemcafe();
        agregaritempastel();
        generarYMostrarCodigoPedido();
        Fecha_Hora();
        modeloLista = new DefaultListModel<>();
        list_nombre.setModel(modeloLista);


    }

private Queue<Pedido> colaPedidos = new LinkedList<>();
private DefaultListModel<String> modeloLista = new DefaultListModel<>();


    
    
 ////////////////////////////////////////////////////////////////////////////////////////////INGRESO EL CODIGO GENERADO EN MI JLABEL   
   private void generarYMostrarCodigoPedido() {
        int codigo = GeneradorCodigoPedido.generarCodigoUnico();
        J_id.setText(String.valueOf(codigo)); // Mostramos el código en el JLabel
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////GENERO EL CODIGO ALEATORIO DE 8 DIGITOS COMO PARAMETRO

public class GeneradorCodigoPedido {
    private static final HashSet<Integer> codigosGenerados = new HashSet<>();
    private static final Random random = new Random();

    public static int generarCodigoUnico() {
        int codigo;
        do {
            codigo = 10000000 + random.nextInt(90000000);
        } while (codigosGenerados.contains(codigo));
        codigosGenerados.add(codigo);
        return codigo;
    }
}

  
        //////////////////////////////////////////////////////////////////////////////////////////////////////////CAPTURO LA FECHA Y HORA Y LA MOSTRAMOS Y GUARDAMOS
   
  private String fechaHoraPedido;

private void Fecha_Hora() {
    LocalDateTime ahora = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    fechaHoraPedido = ahora.format(formato);
    J_fechahora1.setText(fechaHoraPedido);
}

    
    
    
  /////////////////////////////////////////////////////////////////////////////////////////////////////AGREGO MIS ITEMS DE MI LISTADO A MI COMBOBOX  
    private void agregaritemcafe() {
        Listado_de_Productos productos = new Listado_de_Productos();

        CB_listadodecafes.addItem(productos.getNombre1() + " - Q" + productos.getPrecio1());
        CB_listadodecafes.addItem(productos.getNombre2() + " - Q" + productos.getPrecio2());
        CB_listadodecafes.addItem(productos.getNombre3() + " - Q" + productos.getPrecio3());
        CB_listadodecafes.addItem(productos.getNombre4() + " - Q" + productos.getPrecio4());
        CB_listadodecafes.addItem(productos.getNombre5() + " - Q" + productos.getPrecio5());
    }
    
    private void agregaritempastel() {
        Listado_de_Productos productos = new Listado_de_Productos();

        CB_listadodepasteles.addItem(productos.getNombre6() + " - Q" + productos.getPrecio6());
        CB_listadodepasteles.addItem(productos.getNombre7() + " - Q" + productos.getPrecio7());
        CB_listadodepasteles.addItem(productos.getNombre8() + " - Q" + productos.getPrecio8());
        CB_listadodepasteles.addItem(productos.getNombre9() + " - Q" + productos.getPrecio9());
        CB_listadodepasteles.addItem(productos.getNombre10() + " - Q" + productos.getPrecio10());
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////agregar items en listado
    
   private void agregarcafeALista() {
    // Obtener café seleccionado
    String cafeSeleccionado = (String) CB_listadodecafes.getSelectedItem();
    int cantidadCafe = (Integer) SP_cantidad.getValue();
    if (cantidadCafe > 0 && cafeSeleccionado != null) {
        modeloLista.addElement(cantidadCafe + " x " + cafeSeleccionado);
    }

    }

    private void agregarpastelALista() {
    // Obtener pastel seleccionado
    String pastelSeleccionado = (String) CB_listadodepasteles.getSelectedItem();
    int cantidadPastel = (Integer) jSpinner1.getValue();
    if (cantidadPastel > 0 && pastelSeleccionado != null) {
        modeloLista.addElement(cantidadPastel + " x " + pastelSeleccionado);
    }
}


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////CALCULO EL PRECIO Y LO MUESTRO 
   
 private void calcularTotalDesdeLista() {
    double total = 0.0;

    // Recorremos todos los elementos del JList
    for (int i = 0; i < modeloLista.getSize(); i++) {
        String item = modeloLista.getElementAt(i); // Ej: "2 x Café Americano - Q15"
        total += extraerSubtotal(item);
    }

    // Mostrar el total en el campo de texto
    txt_total.setText("Q" + String.format("%.2f", total));
}

// Método auxiliar para extraer el subtotal de cada línea
private double extraerSubtotal(String item) {
    try {
        // Separar por " x " para obtener cantidad y el resto
        String[] partes = item.split(" x ");
        int cantidad = Integer.parseInt(partes[0].trim());

        // Separar el nombre del producto y el precio
        String[] nombreYPrecio = partes[1].split(" - Q");
        double precio = Double.parseDouble(nombreYPrecio[1].trim());

        return cantidad * precio;
    } catch (Exception e) {
        return 0.0; // En caso de error, no suma nada
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ELIMINAR ITEM EN EL LISTADO

private void eliminarItemSeleccionado() {
    int indiceSeleccionado = list_nombre.getSelectedIndex(); // Obtener el índice seleccionado

    if (indiceSeleccionado != -1) { // -1 significa que no hay selección
        modeloLista.remove(indiceSeleccionado); // Eliminar del modelo
    } else {
        JOptionPane.showMessageDialog(null, "Por favor selecciona un producto para eliminar.");
    }
}

     /////////////////////////////////////////////////////////////////////////////////////////////////////////limpiar pantalla 
    
private void limpiarpantallaCompleto() {
    txt_nombrecliente.setText("");
    txt_total.setText("Q0.00");
    SP_cantidad.setValue(0);
    jSpinner1.setValue(0);
    CB_listadodecafes.setSelectedIndex(0);
    CB_listadodepasteles.setSelectedIndex(0);
    modeloLista.clear(); // ← Limpia el JList
    generarYMostrarCodigoPedido();
    Fecha_Hora();
    txt_nombrecliente.requestFocus();
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////LOGICA PARA CAPTURA DATOS y GUARDARLO EN COLA

   public class Pedido {
    private int id;
    private String fechaHora;
    private String nombreCliente;
    private String productos; // Todos los productos en un solo String
    private double total;

    public Pedido(int id, String fechaHora, String nombreCliente, String productos, double total) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.nombreCliente = nombreCliente;
        this.productos = productos;
        this.total = total;
    }

    @Override
    public String toString() {
        return "\nPedido #" + id + "\n\n - Cliente: " + nombreCliente + "\n\n Productos: \n" + productos + "\n\nTotal: Q." + total;
    }
}

    
 //////////////////////////////////////////////////////////////////////////////////////////////////////   

private void Finalizarpedido() {
    String nombreCliente = txt_nombrecliente.getText().trim();

    if (nombreCliente.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del cliente antes de finalizar el pedido.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Fecha_Hora(); // Actualiza fechaHoraPedido y el campo visual

    int id = Integer.parseInt(J_id.getText());

    StringBuilder productosBuilder = new StringBuilder();
    double total = 0.0;

    for (int i = 0; i < modeloLista.getSize(); i++) {
        String item = modeloLista.getElementAt(i);
        productosBuilder.append(item).append("\n");
        total += extraerSubtotal(item);
    }

    String productos = productosBuilder.toString().trim();

    Pedido nuevoPedido = new Pedido(id, fechaHoraPedido, nombreCliente, productos, total);
    colaPedidos.add(nuevoPedido);

    JOptionPane.showMessageDialog(this, "Pedido agregado a la cola:\n" +
        "Fecha y hora: " + fechaHoraPedido + "\n" +
        nuevoPedido.toString());

    limpiarpantallaCompleto();
    generarYMostrarCodigoPedido();
}



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        J_menu = new javax.swing.JLabel();
        J_tipodecafe = new javax.swing.JLabel();
        j_nuevopedido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        CB_listadodecafes = new javax.swing.JComboBox<>();
        CB_listadodepasteles = new javax.swing.JComboBox<>();
        SP_cantidad = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        B_finalizarpedido = new javax.swing.JButton();
        B_salir = new javax.swing.JButton();
        J_idpedido = new javax.swing.JLabel();
        J_nombrecliente = new javax.swing.JLabel();
        txt_nombrecliente = new javax.swing.JTextField();
        J_total = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        J_fechahora = new javax.swing.JLabel();
        J_fechahora1 = new javax.swing.JLabel();
        J_id = new javax.swing.JLabel();
        B_total = new javax.swing.JButton();
        J_limpiarpantalla = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_nombre = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        B_agregarpastel = new javax.swing.JButton();
        B_agregarcafe = new javax.swing.JButton();
        B_eliminaritem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 100));

        J_menu.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        J_menu.setText("Cafeteria Elizabeth");

        J_tipodecafe.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        J_tipodecafe.setText("LISTADO DE CAFE'S");

        j_nuevopedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        j_nuevopedido.setText("NUEVO PEDIDO");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("LISTADO DE PASTELES");

        CB_listadodecafes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CB_listadodecafes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_listadodecafesActionPerformed(evt);
            }
        });

        CB_listadodepasteles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        SP_cantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        B_finalizarpedido.setText("FINALIZAR PEDIDO");
        B_finalizarpedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_finalizarpedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_finalizarpedidoMouseClicked(evt);
            }
        });

        B_salir.setText("SALIR");
        B_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_salirMouseClicked(evt);
            }
        });

        J_idpedido.setText("ID PEDIDO");

        J_nombrecliente.setText("INGRESE NOMBRE DEL CLIENTE");

        txt_nombrecliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        J_total.setText("TOTAL:");

        J_fechahora.setText("Fecha y Hora");

        J_fechahora1.setText("F/H:");

        J_id.setText("ID");

        B_total.setText("TOTAL");
        B_total.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_totalMouseClicked(evt);
            }
        });
        B_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_totalActionPerformed(evt);
            }
        });

        J_limpiarpantalla.setText("Limpiar Pantalla");
        J_limpiarpantalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        J_limpiarpantalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                J_limpiarpantallaMouseClicked(evt);
            }
        });

        list_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(list_nombre);

        jLabel2.setText("Listado de productos");

        B_agregarpastel.setText("Agregar");
        B_agregarpastel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_agregarpastel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_agregarpastelMouseClicked(evt);
            }
        });

        B_agregarcafe.setText("Agregar");
        B_agregarcafe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_agregarcafe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_agregarcafeMouseClicked(evt);
            }
        });
        B_agregarcafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_agregarcafeActionPerformed(evt);
            }
        });

        B_eliminaritem.setText("Eliminar Item");
        B_eliminaritem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        B_eliminaritem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                B_eliminaritemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(J_idpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(J_id, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(213, 213, 213)
                .addComponent(J_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(B_eliminaritem, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(B_finalizarpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(B_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(J_total, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B_total, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(J_limpiarpantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(J_tipodecafe, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CB_listadodepasteles, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(B_agregarpastel)))
                        .addGap(32, 32, 32))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SP_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CB_listadodecafes, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_agregarcafe)))
                        .addGap(132, 673, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 905, Short.MAX_VALUE)
                                .addComponent(J_fechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(J_fechahora1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(J_nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(j_nuevopedido, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(467, 467, 467))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(J_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(j_nuevopedido)
                        .addGap(10, 10, 10)
                        .addComponent(J_fechahora)
                        .addGap(18, 18, 18)
                        .addComponent(J_fechahora1)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(J_idpedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(J_id, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(J_nombrecliente)
                        .addGap(18, 18, 18)
                        .addComponent(txt_nombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(J_tipodecafe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_listadodecafes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_listadodepasteles, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_agregarcafe)
                    .addComponent(B_agregarpastel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SP_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(J_total)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(J_limpiarpantalla))
                        .addGap(18, 18, 18)
                        .addComponent(B_total)
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(B_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(B_finalizarpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(B_eliminaritem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CB_listadodecafesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_listadodecafesActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_CB_listadodecafesActionPerformed

    private void B_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_totalActionPerformed

    private void B_totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_totalMouseClicked
        // TODO add your handling code here:
        calcularTotalDesdeLista();
    }//GEN-LAST:event_B_totalMouseClicked

    private void J_limpiarpantallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_J_limpiarpantallaMouseClicked
        // TODO add your handling code here:
        limpiarpantallaCompleto();
    }//GEN-LAST:event_J_limpiarpantallaMouseClicked

    private void B_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_salirMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_B_salirMouseClicked

    private void B_finalizarpedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_finalizarpedidoMouseClicked
        // TODO add your handling code here:
        Finalizarpedido();
       // Finalizarpedido();
    }//GEN-LAST:event_B_finalizarpedidoMouseClicked

    private void B_agregarcafeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_agregarcafeMouseClicked
        // TODO add your handling code here:
        agregarcafeALista();
    }//GEN-LAST:event_B_agregarcafeMouseClicked

    private void B_agregarcafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_agregarcafeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_agregarcafeActionPerformed

    private void B_agregarpastelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_agregarpastelMouseClicked
        // TODO add your handling code here:
        agregarpastelALista();
    }//GEN-LAST:event_B_agregarpastelMouseClicked

    private void B_eliminaritemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B_eliminaritemMouseClicked
        // TODO add your handling code here:
        eliminarItemSeleccionado();
    }//GEN-LAST:event_B_eliminaritemMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_agregarcafe;
    private javax.swing.JButton B_agregarpastel;
    private javax.swing.JButton B_eliminaritem;
    private javax.swing.JButton B_finalizarpedido;
    private javax.swing.JButton B_salir;
    private javax.swing.JButton B_total;
    private javax.swing.JComboBox<String> CB_listadodecafes;
    private javax.swing.JComboBox<String> CB_listadodepasteles;
    private javax.swing.JLabel J_fechahora;
    private javax.swing.JLabel J_fechahora1;
    private javax.swing.JLabel J_id;
    private javax.swing.JLabel J_idpedido;
    private javax.swing.JButton J_limpiarpantalla;
    private javax.swing.JLabel J_menu;
    private javax.swing.JLabel J_nombrecliente;
    private javax.swing.JLabel J_tipodecafe;
    private javax.swing.JLabel J_total;
    private javax.swing.JSpinner SP_cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel j_nuevopedido;
    private javax.swing.JList<String> list_nombre;
    private javax.swing.JTextField txt_nombrecliente;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}

