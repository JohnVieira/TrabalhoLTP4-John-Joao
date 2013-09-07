/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.SistemaConstrutora.DataAcess;

import br.edu.ifnmg.SistemaConstrutora.DomainModel.Fornecedor;
import br.edu.ifnmg.SistemaConstrutora.DomainModel.FornecedorRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Junior
 */
@Stateless(name = "FornecedorRepositorio")
public class FornecedorDAO
        extends DaoGenerico<Fornecedor>
        implements FornecedorRepositorio {

    public FornecedorDAO() {
        super(Fornecedor.class);
    }

    @Override
    public List<Fornecedor> Buscar(Fornecedor obj) {
        // Corpo da consulta
        String consulta = " select f from Fornecedor f ";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if (obj!=null) {
//            
//            if (obj.getRazaoSocial() != null && obj.getRazaoSocial().length() > 0) {
//                filtro += " f.razaoSocial=:razaoSocial ";
//                parametros.put("razaoSocial", obj.getRazaoSocial());
//            }
//            
//            if (obj.getCnpj() != null && obj.getCnpj().toString().length() > 0) {
//                if (filtro.length() > 0) {
//                    filtro = filtro + " and ";
//                }
//                filtro += " f.cnpj=:cnpj ";
//                parametros.put("cnpj", obj.getCnpj());
//            }   
//            
            //Se houver filtros, coloca o "where" na consulta
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
//    public Fornecedor porCnpj (String cnpj){
//        String consulta = "select f from Fornecedor f where f.cnpj=:cnpj";
//                // Cria a consulta no JPA
//        Query query = manager.createQuery(consulta);
//
//        // Aplica os parâmetros da consulta
//        query.setParameter("cnpj", cnpj);
//
//        // Executa a consulta
//        return (Fornecedor)query.getSingleResult();
//    }
    
    
    
    
}
