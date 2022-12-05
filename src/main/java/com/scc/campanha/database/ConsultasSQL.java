package com.scc.campanha.database;


/*
*      Aqui estão todas as consultas feitas, isto é, toda interação com o banco, em SQL.
* */
public abstract class ConsultasSQL {

    public static final String INSERIR_VOLUNTARIO = "INSERT INTO VOLUNTARIO " +
            "(V_CPF, V_NOME, V_EMAIL, V_TIPO_VOLUNTARIO, V_CIDADE_ENDERECO, V_RUA_ENDERECO, V_BAIRRO_ENDERECO, V_NUMERO_ENDERECO, V_CEP_ENDERECO) " +
            "VALUES(:CPF, :NOME, :EMAIL, :TIPO, :CIDADE, :RUA, :BAIRRO, :NUMERO, :CEP)";

    public static final String BUSCAR_VOLUNTARIO_POR_CPF = "SELECT V_CPF, V_NOME, V_EMAIL, V_TIPO_VOLUNTARIO  " +
            "FROM VOLUNTARIO WHERE V_CPF = :CPF";

    public static final String BUSCAR_ROUPAS_ENTREGUES_POR_PERIODO = "SELECT PT.PT_TIPO, " +
            "   PT.PT_GENERO, " +
            "   EXTRACT(MONTH FROM E.E_DATA_HORA) AS MES, " +
            "   SUM(PT.PT_QUANTIDADE) as TOTAL_ENVIADO " +
            "FROM PACOTE_TRIADO PT " +
            "JOIN ENTREGA E ON E.E_ID = PT.PT_ID_ENTREGA " +
            "WHERE EXTRACT (MONTH FROM E.E_DATA_HORA) BETWEEN :INICIO AND :FIM " +
            "GROUP BY PT.PT_TIPO , PT.PT_GENERO, EXTRACT(MONTH FROM E.E_DATA_HORA) " +
            "ORDER BY MES, PT.PT_TIPO, TOTAL_ENVIADO DESC";
}
