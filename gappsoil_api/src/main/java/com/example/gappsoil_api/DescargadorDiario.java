package com.example.gappsoil_api;

import com.example.gappsoil_api.resourcesObjects.BenzineraRESOURCE;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DescargadorDiario extends Thread {
    @Override
    public void run() {
        // Código existente de la clase

        while (true) {
            try {
                descargarYConvertir();
                leerINSERT();
                // Espera durante 24 horas
                System.out.println("esperant24h");
                Thread.sleep(24 * 60 * 60 * 1000);
                // Ejecuta el código nuevamente después de 24 horas
                descargarYConvertir();
                leerINSERT();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void descargarYConvertir() {

        String url = "https://geoportalgasolineras.es/geoportal/resources/files/preciosEESS_es.xls";
        String directorioDestino = "./src\\main\\resources";

        // Desactivar verificación del certificado SSL
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            } };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Descargar el archivo desde la URL
        try {
            URL archivoURL = new URL(url);
            try (InputStream in = archivoURL.openStream()) {
                Path archivoDestino = Path.of(directorioDestino, "nombre_archivo.xls"); // Reemplazar "nombre_archivo" con el nombre que deseas darle al archivo descargado
                Files.copy(in, archivoDestino, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Descarga completada: " + archivoDestino);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }





        String inputFilePath = "./src\\main\\resources\\nombre_archivo.xls";
        String outputFilePath = "./src\\main\\resources\\nombre_archivo.csv";
        List<String> validCities = Arrays.asList("BARCELONA", "TARRAGONA", "LLEIDA", "GIRONA");

        try {
            FileInputStream fis = new FileInputStream(inputFilePath);
            Workbook workbook = new HSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            FileWriter writer = new FileWriter(outputFilePath);

            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                Cell cityCell = row.getCell(0);

                if (i != 3 && !isValidCity(cityCell, validCities)) {
                    continue; // Salta la fila si no cumple con los criterios
                }

                for (Cell cell : row) {
                    String cellValue = getCellValueAsString(cell);

                    // Agregar comillas dobles alrededor del valor de la celda
                    writer.append("\"").append(cellValue).append("\"|||");
                }
                writer.append("\n");
            }

            writer.flush();
            writer.close();
            workbook.close();
            fis.close();

            System.out.println("La conversión de XLS a CSV se ha completado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void leerINSERT() {

        String csvFile = "./src\\main\\resources\\nombre_archivo.csv"; // Reemplaza con la ruta y nombre de tu archivo CSV
        List<BenzineraRESOURCE> benzineraRESOURCES = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            String[] fieldNames = null;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|\\|\\|"); // Separar los campos por el separador "|||"

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
                        if (fieldNames[i].equalsIgnoreCase("\"Precio gasolina 95 E5\"")){
                            System.out.println("afegint gasolina 95 e5");

                            String originalString = fields[i];

                            System.out.println("original : " + originalString);

                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");

                            System.out.println("modificat : " + mod2);

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
                            System.out.println("afegint preus gnc");

                            String originalString = fields[i];
                            System.out.println("String original " +originalString);

                            String modifiedString = originalString.substring(1, originalString.length() - 1);
                            String mod2 = modifiedString.replace(",",".");
                            System.out.println("String modificat " + mod2);



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


                        System.out.println(fieldNames[i] + ": " + fields[i]); // Imprimir el nombre del campo y su valor
                    }
                    benzineraRESOURCES.add(b);
                    System.out.println(); // Salto de línea entre cada gasolinera
                }
            }

            System.out.println(benzineraRESOURCES.size());

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

                System.out.println("latitude : " +b.getLatituded());
                System.out.println("Longitude : " +b.getLongitude());

                System.out.println("lowest benzina "+b.getLowestBenzina());
                System.out.println("lowest gasoil "+b.getLowestGasoil());
                System.out.println("lowest gnc "+b.getGncd());
                System.out.println("dia toma datos "+b.getDia());
                System.out.println("-------------------------------------");
                System.out.println("-------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String jdbcUrl = "jdbc:postgresql://localhost:5432/nextstop"; // URL de conexión de la base de datos
        String username = "postgres"; // Nombre de usuario de la base de datos (si es necesario)
        String password = "root"; // Contraseña de la base de datos (si es necesario)

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

            // Cierra la conexión cuando hayas terminado
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidCity(Cell cell, List<String> validCities) {
        String cellValue = getCellValueAsString(cell);
        return validCities.contains(cellValue);
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            return "";
        }
    }




}
