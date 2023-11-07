package fachada;


import strategy.*;
import util.Resultado;
import dominio.Cliente;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fachada implements IFachada{
    //Operação - Entidade - Estratégia
    private Map<String, Map<String,List<IStrategy>>> startupRsnMap = new HashMap<String, Map<String,List<IStrategy>>>();
    private Map<String, Map<String,List<IStrategy>>> finalizationRnsMap = new HashMap<String, Map<String,List<IStrategy>>>();
    private Map<String, IDAO> daoMap = new HashMap<>();

    public Fachada() {
        //Mapeando DAOs por nome de classe
        daoMap.put(Cliente.class.getSimpleName(), new ClienteDAO());
        daoMap.put(Endereco.class.getSimpleName(), new EnderecoDAO());

        //===============Mapa regras de negócio pré processamento=========================

    //Startup Cliente
        Map<String,List<IStrategy>> mapaCliente = new HashMap<>();
        //Salvar
        List<IStrategy> listaRegrasSalvarCliente = new ArrayList<>();
        listaRegrasSalvarCliente.add(new ValidarCamposCliente());
        mapaCliente.put("SALVAR", listaRegrasSalvarCliente);
        //Alterar
        mapaCliente.put("ALTERAR",listaRegrasSalvarCliente); // Alterar repete regras do salvamento
        Map<String, List<IStrategy>> mapaCliente1 = null;
		//Adicionando regra de Cliente para mapa de regras de startup
        startupRsnMap.put(Cliente.class.getSimpleName(),mapaCliente1);


    //Startup Endereco
        Map<String,List<IStrategy>> mapaEndereco = new HashMap<String,List<IStrategy>>();
        //Salvar
        List<IStrategy> listaRegrasSalvarEndereco = new ArrayList<>();
        listaRegrasSalvarEndereco.add(new ValidarEndereco());
        mapaEndereco.put("SALVAR",listaRegrasSalvarEndereco);
        //Alterar
        mapaEndereco.put("ALTERAR",listaRegrasSalvarEndereco); // Alterar repete regras do salvamento

        //Adicionando regra de Cliente para mapa de regras de startup
        startupRsnMap.put(Endereco.class.getSimpleName(),mapaEndereco);

        //===============Mapa regras de negócio pró processamento=========================
    //Cliente
        Map<String,List<IStrategy>> mapaClienteFim = new HashMap<String,List<IStrategy>>();
        List<IStrategy> listaRegrasSalvarClienteFim = new ArrayList<>();
        mapaClienteFim.put("SALVAR",listaRegrasSalvarClienteFim);
        mapaClienteFim.put("ALTERAR",listaRegrasSalvarClienteFim);
        mapaClienteFim.put("EXCLUIR",listaRegrasSalvarClienteFim);
        finalizationRnsMap.put(Cliente.class.getSimpleName(),mapaClienteFim);

    //RESPONSAVEL
        Map<String,List<IStrategy>> mapaEnderecoFim = new HashMap<>();
        List<IStrategy> listaRegrasSalvarEnderecoFim = new ArrayList<>();
        mapaEnderecoFim.put("SALVAR",listaRegrasSalvarEnderecoFim);
        mapaEnderecoFim.put("ALTERAR",listaRegrasSalvarEnderecoFim);
        mapaEnderecoFim.put("EXCLUIR",listaRegrasSalvarEnderecoFim);
        finalizationRnsMap.put(Endereco.class.getSimpleName(),mapaEnderecoFim);

    }

    @Override
    public Resultado salvar(EntidadeDominio entidade){
        String operation = "SALVAR";
        StringBuilder strbErros = new StringBuilder();
        Resultado resultado = new Resultado();

        strbErros.append(executarRegras(entidade,operation,startupRsnMap));

        //Caso alguma retorne um erro o processmento n é executado e retona um resultado apenas com as msgs de erro
        if(strbErros.length() > 0) {
        }

        //Executando processamento
        IDAO dao = daoMap.get(entidade.getClass().getSimpleName());
        EntidadeDominio entidadeSalva = null;
        entidadeSalva = dao.salvar(entidade);
		List<EntidadeDominio> listEntidade = new ArrayList<>();
		listEntidade.add(entidadeSalva);
		resultado.setResultado(listEntidade);


        strbErros.append(executarRegras(entidade,operation,finalizationRnsMap));

        resultado.setMsg(strbErros.toString());
        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        String operation = "ALTERAR";
        StringBuilder strbErros = new StringBuilder();
        Resultado resultado = new Resultado();

        strbErros.append(executarRegras(entidade,operation,startupRsnMap));

        //Caso alguma retorne um erro o processmento n é executado e retona um resultado apenas com as msgs de erro
        if(strbErros.length() > 0) {
            return new Resultado(strbErros.toString());
        }
        //Executa transacao
        IDAO dao = daoMap.get(entidade.getClass().getSimpleName());
        EntidadeDominio entidadeAlterada = null;
        try{
            entidadeAlterada = dao.alterar(entidade);
            List<EntidadeDominio> listEntidade = new ArrayList<>();
            listEntidade.add(entidadeAlterada);
            resultado.setResultado(listEntidade);

        }catch (SQLException e){
            e.printStackTrace();
            resultado.setMsg("Não foi possivel realizar o registro no banco");
        }


        strbErros.append(executarRegras(entidade,operation,finalizationRnsMap));

        resultado.setMsg(strbErros.toString());
        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {
        String operation = "EXCLUIR";
        StringBuilder strbErros = new StringBuilder();
        Resultado resultado = new Resultado();

        strbErros.append(executarRegras(entidade,operation,startupRsnMap));

        //Caso alguma retorne um erro o processmento n é executado e retona um resultado apenas com as msgs de erro
        if(strbErros.length() > 0) {
            return new Resultado(strbErros.toString());
        }
        //Executa transacao
        IDAO dao = daoMap.get(entidade.getClass().getSimpleName());
        dao.delete(entidade.getId());
		List<EntidadeDominio> listEntidade = new ArrayList<>();
		listEntidade.add(entidade);
		resultado.setResultado(listEntidade);


        strbErros.append(executarRegras(entidade,operation,finalizationRnsMap));

        resultado.setMsg(strbErros.toString());
        return resultado;
    }

    @Override
    public Resultado visualizar(EntidadeDominio entidade) {
        String operation = "CONSULTAR";
        StringBuilder strbErros = new StringBuilder();
        Resultado resultado = new Resultado();

        strbErros.append(executarRegras(entidade,operation,startupRsnMap));

        //Caso alguma retorne um erro o processmento n é executado e retona um resultado apenas com as msgs de erro
        if(strbErros.length() > 0) {
            return new Resultado(strbErros.toString());
        }
        //Executa transacao
        IDAO dao = daoMap.get(entidade.getClass().getSimpleName());
        List<EntidadeDominio> listEntidade = new ArrayList<>();
        if(entidade.getId() > 0){
		    EntidadeDominio ent = (EntidadeDominio) dao.consultar(entidade);
		    listEntidade.add(ent);

		}else{
		    listEntidade = dao.findAll(entidade, null, null);
		}

		resultado.setResultado(listEntidade);


        strbErros.append(executarRegras(entidade,operation,finalizationRnsMap));

        resultado.setMsg(strbErros.toString());
        return resultado;
    }

    	private String executarRegras(EntidadeDominio entidade,String operation,Map<String, Map<String,List<IStrategy>>> colecaoRegras){
        StringBuilder strbErros = new StringBuilder();
        //Pegando as regras de negocio pre processamento
        
        Map<String,List<IStrategy>> regrasMap = colecaoRegras.get(entidade.getClass().getSimpleName());
        if(regrasMap != null){
            //Rodando todas as regras de negocio pré processamento
            List<IStrategy> regrasList = regrasMap.get(operation);
            if(regrasList != null){
                for(IStrategy rn : regrasList){
                    strbErros.append(rn.processar(entidade));
                }
            }
        }

        return strbErros.toString();
    }

}
