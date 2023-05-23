package com.example.gappsoil_api;

import com.example.gappsoil_api.resourcesObjects.BenzineraRESOURCE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CSVreader extends  Thread{

    @Override
    public void run() {
        while (true) {
            try {
                leerINSERT();
                // Espera durante 24 horas
                System.out.println("esperant24h");
                Thread.sleep(24 * 60 * 60 * 1000);
                // Ejecuta el código nuevamente después de 24 horas
                leerINSERT();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void leerINSERT() {

        String csvFile = "./src\\main\\resources\\nombre_archivo.csv";
        List<BenzineraRESOURCE> benzineraRESOURCES = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            String[] fieldNames = null;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|\\|\\|");

                if (isFirstLine) {
                    fieldNames = fields;
                    isFirstLine = false;
                } else {
                    BenzineraRESOURCE b  = new BenzineraRESOURCE();
                    for (int i = 0; i < fields.length; i++) {
                        if (fieldNames[i].contains("Longitud")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setLongitude(mod2);
                        }
                        if (fieldNames[i].contains("Latitud")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setLatitude(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasolina 95 E5")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasolina951(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasolina 95 E10")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasolina952(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasolina 95 E5 Premium")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasolina953(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasolina 98 E5")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasolina981(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasolina 98 E10")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasolina982(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasóleo A")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasoil1(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasóleo Premium")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasoil2(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasóleo B")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasoil3(mod2);
                        }
                        if (fieldNames[i].contains("Precio gasóleo C")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGasoil4(mod2);
                        }
                        if (fieldNames[i].contains("Precio gases licuados del petróleo")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            b.setGlp(mod2);
                        }
                        if (fieldNames[i].contains("Precio gas natural licuado")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");


                            b.setGnl(mod2);
                        }
                        if (fieldNames[i].contains("Precio gas natural comprimido")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");



                            b.setGnc(mod2);
                        }
                        if (fieldNames[i].contains("Rótulo")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);

                            b.setNom(modifiedString);
                        }
                        if (fieldNames[i].contains("Toma de datos")){
                            String originalString = fields[i];
                            String modifiedString = originalString.substring(1, originalString.length() - 1);

                            b.setDia(modifiedString);
                        }


                        System.out.println(fieldNames[i] + ": " + fields[i]);
                    }
                    benzineraRESOURCES.add(b);
                    System.out.println();
                }
            }

            System.out.println(benzineraRESOURCES.size());
            System.out.println("FENT IMPORTS A OBJECTES");

            for (BenzineraRESOURCE b :
                    benzineraRESOURCES) {
                System.out.println("NOM BENZINERA: " + b.getNom() );
                System.out.println("preus benzina: ");
                System.out.println(b.getGasolina951());
                System.out.println(b.getGasolina951d());
                System.out.println(b.getGasolina952());
                System.out.println(b.getGasolina952d());
                System.out.println(b.getGasolina953());
                System.out.println(b.getGasolina953d());
                System.out.println(b.getGasolina981());
                System.out.println(b.getGasolina981d());
                System.out.println(b.getGasolina981d());
                System.out.println(b.getGasolina982());
                System.out.println(b.getGasolina982d());


                b.stringPricestoDouble();
                b.calculateLowest();
                b.convertLatLong();

                System.out.println(b.getLowestBenzina());
                System.out.println(b.getLowestGasoil());
                System.out.println(b.getGncd());
                System.out.println(b.getDia());
                System.out.println("-------------------------------------");
                System.out.println("-------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String jdbcUrl = "jdbc:postgresql://localhost:5432/nextstop"; // URL de conexión de la base de datos
        String username = "postgres"; // Nombre de usuario de la base de datos
        String password = "root"; // Contraseña de la base de datos

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            try {
                // Crear una declaración
                Statement statement = connection.createStatement();

                for (BenzineraRESOURCE b :
                        benzineraRESOURCES) {


                    String query = "SELECT BENZINERA_ID FROM BENZINERES WHERE LATITUDE = ? AND LONGITUDE = ?";
                    PreparedStatement queryst = connection.prepareStatement(query);

                    queryst.setDouble(1,b.getLatituded());
                    queryst.setDouble(2,b.getLongituded());

                    ResultSet resultSet = queryst.executeQuery();

                    // Procesar los resultados
                    while (resultSet.next()) {
                        long benzineresId = resultSet.getInt("BENZINERA_ID");
                        System.out.println(benzineresId);

                        String insert = "INSERT INTO PREUS (ultima_act, preuSP95, preuSP98, preu_gasoil, preuGLP, preuGNC, preuGNL,benzinera_id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insert);

                        insertStatement.setString(1, b.getDia());
                        insertStatement.setDouble(2, b.getLowestBenzina());
                        insertStatement.setDouble(3, b.getLowestBenzina());
                        insertStatement.setDouble(4, b.getLowestGasoil());
                        insertStatement.setDouble(5, b.getGlpd());
                        insertStatement.setDouble(6, b.getGncd());
                        insertStatement.setDouble(7, b.getGnld());
                        insertStatement.setLong(8,benzineresId);

                        System.out.println("update de la beniznera " + benzineraRESOURCES.indexOf(b));
                        insertStatement.executeUpdate();
                        insertStatement.close();
                    }
                    resultSet.close();

                }


                // Cierra el resultado y la declaración
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
