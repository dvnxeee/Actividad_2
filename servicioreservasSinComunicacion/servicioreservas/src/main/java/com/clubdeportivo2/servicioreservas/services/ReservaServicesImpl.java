package com.clubdeportivo2.servicioreservas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.clubdeportivo2.servicioreservas.client.CanchaClient;
import com.clubdeportivo2.servicioreservas.model.Reserva;

import com.clubdeportivo2.servicioreservas.repository.ReservaRepository;
import java.util.List;

@Service
public class ReservaServicesImpl implements ReservaServices {

    @Autowired
    private CanchaClient canchaClient;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    /*
     * @Override
     * public Reserva crearReserva(Reserva reserva) {
     * return reservaRepository.save(reserva);
     * }
     */

    @Override
    public Reserva crearReserva(Reserva reserva) {

        try {
            canchaClient.obtenerCancha(reserva.getCanchaId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La cancha no existe");
        }

        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> buscarReservasPorCancha(Long canchaId) {
        return reservaRepository.findByCanchaId(canchaId);
    }

}
