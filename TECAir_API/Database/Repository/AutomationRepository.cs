﻿using Dapper;
using Newtonsoft.Json.Linq;
using TECAir_API.Database.Interface;
using TECAir_API.Models.Automation;

namespace TECAir_API.Database.Repository
{
    public class AutomationRepository : IAutomation
    {
        private PostgreSQLConfiguration _connectionString;

        public AutomationRepository(PostgreSQLConfiguration connectionString)
        {
            _connectionString = connectionString;
        }
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<VuelosTotales> GetTotalVuelos()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.vuelo
                        ";
            var test = await db.QueryAsync<Vuelo>(sql, new { });
            var total = Enumerable.Count(test);
            VuelosTotales vuelosTotales = new VuelosTotales(total);
            return vuelosTotales;

        }
        public async Task<ReservacionesTotales> GetTotalReservaciones()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.reservacion
                        ";
            var test = await db.QueryAsync<Reservacion>(sql, new { });
            var total = Enumerable.Count(test);
            ReservacionesTotales reservacionesTotales = new ReservacionesTotales(total);
            return reservacionesTotales;

        }
        public async Task<PromocionesTotales> GetTotalPromociones()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.promocion
                        ";
            var test = await db.QueryAsync<Promocion>(sql, new { });
            var total = Enumerable.Count(test);
            PromocionesTotales promocionesTotales = new PromocionesTotales(total);
            return promocionesTotales;

        }
        public async Task<MaletasTotales> GetTotalMaletas()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.maleta
                        ";
            var test = await db.QueryAsync<Maletum>(sql, new { });
            var total = Enumerable.Count(test);
            MaletasTotales maletasTotales = new MaletasTotales(total);
            return maletasTotales;

        }
        public async Task<TiquetesTotales> GetTotalTiquetes()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.tiquete
                        ";
            var test = await db.QueryAsync<Tiquete>(sql, new { });
            var total = Enumerable.Count(test);
            TiquetesTotales tiquetesTotales = new TiquetesTotales(total);
            return tiquetesTotales;

        }

    }
}
