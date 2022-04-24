﻿using Dapper;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TECAir_API.Database.Repository
{
    public class PromocionRepository : IPromocion
    {
        private PostgreSQLConfiguration _connectionString;

        public PromocionRepository(PostgreSQLConfiguration connectionString)
        {
            _connectionString = connectionString;
        }

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }
        
        public async Task<bool> ingresarPromocion(PromocionOutput promocion)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO promocion (no_promocion, porcentaje, periodo, url, p_dia, p_mes, p_ano, no_vuelo)
                        VALUES (@nopromocion,@porcentaje, @periodo, @url, @pDia, @pMes, @pAno, @noVuelo);
                        ";

            var result = await db.ExecuteAsync(sql, new
            {
                promocion.NoPromocion,
                promocion.Porcentaje,
                promocion.Periodo,
                promocion.Url,
                promocion.PDia,
                promocion.PMes,
                promocion.PAno,
                promocion.NoVuelo
            });

            return result > 0;
        }

        public async Task<IEnumerable<PromocionOutput>> GetPromociones()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT no_promocion, porcentaje, periodo, url, p_dia, p_mes, p_ano, no_vuelo
                        FROM public.promociones
                        ";

            return await db.QueryAsync<PromocionOutput>(sql, new { });
        }
    }
}
