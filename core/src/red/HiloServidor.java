package red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import pantallas.PantallaJuego;
import utiles.Constantes;

public class HiloServidor extends Thread {
	private DatagramSocket conexion;
	private boolean fin = false;
	private int cantClientes = 0;
	private DireccionRed[] clientes = new DireccionRed[2];
	private PantallaJuego app;
	public HiloServidor(PantallaJuego app) {
		this.app = app;
		try {
			conexion = new DatagramSocket(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String mensaje,InetAddress ip,int puerto) {
		byte[] data = mensaje.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length,ip,puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				conexion.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			procesarMensaje(dp);
		} while (!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msj = (new String(dp.getData())).trim();
		int nroCliente = -1;
		if(cantClientes > 1) {
			for (int i = 0; i < clientes.length; i++) {
				if(dp.getPort() == clientes[i].getPuerto() && dp.getAddress().equals(clientes[i].getIp())) {
					nroCliente = i;
				}
			}
		}
		if (cantClientes < 2) {
			if (msj.equals("Conexion")) {
				clientes[cantClientes] = new DireccionRed(dp.getAddress(), dp.getPort());
				enviarMensaje("OK", clientes[cantClientes].getIp(), clientes[cantClientes++].getPuerto());
				if (cantClientes == 2) {
					for (int i = 0; i < clientes.length; i++) {
						enviarMensaje("Empieza", clientes[i].getIp(), clientes[i].getPuerto());
						enviarMensaje("Cliente*" + i,clientes[i].getIp(),clientes[i].getPuerto());
					}
					Constantes.empiezaPartida = true;
				}
			}
		} else {
			if (nroCliente != -1) {
				if (msj.equals("ApreteSaltar")) {
					app.getTeclasClientes()[nroCliente][0] = 1;
				} else if (msj.equals("ApreteDerecha")) {
					app.getTeclasClientes()[nroCliente][1] = 1;
				} else if (msj.equals("ApreteIzquierda")) {
					app.getTeclasClientes()[nroCliente][2] = 1;
				} else if (msj.equals("ApreteArriba")) {
					app.getTeclasClientes()[nroCliente][3] = 1;
				} else if (msj.equals("ApreteDisparar")) {
					app.getTeclasClientes()[nroCliente][4] = 1;
				} else if (msj.equals("ApretePowerUp")) {
					app.getTeclasClientes()[nroCliente][5] = 1;
				} else if (msj.equals("DejeApretarSaltar")) {
					app.getTeclasClientes()[nroCliente][0] = 0;
				} else if (msj.equals("DejeApretarDerecha")) {
					app.getTeclasClientes()[nroCliente][1] = 0;
				} else if (msj.equals("DejeApretarIzquierda")) {
					app.getTeclasClientes()[nroCliente][2] = 0;
				} else if (msj.equals("DejeApretarArriba")) {
					app.getTeclasClientes()[nroCliente][3] = 0;
				} else if (msj.equals("DejeApretarDisparar")) {
					app.getTeclasClientes()[nroCliente][4] = 0;
				} else if (msj.equals("DejeApretarPowerUp")) {
					app.getTeclasClientes()[nroCliente][5] = 0;
				} 
			}
		}
	}

	public void enviarMensajeATodos(String mensaje) {
		for (int i = 0; i < clientes.length; i++) {
			enviarMensaje(mensaje, clientes[i].getIp(), clientes[i].getPuerto());
		}
	}
	
	public void enviarMensaje(String mensaje,int nroCliente) {
		enviarMensaje(mensaje,clientes[nroCliente].getIp(),clientes[nroCliente].getPuerto());
	}
}
