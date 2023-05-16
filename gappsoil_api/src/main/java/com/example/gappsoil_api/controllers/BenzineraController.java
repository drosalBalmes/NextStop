package com.example.gappsoil_api.controllers;

import com.example.gappsoil_api.DTOs.BenzineraDTOall;
import com.example.gappsoil_api.DTOs.BenzineraDTOnoPriceNoVal;
import com.example.gappsoil_api.entitats.Benzinera;
import com.example.gappsoil_api.objects.distances;
import com.example.gappsoil_api.repositories.BenzineraRepositori;
import com.example.gappsoil_api.repositories.PreuRepository;
import com.example.gappsoil_api.repositories.UsuarioRepository;
import com.example.gappsoil_api.repositories.ValoracioRepository;
import com.example.gappsoil_api.services.BenzineraService;
import com.example.gappsoil_api.services.PreuService;
import com.example.gappsoil_api.services.UsuarioService;
import com.example.gappsoil_api.services.ValoracioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("benzineres")
public class BenzineraController {
    @Autowired
    BenzineraRepositori benzineraRepositori;

    @Autowired
    PreuRepository preuRepository;

    @Autowired
    ValoracioRepository valoracioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    @Autowired
    BenzineraService benzineraService = new BenzineraService(benzineraRepositori);

    @Autowired
    PreuService preuService = new PreuService(preuRepository);

    @Autowired
    ValoracioService valoracioService = new ValoracioService(valoracioRepository);

    @Autowired
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);


    @GetMapping("/benzinerasNoPreusNoReviews")
    public List<BenzineraDTOnoPriceNoVal> llistatBenzineres(){
        List<Benzinera> benzineres =benzineraService.getAll();
        List<BenzineraDTOnoPriceNoVal> benzineresR = new ArrayList<>();



        for (Benzinera b: benzineres) {
            benzineresR.add( new BenzineraDTOnoPriceNoVal(
                    b.getId(),
                    b.getNom(),
                    b.getLatitude(),
                    b.getLongitude(),
                    b.isGasolina(),
                    b.isSP95(),
                    b.isSP98(),
                    b.isGNC(),
                    b.isGLP(),
                    b.isGNL(),
                    b.isGasoil(),
                    b.isAdblue(),
                    b.isHidrogen(),
                    b.getHorari()
            ));
        }
        return benzineresR;
    }







    @GetMapping("/benzFinder")
    public List<BenzineraDTOnoPriceNoVal> benzFinder(@RequestParam("locationLONG")double locationLNG,
                                                     @RequestParam("locationLAT")double locationLAT,
                                                     @RequestParam("KMredonda")double KMredonda,
                                                     @RequestParam("typeGAS") String typeGAS){

        List<Benzinera> benzineres = new ArrayList<>();

        if (typeGAS.equalsIgnoreCase("benzina")){
            benzineres =benzineraService.getGASOLINAbenz();

        }
        if (typeGAS.equalsIgnoreCase("gasoil")){
            benzineres =benzineraService.getGASOILbenz();

        }
        if (typeGAS.equalsIgnoreCase("gnc")){
            benzineres =benzineraService.getGNCbenz();

        }
        if (typeGAS.equalsIgnoreCase("glp")){
            benzineres =benzineraService.getGLPbenz();

        }
        if (typeGAS.equalsIgnoreCase("gnl")){
            benzineres =benzineraService.getGNLbenz();

        }




        List<BenzineraDTOnoPriceNoVal> benzineresR = new ArrayList<>();

        final double radiTerra = 6371.01;

        double radius = KMredonda; // en kilómetros

        double latRad = Math.toRadians(locationLAT);
        double lonRad = Math.toRadians(locationLNG);

        double deltaLon = Math.asin(Math.sin(radius / (2 * radiTerra)) / Math.cos(latRad));

        double minLat = latRad - deltaLon;
        double minLon = lonRad - Math.atan2(Math.sin(2 * deltaLon), Math.cos(minLat) * Math.cos(latRad));

        double maxLat = latRad + deltaLon;
        double maxLon = lonRad + Math.atan2(Math.sin(2 * deltaLon), Math.cos(maxLat) * Math.cos(latRad));

        double minLatDeg = Math.toDegrees(minLat);
        double minLonDeg = Math.toDegrees(minLon);
        double maxLatDeg = Math.toDegrees(maxLat);
        double maxLonDeg = Math.toDegrees(maxLon);






        for (Benzinera b: benzineres) {
            benzineresR.add( new BenzineraDTOnoPriceNoVal(
                    b.getId(),
                    b.getNom(),
                    b.getLatitude(),
                    b.getLongitude(),
                    b.isGasolina(),
                    b.isSP95(),
                    b.isSP98(),
                    b.isGNC(),
                    b.isGLP(),
                    b.isGNL(),
                    b.isGasoil(),
                    b.isAdblue(),
                    b.isHidrogen(),
                    b.getHorari()
            ));
        }

        Iterator<BenzineraDTOnoPriceNoVal> iterator = benzineresR.iterator();

        while (iterator.hasNext()) {
            BenzineraDTOnoPriceNoVal bb = iterator.next();
            if (bb.getLatitude()<minLatDeg || bb.getLatitude()>maxLatDeg || bb.getLongitude()<minLonDeg || bb.getLongitude()>maxLonDeg){
                iterator.remove();
            }
        }

        return benzineresR;
    }


    @GetMapping("/closest")
    public List<BenzineraDTOnoPriceNoVal> benz10closest(@RequestParam("locationLONG")double locationLNG,
                                                        @RequestParam("locationLAT")double locationLAT,
                                                        @RequestParam("num")int num,
                                                        @RequestParam("typeGAS") String typeGAS){

        List<Benzinera> benzineras = new ArrayList<>();

        if (typeGAS.equalsIgnoreCase("benzina")){
            benzineras =benzineraService.getGASOLINAbenz();

        }
        if (typeGAS.equalsIgnoreCase("gasoil")){
            benzineras =benzineraService.getGASOILbenz();

        }
        if (typeGAS.equalsIgnoreCase("gnc")){
            benzineras =benzineraService.getGNCbenz();

        }
        if (typeGAS.equalsIgnoreCase("glp")){
            benzineras =benzineraService.getGLPbenz();

        }
        if (typeGAS.equalsIgnoreCase("gnl")){
            benzineras =benzineraService.getGNLbenz();

        }
    List<BenzineraDTOnoPriceNoVal> returnList = new ArrayList<>();
    List <distances> distancias = new ArrayList<>();

        for (Benzinera b :
                benzineras) {
            distancias.add(new distances(
                    b.getId(),
                    calcularDistancia(b,locationLAT,locationLNG)
                    ));
        }

        Collections.sort(distancias, Comparator.comparingDouble(distances::getDistance));

        for (int i = 0; i < num; i++) {
            distances d = distancias.get(i);

            Benzinera b = benzineraService.getBenzineraById(d.getRepoId());
            returnList.add( new BenzineraDTOnoPriceNoVal(
                    b.getId(),
                    b.getNom(),
                    b.getLatitude(),
                    b.getLongitude(),
                    b.isGasolina(),
                    b.isSP95(),
                    b.isSP98(),
                    b.isGNC(),
                    b.isGLP(),
                    b.isGNL(),
                    b.isGasoil(),
                    b.isAdblue(),
                    b.isHidrogen(),
                    b.getHorari()
            ));
        }

        return returnList;

    }

//    @GetMapping("/closestType")
//    public List<BenzineraDTOnoPriceNoVal> benz10closestType(@RequestParam("locationLONG")double locationLNG,
//                                                        @RequestParam("locationLAT")double locationLAT,
//                                                        @RequestParam("num")int num,
//                                                        @RequestParam("type")String type){
//        boolean gnc=false;
//        boolean gnl=false;
//        boolean glp=false;
//        boolean diesel=false;
//        boolean benzina=false;
//
//        if (type.equalsIgnoreCase("GNC")){
//            gnc = true;
//        }
//
//        if (type.equalsIgnoreCase("GNL")){
//            gnl = true;
//        }
//
//        if (type.equalsIgnoreCase("GLP")){
//            glp = true;
//        }
//
//        if (type.equalsIgnoreCase("Benzina")){
//            benzina = true;
//        }
//
//        if (type.equalsIgnoreCase("Gasoil/Diesel")){
//            diesel = true;
//        }
//
//        List<Benzinera> benzineras = benzineraService.getAll();
//        List<BenzineraDTOnoPriceNoVal> returnList = new ArrayList<>();
//        List <distances> distancias = new ArrayList<>();
//
//        for (Benzinera b :
//                benzineras) {
//            distancias.add(new distances(
//                    b.getId(),
//                    calcularDistancia(b,locationLAT,locationLNG)
//            ));
//        }
//
//        Collections.sort(distancias, Comparator.comparingDouble(distances::getDistance));
//
//        for (int i = 0; i < num; i++) {
//            distances d = distancias.get(i);
//
//            Benzinera b = benzineraService.getBenzineraById(d.getRepoId());
//            returnList.add( new BenzineraDTOnoPriceNoVal(
//                    b.getId(),
//                    b.getNom(),
//                    b.getLatitude(),
//                    b.getLongitude(),
//                    b.isGasolina(),
//                    b.isSP95(),
//                    b.isSP98(),
//                    b.isGNC(),
//                    b.isGLP(),
//                    b.isGNL(),
//                    b.isGasoil(),
//                    b.isAdblue(),
//                    b.isHidrogen(),
//                    b.getHorari()
//            ));
//        }
//
//        return returnList;
//
//    }



    public static double calcularDistancia(Benzinera bb, double lat, double lng) {
        double dx = bb.getLatitude() - lat;
        double dy = bb.getLongitude() - lng;
        return Math.sqrt(dx * dx + dy * dy);
    }


}
