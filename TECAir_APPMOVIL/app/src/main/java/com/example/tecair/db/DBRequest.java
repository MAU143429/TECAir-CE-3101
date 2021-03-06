package com.example.tecair.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tecair.db.entities.Usuario;
import com.example.tecair.db.entities.Vuelo;

import java.util.ArrayList;

public class DBRequest extends DBHelper{
    Context context;

    int idUsuario;

    public DBRequest(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Obtiene un usuario de la tabla, seleccionando por el correo
     * @return true o false, dependiendo de si coincide la contrasena y correo del usuario
     */
    public boolean verificarUsuario(String uCorreo, String uContrasena){
        // Se lee la base de datos
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Se crea una instancia de cursor, ya que es lo que devuelve la consulta a la base
        Cursor cursorUsuario;

        //----Consulta a la base de datos---//
        // Columnas que se van a seleccionar
        String fromColumns[] = {CORREO, U_CONTRASENA};
        cursorUsuario = db.rawQuery("SELECT correo AND u_contrasena " + "FROM " + TABLE_USUARIO
                + " WHERE correo = '" + uCorreo + "' AND u_contrasena = '" + uContrasena + "' ", null);
        if(cursorUsuario != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Obtener un vuelo para mostrarlo en la lista de vuelos disponibles
     * @return Lista de vuelos de la base de datos
     */
    public ArrayList<Vuelo> getVuelos(){
        // Se lee la base de datos
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Vuelo> listaVuelos = new ArrayList<>();
        Vuelo vuelo;
        Cursor cursorVuelos;

        // consulta a la base de datos
        cursorVuelos = db.rawQuery("SELECT no_vuelo, origen, destino, prt_abordaje, " +
                 "h_salida, h_llegada, v_dia, v_mes, v_ano, coste_vuelo, matricula " + "FROM " +
                 TABLE_VUELO, null);

        if (cursorVuelos.moveToFirst()) {
            do {
                vuelo = new Vuelo();
                vuelo.setNo_vuelo(cursorVuelos.getInt(0));
                vuelo.setOrigen(cursorVuelos.getString(1));
                vuelo.setDestino(cursorVuelos.getString(2));
                vuelo.setPrt_abordaje(cursorVuelos.getString(3));
                vuelo.setH_salida(cursorVuelos.getString(4));
                vuelo.setH_llegada(cursorVuelos.getString(5));
                vuelo.setV_dia(cursorVuelos.getString(6));
                vuelo.setV_mes(cursorVuelos.getString(7));
                vuelo.setV_ano(cursorVuelos.getString(8));
                vuelo.setCoste_vuelo(cursorVuelos.getInt(9));
                vuelo.setMatricula(cursorVuelos.getInt(11));
                listaVuelos.add(vuelo);
            } while (cursorVuelos.moveToNext());
        }

        cursorVuelos.close();

        return listaVuelos;
    }
    //bases de datos movil:
    // tabla usuario:
        // insertar usuario
        // (LISTO)buscar usuario por correo y contrasena
        // enviar datos del registro a tabla de usuario, cuando me hagan un registro
        // tengo que generar un id_usuario automatico
        // obtener datos de un usuario para el login

    public long insertarUsuario(String nombre, String apellido1, String apellido2, String correo, String contrasena, int telefono) {

        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursorid;

            ContentValues values = new ContentValues();
            cursorid = db.rawQuery("SELECT COUNT (id_usuario) FROM usuario", null);
            cursorid.moveToFirst();
            idUsuario = cursorid.getInt(0);
            cursorid.close();

            idUsuario++;
            values.put("id_usuario", idUsuario);
            values.put("u_nombre", nombre);
            values.put("u_apellido1", apellido1);
            values.put("u_apellido2", apellido2);
            values.put("correo", correo);
            values.put("u_contrasena", contrasena);
            values.put("telefono", telefono);

            id = db.insert(TABLE_USUARIO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    // tabla vuelo:
        // insertar vuelo
        // obtener datos de vuelo para mostrar en la seccion de elegir vuelo


    //- tabla reservacion:
        // Cuando elijo un vuelo y le doy reservar, tengo que ir a guardar a la tabla:
        // un id reservacion que quiero agregar automatico, cancelado en false, obtener id_usuario
        // de la tabla de usuario y un numero de vuelo que obtengo de tabla vuelo
        // esta reservacion hay que agregarla en el carrito
        // tal vez se podria poner un toast que diga que se agrego al carrito y que lo mande a
        // main activity, en vez de que lo lleve a meter los datos de pasajero de una vez.

        // La otra opcion es que cuando le de reservar vaya a guardar los datos a la tabla reservacion,
        // lo mande a poner los datos de pasajero y pago y que guarde esos datos en la tabla pasajero,
        // genere numero de transaccion para la tabla tiquete y guarde lo que ocupe esa tabla tiquete.
        // con esta opcion, se eliminaria el carrito, o se le podria cambiar a que sea una seccion donde
        // pueda ver los vuelos comprados.

    //- promociones:
        // obtener datos de tabla promocion para mostrar en la seccion de promos

        // Cuando elijo una promo y le doy reservar, tengo que ir a guardar a la tabla:
        // un id reservacion que quiero agregar automatico, cancelado en false, obtener id_usuario
        // de la tabla de usuario y un numero de vuelo que obtengo de tabla vuelo
        // esta reservacion de promo hay que agregarla en el carrito
        // tal vez se podria poner un toast que diga que se agrego al carrito
        // aqui tambien se podria aplicar la segunda opcion que escribi arriba.

    //- tabla pasajero:
        // en el carrito tiene que haber opcion de pagar una reservacion o tocarla y que
        // lo lleve a la seccion de poner los datos del pasajero e info de pago y agregar
        // los datos del pasajero a la tabla
        // y tabla tiquete:
        // cuando se hace el pago, se debe generar solo un numero de transaccion y meter a la tabla
        // los datos
}
