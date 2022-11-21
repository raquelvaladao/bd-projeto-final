package com.scc.campanha.services;


import com.scc.campanha.controllers.dtos.PublicacaoRequest;
import com.scc.campanha.controllers.dtos.RedeDivulgadorRequest;
import com.scc.campanha.controllers.dtos.RedeSocialRequest;
import com.scc.campanha.database.models.PerfilRedeSocial;
import com.scc.campanha.database.models.Voluntario;
import com.scc.campanha.database.models.pks.PKPerfilRedeSocial;
import com.scc.campanha.database.repositories.PerfilRedeSocialRepositoryImpl;
import com.scc.campanha.database.repositories.PublicacaoRepository;
import com.scc.campanha.database.repositories.RedeSocialDivulgadorRepository;
import com.scc.campanha.database.repositories.VoluntarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class CriarService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

/*
    @Autowired
    private PerfilRedeSocialRepository perfilRedeSocialRepository;
*/

    @Autowired
    PerfilRedeSocialRepositoryImpl perfilRedeSocialRepository;
    @Autowired
    private RedeSocialDivulgadorRepository redeSocialDivulgadorRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    public Object criar(Voluntario voluntario) {
        //todo: remover isso
        return voluntarioRepository.criar(voluntario);
        return "Não foi criado. Implementar procedure";
    }

    public Object criarRede(RedeSocialRequest request) throws SQLException {
//        perfilRedeSocialRepository.inserirPerfil(request.getNomeRedeSocial(), request.getNomeUsuario());
        PerfilRedeSocial entidade = PerfilRedeSocial.builder().pkPerfilRedeSocial(
                PKPerfilRedeSocial.builder()
                        .nomeUsuario(request.getNomeUsuario())
                        .redeSocial(request.getNomeRedeSocial())
                        .build()
        ).build();

        perfilRedeSocialRepository.inserir(entidade);

        return entidade;
    }

    public Object criarRedeDivulgador(RedeDivulgadorRequest request) {
        //todo: verificar cpf string/Divulgacao como mapeadores duplicados do cpf
        redeSocialDivulgadorRepository.inserirRedeSocialDivulgador(request.getNomeRedeSocial(), request.getNomeUsuario(),
                request.getCpfDivulgacao(), request.getHorasVoluntariado());
        return "OK";
    }

    public Object criarPublicacao(PublicacaoRequest request) {
        publicacaoRepository.criarPublicacao(request.getUsuarioRedeSocial(),
                request.getRedeSocial(),
                request.getTituloDoConteudoDivulgado(),
                request.getAnoDoConteudoDivulgado(),
                request.getData().toString(),
                request.getAlcance(),
                request.getTotalCliques(),
                request.getImpressoes()
        );
        return "ok";
    }
}
