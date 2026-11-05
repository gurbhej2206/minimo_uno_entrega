package edu.upc.dsa.services;

import edu.upc.dsa.repository.VuelosManager;
import edu.upc.dsa.repository.VuelosManagerImpl;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import edu.upc.dsa.models.Reserva;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/vuelos", description = "Endpoint to Manage Vuelos")
@Path("/vuelos")
public class VuelosService {
    
    private VuelosManager vm;

    public VuelosService() {
        this.vm = VuelosManagerImpl.getInstance();
        if(vm.getNum() == 0) {

            Piloto p1 = vm.addPiloto("P1", "Name1", "Apellido1");
            p1.setHorasVuelo(1);
            Piloto p2 = vm.addPiloto("P2", "Name2", "Apellido2");
            p2.setHorasVuelo(2);
            Piloto p3 = vm.addPiloto("P3", "Name3", "Apellido3");
            p3.setHorasVuelo(3);
            Piloto p4 = vm.addPiloto("P4", "Name4", "Apellido4");
            p4.setHorasVuelo(4);


            Dron d1 = vm.addDron("D1", "Dron1", "fabr1", "mod1");
            d1.setHorasVuelo(1);
            Dron d2 = vm.addDron("D2", "Dron2", "fabr2", "mod2");
            d2.setHorasVuelo(2);
            Dron d3 = vm.addDron("D3", "Dron3", "fabr3", "mod3");
            d3.setHorasVuelo(3);
            Dron d4 = vm.addDron("D4", "Dron4", "fabr4", "mod4");
            d4.setHorasVuelo(4);
        }
    }

    @GET
    @ApiOperation(value = "Get Pilotos sorted by horasVuelo", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Piloto.class, responseContainer="List"),
    })
    @Path("/pilotosByHorasVuelo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdVuelosHorasVuelo() {
        List<Piloto> pilotoList = vm.ordPilotosHorasVuelo();

        GenericEntity<List<Piloto>> entity = new GenericEntity<List<Piloto>>(pilotoList) {};
        return Response.status(200).entity(entity).build()  ;
    }


    @GET
    @ApiOperation(value = "Get Drones sorted by horasVuelo", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Dron.class, responseContainer="List"),
    })
    @Path("/dronesByHorasVuelo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdDronesHorasVuelo() {
        List<Dron> dronesList = vm.ordDronesHorasVuelo();

        GenericEntity<List<Dron>> entity = new GenericEntity<List<Dron>>(dronesList) {};
        return Response.status(200).entity(entity).build()  ;
    }


    @POST
    @ApiOperation(value = "Add a pilot", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pilot added"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addPilot/{idPiloto}/{nombre}/{apellidos}")
    public Response addPiloto(@PathParam("idPiloto") String idPiloto, @PathParam("nombre") String nombre, @PathParam("apellidos") String apellidos) {
        Piloto newPiloto = vm.addPiloto(idPiloto, nombre, apellidos);
        return Response.status(201).entity(newPiloto).build();
    }



    @POST
    @ApiOperation(value = "Add a dron", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "dron added"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addDron/{idDron}/{nombre}/{fabricante}/{modelo}")
    public Response addDron(@PathParam("idDron") String idDron, @PathParam("nombre") String nombre, @PathParam("fabricante") String fabricante, @PathParam("modelo") String modelo)  {
        Dron newDron = vm.addDron(idDron, nombre, fabricante, modelo);
        return Response.status(201).entity(newDron).build();
    }

}
