/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Funcionario;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FuncionarioRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
@Stateless(name = "FuncionarioRepositorio")
public class FuncionarioDAO
        extends DaoGenerico<Funcionario>
        implements FuncionarioRepositorio {

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> Buscar(Funcionario obj) {
        // Corpo da consulta
        String consulta = "select f from Funcionario f";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " f.nome=:nome ";
                parametros.put("nome", obj.getNome());
            }

            if (obj.getLogin() != null && obj.getLogin().length() > 0) {
                if (filtro.length() > 0) {
                    filtro += " and ";
                }
                filtro += " f.login=:login ";
                parametros.put("login", obj.getLogin());
            }

            if (obj.getId() != null && obj.getId() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " f.id =:id";
                parametros.put("id", obj.getId());
            }

            if (obj.getSenha() != null && obj.getSenha().length() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " f.senha=:senha";
                parametros.put("senha", obj.getSenha());
            }

            if (obj.getTipo() != null && obj.getTipo().toString().length() > 0) {
                if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
                filtro += " f.tipo=:tipo ";
                parametros.put("tipo", obj.getTipo());
            }
            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta = consulta + " where " + filtro;
            }
        }

        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        for (String par : parametros.keySet()) {
            query.setParameter(par, parametros.get(par));
        }

        // Executa a consulta
        return query.getResultList();

    }
    
//    @Override
//    public Funcionario porLogin(String login){
//        String consulta = "select f from Funcionario f where f.login=:login";
//                // Cria a consulta no JPA
//        Query query = manager.createQuery(consulta);
//
//        // Aplica os parâmetros da consulta
//        query.setParameter("login", login);
//
//        // Executa a consulta
//        return (Funcionario)query.getSingleResult();
//
//
//    }

}
