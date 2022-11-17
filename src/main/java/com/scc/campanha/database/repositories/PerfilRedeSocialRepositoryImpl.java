package com.scc.campanha.database.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class PerfilRedeSocialRepositoryImpl implements PerfilRedeSocialRepository{

    private PreparedStatement statement;
    private Connection con;
    private String x, y;
    @Override
    public void inserirPerfil(String nomeRede, String nomeUsuario) {

    }
}
