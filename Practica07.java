import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

class Practica07{
    public static void main(String[] args){

        ICentro principal = new ICentro();
    }
}

class ICentro extends JFrame implements ActionListener{
    String desc; 
    int ct;
    float mu;
    String dt, nf, ci;

    JLabel datos = new JLabel("Ingrese los datos del equipo:");
    JLabel description = new JLabel("Descripcion: ");
    JTextField descriptionText = new JTextField(30);
    JLabel cantidad = new JLabel("Cantidad:");
    JTextField cantidadText = new JTextField(10);
    JLabel costo = new JLabel("Costo Unitario (Bs.):");
    JTextField costoText = new JTextField(10);
    JLabel fecha = new JLabel("Fecha de adquisicion:");
    JLabel fechaFormato = new JLabel("dd/mm/aaaa");
    JTextField fechaText = new JTextField(10);
    JLabel factura = new JLabel("Nro. de Factura:");
    JTextField facturaText = new JTextField(20);
    JLabel cedula = new JLabel("C.I. del Responsable del equipo:");
    JTextField cedulaText = new JTextField(15);
    JButton data = new JButton("Registrar data");
    JButton reporte = new JButton("Generar reporte");
    JButton salir = new JButton("Salir"); 

    ICentro(){
        super("Registro y Control de Equipos en Centros de Investigacion");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        //Añadir los elementos a la pantalla
        add(datos);
        add(description);
        add(descriptionText);
        add(cantidad);
        add(cantidadText);
        add(costo);
        add(costoText);
        add(fecha);
        add(fechaFormato);
        add(fechaText);
        add(factura);
        add(facturaText);
        add(cedula);
        add(cedulaText);
        add(data);
        add(reporte);
        add(salir);

        //Añadir listener a los botones
        data.addActionListener(this);
        reporte.addActionListener(this);
        salir.addActionListener(this);

        //Ordenar los elementos en pantalla
        datos.setBounds(10, 10, 500,20);
        description.setBounds(10, 50, 500,20);
        descriptionText.setBounds(90, 50, 480,20);
        cantidad.setBounds(10, 90, 480,20);
        cantidadText.setBounds(90, 90, 100,20);
        costo.setBounds(300, 90, 480,20);
        costoText.setBounds(420, 90, 150,20);
        fecha.setBounds(10, 130, 480,20);
        fechaFormato.setBounds(10, 150, 100,20);
        fechaText.setBounds(140, 130, 100,20);
        factura.setBounds(320, 130, 480,20);
        facturaText.setBounds(420, 130, 150,20);
        cedula.setBounds(10, 190, 480,20);
        cedulaText.setBounds(200, 190, 100,20);
        data.setBounds(195, 300, 120,20);
        reporte.setBounds(320, 300, 125,20);
        salir.setBounds(450, 300, 125, 20);

        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent a){
        JButton boton = (JButton) a.getSource();
    
        if(boton == data){
            desc = descriptionText.getText();
            ct = Integer.parseInt(cantidadText.getText());
            mu = Float.parseFloat(costoText.getText());
            dt = fechaText.getText();
            nf = facturaText.getText(); 
            ci = cedulaText.getText();

            try{
                Path filePath = Paths.get("Practica07.txt");
                OutputStream output = new BufferedOutputStream(Files.newOutputStream(filePath, APPEND));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

                writer.write(desc + "#" + ct + "#" + mu + "#" + dt + "#" + nf + "#" + ci +";\n");
                writer.close();
                output.close();
            }
            catch(Exception e){
                System.out.println("ERROR: " + e);
            }
        }
        else if(boton == reporte){
            setVisible(false);
            IReporte reporte = new IReporte();
        }
        else if(boton == salir){
            dispose();
        }
    }
}

    class IReporte extends JFrame implements ActionListener{

    JLabel tipoReporte = new JLabel("Tipo reporte:");
    JCheckBox individual = new JCheckBox("Individual");
    JCheckBox general = new JCheckBox("General");
    JLabel cedulaReporte = new JLabel("C.I. del Responsable de equipos:");
    JTextField cedulaReporteText = new JTextField(15);
    JButton totalizar = new JButton("Totalizar");
    JLabel totalizacion = new JLabel("Totalizacion:");
    JLabel numeroEquipos = new JLabel("___equipos");
    JLabel bolivares = new JLabel("___Bs.");
    JButton continuar = new JButton("Continuar");
    JTextArea textos = new JTextArea();
    JScrollPane tabla = new JScrollPane(textos);

    IReporte(){
        super("Registro y Control de Equipos en Centros de Investigacion");
        setSize(600, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        add(tabla);
        add(tipoReporte);
        add(individual);
        add(general);
        add(cedulaReporte);
        add(cedulaReporteText);
        add(totalizar);
        add(totalizacion);
        add(numeroEquipos);
        add(bolivares);
        add(continuar);

        individual.addActionListener(this);
        general.addActionListener(this);
        totalizar.addActionListener(new accionBotones());
        continuar.addActionListener(new accionBotones());

        tipoReporte.setBounds(10, 10, 200, 20);
        individual.setBounds(100, 10, 100, 20);
        general.setBounds(210, 10, 200, 20);
        totalizacion.setBounds(10, 260, 200, 20);
        numeroEquipos.setBounds(30, 280, 100, 20);
        bolivares.setBounds(30, 300, 200, 20);
        continuar.setBounds(450, 300, 100, 20);

        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent a){
        //JButton boton = (JButton) a.getSource();
        JCheckBox box = (JCheckBox) a.getSource();
        int cantidadEquipos = 0;
        float costoTotal = 0f;

        if(box.isSelected()){
            if(box == individual){
                general.setSelected(false);
                cedulaReporte.setBounds(10, 50, 600, 20);
                cedulaReporteText.setBounds(200, 50, 100, 20);
                totalizar.setBounds(305, 50, 100, 20);
                tabla.setBounds(0, 0, 0, 0);                
            }
            else if(box == general){
                individual.setSelected(false);
                cedulaReporte.setBounds(0, 0, 0, 0);
                cedulaReporteText.setBounds(0, 0, 0, 0);
                totalizar.setBounds(0, 0, 0, 0);
                textos.setText("C.I. Responsable                                            Cantidad equipos                                              Monto Total (Bs.)\n"); 
                tabla.setBounds(10, 80, 565, 100);
                LinkedList<Empleado> empleados = new LinkedList<Empleado>();
                String lecture;
                boolean isInList = false;
                try{
                    Path filePath = Paths.get("Practica07.txt");
                    InputStream input = new BufferedInputStream(Files.newInputStream(filePath));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    lecture = reader.readLine();                    
                    while(lecture != null){
                        String[] lectureArray = lecture.split("#");
                        String[] cedulaArray = lectureArray[5].split(";");
                        for(int i = 0; i < empleados.size(); i++){
                            if(cedulaArray[0].equals(empleados.get(i).cedula)){
                                isInList = true;
                                empleados.get(i).cantidadEquipos += Integer.parseInt(lectureArray[1]);
                                empleados.get(i).costoTotal += Float.parseFloat(lectureArray[2]) * Integer.parseInt(lectureArray[1]);
                            }
                        }
                        
                        lecture = reader.readLine();
                        if(isInList){
                            System.out.println("HOLA");
                            isInList = false;
                            continue;
                        }
                        else{
                            empleados.add(new Empleado(cedulaArray[0], Integer.parseInt(lectureArray[1]), Integer.parseInt(lectureArray[1]) * Float.parseFloat(lectureArray[2])));
                        }
                    }

                    for(int i = 0; i < empleados.size(); i++){
                        textos.append(empleados.get(i).cedula + "                                                        " + empleados.get(i).cantidadEquipos + "                                                                            " + empleados.get(i).costoTotal + "\n");
                    }

                    for(int i = 0; i < empleados.size(); i++){
                        cantidadEquipos += empleados.get(i).cantidadEquipos;
                        costoTotal += empleados.get(i).costoTotal;
                    }
                    numeroEquipos.setText(cantidadEquipos + " equipos");
                    bolivares.setText(costoTotal + " Bs.");
                    input.close();
                    reader.close();
                }catch(Exception e){
                    System.out.println("ERROR:" + e);
                }
            }
        }
   }
   class accionBotones implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            JButton boton = (JButton) a.getSource();
            int equipos = 0;
            float dinero = 0f;
            if(boton == totalizar){
                String lecture;
                try{
                    Path filePath = Paths.get("Practica07.txt");
                    InputStream input = new BufferedInputStream(Files.newInputStream(filePath));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    lecture = reader.readLine();                    
                    while(lecture != null){
                        String[] lectureArray = lecture.split("#");
                        String[] cedulaArray = lectureArray[5].split(";");
                        if(cedulaReporteText.getText().equals(cedulaArray[0])){
                            equipos += Integer.parseInt(lectureArray[1]);
                            dinero += Float.parseFloat(lectureArray[2]) * Integer.parseInt(lectureArray[1]);
                        }
                        lecture = reader.readLine();
                    }
                    numeroEquipos.setText(equipos + " equipos");
                    bolivares.setText(dinero + " Bs.");
                    input.close();
                    reader.close();
                }catch(Exception e){
                    System.out.println("ERROR:" + e);
                }
            }
            else if(boton == continuar){
                setVisible(false);
                ICentro principal = new ICentro();
            }        
        }
   }
}

class Empleado{
    public String cedula;
    public int cantidadEquipos = 0;
    public float costoTotal = 0; 

    Empleado(String cedulaEmpleado, int cantidad, float costo){
        cedula = cedulaEmpleado;
        cantidadEquipos += cantidad;
        costoTotal += costo;
    }
}