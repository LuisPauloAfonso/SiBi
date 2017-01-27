/*
 * Trabalhodesenvolvido para disciplina de ISS - 2016
 */
package controller;

import modelo.Reserva;

/**
 *
 * @author Gabriel
 */
public class ReservaController {
    public void enviarReserva(Reserva reserva) {
        reserva.realizarReserva(reserva);
    }
    
    public boolean buscarReserva(int idMaterial, int idUsuario) {
        Reserva reserva = new Reserva();
        return reserva.verificarReserva(idMaterial, idUsuario);
    }
}
