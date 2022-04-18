﻿using Microsoft.AspNetCore.Mvc;
using TECAir_API.Models.WEB;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace TECAir_API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VueloWEBController : ControllerBase
    {
        List<VueloWEB> vuelos = new List<VueloWEB>();

        // GET: api/<VueloWEBController>
        [HttpGet("{origen}/{destino}/{fecha}")]
        public List<VueloWEB> Get(string origen, string destino, string fecha)
        {
            vuelos.Add(new VueloWEB("vuelo1", "origen1", "destino1", "salida1", "llegada1", "fecha1", "escalas1", "precio1"));
            vuelos.Add(new VueloWEB("vuelo2", "origen2", "destino2", "salida2", "llegada2", "fecha2", "escalas2", "precio2"));
            vuelos.Add(new VueloWEB("vuelo3", "origen3", "destino3", "salida3", "llegada3", "fecha3", "escalas3", "precio3"));
            vuelos.Add(new VueloWEB("vuelo4", "origen2", "destino2", "salida4", "llegada4", "fecha2", "escalas4", "precio4"));

            List<VueloWEB> resultado = new List<VueloWEB>();
            for (int i = 0; i < vuelos.Count; i++)
            {
                if (vuelos[i].origen == origen && vuelos[i].destino == destino && vuelos[i].fecha == fecha)
                {
                    resultado.Add(vuelos[i]);
                }
            }
            return resultado;
        }
    }
}